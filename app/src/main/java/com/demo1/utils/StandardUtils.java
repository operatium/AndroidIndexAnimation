package com.demo1.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by java on 2017/6/5.
 */

public class StandardUtils {

    public static HashMap<String, String> JsonToHashMap(String object)
    {
        HashMap<String, String> data = new HashMap<String, String>();
        if (object == null || object.isEmpty())
            return data;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(object);
        } catch (JSONException e) {
            Log.e("201706051502",e.toString());
            return data;
        }
        Iterator it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            String value = null;
            try {
                value = (String) jsonObject.get(key);
            } catch (JSONException e) {
                Log.e("201706051501",e.toString());
            }
            data.put(key, value);
        }
        return data;
    }
}
