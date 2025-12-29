package vn.edu.stu.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edtID, edtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.edtID);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            APIService api = RetrofitClient.getInstance()
                    .create(APIService.class);

            api.login(
                    edtID.getText().toString(),
                    edtPass.getText().toString()
            ).enqueue(new Callback<LoginKetQua>() {

                @Override
                public void onResponse(Call<LoginKetQua> call,
                                       Response<LoginKetQua> response) {
                    if (response.isSuccessful()
                            && response.body() != null
                            && response.body().getRESULT().equals("1")) {

                        Toast.makeText(MainActivity.this,
                                "Đăng nhập thành công",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Đăng nhập thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginKetQua> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            "Lỗi kết nối",
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
