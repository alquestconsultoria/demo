package br.com.alquest.alquestdemo.app.Connections;

import br.com.alquest.alquestdemo.app.Activities.LoginActivity;
import br.com.alquest.alquestdemo.app.Config.Global;

/**
 * Created by bloodkiller on 5/29/14.
 */

//LoginActivity = 1
public class ActivityConnector {

    public static void success(int fromactivity) {
        switch (fromactivity) {
            case 1:
                LoginActivity.success();
                break;
        }
    }
}
