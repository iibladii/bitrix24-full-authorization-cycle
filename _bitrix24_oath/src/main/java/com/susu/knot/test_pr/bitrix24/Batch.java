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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultManyBatch;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultOne;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth;

/**
 *
 * @author lazareveugene
 */
public class Batch {

    public List<Batch.MethodCall> cmd = new ArrayList<Batch.MethodCall>();

    public Batch addMethodCall(String Name, String Params) {
        cmd.add(new MethodCall(Name, Params));
        return this;
    }

    public ResultManyBatch<LinkedTreeMap<String, Object>> call(auth oauth) {

        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/user.admin.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (MethodCall mc : cmd) {
                sb.append("cmd[").append(count++).append("]=").append(URLEncoder.encode(mc.Name + "?" + mc.Params, "UTF-8")).append("&");
            }
            wr.writeBytes(sb.toString() + "auth=" + oauth.access_token);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return null;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<ResultManyBatch<LinkedTreeMap<String, Object>>>) new Gson().fromJson(response, new TypeToken<ResultOne<ResultManyBatch<LinkedTreeMap<String, Object>>>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return null;
    }

    public class MethodCall {

        private MethodCall() {
        }

        private MethodCall(String Name, String Params) {
            this.Name = Name;
            this.Params = Params;
        }

        public String Name;
        public String Params;
    }
}
