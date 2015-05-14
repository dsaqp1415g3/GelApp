package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

/**
 * Created by marc on 13/05/15.
 */
public class AppException extends Exception {
    public AppException() {
        super();
    }

    public AppException(String detailMessage) {
        super(detailMessage);
    }
}