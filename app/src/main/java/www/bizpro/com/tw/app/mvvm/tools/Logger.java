/**
 * 依據各種事件LOG方便除錯
 */
package www.bizpro.com.tw.app.mvvm.tools;

import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okio.Buffer;

public class Logger {
    private enum LogType {
        DEVELOP("DEVELOP"), //開發者使用
        ERROR("ERROR");  //開發者使用
        String type;

        LogType(String s) {
            type = s;
        }

        private String getValue() {
            return type;
        }
    }

    public static void errorLog(String msg) {
        Log.e(LogType.ERROR.getValue(), msg);
    }

    public static void developLog(String msg) {
        Log.i(LogType.DEVELOP.getValue(), msg);
    }

    public static void formBodyToJsonLog(String title, FormBody body) {
        JsonObject object = new JsonObject();
        for (int i = 0; i < body.size(); i++) {
            object.addProperty(body.encodedName(i), body.encodedValue(i));
        }
        Log.d(LogType.DEVELOP.getValue(), title + ":\n" + object.toString());
    }

    public static void requestBodyToJsonLog(String title, RequestBody body) {
        Buffer buffer = new Buffer();
        try {
            body.writeTo(buffer);
            Log.d(LogType.DEVELOP.getValue(), title + ":\n" + buffer.readUtf8());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
