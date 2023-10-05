package fpoly.minhptph32719.duanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.adapter.LoaiSachSpinnerAdapter;
import fpoly.minhptph32719.duanmau.adapter.SachAdapter;
import fpoly.minhptph32719.duanmau.dao.LoaiSachDAO;
import fpoly.minhptph32719.duanmau.dao.SachDAO;
import fpoly.minhptph32719.duanmau.model.LoaiSach;
import fpoly.minhptph32719.duanmau.model.Sach;

public class SachFragment extends Fragment {
    ListView lv_sach;
    ArrayList<Sach> list;
    FloatingActionButton btn_add;
    Dialog dialog;
    EditText edt_tenSach, edt_giaThue;
    TextView tv_maSach;
    Spinner spinner;
    Button btn_save, btn_cancel;
    static SachDAO dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    int maLoaiSach, position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        lv_sach = view.findViewById(R.id.lv_sach);
        btn_add = view.findViewById(R.id.btn_add);
        dao = new SachDAO(getActivity());
        capNhatLv();
        btn_add.setOnClickListener(v -> {
            openDiaLog(getActivity(), 0);
        });
        lv_sach.setOnItemClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            openDiaLog(getActivity(), 1);
        });
        return view;
    }

    public void openDiaLog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        tv_maSach = dialog.findViewById(R.id.tv_maSach);
        edt_tenSach = dialog.findViewById(R.id.edt_tenSach);
        edt_giaThue = dialog.findViewById(R.id.edt_giaThue);
        spinner = dialog.findViewById(R.id.sp_loaiSach);
        btn_save = dialog.findViewById(R.id.btn_save);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context, listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).maLoai;
                Toast.makeText(context, "Chọn " + listLoaiSach.get(position).tenLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (type == 1) {
            tv_maSach.setText("Mã sách: " + item.maSach);
            edt_tenSach.setText(item.tenSach);
            edt_giaThue.setText(String.valueOf(item.giaThue));
            for (int i = 0; i < listLoaiSach.size(); i++) {
                if (item.maLoai == (listLoaiSach.get(i).maLoai)) {
                    position = i;
                }
                Log.i("demo", "posSach" + position);
                spinner.setSelection(position);
            }
        } else {
            item = new Sach();
        }

        btn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btn_save.setOnClickListener(v -> {
            if (validate() > 0) {
                item.tenSach = edt_tenSach.getText().toString();
                item.giaThue = Integer.parseInt(edt_giaThue.getText().toString());
                item.maLoai = maLoaiSach;
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
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new SachAdapter(getActivity(), this, list);
        lv_sach.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
        if (edt_tenSach.getText().length() == 0 || edt_giaThue.getText().length() == 0) {
            Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
            check = -1;
        } else if (!edt_giaThue.getText().toString().matches("\\d+")) {
            Toast.makeText(getContext(), "Giá thuê phải là số nguyên >=0", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}