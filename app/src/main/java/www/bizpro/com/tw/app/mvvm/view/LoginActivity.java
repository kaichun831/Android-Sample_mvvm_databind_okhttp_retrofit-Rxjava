package www.bizpro.com.tw.app.mvvm.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import www.bizpro.com.tw.app.mvvm.R;
import www.bizpro.com.tw.app.mvvm.databinding.ActivityLoginBinding;
import www.bizpro.com.tw.app.mvvm.model.LoginDataModel;
import www.bizpro.com.tw.app.mvvm.viewmodel.AndroidViewModelFactory;
import www.bizpro.com.tw.app.mvvm.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginDataModel loginDataModel = new LoginDataModel();
        AndroidViewModelFactory androidViewModelFactory = new AndroidViewModelFactory(getApplication(), loginDataModel);
        loginViewModel = new ViewModelProvider(this, androidViewModelFactory).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        init();

    }

    private void init() {
        listener();
        viewModelAction();
    }

    private void listener() {
        //登入按鈕
        binding.BTLogin.setOnClickListener(this);
        binding.EDAccount.setOnFocusChangeListener(this);
        binding.EDPassword.setOnFocusChangeListener(this);
    }

    private void viewModelAction() {
        loginViewModel.errorMessage.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Toast.makeText(LoginActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginViewModel = null;
        binding = null;  //釋放資源
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登入操作
            case R.id.BT_login: {
                String account = binding.EDAccount.getText().toString();
                String password = binding.EDPassword.getText().toString();
//                model.loginAction(account, password);//進行OKHttp+Retrofit動作
                loginViewModel.loginRxAction("AAAA", "BBB");//進行OKHttp+Retrofit+RxJava動作
                //Api成功呼叫Show錯誤訊息

            }
        }
    }
}