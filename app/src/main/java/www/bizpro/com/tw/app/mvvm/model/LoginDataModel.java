package www.bizpro.com.tw.app.mvvm.model;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponse;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponseCallBack;
import www.bizpro.com.tw.app.mvvm.webapi.ApiService;
import www.bizpro.com.tw.app.mvvm.webapi.ApiManager;
import www.bizpro.com.tw.app.mvvm.response.LoginResponse;
import www.bizpro.com.tw.app.mvvm.webapi.RxApiManager;
import www.bizpro.com.tw.app.mvvm.webapi.RxApiResponseCallBack;

public class LoginDataModel {
    ApiService apiService = ApiManager.getInstance().getAPI();

    ApiService RxapiService = RxApiManager.getInstance().getAPI();

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


    public void callRxLoginApi(RxApiResponseCallBack apiCallBack) {
        RxapiService.doRxLogin()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginResponse loginResponse) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        .subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribe(new SingleObserver<LoginResponse>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(@NonNull LoginResponse loginResponse) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//        })
    }


}