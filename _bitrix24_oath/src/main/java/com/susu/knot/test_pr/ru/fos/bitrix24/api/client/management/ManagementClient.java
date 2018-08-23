/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.client.management;

import org.apache.commons.io.IOUtils;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author eugene
 */
public class ManagementClient {

    public ManagementClient() {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    public ManagementClient(String user, String pass) {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        auth(user, pass);
    }

    public boolean auth(String user, String pass) {

        try {
            //auth
            HttpURLConnection con = (HttpURLConnection) new URL("https://partners.1c-bitrix.ru/personal/b24marketplace/"
                    + "?AUTH_FORM=Y&USER_REMEMBER=Y&USER_PASSWORD=" + pass + "&TYPE=AUTH&USER_LOGIN=" + user).openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                    + "Chrome/29.0.1547.76 Safari/537.36");
            con.setRequestMethod("GET");
            con.setInstanceFollowRedirects(false);
            if (con.getResponseCode() != 302) {
                return false;
            }
            return true;
        } catch (IOException ex) {
        }
        return false;
    }

    public String getInstallAppLink(String app, String host, String ver) {
        if (host.isEmpty()) {
            return null;
        }
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://partners.1c-bitrix.ru/personal/b24marketplace/edit_version.php"
                    + "?app=" + app
                    + "&ver=" + ver
                    + "&install=Y"
                    + "&host=" + host).openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                    + "Chrome/29.0.1547.76 Safari/537.36");
            con.setRequestMethod("GET");
            con.setInstanceFollowRedirects(false);
            if (con.getResponseCode() != 302) {
                return null;
            }
            return con.getHeaderField("Location");
        } catch (IOException ex) {
        }
        return null;
    }

    public String getCoupon(String appCode, String period, String name, String email) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://partners.1c-bitrix.ru/personal/b24marketplace/coupon_edit.php").openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                    + "Chrome/29.0.1547.76 Safari/537.36");
            con.setRequestMethod("GET");
            if (con.getResponseCode() != 200) {
                return null;
            }
            String resp = IOUtils.toString(con.getInputStream(), "windows-1251");
            TagNode html = new HtmlCleaner().clean(new StringReader(resp));
            String sessid = ((TagNode) html.evaluateXPath("//input[@id=\"sessid\"]")[0]).getAttributeByName("value");
            //post coupon
            con = (HttpURLConnection) new URL("https://partners.1c-bitrix.ru/personal/b24marketplace/coupon_edit.php").openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                    + "Chrome/29.0.1547.76 Safari/537.36");

            String urlParameters = "ACTIVE=Y"
                    + "&BUYER_NAME=" + URLEncoder.encode(name, "windows-1251")
                    + "&BUYER_EMAIL=" + URLEncoder.encode(email, "windows-1251")
                    + "&B24MP_MODULE_CODE=" + appCode
                    + "&PERIOD=" + period
                    + "&apply=apply"
                    + "&sessid=" + sessid;

            // Send post request
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("charset", "windows-1251");
            con.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes("windows-1251").length));
            con.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            } catch (IOException ex) {
            }

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return null;
            }
            resp = IOUtils.toString(con.getInputStream(), "windows-1251");
            html = new HtmlCleaner().clean(new StringReader(resp));
            return ((TagNode) html.evaluateXPath("//td[@class='bx-field-value']")[1]).getText().toString().replace("\n", "");

        } catch (IOException | XPatherException ex) {
        }
        return null;
    }

    public int getInstalls(String code, boolean onlyEnglish) throws IOException {
        try {
            HttpURLConnection con;
            String pattern = "";
            if (!onlyEnglish) {
                pattern = "Установок";
                con = (HttpURLConnection) new URL("https://www.bitrix24.ru/apps/?app=" + code).openConnection();
            } else {
                pattern = "Installations";
                con = (HttpURLConnection) new URL("https://www.bitrix24.com/apps/?app=" + code).openConnection();
            }
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36");
            con.setRequestMethod("GET");
            if (con.getResponseCode() != 200) {
                return 0;
            }
            TagNode html = new HtmlCleaner().clean(new StringReader(IOUtils.toString(con.getInputStream(), "UTF-8")));
            Object[] lis = html.evaluateXPath("//ul[@class='r-data']/li");
            for (Object li : lis) {
                TagNode node = (TagNode) li;
                String text = node.getText().toString();
                if (pattern.equals(text.split(": ")[0])) {
                    return Integer.parseInt(text.split(": ")[1]);
                }
            }
        } catch (IOException | XPatherException ex) {
        }
        return 0;
    }

    public int getVenue(String code, int days) {
        try {
            HttpURLConnection con;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, -1 * days);
            con = (HttpURLConnection) new URL("https://partners.1c-bitrix.ru/personal/b24marketplace/sale.php"
                    + "?BUY_DATE_datesel=after&BUY_DATE_from=" + new SimpleDateFormat("dd.MM.yyyy").format(cal.getTime())
                    + "&B24MP_MODULE_CODE[]=" + code).openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36");
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {
                return 0;
            }
            TagNode html = new HtmlCleaner().clean(new StringReader(IOUtils.toString(con.getInputStream(), "UTF-8")));
            return Integer.parseInt(((TagNode) html.evaluateXPath("//table[@class='data-table']/tbody/tr/td")[2]).getText().toString().replaceAll(" ", "").split(",")[0]);
        } catch (IOException | XPatherException ex) {
        }
        return 0;
    }

    public List<String> getClients(String appId, String from) {
        try {
            if (from == null || from.isEmpty()) {
                from = "01.01.2010";
            }
            List<String> lines = new ArrayList<String>();
            String now = new SimpleDateFormat("dd.MM.YYYY").format(new Date());
            String newPage = "https://partners.1c-bitrix.ru/personal/b24marketplace/clients.php?ID=" + appId + "&by=DATE_INSERT&DATE_INSERT_datesel=interval&DATE_INSERT_days=&DATE_INSERT_from=" + from + "&DATE_INSERT_to=" + now;
            while (newPage != null && !newPage.isEmpty()) {
                HttpURLConnection con = (HttpURLConnection) new URL(newPage).openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                        + "Chrome/29.0.1547.76 Safari/537.36");
                con.setRequestMethod("GET");
                if (con.getResponseCode() != 200) {
                    return null;
                }
                String resp = IOUtils.toString(con.getInputStream(), "windows-1251");
                TagNode html = new HtmlCleaner().clean(new StringReader(resp));
                Object[] trs = html.evaluateXPath("//*[@id=\"mp24_client\"]/tbody/tr");
                for (Object tro : trs) {
                    TagNode tr = (TagNode) tro;
                    if (tr.hasAttribute("class") && (tr.getAttributeByName("class").equals("bx-grid-head")
                            || tr.getAttributeByName("class").equals("bx-grid-gutter")
                            || tr.getAttributeByName("class").equals("bx-grid-footer"))) {
                        continue;
                    }
                    if (tr.getChildTags().length < 5) {
                        continue;
                    }
                    String temp = tr.getChildTags()[7].getText().toString();
                    if (temp.lastIndexOf("(") == -1) {
                        temp = " (" + temp + ")";
                    }
                    String comapny = temp.substring(
                            0,
                            temp.lastIndexOf("(") - 1);
                    String person = temp.substring(
                            temp.lastIndexOf("(") + 1,
                            temp.lastIndexOf(")"));
                    lines.add(
                            (tr.getChildTags()[1].getText().toString() + ";" +
                                    tr.getChildTags()[2].getText().toString() + ";" +
                                    tr.getChildTags()[3].getText().toString() + ";" +
                                    tr.getChildTags()[4].getText().toString() + ";" +
                                    //tr.getChildTags()[5].getText().toString() + ";" +
                                    tr.getChildTags()[6].getText().toString() + ";" +
                                    comapny + ";" +
                                    person + ";" +
                                    tr.getChildTags()[8].getText().toString())
                                    .replaceAll("&amp;", "&").replaceAll("&nbsp;", " ").replaceAll("&quot;", "\""));
                }
                TagNode next = html.findElementByAttValue("class", "modern-page-next", true, false);
                if (next != null) {
                    newPage = "https://partners.1c-bitrix.ru" + next.getAttributeByName("href").replaceAll("\\&amp;", "&");
                } else {
                    newPage = "";
                }
            }
            return lines;

        } catch (IOException | XPatherException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<String> getClientsb24(String domain, String from) {
        try {
            if (from == null || from.isEmpty()) {
                from = "01.01.2010";
            }
            List<String> lines = new ArrayList<String>();
            String now = new SimpleDateFormat("dd.MM.YYYY").format(new Date());
            String newPage = "https://partners24.1c-bitrix.ru/personal/clients/?B_AFFILIATE_DATE_datesel=after&B_AFFILIATE_DATE_from=" + from + "&B_DOMAIN=" + domain;
            while (newPage != null && !newPage.isEmpty()) {
                HttpURLConnection con = (HttpURLConnection) new URL(newPage).openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                        + "Chrome/29.0.1547.76 Safari/537.36");
                con.setRequestMethod("GET");
                if (con.getResponseCode() != 200) {
                    return null;
                }
                String resp = IOUtils.toString(con.getInputStream(), "windows-1251");
                TagNode html = new HtmlCleaner().clean(new StringReader(resp));
                Object[] trs = html.evaluateXPath("//*[@id=\"sm_b24_af\"]/tbody/tr");
                for (Object tro : trs) {
                    TagNode tr = (TagNode) tro;
                    if (tr.hasAttribute("class") && (tr.getAttributeByName("class").equals("bx-grid-head")
                            || tr.getAttributeByName("class").equals("bx-grid-gutter")
                            || tr.getAttributeByName("class").equals("bx-grid-footer"))) {
                        continue;
                    }
                    if (tr.getChildTags().length < 5) {
                        continue;

                    }
                    if (tr.getChildTags()[2].getAllChildren().size() < 3) {
                        continue;

                    }
                    String comapny = ((ContentNode) tr.getChildTags()[2].getAllChildren().get(0)).getContent().substring(0,
                            ((ContentNode) tr.getChildTags()[2].getAllChildren().get(0)).getContent().lastIndexOf("(") - 1);
                    String person = ((ContentNode) tr.getChildTags()[2].getAllChildren().get(0)).getContent().substring(
                            ((ContentNode) tr.getChildTags()[2].getAllChildren().get(0)).getContent().lastIndexOf("(") + 1,
                            ((ContentNode) tr.getChildTags()[2].getAllChildren().get(0)).getContent().lastIndexOf(")"));
                    lines.add(
                            (tr.getChildTags()[1].getText().toString() + ";" +
                                    comapny + ";" +
                                    person + ";" +
                                    ((TagNode) tr.getChildTags()[2].getAllChildren().get(2)).getText().toString() + ";" +
                                    tr.getChildTags()[3].getText().toString() + ";" +
                                    (tr.getChildTags()[4].getText().toString() + " ").split(" ")[0] + ";" +
                                    tr.getChildTags()[5].getText().toString() + ";" +
                                    tr.getChildTags()[9].getText().toString() + ";" +
                                    tr.getChildTags()[10].getText().toString())
                                    .replaceAll("&amp;", "&").replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"")
                    );
                }
                TagNode next = html.findElementByAttValue("class", "modern-page-next", true, false);
                if (next != null) {
                    newPage = "https://partners24.1c-bitrix.ru" + next.getAttributeByName("href").replaceAll("\\&amp;", "&");
                } else {
                    newPage = "";
                }
            }
            return lines;

        } catch (IOException | XPatherException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<String> getApps() {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://partners.1c-bitrix.ru/personal/b24marketplace/?SHOWALL_1=1").openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                    + "Chrome/29.0.1547.76 Safari/537.36");
            con.setRequestMethod("GET");
            if (con.getResponseCode() != 200) {
                return null;
            }
            String resp = IOUtils.toString(con.getInputStream(), "windows-1251");
            TagNode html = new HtmlCleaner().clean(new StringReader(resp));
            Object[] trs = html.evaluateXPath("//*[@id=\"apps\"]/tbody/tr");
            List<String> lines = new ArrayList<String>();
            for (Object tro : trs) {
                TagNode tr = (TagNode) tro;
                if (tr.hasAttribute("class") && (tr.getAttributeByName("class").equals("bx-grid-head")
                        || tr.getAttributeByName("class").equals("bx-grid-gutter")
                        || tr.getAttributeByName("class").equals("bx-grid-footer"))) {
                    continue;
                }
                lines.add((tr.getChildTags()[8].getText().toString() + ";" +
                        tr.getChildTags()[1].getText().toString() + ";" +
                        tr.getChildTags()[3].getText().toString() + ";" +
                        tr.getChildTags()[5].getText().toString() + ";" +
                        tr.getChildTags()[7].getText().toString())
                        .replaceAll("&amp;", "&").replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"")
                );
            }
            return lines;

        } catch (IOException | XPatherException ex) {
        }
        return null;
    }

    public static String sessid = "";

    public boolean tryToAddClient(String portal) {
        try {
            HttpURLConnection con;
            if ("".equals(sessid)) {
                con = (HttpURLConnection) new URL("https://partners24.1c-bitrix.ru/personal/clients/b24/add.php").openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                        + "Chrome/29.0.1547.76 Safari/537.36");
                con.setRequestMethod("GET");
                if (con.getResponseCode() != 200) {
                    return false;
                }
                String resp = IOUtils.toString(con.getInputStream(), "windows-1251");
                TagNode html = new HtmlCleaner().clean(new StringReader(resp));
                sessid = ((TagNode) html.evaluateXPath("//input[@id=\"sessid\"]")[0]).getAttributeByName("value");
            }
            //post coupon
            con = (HttpURLConnection) new URL("https://partners24.1c-bitrix.ru/personal/clients/b24/add.php").openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                    + "Chrome/29.0.1547.76 Safari/537.36");

            String urlParameters = "ACTIVE=Y"
                    + "&url=" + URLEncoder.encode(portal, "windows-1251")
                    + "&b24_partner_add_active_tab=tab1"
                    + "&apply=apply"
                    + "&sessid=" + sessid;

            // Send post request
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("charset", "windows-1251");
            con.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes("windows-1251").length));
            con.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            } catch (IOException ex) {
            }

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            return true;

        } catch (IOException | XPatherException ex) {
        }
        return true;
    }
}
