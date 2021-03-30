package www.bizpro.com.tw.app.mvvm.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import www.bizpro.com.tw.app.mvvm.R;
import www.bizpro.com.tw.app.mvvm.databinding.ActivityLoginBinding;
import www.bizpro.com.tw.app.mvvm.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private LoginViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        model = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(model);
        binding.setLifecycleOwner(this);
        init();

        // 步骤6：采用Observable<...>形式 对 网络请求 进行封装
//        ApiService apiService = RxApiManager.getInstance().getAPI();
//        Observable<LoginResponse> observable = apiService.doRxLogin();
//        // 步骤7：发送网络请求
//        observable.subscribeOn(Schedulers.io())               // 在IO线程进行网络请求
//                .observeOn(Schedulers.single())  // 回到主线程 处理请求结果
//                .subscribe(new io.reactivex.rxjava3.core.Observer<LoginResponse>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.d("KAI","D");
//                    }
//
//                    @Override
//                    public void onNext(@NonNull LoginResponse loginResponse) {
//                        Log.d("KAI","NEXT");
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d("KAI","ERROR");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("KAI","COMPLETE");
//                    }
//                });
    }

    private void init() {
        //TODO 預載
        listener();
    }

    private void listener() {
        //登入按鈕
        binding.BTLogin.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model = null;
        binding = null;  //釋放資源
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登入操作
            case R.id.BT_login: {
                String account = binding.EDAccount.getText().toString();
                String password = binding.EDPassword.getText().toString();

                //進行OKHttp+Retrofit動作
//                model.loginAction(account, password);
                //進行OKHttp+Retrofit+RxJava動作
                model.loginRxAction(account, password);

                //Api成功呼叫Show錯誤訊息
                model.errorMessage.observe(this, new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        Toast.makeText(LoginActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                if (model.code == 200) {
                    //Api成功呼叫
                }
            }
        }
    }
}