package com.example.nhom6_pro1121_md18402.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom6_pro1121_md18402.DAO.TaikhoanDAO;
import com.example.nhom6_pro1121_md18402.MODEL.TaiKhoan;
import com.example.nhom6_pro1121_md18402.MODEL.ThongTinNguoiDung;
import com.example.nhom6_pro1121_md18402.MainActivity;
import com.example.nhom6_pro1121_md18402.R;
import com.example.nhom6_pro1121_md18402.ultis.SendMail;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputEditText email, pass;
    Button login;
    TextView skip, signUp,forGot;
    TaikhoanDAO dao;
    SendMail sendMail;
    TaiKhoan tk;
    TextInputLayout textInputName, textInputPass;
    public static ThongTinNguoiDung settingFragment = new ThongTinNguoiDung();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edit_sdt);
        pass = findViewById(R.id.edit_pass);
        login = findViewById(R.id.btn_login);
        skip = findViewById(R.id.skip);
        signUp = findViewById(R.id.signUp);
        forGot = findViewById(R.id.forGot);
        textInputName = findViewById(R.id.Login_Til_Name);
        textInputPass = findViewById(R.id.Login_Til_Pass);
        dao = new TaikhoanDAO(this);
        sendMail = new SendMail();

        Intent intent = getIntent();
        int key = intent.getIntExtra("key", -1);
        if (key == 0) {
            email.setText("");
            pass.setText("");
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputPass.setError("");
                textInputName.setError("");
                Intent intent;
                String mail = email.getText().toString();
                String mk = pass.getText().toString();
                if (dao.checkDangNhapkh(mail, mk) == true) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tk", dao.getName(mail));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } else if (mail.equalsIgnoreCase("")) {
                    textInputName.setError("Vui lòng nhập tài khoản");
                } else if (mk.equalsIgnoreCase("")) {
                    textInputPass.setError("Vui lòng nhập mật khẩu");
                } else if (dao.checkDangNhapkhNVAD(mail, mk) == true) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);//AdminActivity
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tk", dao.getName(mail));
                    intent.putExtra("bundle", bundle);
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    textInputName.setError("Tài khoản hoặc mật khẩu sai");
                    textInputPass.setError("Tài khoản hoặc mật khẩu sai");
                }


            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Sign_up.class));
            }
        });
        forGot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForGot();
            }
        });
//       skip.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               sharedPreferences = getSharedPreferences("OKLuon", MODE_PRIVATE);
//               SharedPreferences.Editor editor = sharedPreferences.edit();
//                startActivity(new Intent(Login.this, MainActivity.class));
//                cart_all = new DatHang();
//                check_login = false;
//                account_all = new TaiKhoan();
//               settingFragment = new ThongTinNguoiDung();
//               Log.e("ZZZZZ", "onClick: " + account_all + "." + check_login);
//                editor.putString("FullName", settingFragment.getFullname());
//               editor.putString("Avatar",settingFragment.getAvatarNguoidung());
//                editor.commit();
//            }
//        });
    }

    private boolean checkTK(String mail, String mk) {
        if (dao.getName(mail) == null) {
            textInputName.setError("Tai khoan khong ton tai");
            return false;
        } else {
            tk = dao.getName(mail);
            if (!mk.equals(tk.getPassword())) {
                textInputPass.setError("Mật khẩu sai");
                return false;
            }
        }
        return true;
    }

    private void showDialogForGot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_forgot,null);
        builder.setView(view);
        builder.setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        EditText edtEmail = view.findViewById(R.id.edit_email);
        Button btnSend = view.findViewById(R.id.btn_Send);
        Button btnCancel = view.findViewById(R.id.btn_Cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String matkhau = dao.ForgotPassword(email);
                //Toast.makeText(Login.this, matkhau, Toast.LENGTH_SHORT).show();
                if (matkhau.equals("")){
                    edtEmail.setError("Không tìm thấy tài khoản");
                }else {
                    sendMail.Send(Login.this,email,"LẤY LẠI MẬT KHẨU","Mật khẩu của bạn là: "+matkhau);
                    alertDialog.dismiss();
                }
            }
        });
    }
}