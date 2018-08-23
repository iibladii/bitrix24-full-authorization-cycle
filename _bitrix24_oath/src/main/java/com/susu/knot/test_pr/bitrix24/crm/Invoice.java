/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.bitrix24.crm;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lazareveugene
 */
public class Invoice {

    public String toQueryString() throws UnsupportedEncodingException {
        List<String> list = new ArrayList<String>();
        list.add("fields[ACCOUNT_NUMBER]=" + URLEncoder.encode(getACCOUNT_NUMBER() + "", "UTF-8"));
        list.add("fields[CURRENCY]=" + URLEncoder.encode(getCURRENCY() + "", "UTF-8"));
        list.add("fields[ORDER_TOPIC]=" + URLEncoder.encode(getORDER_TOPIC() + "", "UTF-8"));
        list.add("fields[STATUS_ID]=" + URLEncoder.encode(getSTATUS_ID() + "", "UTF-8"));
        list.add("fields[PAY_VOUCHER_DATE]=" + URLEncoder.encode(new SimpleDateFormat("YYY-MM-dd").format(getPAY_VOUCHER_DATE()) + "", "UTF-8"));
        list.add("fields[PAY_VOUCHER_NUM]=" + URLEncoder.encode(getPAY_VOUCHER_NUM() + "", "UTF-8"));
        list.add("fields[DATE_BILL]=" + URLEncoder.encode(new SimpleDateFormat("YYY-MM-dd").format(getDATE_BILL()) + "", "UTF-8"));
        list.add("fields[DATE_INSERT]=" + URLEncoder.encode(new SimpleDateFormat("YYY-MM-dd").format(getDATE_BILL()) + "", "UTF-8"));
        list.add("fields[DATE_MARKED]=" + URLEncoder.encode(new SimpleDateFormat("YYY-MM-dd").format(getDATE_BILL()) + "", "UTF-8"));
        list.add("fields[REASON_MARKED]=" + URLEncoder.encode("Счёт оплачен сразу.", "UTF-8"));
        list.add("fields[DATE_PAY_BEFORE]=" + URLEncoder.encode(new SimpleDateFormat("YYY-MM-dd").format(getDATE_PAY_BEFORE()) + "", "UTF-8"));
        list.add("fields[DATE_PAYED]=" + URLEncoder.encode(new SimpleDateFormat("YYY-MM-dd").format(getDATE_PAYED()) + "", "UTF-8"));
        list.add("fields[PAY_SYSTEM_ID]=" + URLEncoder.encode(getPAY_SYSTEM_ID() + "", "UTF-8"));
        list.add("fields[PAY_VOUCHER_NUM]=" + URLEncoder.encode(getPAY_VOUCHER_NUM() + "", "UTF-8"));
        list.add("fields[PERSON_TYPE_ID]=" + URLEncoder.encode(getPERSON_TYPE_ID() + "", "UTF-8"));
        list.add("fields[RESPONSIBLE_ID]=" + URLEncoder.encode(getRESPONSIBLE_ID() + "", "UTF-8"));
        list.add("fields[LID]=" + URLEncoder.encode(getLID() + "" + "", "UTF-8"));
        list.add("fields[UF_CONTACT_ID]=" + URLEncoder.encode(getUF_CONTACT_ID() + "", "UTF-8"));
        list.add("fields[UF_COMPANY_ID]=" + URLEncoder.encode(getUF_COMPANY_ID() + "", "UTF-8"));
        list.add("fields[UF_DEAL_ID]=" + URLEncoder.encode(getUF_DEAL_ID() + "", "UTF-8"));
        list.add("fields[USER_DESCRIPTION]=" + URLEncoder.encode(getUSER_DESCRIPTION() + "", "UTF-8"));
        list.add("fields[INVOICE_PROPERTIES][FIO]=");
        list.add("fields[INVOICE_PROPERTIES][COMPANY]=");
        list.add("fields[INVOICE_PROPERTIES][COMPANY_ADR]=");
        list.add("fields[INVOICE_PROPERTIES][INN]=");
        list.add("fields[INVOICE_PROPERTIES][KPP]=");
        list.add("fields[INVOICE_PROPERTIES][CONTACT_PERSON]=");
        list.add("fields[INVOICE_PROPERTIES][EMAIL]=");
        list.add("fields[INVOICE_PROPERTIES][PHONE]=");
        list.add("fields[INVOICE_PROPERTIES][FAX]=");
        list.add("fields[INVOICE_PROPERTIES][ZIP]=");
        list.add("fields[INVOICE_PROPERTIES][CITY]=");
        list.add("fields[INVOICE_PROPERTIES][LOCATION]=");
        list.add("fields[INVOICE_PROPERTIES][ADDRESS]=");
        int count = 0;
        for (PRODUCT_ROW row : PRODUCT_ROWS) {
            list.add("fields[PRODUCT_ROWS][" + count + "][ID]=" + "" + (count + 1));
            list.add("fields[PRODUCT_ROWS][" + count + "][PRODUCT_ID]=" + URLEncoder.encode(row.getPRODUCT_ID() + "", "UTF-8"));
            list.add("fields[PRODUCT_ROWS][" + count + "][PRODUCT_NAME]=" + URLEncoder.encode(row.getPRODUCT_NAME() + "", "UTF-8"));
            list.add("fields[PRODUCT_ROWS][" + count + "][QUANTITY]=" + URLEncoder.encode(row.getQUANTITY() + "", "UTF-8"));
            list.add("fields[PRODUCT_ROWS][" + count + "][PRICE]=" + URLEncoder.encode(row.getPRICE() + "", "UTF-8"));
            list.add("fields[PRODUCT_ROWS][" + count + "][DISCOUNT_PRICE]=" + URLEncoder.encode(row.getDISCOUNT_PRICE() + "", "UTF-8"));
            count++;
        }
        list.add("params[REGISTER_SONET_EVENT]=Y");
        String Result = "";
        for (String item : list) {
            Result += item + "&";
        }
        return Result.substring(0, Result.length() - 1);
    }

