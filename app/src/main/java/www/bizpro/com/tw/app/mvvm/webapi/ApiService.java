package www.bizpro.com.tw.app.mvvm.webapi;

import io.reactivex.Single;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import www.bizpro.com.tw.app.mvvm.response.LoginResponse;

//路徑整合
public interface ApiService {

    //OkHttp+Retrofit
    @POST("albums/")
    Call<LoginResponse> doLogin(@Body FormBody body);
    //OkHttp+Retrofit
    @POST("albums/")
    Single<LoginResponse> doRxLogin(@Body FormBody body);
}
