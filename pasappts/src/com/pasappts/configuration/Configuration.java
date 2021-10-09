package com.pasappts.configuration;

import com.pasappts.PasaPPTsException;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Properties properties = null;

    public static String getPropertyValue(String key) throws PasaPPTsException {
        try {
            if (properties == null) {
                String configPropertyFile = System.getProperty("pasappts.config.propertiesfile");
                if (configPropertyFile != null) {
                    File file = new File(configPropertyFile);
                    if (file.exists()) {
                        properties = new Properties();
                        properties.load(new FileInputStream(file));
                    }
                }

                if (properties == null) {
                    properties.load(Configuration.class.getResourceAsStream("/config.properties"));
                }
            }

            return properties.getProperty(key);
        } catch (IOException e) {
            throw new PasaPPTsException(e.getMessage(), e.getCause());
        }
    }

    public static void setDefaultConfiguration() throws PasaPPTsException {
        try {
            UIManager.setLookAndFeel(Configuration.getPropertyValue("UIMANAGER_LOOK_AND_FILL"));
        } catch (Exception e) {
            throw new PasaPPTsException(e.getMessage(), e.getCause());
        }
    }
}
