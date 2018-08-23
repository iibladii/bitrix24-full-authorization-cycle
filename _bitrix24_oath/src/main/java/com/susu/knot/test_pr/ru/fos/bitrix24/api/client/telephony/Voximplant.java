/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.client.telephony;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultMany;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultOne;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.TelephonyResponse;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth;

/**
 *
 * @author eugene
 */
public class Voximplant {

    public static int addSIP(String Name, String server, String login, String password, auth au) {

        if (server.isEmpty()) {
            return -1;
        }
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + au.domain + "/rest/voximplant.sip.add.json").openConnection();
            con.setRequestMethod("POST");

            String urlParameters = "auth=" + au.access_token
                    + "&TYPE=cloud"
                    + "&TITLE=" + URLEncoder.encode(Name, "UTF-8")
                    + "&SERVER=" + URLEncoder.encode(server, "UTF-8")
                    + "&LOGIN=" + URLEncoder.encode(login, "UTF-8")
                    + "&PASSWORD=" + URLEncoder.encode(password, "UTF-8");
            con.setDoOutput(true);
            con.setDoInput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            } catch (IOException ex) {
            }
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return -2;
            }

            String response = IOUtils.toString(con.getInputStream());
            return ((ResultOne<TelephonyResponse>) new Gson().fromJson(response, new TypeToken<ResultOne<TelephonyResponse>>() {
            }.getType())).result.CONFIG_ID;
        } catch (IOException ex) {
        }
        return -3;
    }

    public static HashMap<String, Integer> listSIP(auth au) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        if (au.domain.isEmpty()) {
            return result;
        }
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + au.domain + "/rest/voximplant.sip.get.json").openConnection();
            con.setRequestMethod("POST");

            String urlParameters = "auth=" + au.access_token;
            con.setDoOutput(true);
            con.setDoInput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            } catch (IOException ex) {
            }
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return result;
            }

            String response = IOUtils.toString(con.getInputStream());
            List<TelephonyResponse> sips = ((ResultMany<TelephonyResponse>) new Gson().fromJson(response, new TypeToken<ResultMany<TelephonyResponse>>() {
            }.getType())).result;
            for (TelephonyResponse sip : sips) {
                result.put(sip.TITLE, sip.CONFIG_ID);
            }
        } catch (IOException ex) {
        }
        return result;
    }

    public static boolean removeSIP(int id, auth au) {
        if (au.domain.isEmpty()) {
            return false;
        }
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + au.domain + "/rest/voximplant.sip.delete.json").openConnection();
            con.setRequestMethod("POST");

            String urlParameters = "auth=" + au.access_token
                    + "&CONFIG_ID=" + id;
            con.setDoOutput(true);
            con.setDoInput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            } catch (IOException ex) {
            }
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }

            String response = IOUtils.toString(con.getInputStream());
            return ((ResultOne<Integer>) new Gson().fromJson(response, new TypeToken<ResultOne<Integer>>() {
            }.getType())).result==1;
        } catch (IOException ex) {
        }
        return false;
    }
}
