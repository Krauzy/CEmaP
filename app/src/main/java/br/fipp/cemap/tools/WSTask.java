package br.fipp.cemap.tools;

import android.os.AsyncTask;

public class WSTask extends AsyncTask <String, String, String> {
    @Override
    protected String doInBackground(String ...strings) {
        return AcessWSTask.getAPI(strings[0]);
    }
}
