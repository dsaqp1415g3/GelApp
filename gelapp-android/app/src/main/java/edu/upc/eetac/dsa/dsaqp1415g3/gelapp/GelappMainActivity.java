package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.AppException;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelappAPI;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Helado;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.HeladoCollection;

public class GelappMainActivity extends ListActivity {
    private final static String TAG = GelappMainActivity.class.toString();
    private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
            "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
            "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
            "augue", "purus" };

    private ArrayAdapter<String> adapter;

  /*  private HeladoAdapter adapter;
   ArrayList<Helado> heladosList; */

/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelapp_main);

        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("oriol", "oriol".toCharArray());
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);


        (new FetchHeladosTask()).execute();
    }

    private class FetchHeladosTask extends
            AsyncTask<Void, Void, HeladoCollection> {
        private ProgressDialog pd;

        @Override
        protected HeladoCollection doInBackground(Void... params) {
            HeladoCollection helados = null;
            try {
                helados = GelappAPI.getInstance(GelappMainActivity.this)
                        .getHelados();
            } catch (AppException e) {
                e.printStackTrace();
            }
            return helados;
        }

        @Override
        protected void onPostExecute(HeladoCollection result) {
            ArrayList<Helado> helados = new ArrayList<Helado>(result.getHelados());
            for (Helado h : helados) {
                Log.d(TAG, h.getHeladoid() + "-" + h.getNombreHelado());
            }
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(GelappMainActivity.this);
            pd.setTitle("Searching...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }



    }

   /* private void addHelados(HeladoCollection helados){
        heladosList.addAll(helados.getHelados());
        adapter.notifyDataSetChanged();
    }
*/

}