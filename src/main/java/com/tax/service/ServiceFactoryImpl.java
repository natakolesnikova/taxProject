package com.tax.service;

public class ServiceFactoryImpl implements ServiceFactory {

    private static ServiceFactoryImpl factory;
    private AuthService authService;
    private InspectorService inspectorService;
    private TaxpayerService taxpayerService;

    private ServiceFactoryImpl() {
        authService = new AuthService();
        inspectorService = new InspectorService();
        taxpayerService = new TaxpayerService();
    }

    public static ServiceFactoryImpl getFactory() {
        if (factory == null) {
            synchronized (ServiceFactoryImpl.class) {
                if (factory == null) {
                    factory = new ServiceFactoryImpl();
                }
            }
        }
        return factory;
    }

    @Override
    public AuthService getAuthService() {
        return authService;
    }

    @Override
    public InspectorService getInspectorService() {
        return inspectorService;
    }

    @Override
    public TaxpayerService getTaxPayerService() {
        return taxpayerService;
    }
}
