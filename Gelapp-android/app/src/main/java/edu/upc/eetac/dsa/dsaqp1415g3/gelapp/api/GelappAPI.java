package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

/**
 * Created by marc on 13/05/15.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GelappAPI {
    private final static String TAG = GelappAPI.class.getName();
    private static GelappAPI instance = null;
    private URL url;

    private GelappRootAPI rootAPI = null;

    private GelappAPI(Context context) throws IOException, AppException {
        super();

        AssetManager assetManager = context.getAssets();
        Properties config = new Properties();
        config.load(assetManager.open("config.properties"));
        String urlHome = config.getProperty("gelapp.home");
        url = new URL(urlHome);

        Log.d("LINKS", url.toString());
        getRootAPI();
    }

    public final static GelappAPI getInstance(Context context) throws AppException {
        if (instance == null)
            try {
                instance = new GelappAPI(context);
            } catch (IOException e) {
                throw new AppException(
                        "No se ha podido cargar el fichero de configuración");
            }
        return instance;
    }

    private void getRootAPI() throws AppException {
        Log.d(TAG, "getRootAPI()");
        rootAPI = new GelappRootAPI();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Gelapp API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, rootAPI.getLinks());
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Gelapp API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Gelapp Root API");
        }

    }


    //Obtener lista de helados
    public HeladoCollection getHelados() throws AppException {
        Log.d(TAG, "getHelados()");
        HeladoCollection helados = new HeladoCollection();

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(rootAPI.getLinks()
                    .get("helados").getTarget()).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Gelapp API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, helados.getLinks());

            //helados.setNewestTimestamp(jsonObject.getLong("newestTimestamp"));
            //helados.setOldestTimestamp(jsonObject.getLong("oldestTimestamp"));
            JSONArray jsonHelados = jsonObject.getJSONArray("helados");
            for (int i = 0; i < jsonHelados.length(); i++) {
                Helado helado = new Helado();
                JSONObject jsonHelado = jsonHelados.getJSONObject(i);

                helado.setAutor(jsonHelado.getString("autor"));
                helado.setAutorid(jsonHelado.getInt("autorid"));
                helado.setCapa1Topping(jsonHelado.getString("capa1Topping"));
                helado.setCapa2Helado(jsonHelado.getString("capa2Helado"));
                helado.setCapa3Topping(jsonHelado.getString("capa3Topping"));
                helado.setCapa4Helado(jsonHelado.getString("capa4Helado"));
                helado.setCapa5Topping(jsonHelado.getString("capa5Topping"));
                helado.setCreationTimestamp(jsonHelado.getLong("creationTimestamp"));
                helado.setHeladoid(jsonHelado.getInt("heladoid"));
                helado.setLastModified(jsonHelado.getLong("lastModified"));
                helado.setNombreHelado(jsonHelado.getString("nombreHelado"));
                helado.setVotos(jsonHelado.getInt("votos"));

                jsonLinks = jsonHelado.getJSONArray("links");
                parseLinks(jsonLinks, helado.getLinks());
                helados.getHelados().add(helado);
            }
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Gelapp API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Gelapp Rooooooot API");
        }

        return helados;
    }

    private void parseLinks(JSONArray jsonLinks, Map<String, Link> map)
            throws AppException, JSONException {
        for (int i = 0; i < jsonLinks.length(); i++) {
            Link link = null;
            try {
                link = SimpleLinkHeaderParser
                        .parseLink(jsonLinks.getString(i));
            } catch (Exception e) {
                throw new AppException(e.getMessage());
            }
            String rel = link.getParameters().get("rel");
            String rels[] = rel.split("\\s");
            for (String s : rels)
                map.put(s, link);
        }
    }


    //Obtener ranking de helados

    public HeladoCollection getRankingHelados() throws AppException {
        Log.d(TAG, "getHelados()");
        HeladoCollection helados = new HeladoCollection();

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(rootAPI.getLinks()
                    .get("gelapp-ranking").getTarget()).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Gelapp API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, helados.getLinks());

            //helados.setNewestTimestamp(jsonObject.getLong("newestTimestamp"));
            //helados.setOldestTimestamp(jsonObject.getLong("oldestTimestamp"));
            JSONArray jsonHelados = jsonObject.getJSONArray("helados");
            for (int i = 0; i < jsonHelados.length(); i++) {
                Helado helado = new Helado();
                JSONObject jsonHelado = jsonHelados.getJSONObject(i);

                helado.setAutor(jsonHelado.getString("autor"));
                helado.setAutorid(jsonHelado.getInt("autorid"));
                helado.setCapa1Topping(jsonHelado.getString("capa1Topping"));
                helado.setCapa2Helado(jsonHelado.getString("capa2Helado"));
                helado.setCapa3Topping(jsonHelado.getString("capa3Topping"));
                helado.setCapa4Helado(jsonHelado.getString("capa4Helado"));
                helado.setCapa5Topping(jsonHelado.getString("capa5Topping"));
                helado.setCreationTimestamp(jsonHelado.getLong("creationTimestamp"));
                helado.setHeladoid(jsonHelado.getInt("heladoid"));
                helado.setLastModified(jsonHelado.getLong("lastModified"));
                helado.setNombreHelado(jsonHelado.getString("nombreHelado"));
                helado.setVotos(jsonHelado.getInt("votos"));

                jsonLinks = jsonHelado.getJSONArray("links");
                parseLinks(jsonLinks, helado.getLinks());
                helados.getHelados().add(helado);
            }
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Gelapp API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Gelapp Rooooooot API");
        }

        return helados;
    }

    //Obtener 1 helado

    private Map<String, Helado> heladosCache = new HashMap<String, Helado>();

    public Helado getHelado(String urlHelado) throws AppException {
        Helado helado = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlHelado);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            helado = heladosCache.get(urlHelado);
            String eTag = (helado == null) ? null : helado.getETag();
            if (eTag != null)
                urlConnection.setRequestProperty("If-None-Match", eTag);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                Log.d(TAG, "CACHE");
                return heladosCache.get(urlHelado);
            }
            Log.d(TAG, "NOT IN CACHE");
            helado = new Helado();
            eTag = urlConnection.getHeaderField("ETag");
            helado.setETag(eTag);
            heladosCache.put(urlHelado, helado);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);

            }
            JSONObject jsonHelado = new JSONObject(sb.toString());

            helado.setAutor(jsonHelado.getString("autor"));
            helado.setAutorid(jsonHelado.getInt("autorid"));
            helado.setCapa1Topping(jsonHelado.getString("capa1Topping"));
            helado.setCapa2Helado(jsonHelado.getString("capa2Helado"));
            helado.setCapa3Topping(jsonHelado.getString("capa3Topping"));
            helado.setCapa4Helado(jsonHelado.getString("capa4Helado"));
            helado.setCapa5Topping(jsonHelado.getString("capa5Topping"));
            helado.setCreationTimestamp(jsonHelado.getLong("creationTimestamp"));
            helado.setHeladoid(jsonHelado.getInt("heladoid"));
            helado.setLastModified(jsonHelado.getLong("lastModified"));
            helado.setNombreHelado(jsonHelado.getString("nombreHelado"));
            helado.setVotos(jsonHelado.getInt("votos"));

            JSONArray jsonLinks = jsonHelado.getJSONArray("links");
            parseLinks(jsonLinks, helado.getLinks());


        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Bad helado url");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Exception when getting the helado");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Exception parsing response");
        }

        return helado;
    }

//Añadir Helado a la BBDD

    public Helado createHelado(int autorid, String nombreHelado, String capa1, String capa2, String capa3, String capa4, String capa5) throws AppException {
        Helado helado = new Helado();
        helado.setAutorid(autorid);
        helado.setNombreHelado(nombreHelado);
        helado.setCapa1Topping(capa1);
        helado.setCapa2Helado(capa2);
        helado.setCapa3Topping(capa3);
        helado.setCapa4Helado(capa4);
        helado.setCapa5Topping(capa5);

        HttpURLConnection urlConnection = null;
        try {
            JSONObject jsonHelado = createJsonHelado(helado);


            URL urlPostHelados = new URL(rootAPI.getLinks().get("create-helado").getTarget());

            urlConnection = (HttpURLConnection) urlPostHelados.openConnection();
            urlConnection.setRequestProperty("Accept",
                    MediaType.GELAPP_API_HELADO);
            urlConnection.setRequestProperty("Content-Type",
                    MediaType.GELAPP_API_HELADO);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            PrintWriter writer = new PrintWriter(
                    urlConnection.getOutputStream());
            writer.println(jsonHelado.toString());
            writer.close();

            int rc = urlConnection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            jsonHelado = new JSONObject(sb.toString());

            helado.setAutor(jsonHelado.getString("autor"));
            helado.setAutorid(jsonHelado.getInt("autorid"));
            helado.setCapa1Topping(jsonHelado.getString("capa1Topping"));
            helado.setCapa2Helado(jsonHelado.getString("capa2Helado"));
            helado.setCapa3Topping(jsonHelado.getString("capa3Topping"));
            helado.setCapa4Helado(jsonHelado.getString("capa4Helado"));
            helado.setCapa5Topping(jsonHelado.getString("capa5Topping"));
            helado.setHeladoid(jsonHelado.getInt("heladoid"));
            helado.setLastModified(jsonHelado.getLong("lastModified"));
            helado.setNombreHelado(jsonHelado.getString("nombreHelado"));
            helado.setVotos(jsonHelado.getInt("votos"));

            JSONArray jsonLinks = jsonHelado.getJSONArray("links");
            parseLinks(jsonLinks, helado.getLinks());
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error parsing response");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error getting response");
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return helado;
    }

    private JSONObject createJsonHelado(Helado helado) throws JSONException {
        JSONObject jsonHelado = new JSONObject();
        jsonHelado.put("autorid", helado.getAutorid());
        jsonHelado.put("nombreHelado", helado.getNombreHelado());
        jsonHelado.put("capa1Topping", helado.getCapa1Topping());
        jsonHelado.put("capa2Helado", helado.getCapa2Helado());
        jsonHelado.put("capa3Topping", helado.getCapa3Topping());
        jsonHelado.put("capa4Helado", helado.getCapa4Helado());
        jsonHelado.put("capa5Topping", helado.getCapa5Topping());

        return jsonHelado;
    }




    //Borrar helado

    public Helado deleteHelado(String urlHelado) throws AppException {
        Helado helado = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlHelado);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setDoInput(true);

            helado = heladosCache.get(urlHelado);
            String eTag = (helado == null) ? null : helado.getETag();
            if (eTag != null)
                urlConnection.setRequestProperty("If-None-Match", eTag);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                Log.d(TAG, "CACHE");
                return heladosCache.get(urlHelado);
            }
            Log.d(TAG, "NOT IN CACHE");
            helado = new Helado();
            eTag = urlConnection.getHeaderField("ETag");
            helado.setETag(eTag);
            heladosCache.put(urlHelado, helado);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Bad helado url");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Exception when getting the helado");
        }
        return helado;
    }


    //LOGIN/////////////

    public User loginUser(String username, String password) throws AppException {
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        HttpURLConnection urlConnection = null;
        try {
            JSONObject jsonUser = createJsonUser(user);


            URL urlPostUser = new URL(rootAPI.getLinks().get("gelapp-profile").getTarget());

            urlConnection = (HttpURLConnection) urlPostUser.openConnection();
            urlConnection.setRequestProperty("Content-Type",
                    MediaType.GELAPP_API_USER);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            PrintWriter writer = new PrintWriter(
                    urlConnection.getOutputStream());
            writer.println(jsonUser.toString());
            writer.close();

            int rc = urlConnection.getResponseCode();
            if(rc==404){
                user.setLoginSuccesful(false);
                return user;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            jsonUser = new JSONObject(sb.toString());

            user.setUsername(jsonUser.getString("username"));
            user.setLoginSuccesful(jsonUser.getBoolean("loginSuccesful"));
            user.setUsuarioid(jsonUser.getInt("usuarioid"));


            //JSONArray jsonLinks = jsonUser.getJSONArray("links");
            //parseLinks(jsonLinks, user.getLinks());
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error parsing response");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error getting response");
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return user;
    }


    private JSONObject createJsonUser(User user) throws JSONException {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", user.getUsername());
        jsonUser.put("password", user.getPassword());

        return jsonUser;
    }

    //REGISTER////////////

    public User registerUser(String username, String password) throws AppException {
        User userReg = new User();

        userReg.setUsername(username);
        userReg.setPassword(password);

        HttpURLConnection urlConnection = null;
        try {
            JSONObject jsonUserReg = createJsonUserReg(userReg);


            URL urlPostUser = new URL(rootAPI.getLinks().get("gelapp-register").getTarget());

            urlConnection = (HttpURLConnection) urlPostUser.openConnection();
            urlConnection.setRequestProperty("Content-Type",
                    MediaType.GELAPP_API_USER);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            PrintWriter writer = new PrintWriter(
                    urlConnection.getOutputStream());
            writer.println(jsonUserReg.toString());
            writer.close();

            int rc = urlConnection.getResponseCode();
            if(rc==409){
                userReg.setLoginSuccesful(true);//indica que ha habido un error
                return userReg;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            jsonUserReg = new JSONObject(sb.toString());

            userReg.setUsername(jsonUserReg.getString("username"));
            userReg.setLoginSuccesful(jsonUserReg.getBoolean("loginSuccesful"));
            //userReg.setStatus(jsonUserReg.getString("status"));

;
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error parsing response");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error getting response, invalid user");
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return userReg;
    }


    private JSONObject createJsonUserReg(User userReg) throws JSONException {
        JSONObject jsonUserReg = new JSONObject();
        jsonUserReg.put("username", userReg.getUsername());
        jsonUserReg.put("password", userReg.getPassword());

        return jsonUserReg;
    }


    //VOTAR/////////////

    public Voto VoteHelado(int idusuario, int idhelado) throws AppException {
        Voto voto = new Voto();

        voto.setId_usuario(idusuario);
        voto.setId_helado(idhelado);

        HttpURLConnection urlConnection = null;
        try {
            JSONObject jsonVoto = createJsonVoto(voto);


            URL urlPostVoto = new URL(rootAPI.getLinks().get("vote").getTarget());//link de votar

            urlConnection = (HttpURLConnection) urlPostVoto.openConnection();
            urlConnection.setRequestProperty("Content-Type",
                    MediaType.GELAPP_API_VOTOS);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            PrintWriter writer = new PrintWriter(
                    urlConnection.getOutputStream());
            writer.println(jsonVoto.toString());
            writer.close();


            int rc = urlConnection.getResponseCode();
            if(rc==500){//código de error si ya se ha votado
                voto.setId_usuario(0);
                voto.setId_helado(0);
                return voto;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            jsonVoto = new JSONObject(sb.toString());

            voto.setId_usuario(jsonVoto.getInt("id_usuario"));
            voto.setId_helado(jsonVoto.getInt("id_helado"));

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error parsing response");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error getting response");
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return voto;
    }


    private JSONObject createJsonVoto(Voto voto) throws JSONException {
        JSONObject jsonVoto = new JSONObject();
        jsonVoto.put("id_usuario", voto.getId_usuario());
        jsonVoto.put("id_helado", voto.getId_helado());

        return jsonVoto;
    }


}

