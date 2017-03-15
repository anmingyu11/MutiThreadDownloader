package xyz.skybox.downloader.bizs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import xyz.skybox.zeusutil.LogUtil;

import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_CURRENT_BYTES;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_DIR_PATH;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_DISPOSITION;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_ETAG;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_FILE_NAME;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_LOCATION;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_MIME_TYPE;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_TOTAL_BYTES;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_URL_BASE;
import static xyz.skybox.downloader.bizs.DLCons.DBCons.TB_TASK_URL_REAL;

class TaskDAO implements ITaskDAO {
    private final DLDBHelper dbHelper;

    TaskDAO(Context context) {
        dbHelper = new DLDBHelper(context);
    }

    @Override
    public void insertTaskInfo(DLInfo info) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + TB_TASK + "(" +
                        TB_TASK_URL_BASE + ", " +
                        TB_TASK_URL_REAL + ", " +
                        TB_TASK_DIR_PATH + ", " +
                        TB_TASK_FILE_NAME + ", " +
                        TB_TASK_MIME_TYPE + ", " +
                        TB_TASK_ETAG + ", " +
                        TB_TASK_DISPOSITION + ", " +
                        TB_TASK_LOCATION + ", " +
                        TB_TASK_CURRENT_BYTES + ", " +
                        TB_TASK_TOTAL_BYTES + ") values (?,?,?,?,?,?,?,?,?,?)",
                new Object[]{info.baseUrl, info.realUrl, info.dirPath, info.fileName,
                        info.mimeType, info.eTag, info.disposition, info.location,
                        info.currentBytes, info.totalBytes});
        db.close();
    }

    @Override
    public void deleteTaskInfo(String url) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TB_TASK + " WHERE " + TB_TASK_URL_BASE + "=?",
                new String[]{url});
        db.close();
    }

    @Override
    public void updateTaskInfo(DLInfo info) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE " + TB_TASK + " SET " +
                TB_TASK_DISPOSITION + "=?," +
                TB_TASK_LOCATION + "=?," +
                TB_TASK_MIME_TYPE + "=?," +
                TB_TASK_TOTAL_BYTES + "=?," +
                TB_TASK_FILE_NAME + "=?," +
                TB_TASK_CURRENT_BYTES + "=? WHERE " +
                TB_TASK_URL_BASE + "=?", new Object[]{info.disposition, info.location,
                info.mimeType, info.totalBytes, info.fileName, info.currentBytes, info.baseUrl});
        db.close();
    }


    @Override
    public DLInfo queryTaskInfo(String url) {
        DLInfo info = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " +
                TB_TASK_URL_BASE + ", " +
                TB_TASK_URL_REAL + ", " +
                TB_TASK_DIR_PATH + ", " +
                TB_TASK_FILE_NAME + ", " +
                TB_TASK_MIME_TYPE + ", " +
                TB_TASK_ETAG + ", " +
                TB_TASK_DISPOSITION + ", " +
                TB_TASK_LOCATION + ", " +
                TB_TASK_CURRENT_BYTES + ", " +
                TB_TASK_TOTAL_BYTES + " FROM " +
                TB_TASK + " WHERE " +
                TB_TASK_URL_BASE + "=?", new String[]{url});
        if (c.moveToFirst()) {
            info = new DLInfo();
            info.baseUrl = c.getString(0);
            info.realUrl = c.getString(1);
            info.dirPath = c.getString(2);
            info.fileName = c.getString(3);
            info.mimeType = c.getString(4);
            info.eTag = c.getString(5);
            info.disposition = c.getString(6);
            info.location = c.getString(7);
            info.currentBytes = c.getInt(8);
            info.totalBytes = c.getInt(9);
        }
        c.close();
        db.close();
        return info;
    }

    @Override
    public List<String> queryAllTaskInfo() {
        List<String> mDlInfos = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            Cursor c = db.query(TB_TASK, new String[]{TB_TASK_URL_REAL},
                    null, null, null, null, null, null);
            LogUtil.d("Task count : " + c.getCount());
            mDlInfos = new ArrayList<String>();
            if (!c.moveToFirst()) {
                throw new Exception("move to first failed");
            }
            mDlInfos.add(c.getString(0));
            while (c.moveToNext()) {
                mDlInfos.add(c.getString(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return mDlInfos;
        }
    }

    public void dropDb() {
    }
}