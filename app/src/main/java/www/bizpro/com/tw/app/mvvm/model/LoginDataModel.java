package www.bizpro.com.tw.app.mvvm.model;


import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.bizpro.com.tw.app.mvvm.response.LoginResponse;
import www.bizpro.com.tw.app.mvvm.webapi.ApiManager;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponse;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponseCallBack;
import www.bizpro.com.tw.app.mvvm.webapi.ApiService;
import www.bizpro.com.tw.app.mvvm.webapi.RxApiManager;
import www.bizpro.com.tw.app.mvvm.webapi.RxApiResponseCallBack;

public class LoginDataModel extends BaseDataModel {
    ApiService apiService = ApiManager.getInstance().getAPI();

    ApiService RxApiService = RxApiManager.getInstance().getAPI();

    public void callLoginApi(FormBody body, ApiResponseCallBack apiCallBack) {
        apiService.doLogin(body).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                apiCallBack.getLoginCallBackResponse(new ApiResponse(response));
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }


    public void callRxLoginApi(RequestBody body, RxApiResponseCallBack apiCallBack) {
        RxApiService.doRxLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(response -> apiCallBack.getRxLoginCallBackResponse(response), throwable -> apiCallBack.getLoginErrorResponse(throwable));
    }


}