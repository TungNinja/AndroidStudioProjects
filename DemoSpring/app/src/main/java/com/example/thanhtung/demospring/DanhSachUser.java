package com.example.thanhtung.demospring;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DanhSachUser extends AppCompatActivity {

    ListView lvUser;

    ArrayList<User> mangUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_user);

        lvUser = (ListView) findViewById(R.id.listViewUser);
        mangUser = new ArrayList<User>();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LoadUser().execute("https://api.github.com/users");
            }
        });
    }

    private class LoadUser extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... params) {
            return GET_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray array = new JSONArray(s);

                for (int i = 0; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    mangUser.add(new User(
                            object.getInt("id"),
                            object.getString("name"),
                            object.getString("createdDate")
                    ));
                }

                UserAdapter adapter = new UserAdapter(
                        DanhSachUser.this,
                        R.layout.dong_user,
                        mangUser
                );

                lvUser.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static String GET_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}
