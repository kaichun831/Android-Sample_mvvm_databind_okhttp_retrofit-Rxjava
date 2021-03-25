package www.bizpro.com.tw.app.mvvm.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import okhttp3.FormBody;
import retrofit2.Response;
import www.bizpro.com.tw.app.mvvm.model.LoginDataModel;
import www.bizpro.com.tw.app.mvvm.response.LoginResponse;
import www.bizpro.com.tw.app.mvvm.tools.SingleLiveEvent;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponse;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponseCallBack;
import www.bizpro.com.tw.app.mvvm.webapi.ApiBody;
import www.bizpro.com.tw.app.mvvm.webapi.RxApiResponseCallBack;

public class LoginViewModel extends ViewModel {
    final  String tag ="LoginViewModel";
    private LoginDataModel  model =  new LoginDataModel();
    public int code;
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public MutableLiveData userName = new MutableLiveData("KG"); //預設
    public MutableLiveData userAge = new MutableLiveData(40);
    public MutableLiveData userSex = new MutableLiveData(0);
    public SingleLiveEvent errorMessage =new SingleLiveEvent();
    //登入動作對應畫面變化
    public  void loginAction(String account,String password){
        isLoading.set(true);
        FormBody body = new ApiBody().sendLoginApi(account,password);//取得要送出的資料
        model.callLoginApi(body, new ApiResponseCallBack<LoginResponse>() {
            @Override
            public void getLoginCallBackResponse(ApiResponse<LoginResponse> response) {
                isLoading.set(true);
                code=response.code;
                if(response.isSuccessful()){
                    userName.setValue(response.body.getName());
                    userAge.setValue(response.body.getAge());
                    userSex.setValue(response.body.getSex());
                }else{
                    errorMessage.setValue(response.errorMessage);
                }
                isLoading.set(false);

            }

            @Override
            public void getLoginErrorResponse(Throwable t) {

            }
        });
    }
    public  void loginRxAction(String account,String password){
        FormBody body = new ApiBody().sendLoginApi(account,password);
        isLoading.set(true);
        model.callRxLoginApi(body, new RxApiResponseCallBack() {
            @Override
            public void getRxLoginCallBackResponse(LoginResponse response) {
                if(response.getCode()==200){
                    userName.postValue(response.getName());
                    userAge.postValue(response.getAge());
                    userName.postValue(response.getName());
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
