package br.fipp.cemap.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AcessWSTask {
    public static String getAPI(String urlws) {
        String info = "";
        try {
            URL url = new URL(urlws);
            int res;
            HttpURLConnection con;
            InputStream is;

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.connect();

            res = con.getResponseCode();
            if (res < HttpURLConnection.HTTP_BAD_REQUEST)
                is = con.getInputStream();
            else
                is = con.getErrorStream();
            info = AcessWSTask.getResponse(is);
            is.close();
            con.disconnect();
        } catch (Exception e) {
            info = "error: " + e.getMessage();
        }
        return info;
    }

    private static String getResponse(InputStream is) {
        StringBuffer buffer = new StringBuffer();
        try {
            String row;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((row = br.readLine()) != null)
                buffer.append(row);
            br.close();
        } catch (Exception e) {
            buffer.append("error:" + e.getMessage());
        }
        return buffer.toString();
    }
}
