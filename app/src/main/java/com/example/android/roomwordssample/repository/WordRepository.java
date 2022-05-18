package com.example.android.roomwordssample.repository;



import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.roomwordssample.DAO.WordDao;
import com.example.android.roomwordssample.db.WordRoomDatabase;
import com.example.android.roomwordssample.entity.Word;

import java.util.List;



public class WordRepository {

    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;


    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }


    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private final WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    public void delete (Word word) {
        new deleteAsyncTask(mWordDao).execute(word);
    }

    private static class deleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private final WordDao mAsyncTaskDao;

        deleteAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.deleteId(params[0].getWord());
            return null;
        }
    }
}
