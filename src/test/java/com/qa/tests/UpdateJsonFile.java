package com.qa.tests;

import com.google.gson.JsonElement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.*;

public class UpdateJsonFile {
    public static String path;

    @Test
    public void updateJsonBasedOnKey() throws IOException, InterruptedException {
        updateJson();
    }

    public static void updateJson() throws IOException, InterruptedException {
        path = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "testdata" +
                File.separator + "keyValue_JSONObject.json";

        InputStream inputstream = new FileInputStream(path);
        JSONTokener tokener = new JSONTokener(inputstream);
        JSONObject jObj = new JSONObject(tokener);

        JSONObject val = jObj.getJSONObject("obj");
        System.out.println(val);

        val.put("Key2", "VALUE100");

        System.out.println(val);
        inputstream.close();
        Thread.sleep(2000);

        FileWriter file = new FileWriter(path);
        val.write(file);
        file.flush();
        file.close();

    }
}
