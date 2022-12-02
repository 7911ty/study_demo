package com.example.biji.dp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.biji.beans.Note;

import java.util.ArrayList;
import java.util.List;

public class CRUD {
    SQLiteOpenHelper dbHandler;
    SQLiteDatabase db;

    private static final String[] columns = {
            NoteDatabase.ID,
            NoteDatabase.CONTENT,
            NoteDatabase.TIME,
            NoteDatabase.MODE
    };

    public CRUD(Context context) {
        dbHandler = new NoteDatabase(context);
    }

    public void open() {
        db = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    //把note 加入到database里面
    public Note addNote(Note note) {
        //add a note object to database
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDatabase.CONTENT, note.getContent());
        contentValues.put(NoteDatabase.TIME, note.getTime());
        contentValues.put(NoteDatabase.MODE, note.getTag());
        long insertId = db.insert(NoteDatabase.TABLE_NAME, null, contentValues);
        note.setId(insertId);
        return note;
    }

    public Note getNote(long id) {
        //get a note from database using cursor index
        Cursor cursor = db.query(NoteDatabase.TABLE_NAME, columns, NoteDatabase.ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Note note = new Note(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        return note;
    }

    @SuppressLint("Range")
    public List<Note> getAllNotes() {
        Cursor cursor =
                db.query(NoteDatabase.TABLE_NAME, columns,
                        null, null, null, null, null);
        List<Note> notes = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Note note = new Note();
                note.setId(cursor.getLong(cursor.getColumnIndex(NoteDatabase.ID)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NoteDatabase.CONTENT)));
                note.setTime(cursor.getString(cursor.getColumnIndex(NoteDatabase.TIME)));
                note.setTag(cursor.getInt(cursor.getColumnIndex(NoteDatabase.MODE)));
                notes.add(note);
            }
        }
        return notes;
    }

    public int updateNote(Note note) {
        //update the info of an existing note
        ContentValues values = new ContentValues();
        values.put(NoteDatabase.CONTENT, note.getContent());
        values.put(NoteDatabase.TIME, note.getTime());
        values.put(NoteDatabase.MODE, note.getTag());
        //updating row
        return db.update(NoteDatabase.TABLE_NAME, values,
                NoteDatabase.ID + "=?", new String[]{String.valueOf(note.getId())});
    }

    public void removeNote(Note note) {
        //remove a note according to ID value
        db.delete(NoteDatabase.TABLE_NAME, NoteDatabase.ID + "=" + note.getId(), null);
    }

    @SuppressLint("Range")
    public void removeAllNote() {
        db.delete("notes", null, null);
        db.execSQL("update sqlite_sequence set seq=0 where name='notes'");
    }

    @SuppressLint("Range")
    public List<Note> queryNoteByContent(String content){
        Cursor cursor = db.query(NoteDatabase.TABLE_NAME, columns,
                NoteDatabase.CONTENT + " LIKE ? ",
                new String[] { "%" + content + "%" }, null, null, null);
        List<Note> notes = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Note note = new Note();
                note.setId(cursor.getLong(cursor.getColumnIndex(NoteDatabase.ID)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NoteDatabase.CONTENT)));
                note.setTime(cursor.getString(cursor.getColumnIndex(NoteDatabase.TIME)));
                note.setTag(cursor.getInt(cursor.getColumnIndex(NoteDatabase.MODE)));
                notes.add(note);
            }
        }
        return notes;
    }
//    // 根据条件模糊查询数据库数据
//    public ArrayList<String> query(int top_int, String... str) {
//        ArrayList<String> result_list = new ArrayList<String>();
//        mDatabase = getReadableDatabase();
//        //模糊查询的三种方式：
///*
// * 全部查询
//        String current_sql_sel = "SELECT  * FROM " + tab_name;
//        Cursor c = mDatabase.rawQuery(current_sql_sel, null);*/
//
//        //1.使用这种query方法%号前不能加' ;
//        Cursor c_test = mDatabase.query(tab_name, new String[]{tab_field02}, tab_field02+"  LIKE ? ",
//                new String[] { "%" + str[0] + "%" }, null, null, null);
//
//        //2.使用这种query方法%号前必须加'  ;
//        //  Cursor  c_test=mDatabase.query(tab_name, new String[]{tab_field02},tab_field02+"  like '%" + str[0] + "%'", null, null, null, null);
//
//        //3.使用这种方式必须在%号前加'  ;
//        String current_sql_sel = "SELECT  * FROM "+tab_name +" where "+tab_field02+" like '%"+str[0]+"%'";
//        //Cursor c_test = mDatabase.rawQuery(current_sql_sel, null);
//
//        Log.e("tag", "查询完成...");
//        while (c_test.moveToNext()) {
//            String name = c_test.getString(c_test.getColumnIndex(tab_field02));
//            //name.contains(str[0]);
//            // 让集合中的数据不重复;
//            if (!result_list.contains(name)) {
//                result_list.add(name);
//                Log.e("tag", name);
//            }
//        }
//        c_test.close();
//
//        return result_list;
//    }

}