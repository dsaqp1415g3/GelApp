package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

/**
 * Created by marc on 4/06/15.
 */
public class User {

    private String username = null;
    private String password;
    private Boolean loginSuccesful;
    private int usuarioid;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoginSuccesful() {
        return loginSuccesful;
    }

    public void setLoginSuccesful(Boolean loginSuccesful) {
        this.loginSuccesful = loginSuccesful;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
