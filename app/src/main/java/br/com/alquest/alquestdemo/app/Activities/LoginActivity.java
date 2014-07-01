package br.com.alquest.alquestdemo.app.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import br.com.alquest.alquestdemo.app.Config.Global;
import br.com.alquest.alquestdemo.app.Connections.RequestHttp;
import br.com.alquest.alquestdemo.app.R;

public class LoginActivity extends Activity {

    private static Context mContext;
    private static List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
    private EditText username_field;
    private EditText password_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        username_field = (EditText) findViewById(R.id.userfield);
        password_field = (EditText) findViewById(R.id.passfield);

        password_field.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login(v);
                }
                return false;
            }
        });

        username_field.requestFocus();
    }

    public void login(View view) {
        parameters.clear();
        if (isEmpty(username_field) && isEmpty(password_field)) {
            Toast.makeText(this, R.string.invalidusername, Toast.LENGTH_SHORT).show();
        } else {
            parameters.add(new BasicNameValuePair("data[Mobile][username]", username_field.getText().toString()));
            parameters.add(new BasicNameValuePair("data[Mobile][password]", password_field.getText().toString()));
            parameters.add(new BasicNameValuePair("data[Mobile][token]", Global.getToken()));
            new RequestHttp(Global.getLoginActivityId(), this, parameters).execute("login");
        }
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().length() == 0;
    }

    public static void success() {
        Intent main = new Intent(mContext, MainActivity.class);
        mContext.startActivity(main);
    }
}




