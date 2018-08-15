package com.tax.service;

public interface ServiceFactory {
    AuthService getAuthService();
    InspectorService getInspectorService();
    TaxpayerService getTaxPayerService();
}
