/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author eugene
 */
public class Activity {

    private Integer ID;
    private String SUBJECT = "Новое дело";
    private Integer RESPONSIBLE_ID = null;
    private String DESCRIPTION = "";
    private String COMPLETED = "N";
    private Integer DIRECTION = 1;
    private String DEADLINE = "";
    private String COMMUNICATIONS;
    private HashMap<String, String> FILES = new HashMap<>();
    private Integer DESCRIPTION_TYPE = 3;
    private Integer TYPE_ID = 3;
    private Integer OWNER_ID = 3;
    private Integer OWNER_TYPE_ID = 3;
    private Date START_TIME = new Date();
    private Date END_TIME = new Date();

    public String toQueryString() throws UnsupportedEncodingException {
        List<String> list = new ArrayList<String>();
        list.add("fields[SUBJECT]=" + URLEncoder.encode(getSUBJECT(), "UTF-8"));
        list.add("fields[RESPONSIBLE_ID]=" + URLEncoder.encode("" + getRESPONSIBLE_ID(), "UTF-8"));
        list.add("fields[DESCRIPTION]=" + URLEncoder.encode(getDESCRIPTION(), "UTF-8"));
        list.add("fields[DESCRIPTION_TYPE]=" + URLEncoder.encode(getDESCRIPTION_TYPE() + "", "UTF-8"));
        list.add("fields[COMPLETED]=" + URLEncoder.encode(getCOMPLETED(), "UTF-8"));
        list.add("fields[TYPE_ID]=" + URLEncoder.encode(getTYPE_ID() + "", "UTF-8"));
        list.add("fields[COMMUNICATIONS][0][VALUE]=" + URLEncoder.encode(getCOMMUNICATIONS(), "UTF-8"));
        list.add("fields[COMMUNICATIONS][0][VALUE_TYPE]=WORK");
        list.add("fields[DIRECTION]=" + URLEncoder.encode(getDIRECTION() + "", "UTF-8"));
        list.add("fields[OWNER_ID]=" + URLEncoder.encode(getOWNER_ID() + "", "UTF-8"));
        list.add("fields[DEADLINE]=" + URLEncoder.encode(getDEADLINE() + "", "UTF-8"));
        list.add("fields[OWNER_TYPE_ID]=" + URLEncoder.encode(getOWNER_TYPE_ID() + "", "UTF-8"));
        list.add("fields[START_TIME]=" + URLEncoder.encode(new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ").format(getSTART_TIME()), "UTF-8"));
        list.add("fields[END_TIME]=" + URLEncoder.encode(new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ").format(getEND_TIME()), "UTF-8"));
        int count = 0;
        for (String key : FILES.keySet()) {
            list.add("fields[FILES][" + count + "][fileData][0]=" + URLEncoder.encode(key, "UTF-8"));
            list.add("fields[FILES][" + count + "][fileData][1]=" + URLEncoder.encode(FILES.get(key), "UTF-8"));
            count++;
        }
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
     * @return the SUBJECT
     */
    public String getSUBJECT() {
        return SUBJECT;
    }

    /**
     * @param SUBJECT the SUBJECT to set
     */
    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    /**
     * @return the RESPONSIBLE_ID
     */
    public Integer getRESPONSIBLE_ID() {
        return RESPONSIBLE_ID;
    }

    /**
     * @param RESPONSIBLE_ID the RESPONSIBLE_ID to set
     */
    public void setRESPONSIBLE_ID(Integer RESPONSIBLE_ID) {
        this.RESPONSIBLE_ID = RESPONSIBLE_ID;
    }

    /**
     * @return the DESCRIPTION
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    /**
     * @param DESCRIPTION the DESCRIPTION to set
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    /**
     * @return the COMPLETED
     */
    public String getCOMPLETED() {
        return COMPLETED;
    }

    /**
     * @param COMPLETED the COMPLETED to set
     */
    public void setCOMPLETED(String COMPLETED) {
        this.COMPLETED = COMPLETED;
    }

    /**
     * @return the DIRECTION
     */
    public Integer getDIRECTION() {
        return DIRECTION;
    }

    /**
     * @param DIRECTION the DIRECTION to set
     */
    public void setDIRECTION(Integer DIRECTION) {
        this.DIRECTION = DIRECTION;
    }

    /**
     * @return the COMMUNICATIONS
     */
    public String getCOMMUNICATIONS() {
        return COMMUNICATIONS;
    }

    /**
     * @param COMMUNICATIONS the COMMUNICATIONS to set
     */
    public void setCOMMUNICATIONS(String COMMUNICATIONS) {
        this.COMMUNICATIONS = COMMUNICATIONS;
    }

    /**
     * @return the FILES
     */
    public HashMap<String, String> getFILES() {
        return FILES;
    }

    /**
     * @param FILES the WEBDAV_ELEMENTS to set
     */
    public void setFILES(HashMap<String, String> FILES) {
        this.FILES = FILES;
    }

    /**
     * @return the DESCRIPTION_TYPE
     */
    public Integer getDESCRIPTION_TYPE() {
        return DESCRIPTION_TYPE;
    }

    /**
     * @param DESCRIPTION_TYPE the DESCRIPTION_TYPE to set
     */
    public void setDESCRIPTION_TYPE(Integer DESCRIPTION_TYPE) {
        this.DESCRIPTION_TYPE = DESCRIPTION_TYPE;
    }

    /**
     * @return the TYPE_ID
     */
    public Integer getTYPE_ID() {
        return TYPE_ID;
    }

    /**
     * @param TYPE_ID the TYPE_ID to set
     */
    public void setTYPE_ID(Integer TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    /**
     * @return the OWNER_ID
     */
    public Integer getOWNER_ID() {
        return OWNER_ID;
    }

    /**
     * @param OWNER_ID the OWNER_ID to set
     */
    public void setOWNER_ID(Integer OWNER_ID) {
        this.OWNER_ID = OWNER_ID;
    }

    /**
     * @return the OWNER_TYPE_ID
     */
    public Integer getOWNER_TYPE_ID() {
        return OWNER_TYPE_ID;
    }

    /**
     * @param OWNER_TYPE_ID the OWNER_TYPE_ID to set
     */
    public void setOWNER_TYPE_ID(Integer OWNER_TYPE_ID) {
        this.OWNER_TYPE_ID = OWNER_TYPE_ID;
    }

    /**
     * @return the DEADLINE
     */
    public String getDEADLINE() {
        return DEADLINE;
    }

    /**
     * @param DEADLINE the DEADLINE to set
     */
    public void setDEADLINE(String DEADLINE) {
        this.DEADLINE = DEADLINE;
    }

    /**
     * @return the START_TIME
     */
    public Date getSTART_TIME() {
        return START_TIME;
    }

    /**
     * @param START_TIME the START_TIME to set
     */
    public void setSTART_TIME(Date START_TIME) {
        this.START_TIME = START_TIME;
    }

    /**
     * @return the END_TIME
     */
    public Date getEND_TIME() {
        return END_TIME;
    }

    /**
     * @param END_TIME the END_TIME to set
     */
    public void setEND_TIME(Date END_TIME) {
        this.END_TIME = END_TIME;
    }

}
