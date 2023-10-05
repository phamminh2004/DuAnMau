package fpoly.minhptph32719.duanmau.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.dao.ThuThuDAO;

public class DangNhap extends AppCompatActivity {
    EditText edt_username, edt_password;
    Button btn_login, btn_cancel;
    CheckBox chk_checkPass;
    String strUser, strPass;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        setTitle("ĐĂNG NHẬP");
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_cancel = findViewById(R.id.btn_cancel);
        chk_checkPass = findViewById(R.id.chk_checkPass);
        thuThuDAO = new ThuThuDAO(this);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edt_username.setText(sharedPreferences.getString("USERNAME", ""));
        edt_password.setText(sharedPreferences.getString("PASSWORD", ""));
        chk_checkPass.setChecked(sharedPreferences.getBoolean("REMEMBER", false));
        btn_cancel.setOnClickListener(v -> {
            edt_username.setText("");
            edt_password.setText("");
        });
        btn_login.setOnClickListener(v -> {
            checkLogin();
        });
    }

    public void checkLogin() {
        strUser = edt_username.getText().toString();
        strPass = edt_password.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if (thuThuDAO.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, chk_checkPass.isChecked());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String user, String pass, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("USERNAME", user);
            editor.putString("PASSWORD", pass);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }
}