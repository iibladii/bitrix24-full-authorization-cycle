/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

/**
 * { "CRMCONTACT": ["CRMCONTACT3", "CRMCONTACT7"], "CRMCOMPANY": ["CRMCOMPANY1",
 * "CRMCOMPANY3"], "CRMDEAL": ["CRMDEAL3", "CRMDEAL5"], "CRMLEAD": ["CRMLEAD9",
 * "CRMLEAD11"], "SG": ["SG5", "SG9"], "U": ["U1", "U3"], "DR": ["DR1", "DR7"] }
 *
 * @author eugene
 */
public class BXPerm {

    public String[] CRMCONTACT = new String[0];
    public String[] CRMCOMPANY = new String[0];
    public String[] CRMDEAL = new String[0];
    public String[] CRMLEAD = new String[0];
    public String[] SG = new String[0];
    public String[] U = new String[0];
    public String[] DR = new String[0];

    public String toQueryString() {
        String result = "";
        if (CRMCONTACT.length > 0) {
            for (String contact : CRMCONTACT) {
                result += "&SPERM[CRMCONTACT][]=" + contact;
            }
        }
        if (CRMCOMPANY.length > 0) {
            for (String company : CRMCOMPANY) {
                result += "&SPERM[CRMCOMPANY][]=" + company;
            }
        }
        if (CRMDEAL.length > 0) {
            for (String deal : CRMDEAL) {
                result += "&SPERM[CRMDEAL][]=" + deal;
            }
        }
        if (CRMLEAD.length > 0) {
            for (String lead : CRMLEAD) {
                result += "&SPERM[CRMLEAD][]=" + lead;
            }
        }
        if (SG.length > 0) {
            for (String group : SG) {
                result += "&SPERM[SG][]=" + group;
            }
        }
        if (U.length > 0) {
            for (String user : U) {
                result += "&SPERM[U][]=" + user;
            }
        }
        if (DR.length > 0) {
            for (String dep : DR) {
                result += "&SPERM[DR][]=" + dep;
            }
        }
        return result.length() > 0 ? result.substring(1) : result;
    }
}
