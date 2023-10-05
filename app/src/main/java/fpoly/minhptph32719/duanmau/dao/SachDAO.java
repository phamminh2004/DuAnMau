package fpoly.minhptph32719.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.minhptph32719.duanmau.database.DbHelper;
import fpoly.minhptph32719.duanmau.model.Sach;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach s) {
        ContentValues values = new ContentValues();
        values.put("tenSach", s.tenSach);
        values.put("giaThue", s.giaThue);
        values.put("maLoai", s.maLoai);
        return db.insert("Sach", null, values);
    }

    public long update(Sach s) {
        ContentValues values = new ContentValues();
        values.put("tenSach", s.tenSach);
        values.put("giaThue", s.giaThue);
        values.put("maLoai", s.maLoai);
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(s.maSach)});
    }

    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    public List<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

    public Sach getMaLoai(String id) {
        String sql = "SELECT * FROM Sach WHERE maLoai=?";
        List<Sach> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Sach s = new Sach();
            s.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            s.tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
            s.giaThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue")));
            s.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            list.add(s);
        }
        return list;
    }
}
