package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private final String baseUrl;
    private final String browser;
    private final boolean headless;
    private final int timeout;

    public Configuration() {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/local.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException(
                        "Конфигурационный файл не найден: config/local.properties");
            }
            Properties properties = new Properties();
            properties.load(inputStream);

            this.baseUrl = getRequiredProperty(properties, "base.url");
            this.browser = getRequiredProperty(properties, "browser");
            this.headless = getRequiredBooleanProperty(properties, "headless");
            this.timeout = getRequiredIntProperty(properties, "timeout");

        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурационного файла: config/local.properties", e);
        }
    }

    private String getRequiredProperty(Properties properties, String key) {
        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Обязательное свойство конфигурации не задано: " + key);
        }
        return value.trim();
    }

    private boolean getRequiredBooleanProperty(Properties properties, String key) {
        String value = getRequiredProperty(properties, key);

        if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
            throw new IllegalStateException(
                    "Свойство конфигурации: " + key + " должно иметь значение true или false, сейчас получено: " + value);
        }
        return Boolean.parseBoolean(value);
    }

    private int getRequiredIntProperty(Properties properties, String key) {
        String value = getRequiredProperty(properties, key);

        try {
            int result = Integer.parseInt(value);
            if (result <= 0) {
                throw new IllegalStateException(
                        "Свойство конфигурации " + key +
                                " должно быть больше 0, сейчас получено: " + value);
            }
            return result;

        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Свойство конфигурации " + key +
                            " должно быть целым числом, сейчас получено: " + value, e);
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBrowser() {
        return browser;
    }

    public boolean isHeadless() {
        return headless;
    }

    public int getTimeout() {
        return timeout;
    }
}

