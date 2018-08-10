package org.webdriver.seleniumUI.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class TestAutomationProperties extends Properties {
    private static final String PROPERTIES_FILE = "src/main/resources/automation.properties";

    public TestAutomationProperties() {
        super();
        try(Reader reader = new FileReader(PROPERTIES_FILE)) {
            load(reader);
            putAll(System.getProperties());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
