package www.bizpro.com.tw.app.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import okhttp3.FormBody;
import www.bizpro.com.tw.app.mvvm.model.LoginDataModel;
import www.bizpro.com.tw.app.mvvm.response.LoginResponse;
import www.bizpro.com.tw.app.mvvm.tools.SingleLiveEvent;
import www.bizpro.com.tw.app.mvvm.webapi.ApiBody;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponse;
import www.bizpro.com.tw.app.mvvm.webapi.ApiResponseCallBack;
import www.bizpro.com.tw.app.mvvm.webapi.RxApiResponseCallBack;

public class LoginViewModel extends ViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private LoginDataModel model = new LoginDataModel();
    public int code;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    public MutableLiveData<String> userName = new MutableLiveData<>("KG"); //預設
    public MutableLiveData<Integer> userAge = new MutableLiveData<>(40);
    public MutableLiveData userSex = new MutableLiveData(0);
    public SingleLiveEvent<String> errorMessage = new SingleLiveEvent();

    /**
     * 登入動作對應畫面變化
     *
     * @param account
     * @param password
     */
    public void loginAction(String account, String password) {
        isLoading.postValue(true);
        FormBody body = new ApiBody().sendLoginApi(account, password);//取得要送出的資料
        model.callLoginApi(body, new ApiResponseCallBack<LoginResponse>() {
            @Override
            public void getLoginCallBackResponse(ApiResponse<LoginResponse> response) {
                isLoading.postValue(true);
                code = response.code;
                if (response.isSuccessful()) {
                    userName.setValue(response.body.getName());
                    userAge.setValue(response.body.getAge());
                    userSex.setValue(response.body.getSex());
                } else {
                    errorMessage.setValue(response.errorMessage);
                }
                isLoading.postValue(false);
            }

            @Override
            public void getLoginErrorResponse(Throwable t) {

            }
        });
    }

    public void loginRxAction(String account, String password) {
        FormBody body = new ApiBody().sendLoginApi(account, password);
        isLoading.postValue(true);
        model.callRxLoginApi(body, new RxApiResponseCallBack() {
            @Override
            public void getRxLoginCallBackResponse(LoginResponse response) {
                userName.postValue(response.getName());
                userAge.postValue(response.getAge());
                userName.postValue(response.getName());
                isLoading.postValue(false);
            }

            @Override
            public void getLoginErrorResponse(Throwable t) {
                errorMessage.postValue(t.getMessage());
                isLoading.postValue(false);
            }
        });
    }
}
