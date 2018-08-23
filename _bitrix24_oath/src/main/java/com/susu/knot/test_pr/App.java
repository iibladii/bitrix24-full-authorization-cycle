package com.susu.knot.test_pr;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App 
{
	//https://www.bitrix24.net/auth?AUTH_FORM=Y&TYPE=AUTH&backurl=127.0.0.1&USER_LOGIN=super.blad2011@yandex.ru&USER_PASSWORD=megroup2megroup2&USER_REMEMBER=Y
	//https://b24-3xfjtm.bitrix24.ru/auth?AUTH_FORM=Y&TYPE=AUTH&backurl=127.0.0.1&USER_LOGIN=super.blad2011@yandex.ru&USER_PASSWORD=megroup2megroup2&USER_REMEMBER=Y
	//
    public static void main( String[] args ) throws IOException
    {
    	String code = getOAuth1("b24-ckaz7s", "local.5ae2861d5d3268.88153159");
        System.out.println( code );
    }
    
    public static String getOAuth1(String portal_name, String client_id) throws IOException {
        //HttpURLConnection con = (HttpURLConnection) new URL("https://"+portal_name+".bitrix24.ru/oauth/authorize/?response_type=code&client_id="+client_id).openConnection();
        HttpURLConnection con = (HttpURLConnection) new URL("https://b24-3xfjtm.bitrix24.ru/auth?AUTH_FORM=Y&TYPE=AUTH&backurl=127.0.0.1&USER_LOGIN=super.blad2011@yandex.ru&USER_PASSWORD=megroup2megroup2&USER_REMEMBER=Y").openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        }
        String res = IOUtils.toString(con.getInputStream(), "UTF-8");
        return res;
        //return new Gson().fromJson(res, );
    }
}


//https://afinogen.su/article/polucenie-sessionnogo-tokena-dla-prilozenia-bitrix24