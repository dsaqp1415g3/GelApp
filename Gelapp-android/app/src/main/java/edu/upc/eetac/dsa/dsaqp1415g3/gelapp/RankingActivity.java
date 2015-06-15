package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.AppException;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelappAPI;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Helado;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.HeladoCollection;

/**
 * Created by marc on 8/06/15.
 */
public class RankingActivity extends ListActivity implements AdapterView.OnItemClickListener{

    private String UserName;
    private final static String TAG = GelappMainActivity.class.toString();
    private ListView listView;
    private DrawerLayout drawerLayout;
    MediaPlayer mediaPlayer;

    private HeladoRankingAdapter adapter;
    ArrayList<Helado> heladosList;
    ////////////Botón flotante
    ImageButton imageButton;
    private static final String[] values = {"Crear Helado", "Lista de Helados", "About", "Log Out"};



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelapp_main);  //Cargo la lista en el main principal

        //Escribo el título del layout Ranking
        TextView tituloMain = (TextView) findViewById(R.id.tituloMain);
        tituloMain.setText("RANKING <Top 5>");

        heladosList = new ArrayList<Helado>();
        adapter = new HeladoRankingAdapter(this, heladosList);
        setListAdapter(adapter);


        getActionBar().setDisplayHomeAsUpEnabled(true);
        ///////////////LOGIN
        SharedPreferences prefs = getSharedPreferences("gelapp-profile",
                Context.MODE_PRIVATE);
        final String username = prefs.getString("username", null);
        final String password = prefs.getString("password", null);
        final int usernameid = prefs.getInt("usuarioid",0);
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password
                        .toCharArray());
            }
        });
        UserName=prefs.getString("username",null);//Envio el nombre del usuario para ponerlo en la barra de arriba

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Hi " +UserName);
////////////////
        listView = (ListView) findViewById(R.id.menu_lateral);
        drawerLayout = (DrawerLayout) findViewById(R.id.ContenedorPrincipal);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values));
        listView.setOnItemClickListener(this);//cuando pulsa una opción de la lista

        ///////Botón flotante
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RankingActivity.this,CreateGelappActivity.class);
                startActivity(i);
            }
        });



        (new FetchHeladosTask()).execute();
    }

    private class FetchHeladosTask extends
            AsyncTask<Void, Void, HeladoCollection> {
        private ProgressDialog pd;

        @Override
        protected HeladoCollection doInBackground(Void... params) {
            HeladoCollection helados = null;
            try {
                helados = GelappAPI.getInstance(RankingActivity.this)
                        .getRankingHelados();
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
            pd = new ProgressDialog(RankingActivity.this);
            pd.setTitle("Searching...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

        private void addHelados(HeladoCollection helados) {
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
    //@Override

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Fragment fragment = null;

        if (position == 0) {

            Intent intent = new Intent(this, CreateGelappActivity.class);
            startActivity(intent);
            //cierro este layout para q no se acumule
            finish();
        }

        if (position == 1) {

            Intent intent = new Intent(this, GelappMainActivity.class);
            startActivity(intent);
            //cierro este layout para q no se acumule
            finish();

        }

        if (position == 3) {

            //Elimino usuario y contraseña en local para no volverme a loggear automaticamente
            SharedPreferences prefs = getSharedPreferences("gelapp-profile",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.remove("username");
            editor.remove("password");
            boolean done = editor.commit();
            if (done) {
                Log.d(TAG, "preferences deleted");
            }
            else {
                Log.d(TAG, "preferences not deleted. THIS A SEVERE PROBLEM");
            }


            //Como me he deslogueado inicio el layout del Login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

        if (position == 2) {
            Intent intent = new Intent(this, aboutActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawers();

    }



    /*
    //Ap pulsar botón HOME
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(listView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(listView);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gelapp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.refresh:
                //metodo refresh
                Intent intent = new Intent(this, RankingActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.menu:

                intent = new Intent(this, GelappMainActivity.class);
                startActivity(intent);
                finish();

                return true;

            case R.id.about:

                intent = new Intent(this, aboutActivity.class);
                startActivity(intent);

                return true;

            case R.id.logOut:
                //Elimino usuario y contraseña en local para no volverme a loggear automaticamente
                SharedPreferences prefs = getSharedPreferences("gelapp-profile",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.remove("username");
                editor.remove("password");
                boolean done = editor.commit();
                if (done) {
                    Log.d(TAG, "preferences deleted");
                }
                else {
                    Log.d(TAG, "preferences not deleted. THIS A SEVERE PROBLEM");
                }


                //Como me he deslogueado inicio el layout del Login
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
