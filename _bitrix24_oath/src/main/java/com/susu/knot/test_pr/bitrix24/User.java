/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.bitrix24;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.client.RestClient;
import static com.susu.knot.test_pr.ru.fos.bitrix24.api.client.RestClient.toQueryString;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultMany;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultOne;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth;

/**
 *
 * @author lazareveugene
 */
public class User {

    public String ID;
    public Boolean ACTIVE;
    public String EMAIL;
    public String NAME;
    public String LAST_NAME;
    public String SECOND_NAME;
    public String PERSONAL_GENDER;
    public String PERSONAL_PROFESSION;
    public String PERSONAL_WWW;
    public String PERSONAL_BIRTHDAY;
    public String PERSONAL_PHOTO;
    public String PERSONAL_ICQ;
    public String PERSONAL_PHONE;
    public String PERSONAL_FAX;
    public String PERSONAL_MOBILE;
    public String PERSONAL_PAGER;
    public String PERSONAL_STREET;
    public String PERSONAL_CITY;
    public String PERSONAL_STATE;
    public String PERSONAL_ZIP;
    public String PERSONAL_COUNTRY;
    public String WORK_COMPANY;
    public String WORK_POSITION;
    // DUE BUG
    //public List<Integer> UF_DEPARTMENT;
    public String UF_INTERESTS;
    public String UF_SKILLS;
    public String UF_WEB_SITES;
    public String UF_XING;
    public String UF_LINKEDIN;
    public String UF_FACEBOOK;
    public String UF_TWITTER;
    public String UF_SKYPE;
    public String UF_DISTRICT;
    public String UF_PHONE_INNER;

    public static Integer add(HashMap<String, String> fields, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/user.add.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(toQueryString("&", fields)
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return null;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Integer>) new Gson().fromJson(response, new TypeToken<ResultOne<Integer>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return null;
    }

    public static LinkedTreeMap<String, Object> get(int id, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/user.get.json"
                    + "?id=" + id
                    + "&auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new LinkedTreeMap<String, Object>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<LinkedTreeMap<String, Object>>) new Gson().fromJson(response, new TypeToken<ResultMany<LinkedTreeMap<String, Object>>>() {
            }.getType())).result.get(0);
        } catch (IOException e) {
        }
        return new LinkedTreeMap<String, Object>();
    }

    public static List<LinkedTreeMap<String, Object>> list(HashMap<String, String> order,
            HashMap<String, String> filter, HashMap<String, String> select, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/user.get.json"
                    + "?auth=" + oauth.access_token
                    + RestClient.toQueryString("&", filter)).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ArrayList<LinkedTreeMap<String, Object>>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return (List<LinkedTreeMap<String, Object>>) ((LinkedTreeMap<String, Object>) new Gson().fromJson(response,
                    new TypeToken<LinkedTreeMap<String, Object>>() {
                    }.getType())).get("result");
        } catch (IOException e) {
        }
        return new ArrayList<LinkedTreeMap<String, Object>>();
    }

    public static List<LinkedTreeMap<String, Object>> getAll(auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/user.get.json"
                    + "?auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ArrayList<LinkedTreeMap<String, Object>>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<LinkedTreeMap<String, Object>>) new Gson().fromJson(response.toString(), new TypeToken<ResultMany<LinkedTreeMap<String, Object>>>() {
            }.getType())).result;
        } catch (IOException ex) {
        }
        return new ArrayList<LinkedTreeMap<String, Object>>();
    }

    public static LinkedTreeMap<String, Object> update(int id, HashMap<String, String> fields, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/user.update.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("ID=" + id
                    + RestClient.toQueryString("&", fields)
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new LinkedTreeMap<String, Object>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((LinkedTreeMap<String, Object>) new Gson().fromJson(response, new TypeToken<LinkedTreeMap<String, Object>>() {
            }.getType()));
        } catch (IOException e) {
        }
        return new LinkedTreeMap<String, Object>();
    }

    public static User current(auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/user.current.json"
                    + "?auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new User();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<User>) new Gson().fromJson(response, new TypeToken<ResultOne<User>>() {
            }.getType())).result;
        } catch (IOException ex) {
        }
        return new User();
    }

    public static Boolean admin(auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/user.admin.json"
                    + "?auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Boolean>) new Gson().fromJson(response, new TypeToken<ResultOne<Boolean>>() {
            }.getType())).result;
        } catch (IOException ex) {
        }
        return false;
    }
}
