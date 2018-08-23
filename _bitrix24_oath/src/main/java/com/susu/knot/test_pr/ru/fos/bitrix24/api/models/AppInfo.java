/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

/**
 *
 * @author eugene
 */
public class AppInfo {
    private String CODE;
    private Integer VERSION;
    private String STATUS;
    private String PAYMENT_EXPIRED;
    private Integer DAYS;

    /**
     * @return the CODE
     */
    public String getCode() {
        return CODE;
    }

    /**
     * @param CODE the CODE to set
     */
    public void setCode(String CODE) {
        this.CODE = CODE;
    }

    /**
     * @return the VERSION
     */
    public Integer getVersion() {
        return VERSION;
    }

    /**
     * @param VERSION the VERSION to set
     */
    public void setVersion(Integer VERSION) {
        this.VERSION = VERSION;
    }

    /**
     * @return the STATUS
     */
    public String getStatus() {
        return STATUS;
    }

    /**
     * @param STATUS the STATUS to set
     */
    public void setStatus(String STATUS) {
        this.STATUS = STATUS;
    }

    /**
     * @return the PAYMENT_EXPIRED
     */
    public String getPayment_expired() {
        return PAYMENT_EXPIRED;
    }

    /**
     * @param PAYMENT_EXPIRED the PAYMENT_EXPIRED to set
     */
    public void setPayment_expired(String PAYMENT_EXPIRED) {
        this.PAYMENT_EXPIRED = PAYMENT_EXPIRED;
    }

    /**
     * @return the DAYS
     */
    public Integer getDays() {
        return DAYS;
    }

    /**
     * @param DAYS the DAYS to set
     */
    public void setDays(Integer DAYS) {
        this.DAYS = DAYS;
    }
}
