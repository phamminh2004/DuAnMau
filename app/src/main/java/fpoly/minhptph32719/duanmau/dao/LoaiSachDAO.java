package fpoly.minhptph32719.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.minhptph32719.duanmau.database.DbHelper;
import fpoly.minhptph32719.duanmau.model.LoaiSach;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach ls) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", ls.tenLoai);
        return db.insert("LoaiSach", null, values);
    }

    public long update(LoaiSach ls) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", ls.tenLoai);
        return db.update("LoaiSach", values, "maLoai=?", new String[]{String.valueOf(ls.maLoai)});
    }

    public int delete(String id) {
        return db.delete("LoaiSach", "maLoai=?", new String[]{id});
    }

    public List<LoaiSach> getAll() {
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id) {
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String... selectionArgs) {
        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiSach ls = new LoaiSach();
            ls.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            ls.tenLoai = cursor.getString(cursor.getColumnIndex("tenLoai"));
            list.add(ls);
        }
        return list;
    }
}
