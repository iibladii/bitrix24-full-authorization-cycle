/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.client;

import com.google.gson.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author eugene
 */
public class OAuthClient {

	public static String getOAuth1(String bitrix_name,String client_id, String site_url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(" https://"+bitrix_name+".bitrix24.ru/oauth/authorize/?client_id="+client_id+"&response_type=code&redirect_uri="+site_url).openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        }
        return IOUtils.toString(con.getInputStream(), "UTF-8");
    }
	
	
	public static auth getOAuth4(String code, String domain, String client_id, String client_secret) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + domain + "/oauth/token/"
                + "?client_id=" + client_id
                + "&grant_type=authorization_code"
                + "&client_secret=" + client_secret
                + "&code=" + code).openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        }
        String res = IOUtils.toString(con.getInputStream(), "UTF-8");
        return new Gson().fromJson(res, auth1.class).toAuth();
    }
	
	
	
	
    public static String getOAuthRequestString(String domain, String client_id, String redirect_uri) throws UnsupportedEncodingException {
        return "https://" + domain + "/oauth/authorize/?"
                + "response_type=code"
                + "&client_id=" + client_id
                + "&redirect_uri=" + URLEncoder.encode(redirect_uri, "UTF-8");
    }

    public static auth getOAuth(String code, String domain, String client_id, String client_secret, String redirect_uri, List<String> scopes) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + domain + "/oauth/token/"
                + "?client_id=" + client_id
                + "&grant_type=authorization_code"
                + "&client_secret=" + client_secret
                + "&redirect_uri=" + URLEncoder.encode(redirect_uri, "UTF-8")
                + "&code=" + code
                + "&scope=" + StringUtils.join(scopes, ",")).openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        }
        String res = IOUtils.toString(con.getInputStream(), "UTF-8");
        return new Gson().fromJson(res, auth1.class).toAuth();
    }

    public static auth refreshOAuth(auth oauth, String client_id, String client_secret, String redirect_uri) throws IOException {
        if (oauth == null) {
            return null;
        }
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/app.info.json"
                + "?auth=" + oauth.access_token).openConnection();
        con.setRequestMethod("GET");

        try {
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                return oauth;
            }
        } catch (UnknownHostException e) {
            return null;
        }
        con = (HttpURLConnection) new URL("https://oauth.bitrix.info/oauth/token/"
                + "?client_id=" + client_id
                + "&grant_type=refresh_token"
                + "&client_secret=" + client_secret
                + "&redirect_uri=" + redirect_uri
                + "&refresh_token=" + oauth.refresh_token).openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        }
        String response = IOUtils.toString(con.getInputStream(), "UTF-8");
        return new GsonBuilder().registerTypeAdapter(String[].class, new JsonDeserializer<String[]>() {

            public String[] deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) throws JsonParseException {
                return json.getAsString().split(",");
            }
        }).create().fromJson(response, auth.class);
    }
}
