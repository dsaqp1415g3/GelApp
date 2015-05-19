package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

/**
 * Created by marc on 15/05/15.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

//import com.google.gson.Gson;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.AppException;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelappAPI;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Helado;

public class CreateGelappActivity extends Activity {
    private final static String TAG = CreateGelappActivity.class.getName();

    private class PostStingTask extends AsyncTask<String, Void, Helado> {
        private ProgressDialog pd;

        @Override
        protected Helado doInBackground(String... params) {
            Helado helado = null;
            try {
                helado = GelappAPI.getInstance(CreateGelappActivity.this)
                        .createHelado(Integer.parseInt(params[0]), params[1], params[2], params[3], params[4], params[5], params[6]);
            } catch (AppException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return helado;
        }

        @Override
        protected void onPostExecute(Helado result) {
            showHelados(result);
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(CreateGelappActivity.this);

            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_gelapp_layout);

    }

    public void cancel(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void postSting(View v) {
        EditText etSubject = (EditText) findViewById(R.id.etSubject);
        EditText etContent = (EditText) findViewById(R.id.etContent);

        String subject = etSubject.getText().toString();
        String content = etContent.getText().toString();

        (new PostStingTask()).execute(subject, content);
    }

    private void showHelados(Helado result) {
        //String json = new Gson().toJson(result);
        Bundle data = new Bundle();
        //data.putString("json-sting", json);
        Intent intent = new Intent();
        intent.putExtras(data);
        setResult(RESULT_OK, intent);
        finish();
    }

}