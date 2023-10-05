package fpoly.minhptph32719.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fpoly.minhptph32719.duanmau.database.DbHelper;
import fpoly.minhptph32719.duanmau.model.PhieuMuon;


public class PhieuMuonDAO {
    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public PhieuMuonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon pm) {
        ContentValues values = new ContentValues();
        values.put("maTT", pm.maTT);
        values.put("maTV", pm.maTV);
        values.put("maSach", pm.maSach);
        values.put("tienThue", pm.tienThue);
        values.put("ngay", sdf.format(pm.ngay));
        values.put("traSach", pm.traSach);
        return db.insert("PhieuMuon", null, values);
    }

    public long update(PhieuMuon pm) {
        ContentValues values = new ContentValues();
        values.put("maTT", pm.maTT);
        values.put("maTV", pm.maTV);
        values.put("maSach", pm.maSach);
        values.put("tienThue", pm.tienThue);
        values.put("ngay", sdf.format(pm.ngay));
        values.put("traSach", pm.traSach);
        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(pm.maPM)});
    }

    public int delete(String id) {
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    public List<PhieuMuon> getAll() {
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    public PhieuMuon getMaSach(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maSach=?";
        List<PhieuMuon> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String... selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            PhieuMuon pm = new PhieuMuon();
            String ngayString = cursor.getString(cursor.getColumnIndex("ngay"));
            try {
                Date ngayDate = sdf.parse(ngayString);
                pm.ngay = ngayDate;
            } catch (ParseException e) {
                e.printStackTrace();
                pm.ngay = null;
            }
            pm.maPM = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPM")));
            pm.maTT = cursor.getString(cursor.getColumnIndex("maTT"));
            pm.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
            pm.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            pm.tienThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienThue")));
            pm.traSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("traSach")));
            list.add(pm);
        }
        return list;
    }
}
