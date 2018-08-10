package org.webdriver.seleniumUI.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class JsonReader extends TestBaseCase {

    private ObjectMapper mapper = new ObjectMapper();
    private String jsonPath;
    private final String jsonName;


    public JsonReader(String jsonName) {
        this.jsonName = jsonName;
    }

    public LinkedHashMap stringData() throws IOException {
            if (grid.equals("no")) {
                jsonPath = properties.getProperty("pathToJsons.path");
            } else if (grid.equals("yes") && browser.equals("Chrome")) {
                jsonPath = properties.getProperty("pathToJsonsGrid.path");
            } else if (grid.equals("yes") && browser.equals("Firefox")) {
                jsonPath = properties.getProperty("pathToJsonsGrid.path").replaceAll("Chr", "FF");
            }
        return mapper.readValue(new File( jsonPath + jsonName), LinkedHashMap.class);
    }

}
