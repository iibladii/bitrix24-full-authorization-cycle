/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

/**
 * {"result":{"TYPE":"cloud","CONFIG_ID":"17","REG_ID":5111,"SERVER":"sip.primatel.ru","LOGIN":"asdf","PASSWORD":"123","TITLE":"test"}}
 * @author eugene
 */
public class TelephonyResponse {

    public String TYPE;
    public int CONFIG_ID;
    public int REG_ID;
    public String SERVER;
    public String LOGIN;
    public String PASSWORD;
    public String TITLE;
}
