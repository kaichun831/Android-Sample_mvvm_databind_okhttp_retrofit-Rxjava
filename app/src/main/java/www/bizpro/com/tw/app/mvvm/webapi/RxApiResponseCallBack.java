package www.bizpro.com.tw.app.mvvm.webapi;

import retrofit2.Response;


public  interface RxApiResponseCallBack<T> {
    void  getRxLoginCallBackResponse(Response<T> response);
    void  getLoginErrorResponse(Throwable t );
}

