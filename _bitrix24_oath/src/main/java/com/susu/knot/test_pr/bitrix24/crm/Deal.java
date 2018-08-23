/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.bitrix24.crm;

/**
 *
 * @author lazareveugene
 */
public class Deal  {


    private Integer ID;
    private String TITLE = "Новая сделка";
    private Integer ASSIGNED_BY_ID = null;
    private String COMMENTS = "";

    private String ADDITIONAL_INFO = "";
    private String BEGINDATE = "NEW";
    private String CLOSED = "N";
    private String CLOSEDATE = "";
    private String COMPANY_ID = "";
    private String TAX_VALUE = "0";
    private String CONTACT_ID = "";
    private Integer CREATED_BY_ID = null;
    private String DATE_CREATE = "";
    private String DATE_MODIFY = "";
    private String LEAD_ID = "";
    private Integer MODIFY_BY_ID = null;
    private String OPENED = "Y";
    private String OPPORTUNITY = "100";
    private Integer ORIGINATOR_ID = null;
    private Integer ORIGIN_ID = null;
    private String PROBABILITY = "";
    private String TYPE_ID = "";

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
     * @return the ADDITIONAL_INFO
     */
    public String getADDITIONAL_INFO() {
        return ADDITIONAL_INFO;
    }

    /**
     * @param ADDITIONAL_INFO the ADDITIONAL_INFO to set
     */
    public void setADDITIONAL_INFO(String ADDITIONAL_INFO) {
        this.ADDITIONAL_INFO = ADDITIONAL_INFO;
    }

    /**
     * @return the BEGINDATE
     */
    public String getBEGINDATE() {
        return BEGINDATE;
    }

    /**
     * @param BEGINDATE the BEGINDATE to set
     */
    public void setBEGINDATE(String BEGINDATE) {
        this.BEGINDATE = BEGINDATE;
    }

    /**
     * @return the CLOSED
     */
    public String getCLOSED() {
        return CLOSED;
    }

    /**
     * @param CLOSED the CLOSED to set
     */
    public void setCLOSED(String CLOSED) {
        this.CLOSED = CLOSED;
    }

    /**
     * @return the CLOSEDATE
     */
    public String getCLOSEDATE() {
        return CLOSEDATE;
    }

    /**
     * @param CLOSEDATE the CLOSEDATE to set
     */
    public void setCLOSEDATE(String CLOSEDATE) {
        this.CLOSEDATE = CLOSEDATE;
    }

    /**
     * @return the COMPANY_ID
     */
    public String getCOMPANY_ID() {
        return COMPANY_ID;
    }

    /**
     * @param COMPANY_ID the COMPANY_ID to set
     */
    public void setCOMPANY_ID(String COMPANY_ID) {
        this.COMPANY_ID = COMPANY_ID;
    }

    /**
     * @return the TAX_VALUE
     */
    public String getTAX_VALUE() {
        return TAX_VALUE;
    }

    /**
     * @param TAX_VALUE the TAX_VALUE to set
     */
    public void setTAX_VALUE(String TAX_VALUE) {
        this.TAX_VALUE = TAX_VALUE;
    }

    /**
     * @return the CONTACT_ID
     */
    public String getCONTACT_ID() {
        return CONTACT_ID;
    }

    /**
     * @param CONTACT_ID the CONTACT_ID to set
     */
    public void setCONTACT_ID(String CONTACT_ID) {
        this.CONTACT_ID = CONTACT_ID;
    }

    /**
     * @return the CREATED_BY_ID
     */
    public Integer getCREATED_BY_ID() {
        return CREATED_BY_ID;
    }

    /**
     * @param CREATED_BY_ID the CREATED_BY_ID to set
     */
    public void setCREATED_BY_ID(Integer CREATED_BY_ID) {
        this.CREATED_BY_ID = CREATED_BY_ID;
    }

    /**
     * @return the DATE_CREATE
     */
    public String getDATE_CREATE() {
        return DATE_CREATE;
    }

    /**
     * @param DATE_CREATE the DATE_CREATE to set
     */
    public void setDATE_CREATE(String DATE_CREATE) {
        this.DATE_CREATE = DATE_CREATE;
    }

    /**
     * @return the DATE_MODIFY
     */
    public String getDATE_MODIFY() {
        return DATE_MODIFY;
    }

    /**
     * @param DATE_MODIFY the DATE_MODIFY to set
     */
    public void setDATE_MODIFY(String DATE_MODIFY) {
        this.DATE_MODIFY = DATE_MODIFY;
    }

    /**
     * @return the LEAD_ID
     */
    public String getLEAD_ID() {
        return LEAD_ID;
    }

    /**
     * @param LEAD_ID the LEAD_ID to set
     */
    public void setLEAD_ID(String LEAD_ID) {
        this.LEAD_ID = LEAD_ID;
    }

    /**
     * @return the MODIFY_BY_ID
     */
    public Integer getMODIFY_BY_ID() {
        return MODIFY_BY_ID;
    }

    /**
     * @param MODIFY_BY_ID the MODIFY_BY_ID to set
     */
    public void setMODIFY_BY_ID(Integer MODIFY_BY_ID) {
        this.MODIFY_BY_ID = MODIFY_BY_ID;
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
     * @return the OPPORTUNITY
     */
    public String getOPPORTUNITY() {
        return OPPORTUNITY;
    }

    /**
     * @param OPPORTUNITY the OPPORTUNITY to set
     */
    public void setOPPORTUNITY(String OPPORTUNITY) {
        this.OPPORTUNITY = OPPORTUNITY;
    }

    /**
     * @return the ORIGINATOR_ID
     */
    public Integer getORIGINATOR_ID() {
        return ORIGINATOR_ID;
    }

    /**
     * @param ORIGINATOR_ID the ORIGINATOR_ID to set
     */
    public void setORIGINATOR_ID(Integer ORIGINATOR_ID) {
        this.ORIGINATOR_ID = ORIGINATOR_ID;
    }

    /**
     * @return the ORIGIN_ID
     */
    public Integer getORIGIN_ID() {
        return ORIGIN_ID;
    }

    /**
     * @param ORIGIN_ID the ORIGIN_ID to set
     */
    public void setORIGIN_ID(Integer ORIGIN_ID) {
        this.ORIGIN_ID = ORIGIN_ID;
    }

    /**
     * @return the PROBABILITY
     */
    public String getPROBABILITY() {
        return PROBABILITY;
    }

    /**
     * @param PROBABILITY the PROBABILITY to set
     */
    public void setPROBABILITY(String PROBABILITY) {
        this.PROBABILITY = PROBABILITY;
    }

    /**
     * @return the TYPE_ID
     */
    public String getTYPE_ID() {
        return TYPE_ID;
    }

    /**
     * @param TYPE_ID the TYPE_ID to set
     */
    public void setTYPE_ID(String TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

}
