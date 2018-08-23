/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.models;

/**
 *
 * @author lazareveugene
 */
public class File {

    //идентификатор
    public String ID;
    //название файла
    public String NAME;
    //символьный код
    public String CODE;
    //идентификатор хранилища
    public String STORAGE_ID;

    public String TYPE;
    //идентификатор родительской папки
    public String PARENT_ID;
    //маркер удаления
    public String DELETED_TYPE;
    //время создания
    public String CREATE_TIME;
    //время изменения
    public String UPDATE_TIME;
    //время перемещения в корзину
    public String DELETE_TIME;
    //идентификатор пользователя, который создал файл
    public String CREATED_BY;
    //идентификатор пользователя, который изменил файл
    public String UPDATED_BY;
    //идентификатор пользователя, который переместил в корзину файл
    public String DELETED_BY;
    //ссылка на скачивание контента
    public String DOWNLOAD_URL;
    //ссылка на страницу детальной информации о файле
    public String DETAIL_URL;

}
