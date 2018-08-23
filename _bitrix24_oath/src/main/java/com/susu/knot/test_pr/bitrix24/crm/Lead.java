/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.bitrix24.crm;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lazareveugene
 */
public class Lead {

    private Integer ID;
    private String TITLE = "Новый лид";
    private String NAME = "";
    private Integer ASSIGNED_BY_ID = null;
    private String COMMENTS = "";
    private String OPENED = "Y";
    private String STATUS_ID = "NEW";
    private String PHONE = "";
    private String EMAIL = "";
    private String SOURCE = "SELF";
    private String SOURCE_DESCRIPTION = "";

    public String toQueryString() throws UnsupportedEncodingException {
        List<String> list = new ArrayList<String>();
        list.add("fields[TITLE]=" + URLEncoder.encode(getTITLE(), "UTF-8"));
        list.add("fields[NAME]=" + URLEncoder.encode(getNAME(), "UTF-8"));
        list.add("fields[SOURCE_DESCRIPTION]=" + URLEncoder.encode(getSOURCE_DESCRIPTION(), "UTF-8"));
        list.add("fields[ASSIGNED_BY_ID]=" + URLEncoder.encode("" + getASSIGNED_BY_ID(), "UTF-8"));
        list.add("fields[COMMENTS]=" + URLEncoder.encode(getCOMMENTS(), "UTF-8"));
        list.add("fields[OPENED]=" + URLEncoder.encode(getOPENED(), "UTF-8"));
        list.add("fields[PHONE][0][VALUE]=" + URLEncoder.encode(getPHONE(), "UTF-8"));
        list.add("fields[PHONE][0][VALUE_TYPE]=WORK");
        list.add("fields[EMAIL][0][VALUE]=" + URLEncoder.encode(getEMAIL(), "UTF-8"));
        list.add("fields[EMAIL][0][VALUE_TYPE]=WORK");
        list.add("fields[SOURCE_ID]=" + URLEncoder.encode(getSOURCE(), "UTF-8"));
        list.add("params[REGISTER_SONET_EVENT]=Y");
        String Result = "";
        for (String item : list) {
            Result += item + "&";
        }
        return Result.substring(0, Result.length() - 1);
    }

    /**
     * @return the ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * @return the TITLE
     */
    public String getTITLE() {
        return TITLE;
    }

    /**
     * @param TITLE the TITLE to set
     */
    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    /**
     * @return the NAME
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * @param NAME the NAME to set
     */
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    /**
     * @return the ASSIGNED_BY_ID
     */
    public Integer getASSIGNED_BY_ID() {
        return ASSIGNED_BY_ID;
    }

    /**
     * @param ASSIGNED_BY_ID the ASSIGNED_BY_ID to set
     */
    public void setASSIGNED_BY_ID(Integer ASSIGNED_BY_ID) {
        this.ASSIGNED_BY_ID = ASSIGNED_BY_ID;
    }

    /**
     * @return the COMMENTS
     */
    public String getCOMMENTS() {
        return COMMENTS;
    }

    /**
     * @param COMMENTS the COMMENTS to set
     */
    public void setCOMMENTS(String COMMENTS) {
        this.COMMENTS = COMMENTS;
    }

    /**
     * @return the OPENED
     */
    public String getOPENED() {
        return OPENED;
    }

    /**
     * @param OPENED the OPENED to set
     */
    public void setOPENED(String OPENED) {
        this.OPENED = OPENED;
    }

    /**
     * @return the STATUS_ID
     */
    public String getSTATUS_ID() {
        return STATUS_ID;
    }

    /**
     * @param STATUS_ID the STATUS_ID to set
     */
    public void setSTATUS_ID(String STATUS_ID) {
        this.STATUS_ID = STATUS_ID;
    }

    /**
     * @return the PHONE
     */
    public String getPHONE() {
        return PHONE;
    }

    /**
     * @param PHONE the PHONE to set
     */
    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    /**
     * @return the SOURCE
     */
    public String getSOURCE() {
        return SOURCE;
    }

    /**
     * @param SOURCE the SOURCE to set
     */
    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }

    /**
     * @return the EMAIL
     */
    public String getEMAIL() {
        return EMAIL;
    }

    /**
     * @param EMAIL the EMAIL to set
     */
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    /**
     * @return the SOURCE_DESCRIPTION
     */
    public String getSOURCE_DESCRIPTION() {
        return SOURCE_DESCRIPTION;
    }

    /**
     * @param SOURCE_DESCRIPTION the SOURCE_DESCRIPTION to set
     */
    public void setSOURCE_DESCRIPTION(String SOURCE_DESCRIPTION) {
        this.SOURCE_DESCRIPTION = SOURCE_DESCRIPTION;
    }
}
