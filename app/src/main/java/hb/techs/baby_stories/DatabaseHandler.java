package hb.techs.baby_stories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHandler";

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "stories_manager";
    private static final String TABLE_NAME = "stories";

    // Coloumn Names
    public static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_STORY = "story";
    private static final String KEY_POSITION = "position";


    // Coloumn Combinations
    private static final String[] COLS_ID_TITLE_STORY = new String[]
            {KEY_ID,KEY_TITLE, KEY_STORY,KEY_POSITION};


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_storyItemS_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"+", "
                + KEY_TITLE + " TEXT NOT NULL"+ ", "
                + KEY_STORY + " TEXT NOT NULL"+ ", "
                + KEY_POSITION + " INTEGER NOT NULL"
                + ")";

        Log.d(TAG,CREATE_storyItemS_TABLE);

        db.execSQL(CREATE_storyItemS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;

        Log.d(TAG,DROP_TABLE);

        db.execSQL(DROP_TABLE);

        onCreate(db);

    }

    //CRUD OPERATIONS

    public long addstoryItem(storyItem storyItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, storyItem.getTitle());
        values.put(KEY_STORY, storyItem.getStory());
        values.put(KEY_POSITION, storyItem.getImageId());


        // insert row
        long id = db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;

    }

    public storyItem getstoryItem(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, COLS_ID_TITLE_STORY,KEY_ID +"=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(c != null){
            c.moveToFirst();
        }
        db.close();

        Log.d(TAG,"Get storyItem Result "+ c.getString(0)+","+c.getString(1)+","+c.getString(2));
        storyItem storyItem = new storyItem(Integer.parseInt(c.getString(0))
                ,c.getString(1),c.getString(2),Integer.parseInt(c.getString(3)));
        return storyItem;
    }

    public List<storyItem> getAllStories() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<storyItem> storyItemList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, COLS_ID_TITLE_STORY,null,
                null,null,null,null);
        if(cursor!= null && cursor.moveToFirst()){

            do{
                storyItem storyItem = new storyItem();
                storyItem.setId(Integer.parseInt(cursor.getString(0)));
                storyItem.setTitle(cursor.getString(1));
                storyItem.setStory(cursor.getString(2));
                storyItem.setImageId(Integer.parseInt(cursor.getString(3)));

                storyItemList.add(storyItem);

            }while (cursor.moveToNext());


        }
        db.close();
        return storyItemList;

    }
public boolean containStory (String title )
{
    SQLiteDatabase db = this.getReadableDatabase();
    boolean k =false ;
    Cursor c = db.query(TABLE_NAME, COLS_ID_TITLE_STORY,KEY_TITLE +"=?",
            new String[]{String.valueOf(title)},null,null,null,null);

    if(c != null){
       if ( c.getCount()>0 ){
           c.moveToFirst();
        k= c.getString(1).equals(title);
    }}
    db.close();
    return k;
}

    public int getStoriesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public void deleteStory(storyItem note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

}
