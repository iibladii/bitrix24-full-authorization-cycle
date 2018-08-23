/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

/**
 *
 * @author eugene
 */
public class auth1 {

    public String member_id;

    @Id
    public String domain;

    public String access_token;

    public long expires_in;

    private String scope;

    public String refresh_token;

    /**
     * @return the scope
     */
    public List<String> getScope() {
        return Arrays.asList(scope.split(","));
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(List<String> scope) {
        this.scope = StringUtils.join(scope, ",");
    }

    public auth toAuth() {

    	com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth au = new com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth();
        au.access_token = access_token;
        au.domain = domain;
        au.expires_in = expires_in;
        au.member_id = member_id;
        au.refresh_token = refresh_token;
        au.scope = getScope().toArray(new String[0]);
        return au;
    }
}
