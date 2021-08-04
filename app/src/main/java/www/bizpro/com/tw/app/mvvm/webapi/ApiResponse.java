/*
  打包處理,檢查是否成功
 */
package www.bizpro.com.tw.app.mvvm.webapi;

import android.util.Log;

import java.io.IOException;

import retrofit2.Response;

public class ApiResponse<T> {
    private final String TAG = this.getClass().getSimpleName();
    public int code;
    public T body;
    public String errorMessage;

    public ApiResponse(Response<T> response) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Log.d(TAG, ignored.getMessage());
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }
    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }
}
