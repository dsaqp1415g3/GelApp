package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

/**
 * Created by marc on 31/05/15.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.AppException;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelappAPI;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.User;

public class LoginActivity extends Activity {
    private final static String TAG = LoginActivity.class.getName();
    MediaPlayer mediaPlayer;

    private class PostUserLoginTask extends AsyncTask<String, Void, User> {
        private ProgressDialog pd;

        @Override
        protected User doInBackground(String... params) {
            User user = null;
            try {
                user = GelappAPI.getInstance(LoginActivity.this).loginUser(params[0], params[1]);

            } catch (AppException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return user;
        }

        @Override
        protected void onPostExecute(User result) {

            //showUser(result);
            if (result != null) {
                if (result.getLoginSuccesful() == true) {
                    //Log.d(TAG, "preferences set");

                    //Guardo usuario y contraseña en local para no volverme a loggear
                    SharedPreferences prefs = getSharedPreferences("gelapp-profile",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.clear();
                    editor.putString("username", result.getUsername());
                    editor.putString("password", result.getPassword());
                    editor.putInt("usuarioid", result.getUsuarioid());
                    boolean done = editor.commit();
                    if (done) {
                        Log.d(TAG, "preferences set");
                    }
                    else {
                        Log.d(TAG, "preferences not set. THIS A SEVERE PROBLEM");
                    }

                    //Como he accedido correctamente accedo a la app
                    startGelappActivity();

                } else if (result.getUsuarioid()!= 0 && result.getLoginSuccesful() == false) {
                    //Log.d(TAG, "preferences not set. THIS A SEVERE PROBLEM");

                    EditText etNombre = (EditText) findViewById(R.id.etUsername);
                    etNombre.setText(result.getUsername());
                    //aviso que tiene mal escrito el password
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
                    startLoginActivity();
                }

                else {
                    Toast.makeText(getApplicationContext(), "User do not exist, Register it!", Toast.LENGTH_LONG).show();
                    startLoginActivity();
                }
                //pd.dismiss();
            }

            else {
                Toast.makeText(getApplicationContext(), "Unexpected Error", Toast.LENGTH_LONG).show();
                startLoginActivity();
            }

        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(LoginActivity.this);

            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        SharedPreferences prefs = getSharedPreferences("gelapp-profile",
                Context.MODE_PRIVATE);
        String username = prefs.getString("username", null);
        String password = prefs.getString("password", null);
        int usuarioid = prefs.getInt("usuarioid", 0);

        //Esto permite que no te tengas que volver a logear, se queda guardado en local user y password
        if ((username != null) && (password != null)) {
            Intent intent = new Intent(this, RankingActivity.class);
            startActivity(intent);
            finish();

            /*
            //Activo la musica de fondo (se añade la canción en la carpeta res>raw)
            mediaPlayer = MediaPlayer.create(this, R.raw.helados);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(100,100);
            mediaPlayer.start();
            */
        }


        setContentView(R.layout.login_layout);

        /////Oculta el teclado al iniciar el layout para que sea el usuario el que de click en el edit text
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    public void signIn(View v) {

        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();


        if (username.length()>=1 && password.length()>=1) {

            //Comprovación del login con la API
            (new PostUserLoginTask()).execute(username, password);
        }
        else {
            Toast.makeText(getApplicationContext(), "Fill all data", Toast.LENGTH_SHORT).show();
        }

    }

    private void showUser(User result) {
        String json = new Gson().toJson(result);
        Bundle data = new Bundle();
        data.putString("json-user", json);
        Intent intent = new Intent();
        intent.putExtras(data);
        setResult(RESULT_OK, intent);
        finish();
    }



    private void startGelappActivity() {
        Intent intent = new Intent(this, RankingActivity.class); //Elijo en que vista se iniciará la app
        startActivity(intent);
        finish();
    }

    public void register (View v) {

        startGelappRegister();
    }

    private void startGelappRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }



}