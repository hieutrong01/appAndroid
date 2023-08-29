package com.ocr.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ocr.navigation.OOP.User;
import com.ocr.navigation.OOP.UserManager;
import com.ocr.navigation.retrofit.ApiInterface;
import com.ocr.navigation.retrofit.ResponseUpDatePass;
import com.ocr.navigation.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    private ImageView ivBackPass;
    private EditText edtOldPass, edtNewPass, edtReNewPass;
    private Button btnUpdate;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initUI();
        onClickListener();
    }


    private void initUI() {
        apiInterface = RetrofitClient.getApi();

        ivBackPass = findViewById(R.id.iv_back_pass);
        edtOldPass = findViewById(R.id.edt_old_pass);
        edtNewPass = findViewById(R.id.edt_new_pass);
        edtReNewPass = findViewById(R.id.edt_edt_confirm_pass);
        btnUpdate = findViewById(R.id.btn_update_pass);

    }


    private void onClickListener() {
        ivBackPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });


    }

    private void changePassword() {
        // Lấy ID của người dùng hiện tại
        String id = UserManager.getInstance().getCurrentUser().getUser_id();

// Lấy mật khẩu cũ và mật khẩu mới từ các trường nhập liệu
        String oldPassword = edtOldPass.getText().toString();
        String newPassword = edtNewPass.getText().toString();
        String reNewPassword = edtReNewPass.getText().toString();

// Kiểm tra xem người dùng đã nhập đầy đủ thông tin mật khẩu hay chưa
        if (oldPassword.isEmpty() || newPassword.isEmpty() || reNewPassword.isEmpty()) {
            // Hiển thị thông báo lỗi
            Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập đầy đủ thông tin mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

// Kiểm tra xem mật khẩu cũ đã nhập khớp với mật khẩu hiện tại hay không
        if (!oldPassword.equals(UserManager.getInstance().getCurrentUser().getPassword())) {
            // Hiển thị thông báo lỗi
            Toast.makeText(ChangePasswordActivity.this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
            return;
        }

// Kiểm tra xem mật khẩu mới và mật khẩu xác nhận có khớp nhau hay không
        if (!newPassword.equals(reNewPassword)) {
            // Hiển thị thông báo lỗi
            Toast.makeText(ChangePasswordActivity.this, "Mật khẩu mới và mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

// Nếu mật khẩu cũ đúng và mật khẩu mới khớp, tiến hành thay đổi mật khẩu trong CSDL của ứng dụng
// Tùy vào cơ chế xử lý dữ liệu của ứng dụng, bạn có thể sử dụng các API hoặc thực hiện truy vấn tới CSDL tại đây.
// Ví dụ:
// userManager.updatePassword(id, newPassword);
// Lưu ý rằng, phương thức updatePassword cần phải được định nghĩa và xử lý trong lớp UserManager.

// Hiển thị thông báo thành công
        Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();

        apiInterface.updatePass(id, reNewPassword)
                    .enqueue(new Callback<ResponseUpDatePass>() {
                        @Override
                        public void onResponse(Call<ResponseUpDatePass> call, Response<ResponseUpDatePass> response) {
                            if (response.isSuccessful()) {

                                if (response.body() != null) {
                                    String status = String.valueOf(response.body().getMessage());
                                    Log.d("AAA", "aa" + status);
                                }
                                UserManager.getInstance().setCurrentUser(user);
                                Toast.makeText(ChangePasswordActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent= new Intent(ChangePasswordActivity.this,SignInActivity.class);
                                startActivity( intent );


                            } else {
                                Toast.makeText(ChangePasswordActivity.this, "Cập nhật thất ", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUpDatePass> call, Throwable t) {
                            Toast.makeText(ChangePasswordActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
        }





    public void onBackPressed() {
        super.onBackPressed();
        // Thực hiện các thao tác khác (nếu cần) khi quay lại màn hình trước đó.
    }
}