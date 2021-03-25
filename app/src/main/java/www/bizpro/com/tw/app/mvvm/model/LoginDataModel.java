package www.bizpro.com.tw.app.mvvm.model;

import android.util.Log;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponse;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponseCallBack;
import www.bizpro.com.tw.app.mvvm.webapi.ApiService;
import www.bizpro.com.tw.app.mvvm.webapi.ApiManager;
import www.bizpro.com.tw.app.mvvm.response.LoginResponse;

public class LoginDataModel {
    ApiService apiService = ApiManager.getInstance().getAPI();

    public void callLoginApi(FormBody body,
                             ApiResponseCallBack apiCallBack) {
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


    public void callRxLoginApi(FormBody body,
                               ApiResponseCallBack apiCallBack) {
        apiService.doRxLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull LoginResponse loginResponse) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


}