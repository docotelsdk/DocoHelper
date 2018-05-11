package docotel.com.sdkhelper.baseutils;

import com.google.gson.Gson;

import java.io.Serializable;

public class JsonHelper {
    public static String toJson(Serializable object) {
        return new Gson().toJson(object);
    }
}
