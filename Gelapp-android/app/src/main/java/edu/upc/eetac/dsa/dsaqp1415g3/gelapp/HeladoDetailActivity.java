package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

/**
 * Created by marc on 15/05/15.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.AppException;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelappAPI;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Helado;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Voto;


public class HeladoDetailActivity extends Activity {
    private final static String TAG = HeladoDetailActivity.class.getName();

    private String usernameReg;
    private String passwordReg;
    private String usernameHel;

    private String usuario_id;
    private String helado_id;
    private String votos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Escondo la barra de acción
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.helado_detail_layout);
        String urlHelado = (String) getIntent().getExtras().get("url");

        (new FetchHeladoTask()).execute(urlHelado);

        //Cojo los datos del usuario loggeado
        SharedPreferences prefs = getSharedPreferences("gelapp-profile", Context.MODE_PRIVATE);
        usernameReg = prefs.getString("username", null);
        passwordReg = prefs.getString("password", null);

        final int usernameid = prefs.getInt("usuarioid",0); //recojo el usuario id por si ejecuto la función "votar"
        usuario_id = Integer.toString(usernameid);

    }

    private void loadHelado(Helado helado) {

        //Envio la id del helado por si posteriormente ejecuta la función votar.
        this.helado_id=Integer.toString(helado.getHeladoid());
        //Guardo el voto actual +1 por si vota, poner el valor de votos que tiene+1
        this.votos=Integer.toString(helado.getVotos()+1);


        //añado los diferentes campos a mis textviews del layout
        TextView tvNombreHelado = (TextView) findViewById(R.id.tvNombreHelado);
        TextView tvCapa1 = (TextView) findViewById(R.id.tvDetailCapa1);
        TextView tvCapa2 = (TextView) findViewById(R.id.tvDetailCapa2);
        TextView tvCapa3 = (TextView) findViewById(R.id.tvDetailCapa3);
        TextView tvCapa4 = (TextView) findViewById(R.id.tvDetailCapa4);
        TextView tvCapa5 = (TextView) findViewById(R.id.tvDetailCapa5);
        TextView tvDetailAutor = (TextView) findViewById(R.id.tvDetailAutor);
        TextView tvVotos = (TextView) findViewById(R.id.tvVotos);
        TextView tvFechaCreacion = (TextView) findViewById(R.id.tvFechaCreacion);
        TextView tvIDHelado = (TextView) findViewById(R.id.tvIDHelado);



        tvNombreHelado.setText(helado.getNombreHelado());
        tvCapa1.setText(helado.getCapa1Topping());
        tvCapa2.setText(helado.getCapa2Helado());
        tvCapa3.setText(helado.getCapa3Topping());
        tvCapa4.setText(helado.getCapa4Helado());
        tvCapa5.setText(helado.getCapa5Topping());
        tvDetailAutor.setText(helado.getAutor());
        tvVotos.setText(Integer.toString(helado.getVotos()));
        tvFechaCreacion.setText(SimpleDateFormat.getInstance().format(helado.getCreationTimestamp()));
        tvIDHelado.setText("helado ID: "+Integer.toString(helado.getHeladoid()));

        ///Creamos los colores de las capas
        String capa1 = helado.getCapa1Topping();
        String capa2 = helado.getCapa2Helado();
        String capa3 = helado.getCapa3Topping();
        String capa4 = helado.getCapa4Helado();
        String capa5 = helado.getCapa5Topping();

        TextView ColorC1= (TextView) findViewById(R.id.ColorCapa1);
        TextView ColorC2= (TextView) findViewById(R.id.ColorCapa2);
        TextView ColorC3= (TextView) findViewById(R.id.ColorCapa3);
        TextView ColorC4= (TextView) findViewById(R.id.ColorCapa4);
        TextView ColorC5= (TextView) findViewById(R.id.ColorCapa5);



        if (capa1.equals("caramelo"))
        {ColorC1.setBackgroundColor(Color.parseColor("#FF8000"));}

        if (capa1.equals("chocolate_blanco"))
        {ColorC1.setBackgroundColor(Color.parseColor("#E6E6E6"));}

        if (capa1.equals("chocolate_negro"))
        {ColorC1.setBackgroundColor(Color.parseColor("#2A0A0A"));}

        if (capa1.equals("sirope_fresa"))
        {ColorC1.setBackgroundColor(Color.parseColor("#FF0080"));}

        if (capa1.equals("multicolor"))
        {ColorC1.setBackgroundColor(Color.parseColor("#00FFFF"));}


        if (capa3.equals("caramelo"))
        {ColorC3.setBackgroundColor(Color.parseColor("#FF8000"));}

        if (capa3.equals("chocolate_blanco"))
        {ColorC3.setBackgroundColor(Color.parseColor("#E6E6E6"));}

        if (capa3.equals("chocolate_negro"))
        {ColorC3.setBackgroundColor(Color.parseColor("#2A0A0A"));}

        if (capa3.equals("sirope_fresa"))
        {ColorC3.setBackgroundColor(Color.parseColor("#FF0080"));}

        if (capa3.equals("multicolor"))
        {ColorC3.setBackgroundColor(Color.parseColor("#00FFFF"));}


        if (capa5.equals("caramelo"))
        {ColorC5.setBackgroundColor(Color.parseColor("#FF8000"));}

        if (capa5.equals("chocolate_blanco"))
        {ColorC5.setBackgroundColor(Color.parseColor("#E6E6E6"));}

        if (capa5.equals("chocolate_negro"))
        {ColorC5.setBackgroundColor(Color.parseColor("#2A0A0A"));}

        if (capa5.equals("sirope_fresa"))
        {ColorC5.setBackgroundColor(Color.parseColor("#FF0080"));}

        if (capa5.equals("multicolor"))
        {ColorC5.setBackgroundColor(Color.parseColor("#00FFFF"));}


        if (capa2.equals("fresa"))
        {ColorC2.setBackgroundColor(Color.parseColor("#FF0000"));}

        if (capa2.equals("nata"))
        {ColorC2.setBackgroundColor(Color.parseColor("#F5F6CE"));}

        if (capa2.equals("vainilla"))
        {ColorC2.setBackgroundColor(Color.parseColor("#F4FA58"));}

        if (capa2.equals("chocolate"))
        {ColorC2.setBackgroundColor(Color.parseColor("#8A2908"));}

        if (capa2.equals("turron"))
        {ColorC2.setBackgroundColor(Color.parseColor("#FFBF00"));}


        if (capa4.equals("fresa"))
        {ColorC4.setBackgroundColor(Color.parseColor("#FF0000"));}

        if (capa4.equals("nata"))
        {ColorC4.setBackgroundColor(Color.parseColor("#F5F6CE"));}

        if (capa4.equals("vainilla"))
        {ColorC4.setBackgroundColor(Color.parseColor("#F4FA58"));}

        if (capa4.equals("chocolate"))
        {ColorC4.setBackgroundColor(Color.parseColor("#8A2908"));}

        if (capa4.equals("turron"))
        {ColorC4.setBackgroundColor(Color.parseColor("#FFBF00"));}

    }

    private class FetchHeladoTask extends AsyncTask<String, Void, Helado> {
        private ProgressDialog pd;

        @Override
        protected Helado doInBackground(String... params) {
            Helado helado = null;
            try {
                helado = GelappAPI.getInstance(HeladoDetailActivity.this).getHelado(params[0]);
            } catch (AppException e) {
                Log.d(TAG, e.getMessage(), e);
            }
            return helado;

        }

        @Override
        protected void onPostExecute(Helado result) {
            loadHelado(result);

            usernameHel = result.getAutor().toString();
            if (pd != null) {
                pd.dismiss();
            }
                //Si el usuario loggeado no es el creador del helado que ve, aparece el botón "vote" en vez de "delete"
                if (!usernameReg.equals(usernameHel)) {
                    Button btn = (Button) findViewById(R.id.bDelete);
                    btn.setBackgroundResource(R.drawable.button_default_green);
                    btn.setText("Vote");
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
/////////BOTON DELETE / VOTE
    public void delete (View v){

        if (usernameReg.equals(usernameHel))//SI SON IGUALES EL BOTON ES DELETE
        {
            String urlHelado = (String) getIntent().getExtras().get("url");
            //envio para hacer el delete nombre y password
            (new DeleteHeladoTask()).execute(urlHelado);

            //Como se ha borrado, cargo la lista de helados para q ya no aparezca
            Intent intent = new Intent(this, GelappMainActivity.class);
            startActivity(intent);



        }
        else {//SI USUARIOS DIFERENTES EL BOTON ES VOTE

         //Funcion para votar
            (new PostVoteTask()).execute(usuario_id,helado_id);


        }
    }

//////////BOTON COMPRAR
    public void buy (View v){

        Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();

    }



//Funcion delete
    private class DeleteHeladoTask extends AsyncTask<String, Void, Helado> {
        private ProgressDialog pd;

        @Override
        protected Helado doInBackground(String... params) {
            Helado helado = null;
            try {
                helado = GelappAPI.getInstance(HeladoDetailActivity.this).deleteHelado(params[0]);
            } catch (AppException e) {
                Log.d(TAG, e.getMessage(), e);
            }
            return helado;

        }


        @Override
        protected void onPostExecute(Helado result) {
            //showHelados(result);
            if (pd != null) {
                pd.dismiss();
            }
            Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();

            //Como ya está borrado, cargo el layout ranking de nuevo para q se actualicen los layouts y ya no
            //aparezca este helado

            finish();
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(HeladoDetailActivity.this);

            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }
    }

/*
    private void showHelados(Helado result) {
        String json = new Gson().toJson(result);
        Bundle data = new Bundle();
        data.putString("json-sting", json);
        Intent intent = new Intent();
        intent.putExtras(data);
        setResult(RESULT_OK, intent);
        finish();
    }
*/



    //Votar

    private class PostVoteTask extends AsyncTask<String, Void, Voto> {
        private ProgressDialog pd;

        @Override
        protected Voto doInBackground(String... params) {
            Voto voto = null;
            try {
                voto = GelappAPI.getInstance(HeladoDetailActivity.this).VoteHelado(Integer.parseInt(params[0]), Integer.parseInt(params[1]));

            } catch (AppException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return voto;
        }

        @Override
        protected void onPostExecute(Voto result) {

            if (result.getId_helado()!=0 && result.getId_usuario()!=0)
            {
                Toast.makeText(getApplicationContext(), "Voto correcto", Toast.LENGTH_SHORT).show();
                TextView tvVotos = (TextView) findViewById(R.id.tvVotos);
                tvVotos.setText(votos);//meto la variable votos para actualizar el layout, que era los votos +1

                if (pd != null) {
                    pd.dismiss();
                }

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Ya has votado este helado", Toast.LENGTH_SHORT).show();
                if (pd != null) {
                    pd.dismiss();
                }

            }

        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(HeladoDetailActivity.this);

            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }


    }



}