package br.com.alquest.alquestdemo.app.Connections;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.alquest.alquestdemo.app.Config.Global;
import br.com.alquest.alquestdemo.app.R;

public class RequestHttp extends AsyncTask<String, String, String> {

    private static List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
    static Context context;
    ProgressDialog progress;
    HttpParams httpParameters = new BasicHttpParams();
    int ReturnActivity;
    int timeoutConnection = Global.getTimeoutConnection();

    public RequestHttp(int fromactivity, Context from, List<NameValuePair> nameValuePairs) {
        parameters = nameValuePairs;
        context = from;
        ReturnActivity = fromactivity;
    }

    private static byte[] dumpInputStream(InputStream paramInputStream)
            throws IOException {
        byte[] arrayOfByte = new byte[32000];
        StringBuilder localStringBuffer = new StringBuilder();
        for (; ; ) {
            int i = paramInputStream.read(arrayOfByte);
            if (-1 == i) {
                return localStringBuffer.toString().getBytes();
            }
            localStringBuffer.append(new String(arrayOfByte, 0, i));
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setMessage(context.getString(R.string.sending));
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
    }

    @Override
    protected String doInBackground(String... aurl) {
        String result;
        String URL = Global.getUrl() + aurl[0];
        HttpPost httppost = new HttpPost(URL);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(parameters));
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutConnection);
            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpEntity localHttpEntity = httpClient.execute(httppost).getEntity();
            byte[] arrayOfByte = null;
            if (localHttpEntity != null) {
                arrayOfByte = dumpInputStream(localHttpEntity.getContent());
            }
            result = new String(arrayOfByte);
        } catch (ClientProtocolException e) {
            if (Global.debug()) {
                Log.e("DEBUG", e.toString());
            }
            result = "failed";
        } catch (IOException e) {
            if (Global.debug()) {
                Log.e("DEBUG", e.toString());
            }
            result = "failed";
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        progress.dismiss();
        String value = context.getString(R.string.generic_failure);
        String status = "false";
        if (Global.debug()) {
            Log.e("debug", result);
        }
        try {
            JSONObject jObj = new JSONObject(result);
            status = jObj.getString("return");
            if (!status.equals("true")) value = jObj.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
            alert(value);
        }
        if (status.equals("true")) {
            ActivityConnector.success(ReturnActivity);
        } else {
            alert(value);
        }
    }

    private void alert(String alert) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(alert)
                .setPositiveButton(context.getString(R.string.positive_action), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }
}