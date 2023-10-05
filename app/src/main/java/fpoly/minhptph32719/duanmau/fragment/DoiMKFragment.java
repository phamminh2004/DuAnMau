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

public class DoiMKFragment extends Fragment {
    EditText edtOldPass, edtPass, edtRePass;
    Button btn_save, btn_cancel;
    ThuThuDAO thuThuDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimk, container, false);

        edtOldPass = view.findViewById(R.id.edtOldPass);
        edtPass = view.findViewById(R.id.edtPass);
        edtRePass = view.findViewById(R.id.edtRePass);
        btn_save = view.findViewById(R.id.btn_save);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        thuThuDAO = new ThuThuDAO(getActivity());
        btn_cancel.setOnClickListener(v -> {
            edtOldPass.setText("");
            edtPass.setText("");
            edtRePass.setText("");
        });
        btn_save.setOnClickListener(v -> {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String user = pref.getString("USERNAME", "");
            if (validate() > 0) {
                ThuThu thuThu = thuThuDAO.getID(user);
                thuThu.matKhau = edtPass.getText().toString();
                thuThuDAO.updatePass(thuThu);
                if (thuThuDAO.updatePass(thuThu) > 0) {
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public int validate() {
        int check = 1;
        if (edtOldPass.getText().length() == 0 || edtPass.getText().length() == 0 || edtRePass.getText().length() == 0) {
            Toast.makeText(getActivity(), "Bạn phải nhập thông tin đầy đủ", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            String pass = edtPass.getText().toString();
            String rePass = edtRePass.getText().toString();
            if (!passOld.equals(edtOldPass.getText().toString())) {
                Toast.makeText(getActivity(), "Mật khẩu hiện tại sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void reset() {
        edtOldPass.setText("");
        edtPass.setText("");
        edtRePass.setText("");
    }
}