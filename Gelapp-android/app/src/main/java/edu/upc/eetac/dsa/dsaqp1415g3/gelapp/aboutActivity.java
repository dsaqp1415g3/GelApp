package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by marc on 14/06/15.
 */
public class aboutActivity extends Activity {
    private final static String TAG = aboutActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.about);
    }

    public void cancel (View v) {

        finish();
    }

    public void support (View v) {
        Toast.makeText(getApplicationContext(), "support@gelapp.com", Toast.LENGTH_LONG).show();
    }

}
