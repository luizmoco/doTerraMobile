package com.luiz.moco.doterra;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFDownloadTask extends AsyncTask<String, Void, InputStream> {

    private OnDownloadCompleteListener listener;

    public PDFDownloadTask(OnDownloadCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected InputStream doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return connection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        super.onPostExecute(inputStream);
        if (listener != null) {
            listener.onDownloadComplete(inputStream);
        }
    }

    public interface OnDownloadCompleteListener {
        void onDownloadComplete(InputStream inputStream);
    }
}

