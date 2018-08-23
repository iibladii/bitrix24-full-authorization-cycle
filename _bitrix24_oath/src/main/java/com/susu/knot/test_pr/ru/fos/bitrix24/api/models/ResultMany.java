/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

import java.util.List;

/**
 *
 * @author eugene
 */
public class ResultMany<T> {

    public List<T> result;
    public int total;
}
