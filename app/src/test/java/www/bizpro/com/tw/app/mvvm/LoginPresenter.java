package www.bizpro.com.tw.app.mvvm;



public class LoginPresenter{

    private ServerApi serverApi;
    private String loginName;
    private String loginPwd;
    private DataBase db;
    public LoginPresenter(DataBase db, ServerApi serverApi){
        this.serverApi = serverApi;
        this.db = db;
    }

    public void onLoginClick(){
        serverApi.login(loginName, loginPwd, new ServerApi.Callback() {
            @Override
            public void onComplete(ServerApi.UserInfo userInfo) {
                db.saveUser(userInfo);
            }
        });
    }
}
