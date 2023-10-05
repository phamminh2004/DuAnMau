package fpoly.minhptph32719.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import fpoly.minhptph32719.duanmau.dao.ThuThuDAO;
import fpoly.minhptph32719.duanmau.fragment.DoanhThuFragment;
import fpoly.minhptph32719.duanmau.fragment.DoiMKFragment;
import fpoly.minhptph32719.duanmau.fragment.LoaiSachFragment;
import fpoly.minhptph32719.duanmau.fragment.PhieuMuonFragment;
import fpoly.minhptph32719.duanmau.fragment.SachFragment;
import fpoly.minhptph32719.duanmau.fragment.ThanhVienFragment;
import fpoly.minhptph32719.duanmau.fragment.ThemFragment;
import fpoly.minhptph32719.duanmau.fragment.TopFragment;
import fpoly.minhptph32719.duanmau.model.ThuThu;
import fpoly.minhptph32719.duanmau.model.Top;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        NavigationView nv = findViewById(R.id.nvView);
        nv.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mHeaderView = nv.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String name = thuThu.hoTen;
        tvUser.setText("Welcome " + name + "!");
        if (user.equalsIgnoreCase("admin")) {
            nv.getMenu().findItem(R.id.them).setVisible(true);
        }
        if (savedInstanceState == null) {
            setTitle("Phiếu Mượn");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new PhieuMuonFragment()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.phieuMuon) {
            setTitle("Phiếu Mượn");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new PhieuMuonFragment()).commit();
        } else if (item.getItemId() == R.id.loaiSach) {
            setTitle("Loại Sách");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new LoaiSachFragment()).commit();
        } else if (item.getItemId() == R.id.sach) {
            setTitle("Sách");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new SachFragment()).commit();
        } else if (item.getItemId() == R.id.thanhVien) {
            setTitle("Thành Viên");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new ThanhVienFragment()).commit();
        } else if (item.getItemId() == R.id.top) {
            setTitle("Top 10");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new TopFragment()).commit();
        } else if (item.getItemId() == R.id.doanhThu) {
            setTitle("Doanh Thu");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new DoanhThuFragment()).commit();
        } else if (item.getItemId() == R.id.them) {
            setTitle("Thêm");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new ThemFragment()).commit();
        } else if (item.getItemId() == R.id.doiMK) {
            setTitle("Đổi mật khẩu");
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new DoiMKFragment()).commit();
        } else if (item.getItemId() == R.id.dangXuat) {
            startActivity(new Intent(MainActivity.this, DangNhap.class));
            finish();
        }
        drawer.closeDrawers();
        return false;
    }
}