    private String ACCOUNT_NUMBER = "1";
    private String COMMENTS;
    private String CURRENCY = "RUB";
    private Date DATE_BILL = new Date();
    private Date DATE_PAY_BEFORE = new Date();
    private Date DATE_PAYED = new Date();
    private Integer ID;
    private Integer LID = 0;
    private String ORDER_TOPIC = "Оплата";
    private Integer PAY_SYSTEM_ID = 3;
    private Date PAY_VOUCHER_DATE = new Date();
    private String PAY_VOUCHER_NUM;
    private Integer PERSON_TYPE_ID = 3;
    private Double PRICE;
    private Integer RESPONSIBLE_ID = 1;
    private String STATUS_ID = "P";
    private Double TAX_VALUE;
    private Integer UF_COMPANY_ID = 0;
    private Integer UF_CONTACT_ID = 0;
    private Integer UF_DEAL_ID = 0;
    private String USER_DESCRIPTION = "";
    private HashMap<String, String> INVOICE_PROPERTIES;
    private List<PRODUCT_ROW> PRODUCT_ROWS = new ArrayList<PRODUCT_ROW>();

    /**
     * @return the ACCOUNT_NUMBER
     */
    public String getACCOUNT_NUMBER() {
        return ACCOUNT_NUMBER;
    }

