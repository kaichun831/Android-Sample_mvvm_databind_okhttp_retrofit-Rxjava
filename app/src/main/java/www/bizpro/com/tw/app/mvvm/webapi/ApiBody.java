/*
  傳參數整合
 */
package www.bizpro.com.tw.app.mvvm.webapi;

import com.google.gson.JsonObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import www.bizpro.com.tw.app.mvvm.tools.Logger;


public class ApiBody {
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    //FormBody
    public FormBody sendLoginApi(String account, String password) {
        FormBody body = new FormBody.Builder()
                .add("accessToken", "token")
                .add("code", "400")
                .add("account", account)
                .add("password", password)
                .add("name", "我是送出去了")
                .add("age", "50")
                .add("languageAbbr", "zh_TW").build();
        return body;
    }

    //Json
    public RequestBody sendJsonLoginApi(String account, String password) {
        JsonObject object = new JsonObject();
        object.addProperty("accessToken", "token");
        object.addProperty("code", "400");
        object.addProperty("account", account);
        object.addProperty("password", password);
        object.addProperty("name", "我是送出去了");
        object.addProperty("age", "50");
        object.addProperty("languageAbbr", "zh_TW");
        RequestBody body = RequestBody.create(object.toString(), JSON);
        Logger.requestBodyToJsonLog("Login", body);
        return body;
    }

}
