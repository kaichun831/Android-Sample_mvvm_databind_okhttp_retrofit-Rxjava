package www.bizpro.com.tw.app.mvvm.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Response;


//@SerializedName 為了使OKHttp自動對應
public class LoginResponse implements Serializable {
    @SerializedName("code")
    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @SerializedName("account")
    String account;
    @SerializedName("password")
    String password;
    @SerializedName("name")
    String name;
    @SerializedName("age")
    int age;
    @SerializedName("sex")
    int sex;
    @SerializedName("id")
    int id;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
