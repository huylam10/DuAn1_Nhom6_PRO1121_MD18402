package com.example.nhom6_pro1121_md18402.Admin;


import static com.example.nhom6_pro1121_md18402.MainActivity.account_all;
import static com.example.nhom6_pro1121_md18402.MainActivity.check_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nhom6_pro1121_md18402.MODEL.TaiKhoan;
import com.example.nhom6_pro1121_md18402.R;
import com.example.nhom6_pro1121_md18402.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding mainBinding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            account_all = (TaiKhoan) bundle.getSerializable("tk");
            check_login = true;
        }

        if(account_all.getRole()==2){
            replaceFragment(new SeingAdminFragment());
            mainBinding.bottomNavigationView.getMenu()
                    .findItem(R.id.order1).setChecked(true);
            mainBinding.bottomNavigationView.getMenu()
                    .findItem(R.id.home1).setVisible(false);
            mainBinding.bottomNavigationView.getMenu()
                    .findItem(R.id.setting1).setVisible(false);
        }else {
            replaceFragment(new HomeAdminFragment());
        }

        Log.e("ZZZZZ", "onCreate: " + account_all);

        mainBinding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home1){
                replaceFragment(new HomeAdminFragment());
            }
            if (item.getItemId() == R.id.order1){
                replaceFragment(new SeingAdminFragment());
            }
            if (item.getItemId() == R.id.Statistical1){
                replaceFragment(new ThongKeFragment());
            }
            if (item.getItemId() == R.id.setting1){
                replaceFragment(new AccoutManagerFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frameLayout_admin, fragment);
        fragmentTransaction.commit();
    }
}