package www.bizpro.com.tw.app.mvvm.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {


    SearchService provideService(){
        return  new  Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchService.class);
    }
}
