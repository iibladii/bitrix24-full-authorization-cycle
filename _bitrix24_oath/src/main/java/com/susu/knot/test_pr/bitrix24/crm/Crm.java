/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.bitrix24.crm;

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
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.client.RestClient;

import static com.susu.knot.test_pr.ru.fos.bitrix24.api.client.RestClient.toQueryString;

import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultOne;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth;

/**
 * @author lazareveugene
 */
public class Crm {

    public static Integer add(HashMap<String, String> fields, auth oauth, String namespace) throws Exception {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + namespace + ".add.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(toQueryString("&", fields, "fields")
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("response Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Integer>) new Gson().fromJson(response, new TypeToken<ResultOne<Integer>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return null;
    }

    public static Integer add(String fields, auth oauth, String namespace) throws Exception {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + namespace + ".add.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(fields
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("response Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Integer>) new Gson().fromJson(response, new TypeToken<ResultOne<Integer>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return null;
    }

    public static boolean delete(int id, auth oauth, String namespace) throws Exception {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + namespace + ".delete.json"
                    + "?id=" + id
                    + "&auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("response Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Boolean>) new Gson().fromJson(response, new TypeToken<ResultOne<Boolean>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return false;
    }

    public static LinkedTreeMap<String, Object> get(int id, auth oauth, String namespace) throws Exception {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + namespace + ".get.json"
                    + "?id=" + id
                    + "&auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("response Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((LinkedTreeMap<String, Object>) new Gson().fromJson(response, new TypeToken<LinkedTreeMap<String, Object>>() {
            }.getType()));
        } catch (IOException e) {
        }
        return new LinkedTreeMap<String, Object>();
    }

    public static Boolean exist(int id, auth oauth, String namespace) throws Exception {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + namespace + ".list.json"
                    + "?auth=" + oauth.access_token
                    + "&filter[ID]=" + id
                    + "&select[]=ID").openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("response Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((Integer) ((LinkedTreeMap<String, Object>) new Gson().fromJson(response,
                    new TypeToken<LinkedTreeMap<String, Object>>() {
                    }.getType())).get("total")) > 0;
        } catch (IOException e) {
        }
        return false;
    }

    public static List<LinkedTreeMap<String, Object>> list(HashMap<String, String> order,
                                                           HashMap<String, String> filter, HashMap<String, String> select, auth oauth, String namespace) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + namespace + ".list.json"
                + "?auth=" + oauth.access_token
                + RestClient.toQueryString("&", order, "order")
                + RestClient.toQueryString("&", filter, "filter")
                + RestClient.toQueryString("&", select, "select")).openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("response Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
        }
        String response = IOUtils.toString(con.getInputStream(), "UTF-8");
        return (List<LinkedTreeMap<String, Object>>) ((LinkedTreeMap<String, Object>) new Gson().fromJson(response,
                new TypeToken<LinkedTreeMap<String, Object>>() {
                }.getType())).get("result");
    }

    public static LinkedTreeMap<String, Object> page(HashMap<String, String> order,
                                                     HashMap<String, String> filter, HashMap<String, String> select, auth oauth, String namespace, int start) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + namespace + ".list.json"
                + "?auth=" + oauth.access_token
                + RestClient.toQueryString("&", order, "order")
                + RestClient.toQueryString("&", filter, "filter")
                + "&start=" + start
                + RestClient.toQueryString("&", select, "select")).openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("response Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
        }
        String response = IOUtils.toString(con.getInputStream(), "UTF-8");
        return (LinkedTreeMap<String, Object>) new Gson().fromJson(response,
                new TypeToken<LinkedTreeMap<String, Object>>() {
                }.getType());
    }

    public static LinkedTreeMap<String, Object> update(int id, HashMap<String, String> fields, auth oauth, String namespace) throws Exception {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + namespace + ".update.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("id=" + id
                    + RestClient.toQueryString("&", fields, "fields")
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("response Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((LinkedTreeMap<String, Object>) new Gson().fromJson(response, new TypeToken<LinkedTreeMap<String, Object>>() {
            }.getType()));
        } catch (IOException e) {
        }
        return new LinkedTreeMap<String, Object>();
    }

    public static LinkedTreeMap<String, Object> findbycomm(FindByCommType type, String[] values, auth oauth) throws Exception {
        if (values == null || values.length == 0)
            return new LinkedTreeMap<String, Object>();
        List<String> valuesClean = new ArrayList<String>();
        for (String item : values) {
            if (item != null && !Objects.equals(item.trim(), "")) {
                valuesClean.add(item.trim());
            }
        }
        if (valuesClean.isEmpty())
            return new LinkedTreeMap<String, Object>();
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm.duplicate.findbycomm.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            HashMap<String, String[]> map = new HashMap<String, String[]>();
            map.put("values", valuesClean.toArray(new String[0]));
            wr.writeBytes("type=" + type.name()
                    + RestClient.toQueryStringArray("&", map)
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("type=" + type.name()
                        + RestClient.toQueryStringArray("&", map)
                        + "&auth=" + oauth.access_token
                        + "\nresponse Code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((LinkedTreeMap<String, Object>) new Gson().fromJson(response, new TypeToken<LinkedTreeMap<String, Object>>() {
            }.getType()));
        } catch (IOException e) {
        }
        return new LinkedTreeMap<String, Object>();
    }
}

