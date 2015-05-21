package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

/**
 * Created by marc on 13/05/15.
 */
import java.util.HashMap;
import java.util.Map;

public class GelappRootAPI {

    private Map<String, Link> links;

    public GelappRootAPI() {
        links = new HashMap<String, Link>();
    }

    public Map<String, Link> getLinks() {
        return links;
    }

}