package fpoly.minhptph32719.duanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.adapter.ThanhVienAdapter;
import fpoly.minhptph32719.duanmau.dao.ThanhVienDAO;
import fpoly.minhptph32719.duanmau.model.ThanhVien;

public class ThanhVienFragment extends Fragment {
    ListView lv_thanhVien;
    ArrayList<ThanhVien> list;
    FloatingActionButton btn_add;
    Dialog dialog;
    EditText edt_tenTV, edt_namSinh, edt_stk;
    TextView tv_maTV;
    Button btn_save, btn_cancel;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lv_thanhVien = view.findViewById(R.id.lv_thanhVien);
        btn_add = view.findViewById(R.id.btn_add);
        dao = new ThanhVienDAO(getActivity());
        capNhatLv();
        btn_add.setOnClickListener(v -> {
            openDiaLog(getActivity(), 0);
        });
        lv_thanhVien.setOnItemClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            openDiaLog(getActivity(), 1);
        });
        return view;
    }

    public void openDiaLog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_dialog);
        tv_maTV = dialog.findViewById(R.id.tv_maTV);
        edt_tenTV = dialog.findViewById(R.id.edt_tenTV);
        edt_stk = dialog.findViewById(R.id.edt_stk);
        edt_namSinh = dialog.findViewById(R.id.edt_namSinh);
        btn_save = dialog.findViewById(R.id.btn_save);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);
        if (type == 1) {
            tv_maTV.setText("Mã thành viên: " + item.maTV);
            edt_tenTV.setText(item.hoTen);
            edt_namSinh.setText(item.namSinh+"");
            edt_stk.setText(item.stk+"");
        } else {
            item = new ThanhVien();
        }
        btn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btn_save.setOnClickListener(v -> {
            if (validate() > 0) {
                item.hoTen = edt_tenTV.getText().toString();
                item.namSinh = edt_namSinh.getText().toString();
                item.stk = edt_stk.getText().toString();
                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", (dialog1, which) -> {
        });
        builder.show();
    }

    public void capNhatLv() {
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(), this, list);
        lv_thanhVien.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
        if (edt_tenTV.getText().length() == 0 || edt_namSinh.length() == 0||edt_stk.length() == 0) {
            Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
            check = -1;
        } else if (!edt_namSinh.getText().toString().matches("\\d+")||!edt_stk.getText().toString().matches("\\d+")) {
            Toast.makeText(getContext(), "Năm sinh và stk phải là số nguyên >=0 ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}