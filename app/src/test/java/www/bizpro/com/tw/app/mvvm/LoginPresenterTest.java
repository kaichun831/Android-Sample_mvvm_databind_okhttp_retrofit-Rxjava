package www.bizpro.com.tw.app.mvvm;



import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoginPresenterTest {

    @Mock
    private ServerApi serverApi;

    @Mock
    private DataBase db;

    @Test
    public void login_should_save_userInfo_to_db() throws Exception {
        MockitoAnnotations.initMocks(this);
        LoginPresenter presenter = new LoginPresenter(db,serverApi);
        //卡住了無法繼續進行下去
        presenter.onLoginClick();
        Mockito.verify(db).saveUser(Mockito.any());
    }
    @Test
    public void login_should_save_userInfo_to_db_fix() throws Exception {
        //arrange
        MockitoAnnotations.initMocks(this);
        ServerApi.UserInfo verifyData = new ServerApi.UserInfo("john", 20, "taipei city");
        LoginPresenter presenter = new LoginPresenter(db, serverApi);

        //doAnswer 非同步動作
        Mockito.doAnswer(invocation -> {
            ServerApi.Callback callback = (ServerApi.Callback) invocation.getArguments()[2];  //模擬這個login第三個參數callback
            //拿login的第三的參數來轉型
            callback.onComplete(verifyData);
            return null;
        })
                .when(serverApi) //模擬serverApi動作
                .login(Mockito.anyString(), Mockito.anyString(), Mockito.any(ServerApi.Callback.class));
        //在這裡無法預期login三個參數的實際內容
        //act
        presenter.onLoginClick();
        //assert
        Mockito.verify(db).saveUser(verifyData);
    }
}