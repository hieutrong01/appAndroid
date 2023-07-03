package com.ocr.navigation;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
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
import com.ocr.navigation.retrofit.RetrofitClient;
import com.ocr.navigation.retrofit.com.ocr.navigation.ApiInterface;
import com.ocr.navigation.retrofit.com.ocr.navigation.ResponseUpDateUser;



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
    private  User user;

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
                updateUserAddress();
            }
        });
    }

    private void updateUserAddress() {
        String id = UserManager.getInstance().getCurrentUser().getUser_id();

        String str_user_name = edtChangeName.getText().toString().trim();
        edtChangeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setUsername(edtChangeName.getText().toString().trim());
            }
        });
        String str_phone_number = edtChangePhone.getText().toString().trim();
        edtChangePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setPhoneNumber(edtChangePhone.getText().toString().trim());

            }
        });
        String str_address = edtChangeAddress.getText().toString().trim();
        String str_city = edtChangeCity.getText().toString().trim();
        String email= tvEmail.getText().toString().trim();
        String password=UserManager.getInstance().getCurrentUser().getPassword();
        //checked nam nu
        Boolean isGenderChecked = radio_Nam.isChecked() || radio_nu.isChecked();
        boolean isMale = isGenderChecked && radio_Nam.isChecked();

        if (TextUtils.isEmpty(str_user_name)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên người dùng", Toast.LENGTH_SHORT).show();
        } else if (!isGenderChecked) {
            Toast.makeText(getApplicationContext(), "Bạn chưa chọn giới tính", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_phone_number)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điên thoại", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_address)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_city)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập thành phố", Toast.LENGTH_SHORT).show();
        } else {
            apiInterface.updateUser(id,str_user_name, isMale ? "Nam" : "Nữ", str_phone_number, str_address, str_city,email,password).enqueue(new Callback<ResponseUpDateUser>() {
                @Override
                public void onResponse(Call<ResponseUpDateUser> call, Response<ResponseUpDateUser> response) {
                    if (response.isSuccessful()) {

                        if (response.body() != null) {
                            String status = String.valueOf(response.body().getMessage());
                            Log.d("AAA","aa"+status);




                        }

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

////
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

        tvEmail.setText(UserManager.getInstance().getCurrentUser().getEmail());
        edtChangeName.setText(UserManager.getInstance().getCurrentUser().getUsername());
        edtChangeAddress.setText(UserManager.getInstance().getCurrentUser().getAddress());
        edtChangeCity.setText(UserManager.getInstance().getCurrentUser().getCity());
        edtChangePhone.setText(UserManager.getInstance().getCurrentUser().getPhoneNumber());

        radio_Nam.setChecked(UserManager.getInstance().getCurrentUser().getGender().equals("Nam"));
        radio_nu.setChecked(UserManager.getInstance().getCurrentUser().getGender().equals("Nữ"));

    }

    public void onBackPressed() {
        super.onBackPressed();
        // Thực hiện các thao tác khác (nếu cần) khi quay lại màn hình trước đó.git
    }


}