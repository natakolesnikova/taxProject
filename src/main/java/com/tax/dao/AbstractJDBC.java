package com.tax.dao;

import com.tax.dao.connection.WrapperConnector;
import com.tax.exception.PersistException;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */

public abstract class AbstractJDBC<T, PK extends Serializable> implements GenericDAO {
    WrapperConnector wrapperConnector = WrapperConnector.getInstance();

    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet rs);

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
     public abstract String getCreateQuery();

    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */

    public abstract String getDeleteQuery();


    @Override
    public T getByPK(int key) throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = wrapperConnector.getStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);

            wrapperConnector.closePrepareStatement(statement);
        } catch (SQLException e) {
            throw new PersistException();
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new PersistException("received more than one record");
        }
        return list.iterator().next();
    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = wrapperConnector.getStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
            wrapperConnector.closePrepareStatement(statement);
        } catch (SQLException e) {
            throw new PersistException();
        }
        return list;
    }

    @Override
    public void update(Object object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = wrapperConnector.getStatement(sql)) {
            prepareStatementForUpdate(statement, (T) object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException();
            }
            wrapperConnector.closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = wrapperConnector.getStatement(sql)) {
            prepareStatementForDelete(statement,(T) object);
            int cout = statement.executeUpdate();
            if (cout != 1) {
                throw new PersistException();
            }
            wrapperConnector.closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T persist(Object object) throws PersistException {
        T persistInstance;
        // Добавляем запись
        String sql = getCreateQuery();
        try (PreparedStatement statement = wrapperConnector.getStatement(sql)) {
            prepareStatementForInsert(statement, (T) object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        // Получаем только что вставленную запись
        sql = getSelectQuery() + " WHERE id = last_insert_id()";
        try (PreparedStatement statement = wrapperConnector.getStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return persistInstance;
    }

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);

    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object);

    protected abstract void prepareStatementForUpdate(PreparedStatement preparedStatement, T object);
}
