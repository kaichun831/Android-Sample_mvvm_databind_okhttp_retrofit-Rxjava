package www.bizpro.com.tw.app.mvvm.net;

import okhttp3.FormBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchService {
    @GET("search/repositories")
    SearchRepo searchRepo(@Query(value = "q",encoded = true) String repo);
}