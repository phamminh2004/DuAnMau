package fpoly.minhptph32719.duanmau.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.dao.ThuThuDAO;
import fpoly.minhptph32719.duanmau.model.ThuThu;

public class ThemFragment extends Fragment {
    EditText edtUser, edtHoTen, edtPass, edtRePass;
    Button btn_save, btn_cancel;
    ThuThuDAO thuThuDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them, container, false);
        edtUser = view.findViewById(R.id.edtUser);
        edtHoTen = view.findViewById(R.id.edtHoTen);
        edtPass = view.findViewById(R.id.edtPass);
        edtRePass = view.findViewById(R.id.edtRePass);
        btn_save = view.findViewById(R.id.btn_save);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        thuThuDAO = new ThuThuDAO(getActivity());
        btn_cancel.setOnClickListener(v -> {
            reset();
        });
        btn_save.setOnClickListener(v -> {
            ThuThu thuThu = new ThuThu();
            thuThu.maTT = edtUser.getText().toString();
            thuThu.hoTen = edtHoTen.getText().toString();
            thuThu.matKhau = edtPass.getText().toString();
            if (validate() > 0) {
                if (thuThuDAO.insert(thuThu) > 0) {
                    Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    reset();
                } else {
                    Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public int validate() {
        int check = 1;
        if (edtUser.getText().length() == 0 || edtHoTen.getText().length() == 0 || edtPass.getText().length() == 0 || edtRePass.getText().length() == 0) {
            Toast.makeText(getActivity(), "Bạn phải nhập thông tin đầy đủ", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPass.getText().toString();
            String rePass = edtRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void reset() {
        edtUser.setText("");
        edtHoTen.setText("");
        edtPass.setText("");
        edtRePass.setText("");
    }
}