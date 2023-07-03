package com.ocr.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ocr.navigation.OOP.User;
import com.ocr.navigation.retrofit.RetrofitClient;
import com.ocr.navigation.retrofit.com.ocr.navigation.ApiInterface;

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
        edtReNewPass = findViewById(R.id.edt_repassword);
        btnUpdate = findViewById(R.id.btn_change_password);

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
        String oldPassword = edtOldPass.getText().toString();
        String newPassword = edtNewPass.getText().toString();
        String reNewPassword = edtReNewPass.getText().toString();
//        String password = user.getPassword();

        // Kiểm tra xem mật khẩu cũ đã nhập khớp với mật khẩu hiện tại hay không
        if (!oldPassword.equals("mật khẩu hiện tại")) {
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

        // Gọi phương thức để thực hiện thay đổi mật khẩu
                performPasswordChange(newPassword);
    }

    private void performPasswordChange(String newPassword) {
        // Gọi API hoặc thực hiện các thao tác cần thiết để thay đổi mật khẩu trong cơ sở dữ liệu hoặc hệ thống của bạn.
        // Ở đây, chúng ta chỉ mô phỏng việc in ra thông báo thành công.

        // In ra thông báo thành công
        Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();

        // Đặt lại giá trị của các trường nhập liệu
        edtOldPass.setText("");
        edtNewPass.setText("");
        edtReNewPass.setText("");
    }


    public void onBackPressed() {
        super.onBackPressed();
        // Thực hiện các thao tác khác (nếu cần) khi quay lại màn hình trước đó.
    }
}