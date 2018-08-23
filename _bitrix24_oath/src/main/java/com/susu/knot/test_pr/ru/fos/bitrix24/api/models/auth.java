/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

import org.springframework.data.annotation.Id;

/**
 *
 * @author eugene
 */
public class auth {
    
    @Id
    public String domain;
    
    public String member_id;

    public String access_token;

    public long expires_in;

    public String[] scope;

    public String refresh_token;
}
