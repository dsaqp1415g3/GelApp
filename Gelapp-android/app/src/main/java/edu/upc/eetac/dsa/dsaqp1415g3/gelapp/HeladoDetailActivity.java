package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

/**
 * Created by marc on 15/05/15.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.AppException;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelappAPI;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Helado;


public class HeladoDetailActivity extends Activity {
    private final static String TAG = HeladoDetailActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helado_detail_layout);
        String urlHelado = (String) getIntent().getExtras().get("url");
        (new FetchHeladoTask()).execute(urlHelado);
    }

    private void loadHelado(Helado helado) {

        TextView tvNombreHelado = (TextView) findViewById(R.id.tvNombreHelado);
        TextView tvCapa1 = (TextView) findViewById(R.id.tvDetailCapa1);
        TextView tvCapa2 = (TextView) findViewById(R.id.tvDetailCapa2);
        TextView tvCapa3 = (TextView) findViewById(R.id.tvDetailCapa3);
        TextView tvCapa4 = (TextView) findViewById(R.id.tvDetailCapa4);
        TextView tvCapa5 = (TextView) findViewById(R.id.tvDetailCapa5);
        TextView tvDetailAutor = (TextView) findViewById(R.id.tvDetailAutor);

        tvNombreHelado.setText(helado.getNombreHelado());
        tvCapa1.setText(helado.getCapa1Topping());
        tvCapa2.setText(helado.getCapa2Helado());
        tvCapa3.setText(helado.getCapa3Topping());
        tvCapa4.setText(helado.getCapa4Helado());
        tvCapa5.setText(helado.getCapa5Topping());
        tvDetailAutor.setText(helado.getAutor());

    }

    private class FetchHeladoTask extends AsyncTask<String, Void, Helado> {
        private ProgressDialog pd;

        @Override
        protected Helado doInBackground(String... params) {
            Helado helado = null;
            try {
                helado = GelappAPI.getInstance(HeladoDetailActivity.this)
                        .getHelado(params[0]);
            } catch (AppException e) {
                Log.d(TAG, e.getMessage(), e);
            }
            return helado;
        }

        @Override
        protected void onPostExecute(Helado result) {
            loadHelado(result);
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(HeladoDetailActivity.this);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }

}