    /**
     * @param ACCOUNT_NUMBER the ACCOUNT_NUMBER to set
     */
    public void setACCOUNT_NUMBER(String ACCOUNT_NUMBER) {
        this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
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
     * @return the CURRENCY
     */
    public String getCURRENCY() {
        return CURRENCY;
    }

    /**
     * @param CURRENCY the CURRENCY to set
     */
    public void setCURRENCY(String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    /**
     * @return the DATE_BILL
     */
    public Date getDATE_BILL() {
        return DATE_BILL;
    }

    /**
     * @param DATE_BILL the DATE_BILL to set
     */
    public void setDATE_BILL(Date DATE_BILL) {
        this.DATE_BILL = DATE_BILL;
    }

    /**
     * @return the DATE_PAY_BEFORE
     */
    public Date getDATE_PAY_BEFORE() {
        return DATE_PAY_BEFORE;
    }

    /**
     * @param DATE_PAY_BEFORE the DATE_PAY_BEFORE to set
     */
    public void setDATE_PAY_BEFORE(Date DATE_PAY_BEFORE) {
        this.DATE_PAY_BEFORE = DATE_PAY_BEFORE;
    }

    /**
     * @return the DATE_PAYED
     */
    public Date getDATE_PAYED() {
        return DATE_PAYED;
    }

    /**
     * @param DATE_PAYED the DATE_PAYED to set
     */
    public void setDATE_PAYED(Date DATE_PAYED) {
        this.DATE_PAYED = DATE_PAYED;
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
     * @return the LID
     */
    public Integer getLID() {
        return LID;
    }

    /**
     * @param LID the LID to set
     */
    public void setLID(Integer LID) {
        this.LID = LID;
    }

    /**
     * @return the ORDER_TOPIC
     */
    public String getORDER_TOPIC() {
        return ORDER_TOPIC;
    }

    /**
     * @param ORDER_TOPIC the ORDER_TOPIC to set
     */
    public void setORDER_TOPIC(String ORDER_TOPIC) {
        this.ORDER_TOPIC = ORDER_TOPIC;
    }

    /**
     * @return the PAY_SYSTEM_ID
     */
    public Integer getPAY_SYSTEM_ID() {
        return PAY_SYSTEM_ID;
    }

    /**
     * @param PAY_SYSTEM_ID the PAY_SYSTEM_ID to set
     */
    public void setPAY_SYSTEM_ID(Integer PAY_SYSTEM_ID) {
        this.PAY_SYSTEM_ID = PAY_SYSTEM_ID;
    }

    /**
     * @return the PAY_VOUCHER_DATE
     */
    public Date getPAY_VOUCHER_DATE() {
        return PAY_VOUCHER_DATE;
    }

    /**
     * @param PAY_VOUCHER_DATE the PAY_VOUCHER_DATE to set
     */
    public void setPAY_VOUCHER_DATE(Date PAY_VOUCHER_DATE) {
        this.PAY_VOUCHER_DATE = PAY_VOUCHER_DATE;
    }

    /**
     * @return the PAY_VOUCHER_NUM
     */
    public String getPAY_VOUCHER_NUM() {
        return PAY_VOUCHER_NUM;
    }

    /**
     * @param PAY_VOUCHER_NUM the PAY_VOUCHER_NUM to set
     */
    public void setPAY_VOUCHER_NUM(String PAY_VOUCHER_NUM) {
        this.PAY_VOUCHER_NUM = PAY_VOUCHER_NUM;
    }

    /**
     * @return the PERSON_TYPE_ID
     */
    public Integer getPERSON_TYPE_ID() {
        return PERSON_TYPE_ID;
    }

    /**
     * @param PERSON_TYPE_ID the PERSON_TYPE_ID to set
     */
    public void setPERSON_TYPE_ID(Integer PERSON_TYPE_ID) {
        this.PERSON_TYPE_ID = PERSON_TYPE_ID;
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
     * @return the TAX_VALUE
     */
    public Double getTAX_VALUE() {
        return TAX_VALUE;
    }

    /**
     * @param TAX_VALUE the TAX_VALUE to set
     */
    public void setTAX_VALUE(Double TAX_VALUE) {
        this.TAX_VALUE = TAX_VALUE;
    }

    /**
     * @return the UF_COMPANY_ID
     */
    public Integer getUF_COMPANY_ID() {
        return UF_COMPANY_ID;
    }

    /**
     * @param UF_COMPANY_ID the UF_COMPANY_ID to set
     */
    public void setUF_COMPANY_ID(Integer UF_COMPANY_ID) {
        this.UF_COMPANY_ID = UF_COMPANY_ID;
    }

    /**
     * @return the UF_CONTACT_ID
     */
    public Integer getUF_CONTACT_ID() {
        return UF_CONTACT_ID;
    }

    /**
     * @param UF_CONTACT_ID the UF_CONTACT_ID to set
     */
    public void setUF_CONTACT_ID(Integer UF_CONTACT_ID) {
        this.UF_CONTACT_ID = UF_CONTACT_ID;
    }

    /**
     * @return the UF_DEAL_ID
     */
    public Integer getUF_DEAL_ID() {
        return UF_DEAL_ID;
    }

    /**
     * @param UF_DEAL_ID the UF_DEAL_ID to set
     */
    public void setUF_DEAL_ID(Integer UF_DEAL_ID) {
        this.UF_DEAL_ID = UF_DEAL_ID;
    }

    /**
     * @return the USER_DESCRIPTION
     */
    public String getUSER_DESCRIPTION() {
        return USER_DESCRIPTION;
    }

    /**
     * @param USER_DESCRIPTION the USER_DESCRIPTION to set
     */
    public void setUSER_DESCRIPTION(String USER_DESCRIPTION) {
        this.USER_DESCRIPTION = USER_DESCRIPTION;
    }

    /**
     * @return the INVOICE_PROPERTIES
     */
    public HashMap<String, String> getINVOICE_PROPERTIES() {
        return INVOICE_PROPERTIES;
    }

    /**
     * @param INVOICE_PROPERTIES the INVOICE_PROPERTIES to set
     */
    public void setINVOICE_PROPERTIES(HashMap<String, String> INVOICE_PROPERTIES) {
        this.INVOICE_PROPERTIES = INVOICE_PROPERTIES;
    }

    /**
     * @return the PRODUCT_ROWS
     */
    public List<PRODUCT_ROW> getPRODUCT_ROWS() {
        return PRODUCT_ROWS;
    }

    /**
     * @param PRODUCT_ROWS the PRODUCT_ROWS to set
     */
    public void setPRODUCT_ROWS(List<PRODUCT_ROW> PRODUCT_ROWS) {
        this.PRODUCT_ROWS = PRODUCT_ROWS;
    }

    public PRODUCT_ROW addProductRow(Double PRICE, Double DISCOUNT_PRICE, Integer PRODUCT_ID, String PRODUCT_NAME, Double QUANTITY, Double VAT_RATE) {
        PRODUCT_ROW row = new PRODUCT_ROW();
        row.PRICE = PRICE;
        row.DISCOUNT_PRICE = DISCOUNT_PRICE;
        row.PRODUCT_ID = PRODUCT_ID;
        row.PRODUCT_NAME = PRODUCT_NAME;
        row.QUANTITY = QUANTITY;
        row.VAT_RATE = VAT_RATE;
        PRODUCT_ROWS.add(row);
        return row;
    }

    public class PRODUCT_ROW {

        private Double PRICE;
        private Double DISCOUNT_PRICE;
        private Integer PRODUCT_ID;
        private String PRODUCT_NAME;
        private Double QUANTITY;
        private Double VAT_RATE;
        private String VAT_INCLUDED = "Y";

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
         * @return the DISCOUNT_PRICE
         */
        public Double getDISCOUNT_PRICE() {
            return DISCOUNT_PRICE;
        }

        /**
         * @param DISCOUNT_PRICE the DISCOUNT_PRICE to set
         */
        public void setDISCOUNT_PRICE(Double DISCOUNT_PRICE) {
            this.DISCOUNT_PRICE = DISCOUNT_PRICE;
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
         * @return the VAT_RATE
         */
        public Double getVAT_RATE() {
            return VAT_RATE;
        }

        /**
         * @param VAT_RATE the VAT_RATE to set
         */
        public void setVAT_RATE(Double VAT_RATE) {
            this.VAT_RATE = VAT_RATE;
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
    }
}
