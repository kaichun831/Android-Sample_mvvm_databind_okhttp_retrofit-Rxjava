package www.bizpro.com.tw.app.mvvm.webapi;

import okhttp3.FormBody;
//傳參數整合
public class ApiBody {

    public   FormBody sendLoginApi(String account,String password){
        FormBody body = new FormBody.Builder()
                .add("accessToken","token")
                .add("account",account)
                .add("password",password)
                .add("name","我是送出去了")
                .add("age","50")
                .add("languageAbbr", "zh_TW").build();
        return body;
    }
}
