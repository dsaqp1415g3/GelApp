package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

/**
 * Created by marc on 13/05/15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

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

            helados.setNewestTimestamp(jsonObject.getLong("newestTimestamp"));
            helados.setOldestTimestamp(jsonObject.getLong("oldestTimestamp"));
            JSONArray jsonHelados = jsonObject.getJSONArray("helados");
            for (int i = 0; i < jsonHelados.length(); i++) {
                Helado helado = new Helado();
                JSONObject jsonHelado = jsonHelados.getJSONObject(i);

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
}

