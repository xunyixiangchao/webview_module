package com.lis.base.autoservice;

import java.util.ServiceLoader;

public class ServiceLoaderUtil {
    private ServiceLoaderUtil() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }

    }
}
