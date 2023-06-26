package com.ocr.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ocr.navigation.OOP.UserManager;

public class ChangeAddressActivity extends AppCompatActivity {
    private ImageView ivBackAddress;
    private EditText editAddress, editCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        initUI();
        onClickListener();
    }

    private void onClickListener() {
        ivBackAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initUI() {
        ivBackAddress = findViewById(R.id.iv_back_address);
        editAddress = findViewById(R.id.edt_change_address);
        editCity = findViewById(R.id.edt_change_city);

        editAddress.setText(UserManager.getInstance().getCurrentUser().getAddress());
        editCity.setText(UserManager.getInstance().getCurrentUser().getCity());

    }

    public void onBackPressed() {
        super.onBackPressed();
        // Thực hiện các thao tác khác (nếu cần) khi quay lại màn hình trước đó.git
    }


}