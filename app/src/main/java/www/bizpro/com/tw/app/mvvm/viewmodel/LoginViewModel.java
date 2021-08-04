package www.bizpro.com.tw.app.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import www.bizpro.com.tw.app.mvvm.model.IBaseModel;
import www.bizpro.com.tw.app.mvvm.model.LoginDataModel;
import www.bizpro.com.tw.app.mvvm.response.LoginResponse;
import www.bizpro.com.tw.app.mvvm.tools.Logger;
import www.bizpro.com.tw.app.mvvm.tools.SingleLiveEvent;
import www.bizpro.com.tw.app.mvvm.webapi.ApiBody;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponse;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponseCallBack;
import www.bizpro.com.tw.app.mvvm.webapi.RxApiResponseCallBack;

public class LoginViewModel extends BaseViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private LoginDataModel model;
    public int code;
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public MutableLiveData userName = new MutableLiveData("KG"); //預設
    public MutableLiveData userAge = new MutableLiveData(40);
    public MutableLiveData userSex = new MutableLiveData(0);
    public SingleLiveEvent errorMessage = new SingleLiveEvent();

    public LoginViewModel(@NonNull Application application, @NonNull IBaseModel baseModel) {
        super(application, baseModel);
        if (baseModel instanceof LoginDataModel) {
            this.model = (LoginDataModel) baseModel;
        } else {
            Logger.errorLog(TAG + " Not Match ViewFactory");
        }
    }

    //登入動作對應畫面變化
    public void loginAction(String account, String password) {
        isLoading.set(true);
        FormBody body = new ApiBody().sendLoginApi(account, password);//取得要送出的資料
        model.callLoginApi(body, new ApiResponseCallBack<LoginResponse>() {
            @Override
            public void getLoginCallBackResponse(ApiResponse<LoginResponse> response) {
                isLoading.set(true);
                code = response.code;
                if (response.isSuccessful()) {
                    userName.postValue(response.body.getName());
                    userAge.postValue(response.body.getAge());
                    userSex.postValue(response.body.getSex());
                } else {
                    errorMessage.postValue(response.errorMessage);
                }
                isLoading.set(false);
            }
            @Override
            public void getLoginErrorResponse(Throwable t) {
            }
        });
    }

    public void loginRxAction(String account, String password) {
        RequestBody body = new ApiBody().sendJsonLoginApi(account, password);
        isLoading.set(true);
        model.callRxLoginApi(body, new RxApiResponseCallBack<LoginResponse>() {
            @Override
            public void getRxLoginCallBackResponse(Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    userName.postValue(response.body().getName());
                    userAge.postValue(response.body().getAge());
                    userName.postValue(response.body().getName());
                }
                isLoading.set(false);
            }
            @Override
            public void getLoginErrorResponse(Throwable t) {
                errorMessage.postValue(t.getMessage());
                isLoading.set(false);
            }
        });
    }


}
