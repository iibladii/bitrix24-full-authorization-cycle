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
public class Product  {


    private String ACTIVE;
    private Integer CATALOG_ID;
    private String CURRENCY_ID;
    private String DESCRIPTION;
    private Integer ID;
    private Integer MEASURE;
    private String NAME;
    private Double PRICE;
    private Integer SECTION_ID;
    private Integer SORT;
    private Integer VAT_ID;
    private String VAT_INCLUDED;
    private Integer XML_ID;

    public Product() {

    }

    public Product(String NAME, Integer MEASURE, Double PRICE) {
        this.NAME = NAME;
        this.MEASURE = MEASURE;
        this.PRICE = PRICE;
    }

    /**
     * @return the ACTIVE
     */
    public String getACTIVE() {
        return ACTIVE;
    }

    /**
     * @param ACTIVE the ACTIVE to set
     */
    public void setACTIVE(String ACTIVE) {
        this.ACTIVE = ACTIVE;
    }

    /**
     * @return the CATALOG_ID
     */
    public Integer getCATALOG_ID() {
        return CATALOG_ID;
    }

    /**
     * @param CATALOG_ID the CATALOG_ID to set
     */
    public void setCATALOG_ID(Integer CATALOG_ID) {
        this.CATALOG_ID = CATALOG_ID;
    }

    /**
     * @return the CURRENCY_ID
     */
    public String getCURRENCY_ID() {
        return CURRENCY_ID;
    }

    /**
     * @param CURRENCY_ID the CURRENCY_ID to set
     */
    public void setCURRENCY_ID(String CURRENCY_ID) {
        this.CURRENCY_ID = CURRENCY_ID;
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
     * @return the MEASURE
     */
    public Integer getMEASURE() {
        return MEASURE;
    }

    /**
     * @param MEASURE the MEASURE to set
     */
    public void setMEASURE(Integer MEASURE) {
        this.MEASURE = MEASURE;
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
     * @return the SECTION_ID
     */
    public Integer getSECTION_ID() {
        return SECTION_ID;
    }

    /**
     * @param SECTION_ID the SECTION_ID to set
     */
    public void setSECTION_ID(Integer SECTION_ID) {
        this.SECTION_ID = SECTION_ID;
    }

    /**
     * @return the SORT
     */
    public Integer getSORT() {
        return SORT;
    }

    /**
     * @param SORT the SORT to set
     */
    public void setSORT(Integer SORT) {
        this.SORT = SORT;
    }

    /**
     * @return the VAT_ID
     */
    public Integer getVAT_ID() {
        return VAT_ID;
    }

    /**
     * @param VAT_ID the VAT_ID to set
     */
    public void setVAT_ID(Integer VAT_ID) {
        this.VAT_ID = VAT_ID;
    }

    /**
     * @return the VAT_INCLUDED
     */
    public String getVAT_INCLUDED() {
        return VAT_INCLUDED;
    }

    /**
     * @param VAT_INCLUDED the VAT_INCLUDED to set
     */
    public void setVAT_INCLUDED(String VAT_INCLUDED) {
        this.VAT_INCLUDED = VAT_INCLUDED;
    }

    /**
     * @return the XML_ID
     */
    public Integer getXML_ID() {
        return XML_ID;
    }

    /**
     * @param XML_ID the XML_ID to set
     */
    public void setXML_ID(Integer XML_ID) {
        this.XML_ID = XML_ID;
    }
}
