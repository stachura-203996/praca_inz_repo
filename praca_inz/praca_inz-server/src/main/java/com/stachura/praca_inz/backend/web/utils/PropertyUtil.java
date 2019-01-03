package com.stachura.praca_inz.backend.web.utils;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Dostarcza metod do wczytwania konfiguracji z plików reasource
 */
public class PropertyUtil {
    public static final String APP_BUNDLE = "application";

    /**
     * Wczytuje property z pliku
     *
     * @param bundle nazwa pliku konfiguracyjnego
     * @param propertyName klucz property
     * @return wartość property
     */
    public static String getProperty(String bundle, String propertyName) {
        return ResourceBundle.getBundle(bundle).getString(propertyName);
    }

    /**
     * Wczytuje kolekcje property z pliku z określonym przyrostkiem
     *
     * @param bundle nazwa pliku konfiguracyjnego
     * @param suffix przyrostek klucza
     * @return kolekcje property
     */
    public static List<String> getPropertiesWithSuffix(String bundle, String suffix) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle);
        return resourceBundle.keySet()
                .stream()
                .filter(b -> b.endsWith(suffix))
                .map(resourceBundle::getString)
                .collect(Collectors.toList());
    }
}