package www.bizpro.com.tw.app.mvvm.webapi;

import retrofit2.Response;
import www.bizpro.com.tw.app.mvvm.response.LoginResponse;

//每個呼叫API都必須實作,目的為了檢查成功與否
public  interface ApiResponseCallBack<T> {
    void  getLoginCallBackResponse(ApiResponse<T> response);
    void  getLoginErrorResponse(Throwable t );
}
