package com.example.commonbudgetmanager;

import android.os.AsyncTask;
import android.widget.Toast;

public class insertUserOperation extends AsyncTask<User, Object, String> {

    UserOperations listener;

    insertUserOperation(UserOperations listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(User... users){
        try{
            MyApplication.getAppDatabase().userDao().insertTransaction(users);
        }catch(Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onProgressUpdate(Object[] values){
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result){
        listener.insertUsers(result);
    }
}
