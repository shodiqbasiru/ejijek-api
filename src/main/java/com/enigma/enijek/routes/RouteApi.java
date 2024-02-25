package com.enigma.enijek.routes;

public class RouteApi {

    // route customer
    public static final String POST_CUSTOMER = "/api/customers/";
    public static final String GET_CUSTOMER = "/api/customers/{customerId}";
    public static final String GET_CUSTOMERS = "/api/customers";
    public static final String PUT_CUSTOMER = "/api/customers/{customerId}";
    public static final String DELETE_CUSTOMER = "/api/customers/{customerId}";

    // route driver
    public static final String POST_DRIVER = "/api/drivers/";
    public static final String GET_DRIVER = "/api/drivers/{driverId}";
    public static final String GET_DRIVERS = "/api/drivers";
    public static final String PUT_DRIVER = "/api/drivers/{driverId}";
    public static final String DELETE_DRIVER = "/api/drivers/{driverId}";

    // route brand
    public static final String POST_BRAND = "/api/brands/";
    public static final String GET_BRAND = "/api/brands/{brandId}";
    public static final String GET_BRANDS = "/api/brands";
    public static final String PUT_BRAND = "/api/brands/{brandId}";
    public static final String DELETE_BRAND = "/api/brands/{brandId}";

    // route transaction
    public static final String POST_TRANSACTION = "/api/transaction/";
    public static final String GET_TRANSACTION ="/api/transactions";
}
