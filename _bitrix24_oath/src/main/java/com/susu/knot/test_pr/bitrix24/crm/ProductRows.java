/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.bitrix24.crm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultMany;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.ResultOne;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth;

/**
 *
 * @author lazareveugene
 */
public class ProductRows {

    private Integer PRODUCT_ID;
    private String PRODUCT_NAME;
    private Double PRICE;
    private Double QUANTITY;
    private Double DISCOUNT_RATE;
    private Integer MEASURE_CODE;
    private String MEASURE_NAME;

    /**
     * @return the DISCOUNT_RATE
     */
    public Double getDISCOUNT_RATE() {
        return DISCOUNT_RATE;
    }

    /**
     * @param DISCOUNT_RATE the DISCOUNT_RATE to set
     */
    public void setDISCOUNT_RATE(Double DISCOUNT_RATE) {
        this.DISCOUNT_RATE = DISCOUNT_RATE;
    }

    public enum Type {

        Deal,
        Lead
    }

    public ProductRows() {
    }

    public ProductRows(Integer PRODUCT_ID, Double PRICE, Double QUANTITY, Double DISCOUNT_RATE) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRICE = PRICE * (1 - DISCOUNT_RATE);
        this.QUANTITY = QUANTITY;
        this.DISCOUNT_RATE = DISCOUNT_RATE;
    }

    public static List<ProductRows> get(int id, Type type, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + (type == Type.Deal ? "deal" : "lead") + ".productrows.get.json"
                    + "?id=" + id
                    + "&auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ArrayList<ProductRows>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<ProductRows>) new Gson().fromJson(response, new TypeToken<ResultMany<ProductRows>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return new ArrayList<ProductRows>();
    }

    public static List<ProductRows> list(int id, Type type, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm.productrow.list.json"
                    + "?filter[OWNER_TYPE]=" + (type == Type.Deal ? "D" : "L")
                    + "&filter[OWNER_ID]=" + id
                    + "&auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ArrayList<ProductRows>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<ProductRows>) new Gson().fromJson(response, new TypeToken<ResultMany<ProductRows>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return new ArrayList<ProductRows>();
    }

    public static Boolean set(int id, Type type, List<ProductRows> rows, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm." + (type == Type.Deal ? "deal" : "lead") + ".productrows.set.json"
                    + "?id=" + id
                    + "&" + ProductRows.toQueryStrings(rows)
                    + "&auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Boolean>) new Gson().fromJson(response, new TypeToken<ResultOne<Boolean>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return false;
    }

    public static String toQueryStrings(List<ProductRows> rows) throws UnsupportedEncodingException {
        List<String> list = new ArrayList<String>();
        int count = 0;
        for (ProductRows row : rows) {
            list.add("rows[" + count + "][PRODUCT_ID]=" + URLEncoder.encode("" + row.getPRODUCT_ID(), "UTF-8"));
            list.add("rows[" + count + "][PRICE]=" + URLEncoder.encode("" + row.getPRICE(), "UTF-8"));
            list.add("rows[" + count + "][QUANTITY]=" + URLEncoder.encode("" + row.getQUANTITY(), "UTF-8"));
            list.add("rows[" + count + "][DISCOUNT_RATE]=" + URLEncoder.encode("" + row.getDISCOUNT_RATE(), "UTF-8"));
            list.add("rows[" + count + "][DISCOUNT_TYPE_ID]=2");
            count++;
        }
        String Result = "";
        for (String item : list) {
            Result += item + "&";
        }
        return Result.substring(0, Result.length() - 1);
    }

    public String toQueryString(int row) throws UnsupportedEncodingException {
        List<String> list = new ArrayList<String>();
        list.add("rows[" + row + "][PRODUCT_ID]=" + URLEncoder.encode("" + getPRODUCT_ID(), "UTF-8"));
        list.add("rows[" + row + "][PRICE]=" + URLEncoder.encode("" + getPRICE(), "UTF-8"));
        list.add("rows[" + row + "][QUANTITY]=" + URLEncoder.encode("" + getQUANTITY(), "UTF-8"));
        list.add("rows[" + row + "][DISCOUNT_RATE]=" + URLEncoder.encode("" + getDISCOUNT_RATE(), "UTF-8"));
        list.add("rows[" + row + "][DISCOUNT_TYPE_ID]=2");
        String Result = "";
        for (String item : list) {
            Result += item + "&";
        }
        return Result.substring(0, Result.length() - 1);
    }

    /**
     * @return the PRODUCT_ID
     */
    public Integer getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    /**
     * @param PRODUCT_ID the PRODUCT_ID to set
     */
    public void setPRODUCT_ID(Integer PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    /**
     * @return the PRICE
     */
    public Double getPRICE() {
        return PRICE;
    }

    /**
     * @param PRICE the PRICE to set
     */
    public void setPRICE(Double PRICE) {
        this.PRICE = PRICE;
    }

    /**
     * @return the QUANTITY
     */
    public Double getQUANTITY() {
        return QUANTITY;
    }

    /**
     * @param QUANTITY the QUANTITY to set
     */
    public void setQUANTITY(Double QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    /**
     * @return the PRODUCT_NAME
     */
    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    /**
     * @param PRODUCT_NAME the PRODUCT_NAME to set
     */
    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    /**
     * @return the MEASURE_CODE
     */
    public Integer getMEASURE_CODE() {
        return MEASURE_CODE;
    }

    /**
     * @param MEASURE_CODE the MEASURE_CODE to set
     */
    public void setMEASURE_CODE(Integer MEASURE_CODE) {
        this.MEASURE_CODE = MEASURE_CODE;
    }

    /**
     * @return the MEASURE_NAME
     */
    public String getMEASURE_NAME() {
        return MEASURE_NAME;
    }

    /**
     * @param MEASURE_NAME the MEASURE_NAME to set
     */
    public void setMEASURE_NAME(String MEASURE_NAME) {
        this.MEASURE_NAME = MEASURE_NAME;
    }
}
