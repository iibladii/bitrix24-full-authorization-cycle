/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author eugene
 */
public class Task {

    private Integer ID;
    private String TITLE = "Новая задача";
    private Integer RESPONSIBLE_ID = null;
    private Integer CREATED_BY = null;
    private String DESCRIPTION = null;
    private String TASK_CONTROL = "N";
    private String ALLOW_CHANGE_DEADLINE = "N";
    private String DEADLINE = "";
    private String STATUS;
    private String ALLOW_TIME_TRACKING = "Y";
    private String ADD_IN_REPORT = "Y";
    private List<String> UF_CRM_TASK = new ArrayList<String>();
    private String REAL_STATUS;
    private List<Integer> AUDITORS = new ArrayList<Integer>();
    private List<Integer> ACCOMPLICES = new ArrayList<Integer>();
    private Integer GROUP_ID = null;
    private String TAGS;

    public String toQueryString() throws UnsupportedEncodingException {
        List<String> list = new ArrayList<String>();
        list.add("0[TITLE]=" + URLEncoder.encode(getTITLE(), "UTF-8"));
        list.add("0[RESPONSIBLE_ID]=" + URLEncoder.encode("" + getRESPONSIBLE_ID(), "UTF-8"));
        if (getCREATED_BY() != null) {
            list.add("0[CREATED_BY]=" + URLEncoder.encode("" + getCREATED_BY(), "UTF-8"));
        }
        list.add("0[DESCRIPTION]=" + URLEncoder.encode(getDESCRIPTION(), "UTF-8"));
        list.add("0[DEADLINE]=" + URLEncoder.encode(getDEADLINE(), "UTF-8"));
        list.add("0[TASK_CONTROL]=" + URLEncoder.encode(getTASK_CONTROL(), "UTF-8"));
        for (Integer item : getAUDITORS()) {
            list.add("0[AUDITORS][]=" + URLEncoder.encode(item + "", "UTF-8"));
        }
        for (String item : getUF_CRM_TASK()) {
            list.add("0[UF_CRM_TASK][]=" + URLEncoder.encode(item + "", "UTF-8"));
        }
        for (Integer item : getACCOMPLICES()) {
            list.add("0[ACCOMPLICES][]=" + URLEncoder.encode(item + "", "UTF-8"));
        }
        list.add("0[GROUP_ID]=" + URLEncoder.encode("" + getGROUP_ID(), "UTF-8"));
        list.add("0[TAGS]=" + URLEncoder.encode(getTAGS(), "UTF-8"));
        list.add("0[ALLOW_TIME_TRACKING]=" + URLEncoder.encode(getALLOW_TIME_TRACKING(), "UTF-8"));
        list.add("0[ADD_IN_REPORT]=" + URLEncoder.encode(getADD_IN_REPORT(), "UTF-8"));
        list.add("0[ALLOW_CHANGE_DEADLINE]=" + URLEncoder.encode(getALLOW_CHANGE_DEADLINE(), "UTF-8"));
        String Result = "";
        for (String item : list) {
            Result += item + "&";
        }
        return Result.substring(0, Result.length() - 1);
    }

    /**
     * @param TAGS the TAGS to set
     */
    public void setTAGS(String TAGS) {
        this.TAGS = TAGS;
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
     * @return the TASK_CONTROL
     */
    public String getTASK_CONTROL() {
        return TASK_CONTROL;
    }

    /**
     * @param ALLOW_CHANGE_DEADLINE the ALLOW_CHANGE_DEADLINE to set
     */
    public void setALLOW_CHANGE_DEADLINE(String ALLOW_CHANGE_DEADLINE) {
        this.ALLOW_CHANGE_DEADLINE = ALLOW_CHANGE_DEADLINE;
    }

    /**
     * @return the ALLOW_CHANGE_DEADLINE
     */
    public String getALLOW_CHANGE_DEADLINE() {
        return ALLOW_CHANGE_DEADLINE;
    }

    /**
     * @param TASK_CONTROL the TASK_CONTROL to set
     */
    public void setTASK_CONTROL(String TASK_CONTROL) {
        this.TASK_CONTROL = TASK_CONTROL;
    }

    /**
     * @return the STATUS
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     * @param STATUS the STATUS to set
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    /**
     * @return the REAL_STATUS
     */
    public String getREAL_STATUS() {
        return REAL_STATUS;
    }

    /**
     * @param REAL_STATUS the REAL_STATUS to set
     */
    public void setREAL_STATUS(String REAL_STATUS) {
        this.REAL_STATUS = REAL_STATUS;
    }

    /**
     * @return the AUDITORS
     */
    public List<Integer> getAUDITORS() {
        return AUDITORS;
    }

    /**
     * @param AUDITORS the AUDITORS to set
     */
    public void setAUDITORS(List<Integer> AUDITORS) {
        this.AUDITORS = AUDITORS;
    }

    /**
     * @return the GROUP_ID
     */
    public Integer getGROUP_ID() {
        return GROUP_ID;
    }

    /**
     * @param GROUP_ID the GROUP_ID to set
     */
    public void setGROUP_ID(Integer GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    /**
     * @return the TAGS
     */
    public String getTAGS() {
        return TAGS;
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
     * @return the ACCOMPLICES
     */
    public List<Integer> getACCOMPLICES() {
        return ACCOMPLICES;
    }

    /**
     * @param ACCOMPLICES the ACCOMPLICES to set
     */
    public void setACCOMPLICES(List<Integer> ACCOMPLICES) {
        this.ACCOMPLICES = ACCOMPLICES;
    }

    /**
     * @return the UF_CRM_TASK
     */
    public List<String> getUF_CRM_TASK() {
        return UF_CRM_TASK;
    }

    /**
     * @param UF_CRM_TASK the UF_CRM_TASK to set
     */
    public void setUF_CRM_TASK(List<String> UF_CRM_TASK) {
        this.UF_CRM_TASK = UF_CRM_TASK;
    }

    /**
     * @return the CREATED_BY
     */
    public Integer getCREATED_BY() {
        return CREATED_BY;
    }

    /**
     * @param CREATED_BY the CREATED_BY to set
     */
    public void setCREATED_BY(Integer CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    /**
     * @return the ALLOW_TIME_TRACKING
     */
    public String getALLOW_TIME_TRACKING() {
        return ALLOW_TIME_TRACKING;
    }

    /**
     * @param ALLOW_TIME_TRACKING the ALLOW_TIME_TRACKING to set
     */
    public void setALLOW_TIME_TRACKING(String ALLOW_TIME_TRACKING) {
        this.ALLOW_TIME_TRACKING = ALLOW_TIME_TRACKING;
    }

    /**
     * @return the ADD_IN_REPORT
     */
    public String getADD_IN_REPORT() {
        return ADD_IN_REPORT;
    }

    /**
     * @param ADD_IN_REPORT the ADD_IN_REPORT to set
     */
    public void setADD_IN_REPORT(String ADD_IN_REPORT) {
        this.ADD_IN_REPORT = ADD_IN_REPORT;
    }

}
