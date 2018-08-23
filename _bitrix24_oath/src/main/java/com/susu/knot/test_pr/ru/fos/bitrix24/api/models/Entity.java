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
public class Entity<T> {

    public int ID;
    public String TIMESTAMP_X;
    public int MODIFIED_BY;
    public String DATE_CREATE;
    public int CREATED_BY;
    public String ACTIVE;
    public String DATE_ACTIVE_FROM;
    public String DATE_ACTIVE_TO;
    public int SORT;
    public String NAME;
    public Object PREVIEW_PICTURE;
    public Object PREVIEW_TEXT;
    public Object DETAIL_PICTURE;
    public Object DETAIL_TEXT;
    public Object CODE;
    public String ENTITY;
    public Object SECTION;
    public T PROPERTY_VALUES;
}
