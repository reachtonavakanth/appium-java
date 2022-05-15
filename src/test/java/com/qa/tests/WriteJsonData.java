package com.qa.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WriteJsonData {

    public static String path;

    @Test
    public void write() throws IOException {
        //  keyValue_JSONStringer(); // This works for Plain JSON Generation
        keyValue_JSONObject(); // This works for complex Json Generation
    }

    public static void keyValue_JSONObject() throws IOException {
        path = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "testdata" +
                File.separator + "keyValue_JSONObject.json";
        FileWriter file = new FileWriter(path);
        Map<Object, Object> jsonObject = new HashMap<Object, Object>();
        jsonObject.put("Key1", "value1");
        jsonObject.put("Key2", "value2");
        jsonObject.put("Key3", "value3");

        Map<String, String> innerMap1 = new HashMap<String, String>();
        innerMap1.put("innerkey1", "innervalue1");
        innerMap1.put("innerkey2", "innervalue2");
        innerMap1.put("innerkey3", "innervalue3");
        innerMap1.put("innerkey4", "innervalue4");

        Map<String, String> innerMap2 = new HashMap<String, String>();
        innerMap2.put("innerkey1", "innervalue1");
        innerMap2.put("innerkey2", "innervalue2");
        innerMap2.put("innerkey3", "innervalue3");
        innerMap2.put("innerkey4", "innervalue4");

        Map<String, String> innerMap3 = new HashMap<String, String>();
        innerMap3.put("innerkey1", "innervalue1");
        innerMap3.put("innerkey2", "innervalue2");
        innerMap3.put("innerkey3", "innervalue3");
        innerMap3.put("innerkey4", "innervalue4");
        innerMap3.put("innerkey5", "innervalue5");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(innerMap1);
        jsonArray.put(innerMap2);
        jsonArray.put(innerMap3);

        JSONObject jsonOutput = new JSONObject();

        jsonOutput.put("JSON", "Hello, World!");
        jsonOutput.put("obj", jsonObject);
        jsonOutput.put("cl", jsonArray);

        jsonOutput.write(file);
        file.flush();
        file.close();
    }

    public static void keyValue_JSONStringer() throws IOException {
        path = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "testdata" +
                File.separator + "keyValue_Stringer.json";
        FileWriter fout = new FileWriter(path);
        String str = new JSONStringer()
                .object()
                .key("key1").value("value1")
                .key("key2").value("value2")
                .endObject()
                .toString();
        fout.write(str);
        fout.close();
    }

}
