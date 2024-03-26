package com.example.nhom6_pro1121_md18402.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom6_pro1121_md18402.DAO.TaikhoanDAO;
import com.example.nhom6_pro1121_md18402.DAO.ThongTinNguoiDungDAO;
import com.example.nhom6_pro1121_md18402.MODEL.TaiKhoan;
import com.example.nhom6_pro1121_md18402.MODEL.ThongTinNguoiDung;
import com.example.nhom6_pro1121_md18402.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DangKyThongTinActivities extends AppCompatActivity {
    ThongTinNguoiDungDAO nguoiDungDAO;
    Button hoantat;
    ImageView avatar;
    EditText sdt, email, dia_chi, name, ngay_sinh;
    RadioButton GT_nam, GT_nu;
    String userDK, passDK;
    TaikhoanDAO taikhoanDAO;
    TaiKhoan taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_thong_tin_activities);

        Intent intent = getIntent();
        userDK = intent.getStringExtra("user");
        passDK = intent.getStringExtra("pass");

        hoantat = findViewById(R.id.btnDangkythongtin);
        sdt = findViewById(R.id.edtSdt);
        email = findViewById(R.id.edtEmail);
        dia_chi = findViewById(R.id.edtDiachi);
        name = findViewById(R.id.edtHoten);
        ngay_sinh = findViewById(R.id.edtNgaysinh);
        GT_nam = findViewById(R.id.rdo_GT_Nam);
        GT_nu = findViewById(R.id.rdo_BTN_Nu);
        avatar = findViewById(R.id.edtAvatar);
        nguoiDungDAO = new ThongTinNguoiDungDAO(this);
        taikhoanDAO = new TaikhoanDAO(this);
        if (taikhoanDAO.getName(userDK).getPassword().equals(passDK)){
            taiKhoan=taikhoanDAO.getName(userDK);
        }
        hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = name.getText().toString();
                String phone = sdt.getText().toString();
                String mail = email.getText().toString();
                String address = dia_chi.getText().toString();
                String ngay = ngay_sinh.getText().toString();
                String anh = avatar.getResources().toString();

                ThongTinNguoiDung nguoiDung = new ThongTinNguoiDung();
                nguoiDung.setEmailNguoidung(mail);
                nguoiDung.setAddresNguoidung(address);
                nguoiDung.setBirthdayNguoidung(ngay);
                nguoiDung.setFullname(ten);
                nguoiDung.setSdtNguoidung(phone);

                if (GT_nu.isChecked()) {
                    nguoiDung.setGender(0);
                } else if (GT_nam.isChecked()) {
                    nguoiDung.setGender(1);
                }
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String text = simpleDateFormat.format(calendar.getTime());
                nguoiDung.setCreateNguoidung(text);
                nguoiDung.setAvatarNguoidung(anh);
                nguoiDung.setUpdateNguoidung(text);
                nguoiDung.setIdtaikhoan(taiKhoan.getId());
                if (nguoiDungDAO.themThongTinNguoiDung(nguoiDung)) {
                    Toast.makeText(DangKyThongTinActivities.this, "Them thong tin thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                } else {
                    Toast.makeText(DangKyThongTinActivities.this, "Them thong tin that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}