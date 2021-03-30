package www.bizpro.com.tw.app.mvvm.webapi;

import www.bizpro.com.tw.app.mvvm.response.LoginResponse;

public interface RxApiResponseCallBack<T> {
    void getRxLoginCallBackResponse(LoginResponse response);

    void getLoginErrorResponse(Throwable t);
}