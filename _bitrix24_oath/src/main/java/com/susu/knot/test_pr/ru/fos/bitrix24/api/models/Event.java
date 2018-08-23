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
public class Event {
    public String event;
    public String handler;
    public String auth_type;

    public Event(String event, String handler, String auth_type) {
        this.event = event;
        this.handler = handler;
        this.auth_type = auth_type;
    }
}
