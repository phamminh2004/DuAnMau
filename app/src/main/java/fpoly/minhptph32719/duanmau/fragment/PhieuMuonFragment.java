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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;


import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.adapter.PhieuMuonAdapter;
import fpoly.minhptph32719.duanmau.adapter.SachSpinnerAdapter;
import fpoly.minhptph32719.duanmau.adapter.ThanhVienSpinnerAdapter;
import fpoly.minhptph32719.duanmau.dao.PhieuMuonDAO;
import fpoly.minhptph32719.duanmau.dao.SachDAO;
import fpoly.minhptph32719.duanmau.dao.ThanhVienDAO;
import fpoly.minhptph32719.duanmau.model.PhieuMuon;
import fpoly.minhptph32719.duanmau.model.Sach;
import fpoly.minhptph32719.duanmau.model.ThanhVien;

public class PhieuMuonFragment extends Fragment {
    ListView lv_pm;
    ArrayList<PhieuMuon> list;
    FloatingActionButton btn_add;
    Dialog dialog;
    Spinner sp_tv, sp_sach;
    TextView tvNgay, tvTienThue, tvMaPM;
    CheckBox chk_checkPM;
    Button btn_save, btn_cancel;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDA0;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDA0;
    int maSach, tienThue;
    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lv_pm = view.findViewById(R.id.lv_pm);
        btn_add = view.findViewById(R.id.btn_add);
        dao = new PhieuMuonDAO(getActivity());
        capNhatLv();
        btn_add.setOnClickListener(v -> {
            openDiaLog(getActivity(), 0);
        });
        lv_pm.setOnItemClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            openDiaLog(getActivity(), 1);
        });
        return view;
    }

    public void openDiaLog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        tvMaPM = dialog.findViewById(R.id.tv_maPM);
        sp_sach = dialog.findViewById(R.id.sp_sach);
        sp_tv = dialog.findViewById(R.id.sp_tv);
        tvNgay = dialog.findViewById(R.id.tv_ngay);
        tvTienThue = dialog.findViewById(R.id.tv_tienThue);
        chk_checkPM = dialog.findViewById(R.id.chk_checkPM);
        btn_save = dialog.findViewById(R.id.btn_save);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);
        thanhVienDA0 = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDA0.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        sp_tv.setAdapter(thanhVienSpinnerAdapter);
        sp_tv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).maTV;
                Toast.makeText(context, "Chọn " + listThanhVien.get(position).hoTen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDA0 = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDA0.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        sp_sach.setAdapter(sachSpinnerAdapter);
        sp_sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).maSach;
                tienThue = listSach.get(position).giaThue;
                tvTienThue.setText("Tiền thuê: " + tienThue);
                Toast.makeText(context, "Chọn " + listSach.get(position).tenSach, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (type == 1) {
            tvMaPM.setText("Mã phiếu: " + item.maPM);
            for (int i = 0; i < listThanhVien.size(); i++) {
                if (item.maTV == (listThanhVien.get(i).maTV)) {
                    positionTV = i;
                }
                sp_tv.setSelection(positionTV);
            }
            for (int i = 0; i < listSach.size(); i++) {
                if (item.maSach == (listSach.get(i).maSach)) {
                    positionSach = i;
                }
                sp_sach.setSelection(positionSach);
            }
            tvNgay.setText("Ngày thuê: " + sdf.format(item.ngay));
            if (item.traSach == 0) {
                chk_checkPM.setChecked(false);
            } else {
                chk_checkPM.setChecked(true);
            }
        } else {
            item = new PhieuMuon();
            item.ngay = java.sql.Date.valueOf(String.valueOf(LocalDate.now()));
            tvNgay.setText("Ngày thuê: " + item.ngay);
        }
        btn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btn_save.setOnClickListener(v -> {
            item.maSach = maSach;
            item.maTV = maThanhVien;
            item.tienThue = tienThue;
            if (chk_checkPM.isChecked()) {
                item.traSach = 1;
            } else {
                item.traSach = 0;
            }
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
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lv_pm.setAdapter(adapter);
    }
}