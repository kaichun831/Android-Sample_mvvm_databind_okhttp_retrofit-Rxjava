package www.bizpro.com.tw.app.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

import www.bizpro.com.tw.app.mvvm.model.IBaseModel;

public class AndroidViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private IBaseModel baseModel;

    public AndroidViewModelFactory(@NonNull Application application, @NonNull IBaseModel baseModel) {
        this.application = application;
        this.baseModel = baseModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return (T) modelClass.getConstructor(Application.class, IBaseModel.class).newInstance(application, baseModel);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}