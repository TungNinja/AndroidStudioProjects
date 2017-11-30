package com.example.admin.lazada.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by ADMIN on 11/30/2017.
 */

public class DownloadJSON extends AsyncTask<String, Void, String> {

    String url;
    List<Map<String, String>> atrrs;
    StringBuilder data;

    public DownloadJSON(String url) {
        this.url = url;
    }

    public DownloadJSON(String url, List<Map<String, String>> atrrs) {
        this.url = url;
        this.atrrs = atrrs;
    }

    @Override
    protected String doInBackground(String... strings) {
        String strData = "";
        try {
            URL urlConnect = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnect.openConnection();

            if (atrrs != null && atrrs.size() > 0){
                strData = methodPost(httpURLConnection);
            }else {
                strData = methodGet(httpURLConnection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("data: ", strData);

        return strData;
    }

    private String methodGet(HttpURLConnection httpURLConnection){
        String strData = "";

        InputStream inputStream = null;
        try {
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            data = new StringBuilder();
            String line = "";

            while ( (line = bufferedReader.readLine()) != null){
                data.append(line);
            }

            strData = data.toString();

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return strData;
    }

    private String methodPost(HttpURLConnection httpURLConnection){
        String strData = "";

        String key = "";
        String value = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();

            for (Map<String, String> map: atrrs ){

                for (Map.Entry<String, String> values : map.entrySet()){
                    key = values.getKey();
                    value = values.getValue();

                }

                builder.appendQueryParameter(key, value);
            }

            String query = builder.build().getEncodedQuery();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStreamWriter.close();
            outputStream.close();

            strData = methodGet(httpURLConnection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strData;
    }

}
