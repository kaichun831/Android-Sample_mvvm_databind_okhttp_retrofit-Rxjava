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

    }

    private void init() {
        //TODO 預載
        listener();
        event();
    }

    private void listener() {
        //登入按鈕
        binding.BTLogin.setOnClickListener(this);
    }
    private  void event(){
        //Api成功呼叫Show錯誤訊息
        model.errorMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
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
            }
        }
    }
}