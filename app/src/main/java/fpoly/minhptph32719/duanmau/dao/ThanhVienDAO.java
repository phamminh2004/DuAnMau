package fpoly.minhptph32719.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.minhptph32719.duanmau.database.DbHelper;
import fpoly.minhptph32719.duanmau.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien tv) {
        ContentValues values = new ContentValues();
        values.put("hoTen", tv.hoTen);
        values.put("stk",tv.stk);
        values.put("namSinh", tv.namSinh);
        return db.insert("ThanhVien", null, values);
    }

    public long update(ThanhVien tv) {
        ContentValues values = new ContentValues();
        values.put("hoTen", tv.hoTen);
        values.put("stk",tv.stk);
        values.put("namSinh", tv.namSinh);
        return db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(tv.maTV)});
    }

    public int delete(String id) {
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }

    public List<ThanhVien> getAll() {
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(String id) {
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String... selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThanhVien tv = new ThanhVien();
            tv.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
            tv.hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
            tv.stk = cursor.getString(cursor.getColumnIndex("stk"));
            tv.namSinh = cursor.getString(cursor.getColumnIndex("namSinh"));
            list.add(tv);
        }
        return list;
    }
}
