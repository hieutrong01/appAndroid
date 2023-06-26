package com.ocr.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageView ivBackPass;
    private EditText edtNewPass, edtReNewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initUI();
        onClickListener();
    }

    private void onClickListener() {
        ivBackPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initUI() {
        ivBackPass = findViewById(R.id.iv_back_pass);
        edtNewPass = findViewById(R.id.edt_new_pass);
        edtReNewPass = findViewById(R.id.edt_confirm_pass);

    }



    public void onBackPressed() {
        super.onBackPressed();
        // Thực hiện các thao tác khác (nếu cần) khi quay lại màn hình trước đó.
    }
}