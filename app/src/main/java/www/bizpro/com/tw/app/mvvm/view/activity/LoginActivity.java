package www.bizpro.com.tw.app.mvvm.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
        RxJava();

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
                model.loginAction(account, password);
                //進行OKHttp+Retrofit+RxJava動作
//                model.loginRxAction(account,password);
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

    void RxJava() {
        Observable.just(1, 2, 3)      //進入的內容
                .filter(x -> x % 2 == 0) //過濾條件
                .map(x -> x * 2) //結果處理
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        //結果
                        Log.d("RX", integer.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("RX", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //完成
                        Log.d("RX", "Complete");
                    }
                });


    }
}