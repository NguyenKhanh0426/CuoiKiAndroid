package vn.edu.stu.login;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginKetQua> login(
            @Field("ID") String id,
            @Field("PASSCODE") String pass
    );
}

