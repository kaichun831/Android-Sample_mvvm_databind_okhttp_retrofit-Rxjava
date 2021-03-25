package www.bizpro.com.tw.app.mvvm;

import java.util.concurrent.TimeUnit;

public class ServerApi{

    public void login(String name, String pwd, Callback callback){
        //這裡模擬實際上的操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callback.onComplete(new UserInfo("john", 20, "Taipei city"));
            }
        }).start();
    }

    public interface Callback{
        void onComplete(UserInfo userInfo);
    }
    static class UserInfo{
        String name;
        int age;
        String cityName;
        UserInfo(String name,int age,String cityName){
            this.name=name;
            this.age=age;
            this.cityName=cityName;
        }
    }
}

