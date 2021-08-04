/*
   Api呼叫套件
 */
package www.bizpro.com.tw.app.mvvm.webapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private ApiService apiService;
    private static ApiManager mInstance;

    public ApiManager() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        String baseUrl = "https://jsonplaceholder.typicode.com/";

        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getAPI() {
        return apiService;
    }

    public static ApiManager getInstance() {
        if (mInstance == null) {
            mInstance = new ApiManager();
            return mInstance;
        }
        return mInstance;
    }
}
