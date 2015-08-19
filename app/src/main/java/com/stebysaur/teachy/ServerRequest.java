package com.stebysaur.teachy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;

public class ServerRequest {

    ProgressDialog progressDialog;
    public static final int TIMEOUT = 5 * 1000;
    public static final String SERVER_ADDRESS = "http://steby.site40.net/";


    public ServerRequest(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing request");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new FetchUserDataAsyncTask(user, userCallback).execute();
    }


    public class FetchUserDataAsyncTask extends AsyncTask<User, Void, User> {
        User user;
        GetUserCallback userCallback;

        public FetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }


        @Override
        protected User doInBackground(User... params) {
            HashMap<String, String> toSend = new HashMap<>();
            User returnedUser = null;

            toSend.put("email", user.email);
            toSend.put("password", user.password);

            try {
                URL url = new URL(SERVER_ADDRESS + "fetch_user_data.php");
                HttpRequest request = new HttpRequest(url);

                JSONObject jsonObject = request.preparePost().withData(toSend).sendAndReadJSON();

                if(jsonObject.length() == 0) {
                    //user fail
                } else {
                    String name = jsonObject.getString("name");
                    String school = jsonObject.getString("school");
                    String phone = jsonObject.getString("phone");

                    returnedUser = new User(name, user.email, user.password, school, phone);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return returnedUser;
        }

        @Override
        protected void onPostExecute(User user) {
            progressDialog.dismiss();
            userCallback.done(user);

            super.onPostExecute(user);
        }
    }


    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }


        @Override
        protected Void doInBackground(Void... params) {
            HashMap<String, String> toSend = new HashMap<>();
            toSend.put("name", user.name);
            toSend.put("email", user.email);
            toSend.put("password", user.password);
            toSend.put("school", user.school);
            toSend.put("phone", user.phone);

            try {
                URL url = new URL(SERVER_ADDRESS + "register.php");

                HttpRequest request = new HttpRequest(url);
                request.preparePost().withData(toSend).send();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            userCallback.done(null);
        }
    }

}
