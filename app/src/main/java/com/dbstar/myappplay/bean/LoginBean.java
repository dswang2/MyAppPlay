package com.dbstar.myappplay.bean;

import com.google.gson.Gson;

/**
 * Created by dswang on 2017/10/6.
 */

public class LoginBean extends BaseEntity{
    /**
     * token : fe7b6639-a888-4936-b572-d37dcc3fd379
     * user : {"id":273025,"logo_url":"http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epiaAuIUAm2ElaPc2y0XN65EAzSNErdP5Gk7OWlNFS39AV4OmfVsga6x9vUUXqAibgxiceibiaEOZyvejQ/0","username":"宝剑无忌","mobi":"18721830936"}
     */

    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
