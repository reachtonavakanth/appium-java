package com.qa.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.Properties;

public class TestUtil {

    protected Properties prop;
    public long timeOut = 10;

    /*
     * @author: Navakanth Tunga
     * @description: To read data from '.properties' file based on Key
     * @param: filepath - File Path for .properties file, key - Key for searching in .properties file
     * @return: returns string Value if key matches
     * */
    protected String getKeyValue(String filepath, String key) throws IOException {
        prop = new Properties();
        String value = null;
        InputStream input = null;
        try {
            input = new FileInputStream(filepath);
            prop.load(input);
            value = prop.getProperty(key);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }

        return value;
    }

    /*
     * @author: Navakanth Tunga
     * @description: To write data in to  '.properties' file based on Key
     * @param: filepath - File Path for '.properties' file, key - Key for searching in '.properties' file,
     * Value - value to be updated in '.properties' file
     * */
    protected void setCongigValue(String filepath, String key, String value) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File(filepath));
            prop.load(fis);
            fis.close();
            fos = new FileOutputStream(new File(filepath));
            prop.setProperty(key, value);
            prop.store(fos, "Updated " + key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

    }

    protected static String getMainResourcesPath(String fileName) {

        String getMainResourcesPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "main" + File.separator + "resources" +
                File.separator + fileName;
        return getMainResourcesPath;
    }

    protected static String getTestDataPath(String fileName) {

        String getTestResourcesPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" +File.separator + "testdata" +
                File.separator + fileName;
        return getTestResourcesPath;
    }

    protected static String getAppPath(String fileName) {
        String appPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "app" +
                File.separator + fileName;
        return appPath;
    }

    protected String getJsonString(String path, String key) throws IOException {
        InputStream inputstream = null;
        JSONObject jsonObj = null;
        try {
            inputstream = new FileInputStream(path);
            JSONTokener jsonTokener = new JSONTokener(inputstream);
            jsonObj = new JSONObject(jsonTokener);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (inputstream != null) {
                inputstream.close();
            }
        }
        return jsonObj.getString(key);
    }

    protected JSONObject getJsonObject(String path, String key) throws IOException {
        InputStream inputstream = null;
        JSONObject jsonObj = null;
        try {
            inputstream = new FileInputStream(path);
            JSONTokener jsonTokener = new JSONTokener(inputstream);
            jsonObj = new JSONObject(jsonTokener);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (inputstream != null) {
                inputstream.close();
            }
        }
        return jsonObj.getJSONObject(key);
    }

    protected JSONArray getJsonArray(String path, String key) throws IOException {
        InputStream inputstream = null;
        JSONObject jsonObj = null;
        try {
            inputstream = new FileInputStream(path);
            JSONTokener jsonTokener = new JSONTokener(inputstream);
            jsonObj = new JSONObject(jsonTokener);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (inputstream != null) {
                inputstream.close();
            }
        }
        return jsonObj.getJSONArray(key);
    }
}

