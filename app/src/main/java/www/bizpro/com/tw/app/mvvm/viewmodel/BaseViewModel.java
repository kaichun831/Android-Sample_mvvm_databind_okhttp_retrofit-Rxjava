package www.bizpro.com.tw.app.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import www.bizpro.com.tw.app.mvvm.model.IBaseModel;

public class BaseViewModel extends AndroidViewModel {
    protected BaseViewModel model;
    public BaseViewModel(@NonNull Application application, IBaseModel baseModel) {
        super(application);
        if(baseModel instanceof BaseViewModel){
            this.model = (BaseViewModel) baseModel;
        }
    }
}
