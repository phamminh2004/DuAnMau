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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.adapter.LoaiSachAdapter;
import fpoly.minhptph32719.duanmau.dao.LoaiSachDAO;
import fpoly.minhptph32719.duanmau.model.LoaiSach;

public class LoaiSachFragment extends Fragment {
    ListView lv_loaiSach;
    ArrayList<LoaiSach> list;
    FloatingActionButton btn_add;
    Dialog dialog;
    EditText edt_maLoai, edt_tenLoai;
    Button btn_save, btn_cancel;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lv_loaiSach = view.findViewById(R.id.lv_loaiSach);
        btn_add = view.findViewById(R.id.btn_add);
        dao = new LoaiSachDAO(getActivity());
        capNhatLv();
        btn_add.setOnClickListener(v -> {
            openDiaLog(getActivity(), 0);
        });
        lv_loaiSach.setOnItemClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            openDiaLog(getActivity(), 1);
        });
        return view;
    }

    public void openDiaLog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edt_maLoai = dialog.findViewById(R.id.edt_maLoai);
        edt_tenLoai = dialog.findViewById(R.id.edt_tenLoai);
        btn_save = dialog.findViewById(R.id.btn_save);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);
        edt_maLoai.setEnabled(false);
        if (type != 0) {
            edt_maLoai.setText(String.valueOf(item.maLoai));
            edt_tenLoai.setText(item.tenLoai);
        }
        btn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btn_save.setOnClickListener(v -> {
            item = new LoaiSach();
            item.tenLoai = edt_tenLoai.getText().toString();
            if (validate() > 0) {
                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    item.maLoai = Integer.parseInt(edt_maLoai.getText().toString());
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
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    public void capNhatLv() {
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this, list);
        lv_loaiSach.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
        if (edt_tenLoai.getText().length() == 0) {
            Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}