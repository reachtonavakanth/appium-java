//https://github.com/stleary/JSON-java
// <!-- https://mvnrepository.com/artifact/org.json/json -->

package com.qa.tests;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.*;
import java.io.File;

public class ReadData {
    public static String path;

    @Test
    public void readJsonFileData() throws FileNotFoundException {
        path = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "testdata" +
                File.separator + "Sample.json";

        System.out.println(path);
        readfromFile();

    }

    private static void readfromFile() throws FileNotFoundException {
        //FileReader file = new FileReader(path);

        InputStream inputstream = new FileInputStream(path);


        JSONTokener tokener = new JSONTokener(inputstream);
        JSONObject jObj = new JSONObject(tokener);
        System.out.println(jObj.getString("key").toString());

        System.out.println(jObj.getJSONObject("TC_01"));

        System.out.println(jObj.getJSONObject("TC_01").getString("username"));
        System.out.println(jObj.getJSONObject("TC_01").getString("password"));
        System.out.println(jObj.getJSONObject("TC_02").getString("username"));
        System.out.println(jObj.getJSONObject("TC_02").getString("password"));
        System.out.println(jObj.getJSONObject("TC_03").getString("username"));
        System.out.println(jObj.getJSONObject("TC_03").getString("password"));
        System.out.println(jObj.getJSONArray("employees").getJSONObject(0).getString("firstName"));
        System.out.println(jObj.getJSONArray("employees").getJSONObject(0).getString("lastName"));

        System.out.println(jObj.getJSONArray("employees").getJSONObject(1).getString("firstName"));
        System.out.println(jObj.getJSONArray("employees").getJSONObject(1).getString("lastName"));

        System.out.println(jObj.getJSONArray("employees").getJSONObject(2).getString("firstName"));
        System.out.println(jObj.getJSONArray("employees").getJSONObject(2).getString("lastName"));

        System.out.println(jObj.getJSONArray("manager").getJSONObject(0).getString("firstName"));
        System.out.println(jObj.getJSONArray("manager").getJSONObject(0).getString("lastName"));


        System.out.println(jObj.getJSONArray("manager").getJSONObject(1).getString("firstName"));
        System.out.println(jObj.getJSONArray("manager").getJSONObject(1).getString("lastName"));

        System.out.println(jObj.getJSONArray("manager").getJSONObject(2).getString("firstName"));
        System.out.println(jObj.getJSONArray("manager").getJSONObject(2).getString("lastName"));

        System.out.println(jObj.getJSONArray("manager").getJSONObject(2));
        System.out.println(jObj.getJSONArray("manager"));

        System.out.println(jObj.getJSONObject("temp").getJSONObject("arr1").getString("alpha1"));
        System.out.println(jObj.getJSONObject("temp").getJSONObject("arr1").getJSONArray("keys"));
        System.out.println(jObj.getJSONObject("temp").getJSONObject("arr1").getJSONArray("keys").getJSONObject(0).getString("test1"));

    }
}
