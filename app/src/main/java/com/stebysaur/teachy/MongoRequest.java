package com.stebysaur.teachy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;


public class MongoRequest {

    ProgressDialog progressDialog;
    public static final String MONGOLAB = "mongodb://stebysaur:ppdk123@ds059692.mongolab.com:59692/teachy-app";
    public static final String DB_NAME = "teachy-app";
    public static final String USERS_COLLECTION = "users";

    public MongoRequest(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing request");
        progressDialog.setMessage("Please wait...");
    }


    public void loginUserInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new LoginUserAsyncTask(user, userCallback).execute();
    }


    public class LoginUserAsyncTask extends AsyncTask<User, Void, User> {
        User user;
        GetUserCallback userCallback;

        public LoginUserAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }


        @Override
        protected User doInBackground(User... params) {

            MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGOLAB));
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION);

            Document findDoc = new Document("email", user.email)
                    .append("password", user.password);

            FindIterable<Document> iterable = collection.find(findDoc);


            if (iterable.first() == null) {
                Log.d("LOGIN QUERY", "NULL USER");
                return null;
            } else {
                Document retDoc = iterable.first();

                return new User(retDoc.getString("name"),
                        user.email, user.password, retDoc.getString("school"),
                        retDoc.getString("phone"), retDoc.getBoolean("isTeacher"));
            }
        }


        @Override
        protected void onPostExecute(User user) {
            progressDialog.dismiss();
            userCallback.done(user);

            super.onPostExecute(user);
        }

    }


    public void registerUserInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new RegisterUserAsyncTask(user, userCallback).execute();
    }


    public class RegisterUserAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallback;


        public RegisterUserAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }


        @Override
        protected Void doInBackground(Void... params) {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGOLAB));
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION);

            Document doc = new Document("name", user.name)
                    .append("email", user.email)
                    .append("password", user.password)
                    .append("school", user.school)
                    .append("phone", user.phone)
                    .append("isTeacher", false);

            collection.insertOne(doc);

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
