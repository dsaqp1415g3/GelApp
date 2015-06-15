package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

/**
 * Created by marc on 31/05/15.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.AppException;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelappAPI;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.User;

public class RegisterActivity extends Activity {
    private final static String TAG = RegisterActivity.class.getName();

    private class PostUserRegisterTask extends AsyncTask<String, Void, User> {
        private ProgressDialog pd;

        @Override
        protected User doInBackground(String... params) {
            User user = null;
            try {
                user = GelappAPI.getInstance(RegisterActivity.this).registerUser(params[0], params[1]);

            } catch (AppException e) {
                Log.e(TAG, e.getMessage(), e);
                Toast.makeText(getApplicationContext(), "El usuario ya existe!", Toast.LENGTH_SHORT).show();
                user = null;
            }
            return user;
        }

        @Override
        protected void onPostExecute(User result) {

            //showUser(result);
            String user = result.getUsername();
            if (result != null && result.getLoginSuccesful()==false) {//cuando se ha registrado bien sale un false!

                    //Como he accedido correctamente accedo a la app
                    startLoginActivity();
                Toast.makeText(getApplicationContext(), "Register succesful", Toast.LENGTH_LONG).show();

            }

            else {
                Toast.makeText(getApplicationContext(), "Username already exist, try another", Toast.LENGTH_LONG).show();
                startRegisterActivity();
            }

        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(RegisterActivity.this);

            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Escondo la barra de acción
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        /////Oculta el teclado al iniciar el layout para que sea el usuario el que de click en el edit text
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        super.onCreate(savedInstanceState);

        /////Oculta el teclado al iniciar el layout para que sea el usuario el que de click en el edit text
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.register_layout);
    }

    public void done(View v) {//pulsamos el botón "create" del register

        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        EditText etPassword2 = (EditText)  findViewById(R.id.etPassword2);

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String password2 = etPassword2.getText().toString();

        if (username.length() >=1 && password.length() >=1 && password2.length()>=1) {
            if (password.equals(password2)) {
                //envio Post a la API para registrar o detectar errores
                (new PostUserRegisterTask()).execute(username, password);
            } else if (!password.equals(password2)) {
                Toast.makeText(getApplicationContext(), "Diferent passwords", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
            }

        }
        else
            Toast.makeText(getApplicationContext(), "Fill all data", Toast.LENGTH_SHORT).show();



    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        //Toast.makeText(getApplicationContext(), "Out of service!", Toast.LENGTH_SHORT).show();
    }

    public void cancel (View v) {

        setResult(RESULT_CANCELED);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(getApplicationContext(), "Register cancelled!", Toast.LENGTH_SHORT).show();

    }

    private void startRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}