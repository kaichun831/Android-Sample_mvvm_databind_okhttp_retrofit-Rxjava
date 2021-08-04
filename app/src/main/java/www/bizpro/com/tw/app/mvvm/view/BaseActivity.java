package www.bizpro.com.tw.app.mvvm.view;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements View.OnFocusChangeListener,View.OnClickListener,View.OnKeyListener {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
