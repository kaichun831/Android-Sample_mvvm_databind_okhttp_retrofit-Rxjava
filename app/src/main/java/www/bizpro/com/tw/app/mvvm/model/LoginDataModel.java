package www.bizpro.com.tw.app.mvvm.model;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.FormBody;
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

public class LoginDataModel {
    private ApiService apiService;
    private ApiService rxApiService;

    public LoginDataModel() {
        this.apiService = ApiManager.getInstance().getAPI();
        this.rxApiService = RxApiManager.getInstance().getAPI();
    }

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

    public void callRxLoginApi(FormBody body, RxApiResponseCallBack apiCallBack) {
        rxApiService.doRxLogin(body)
                .timeout(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(new SingleObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("KAI", "Disposable" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onSuccess(@NonNull LoginResponse response) {
                        apiCallBack.getRxLoginCallBackResponse(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("KAI", "e" + Thread.currentThread().getName());
                        apiCallBack.getLoginErrorResponse(e);
                    }
                });
    }
}