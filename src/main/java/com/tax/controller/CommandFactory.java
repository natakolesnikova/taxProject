package com.tax.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory factory;
    private Map<String, Command> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
        commandMap.put("incorrect", new IncorrectRequest());
        commandMap.put("login", new LoginServlet());
        commandMap.put("inspector", new InspectorCommand());
        commandMap.put("taxpayer", new TaxPayerCommand());
    }

    public static CommandFactory getFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    public Command getCommand(HttpServletRequest request) {
        String command = request.getParameter("command");
        Command commandFromMap = commandMap.get(command);
        if (commandFromMap == null) {
            commandFromMap = commandMap.get("incorrect");
        }
        return commandFromMap;
    }
}
