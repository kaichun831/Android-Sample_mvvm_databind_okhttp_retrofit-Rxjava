package www.bizpro.com.tw.app.mvvm.view.activity;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

interface GetRequest_Interface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation> getCall();
}
