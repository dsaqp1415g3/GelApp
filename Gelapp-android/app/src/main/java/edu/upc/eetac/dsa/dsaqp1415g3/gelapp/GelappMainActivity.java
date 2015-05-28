package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.AppException;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelappAPI;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Helado;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.HeladoCollection;

public class GelappMainActivity extends ListActivity implements AdapterView.OnItemClickListener{

////////////
    private DrawerLayout drawerLayout;
    private ListView listView;
    private static final String[] values = {"Crear Helado", "Opcion 2", "Opcion 3", "Parar música"};
////////////

    private final static String TAG = GelappMainActivity.class.toString();

    private HeladoAdapter adapter;
    ArrayList<Helado> heladosList;

    MediaPlayer mediaPlayer;

/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelapp_main);
////////
        getActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.menu_lateral);
        drawerLayout = (DrawerLayout) findViewById(R.id.ContenedorPrincipal);


        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values));
        listView.setOnItemClickListener(this);//cuando pulsa una opción de la lista
////////

        heladosList = new ArrayList<Helado>();
        adapter = new HeladoAdapter(this, heladosList);
        setListAdapter(adapter);


        /*
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("oriol", "oriol".toCharArray());
            }
        });
        */

        //Poner musica de fondo (se añade la canción en la carpeta res>raw)
/*
        mediaPlayer = MediaPlayer.create(this,R.raw.sneakysnitch);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
*/

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
            addHelados(result);
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

        private void addHelados(HeladoCollection helados){
            heladosList.addAll(helados.getHelados());
            adapter.notifyDataSetChanged();
        }

    }

    //Si pulsamos un objeto de la lista se nos abre el helado_detail_layout
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Helado helado = heladosList.get(position);
        Log.d(TAG, helado.getLinks().get("heladoid").getTarget()); //pones el "rel" del link para seleccionar el link correcto
        Intent intent = new Intent(this, HeladoDetailActivity.class);
        intent.putExtra("url", helado.getLinks().get("heladoid").getTarget());
        startActivity(intent);


    }

    ////////////Al pulsar una opción del drawer lateral
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Fragment fragment = null;

        if (position == 0) {

            Intent intent = new Intent(this, CreateGelappActivity.class);
            startActivity(intent);
        }

        if (position == 1) {


        }

        if (position == 2) {

        }

        if (position == 3) {
            mediaPlayer.stop();
            Toast.makeText(this, "Music OFF", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawers();


    }



    //Ap pulsar botón HOME
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(listView)){
                    drawerLayout.closeDrawers();
                }else{
                    drawerLayout.openDrawer(listView);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
