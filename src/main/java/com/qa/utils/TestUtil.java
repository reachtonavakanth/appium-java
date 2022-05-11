package com.qa.utils;

import java.io.*;
import java.util.Properties;

public class TestUtil {

    protected Properties prop;

    /*
     * @author: Navakanth Tunga
     * @description: To read data from '.properties' file based on Key
     * @param: filepath - File Path for .properties file, key - Key for searching in .properties file
     * @return: returns string Value if key matches
     * */
    protected String getKeyValue(String filepath, String key) {
        prop = new Properties();
        String value = null;
        try {
            InputStream input = new FileInputStream(filepath);
            prop.load(input);
            value = prop.getProperty(key);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /*
     * @author: Navakanth Tunga
     * @description: To write data in to  '.properties' file based on Key
     * @param: filepath - File Path for '.properties' file, key - Key for searching in '.properties' file,
     * Value - value to be updated in '.properties' file
     * */
    protected void setCongigValue(String filepath, String key, String value) {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(new File(filepath));
            prop.load(fis);
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File(filepath));
            prop.setProperty(key, value);
            prop.store(fos, "Updated " + key);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static String getMainResources(String fileName) {

        String getMainResourcesPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "main" + File.separator + "resources" +
                File.separator + fileName;
        return getMainResourcesPath;
    }

    protected static String getTestResources(String fileName) {

        String getTestResourcesPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "main" + File.separator + "resources" +
                File.separator + fileName;
        return getTestResourcesPath;
    }
    protected static String getAppLocation(String fileName) {
        String appPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "app" +
                File.separator + fileName;
        return appPath;
    }
}

