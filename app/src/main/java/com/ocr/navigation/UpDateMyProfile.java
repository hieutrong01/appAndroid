package com.ocr.navigation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ocr.navigation.OOP.User;
import com.ocr.navigation.OOP.UserManager;
import com.ocr.navigation.retrofit.ApiInterface;
import com.ocr.navigation.retrofit.ResponseUpDateUser;
import com.ocr.navigation.retrofit.RetrofitClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpDateMyProfile extends AppCompatActivity {
    ApiInterface apiInterface;
    private ImageView ivBackAddress;
    private TextView tvEmail;
    private EditText edtChangeName, edtChangeCity, edtChangeAddress, edtChangePhone;
    private Button btnUpdate;
    private RadioButton radio_Nam, radio_nu;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date_my_profile);
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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
        edtChangeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtChangeName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên người dùng", Toast.LENGTH_SHORT).show();
                } else {
                    user.setUsername(edtChangeName.getText().toString().trim());
                }
            }
        });
        edtChangePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtChangePhone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điên thoại", Toast.LENGTH_SHORT).show();
                } else {
                    user.setPhoneNumber(edtChangePhone.getText().toString().trim());
                }
            }
        });
        edtChangeAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtChangeAddress.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                } else {
                    user.setAddress(edtChangeAddress.getText().toString().trim());
                }
            }
        });
        edtChangeCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtChangeCity.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập thành phố", Toast.LENGTH_SHORT).show();
                } else {
                    user.setCity(edtChangeCity.getText().toString().trim());
                }
            }
        });
        //checked nam nu
        if (radio_Nam.isChecked()) {
            user.setGender("Nam");
        } else {
            user.setGender("Nữ");
        }
    }

    private void updateUser() {
        String id = UserManager.getInstance().getCurrentUser().getUser_id();
        String email = tvEmail.getText().toString().trim();
        String password = user.getPassword();

        if (
                !user.getEmail().isEmpty() && !user.getCity().isEmpty() && !user.getAddress().isEmpty() && !user.getPhoneNumber().isEmpty()
        ) {
            apiInterface.updateUser(id, user.getUsername(), user.getGender(), user.getPhoneNumber(), user.getAddress(), user.getCity(), email, password)
                    .enqueue(new Callback<ResponseUpDateUser>() {
                        @Override
                        public void onResponse(Call<ResponseUpDateUser> call, Response<ResponseUpDateUser> response) {
                            if (response.isSuccessful()) {

                                if (response.body() != null) {
                                    String status = String.valueOf(response.body().getMessage());
                                    Log.d("AAA", "aa" + status);
                                }
                                UserManager.getInstance().setCurrentUser(user);
                                Toast.makeText(UpDateMyProfile.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UpDateMyProfile.this, "Cập nhật thất ", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUpDateUser> call, Throwable t) {
                            Toast.makeText(UpDateMyProfile.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
        }
    }


    private void initUI() {
        apiInterface = RetrofitClient.getApi();

        ivBackAddress = findViewById(R.id.iv_back_address);
        tvEmail = findViewById(R.id.tv_email);
        edtChangeName = findViewById(R.id.edt_change_name);
        edtChangeCity = findViewById(R.id.edt_change_city);
        edtChangeAddress = findViewById(R.id.edt_change_address);
        edtChangePhone = findViewById(R.id.edt_change_phone);
        radio_Nam = findViewById(R.id.radio_nam);
        radio_nu = findViewById(R.id.radio_nu);
        btnUpdate = findViewById(R.id.btn_update);

        user = UserManager.getInstance().getCurrentUser();

        tvEmail.setText(user.getEmail());
        edtChangeName.setText(user.getUsername());
        edtChangeAddress.setText(user.getAddress());
        edtChangeCity.setText(user.getCity());
        edtChangePhone.setText(user.getPhoneNumber());

        if (user.getGender().equals("Nam")) {
            radio_Nam.setChecked(true);
        } else {
            radio_nu.setChecked(true);
        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        // Thực hiện các thao tác khác (nếu cần) khi quay lại màn hình trước đó.git
    }


}