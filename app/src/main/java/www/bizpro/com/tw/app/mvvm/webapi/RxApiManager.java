package www.bizpro.com.tw.app.mvvm.webapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxApiManager {

    private ApiService apiService;
    private static RxApiManager mInstance;

    public RxApiManager() {
        final String baseUrl = "https://jsonplaceholder.typicode.com/";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
                .client(client)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getAPI() {
        return apiService;
    }

    public static RxApiManager getInstance() {
        if (mInstance == null) {
            mInstance = new RxApiManager();
            return mInstance;
        }
        return mInstance;
    }
}
