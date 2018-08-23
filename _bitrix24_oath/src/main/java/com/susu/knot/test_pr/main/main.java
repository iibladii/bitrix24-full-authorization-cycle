package com.susu.knot.test_pr.main;

import java.io.IOException;
import java.net.URLEncoder;

import com.susu.knot.test_pr.ru.fos.bitrix24.api.client.OAuthClient;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth;

public class main{
	 public static void main(String[] args) throws Throwable {
		 //auth a = OAuthClient.getOAuth(code, domain, client_id, client_secret, redirect_uri, scopes);
		 String code = OAuthClient.getOAuth1("b24-ckaz7s", "local.5ae2861d5d3268.88153159", "localhost");
		 System.out.println(code);
		 int i =9;
	 }
	/*
	https://oauth.bitrix.info/oauth/token/?
    grant_type=authorization_code
    &client_id=local.5ae2861d5d3268.88153159
    &client_secret=	nf7lTiCU0wZ9e0oTooce9LyjvIbnECiQP8Dct7p7U8XJjhCLGn
    &code=avmocpghblyi01m3h42bljvqtyd19sw1
    
    
    
    https:// /oauth/authorize/?response_type=code&client_id=&redirect_uri=
    
    
    
    https://apps-b6405739.bitrix24.ru/oauth/authorize/?response_type=code&client_id=local.5ae2861d5d3268.88153159
    */
}


//https://hello-site.ru/share/avtorizaciya-prilozheniya-bitr/