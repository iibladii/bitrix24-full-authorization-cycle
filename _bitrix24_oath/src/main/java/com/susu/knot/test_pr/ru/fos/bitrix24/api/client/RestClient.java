/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.susu.knot.test_pr.ru.fos.bitrix24.api.client;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author eugene
 */
public class RestClient {

    public static LinkedTreeMap<String, LinkedTreeMap<String, Object>> getCrmCompanyFields(String domain, String authId) {

        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + domain + "/rest/crm.company.fields.json"
                    + "?full=true"
                    + "&auth=" + authId).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new LinkedTreeMap<String, LinkedTreeMap<String, Object>>();
            }

            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<LinkedTreeMap<String, LinkedTreeMap<String, Object>>>) new Gson().fromJson(response, new TypeToken<ResultOne<LinkedTreeMap<String, LinkedTreeMap<String, Object>>>>() {
            }.getType())).result;
        } catch (IOException ex) {
        }
        return new LinkedTreeMap<String, LinkedTreeMap<String, Object>>();
    }

    public static Integer addCrmActivity(Activity activity, auth oauth) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/crm.activity.add.json").openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(activity.toQueryString()
                + "&auth=" + oauth.access_token);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        }
        String response = IOUtils.toString(con.getInputStream(), "UTF-8");
        return ((ResultOne<Integer>) new Gson().fromJson(response, new TypeToken<ResultOne<Integer>>() {
        }.getType())).result;
    }

    public static String toQueryString(String fchar, Map<String, String> fields, String group) {
        if (fields == null || fields.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> field : fields.entrySet()) {
            String key = group + "[" + field.getKey() + "]";
            String val = field.getValue();
            if (field.getKey().contains("[")) {
                key = group + "[" + field.getKey().substring(0, field.getKey().indexOf("[")) + "]"
                        + field.getKey().substring(field.getKey().indexOf("["));
            }
            try {
                sb.append("&").append(key).append("=").append(URLEncoder.encode(val, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                sb.append("&").append(key).append("=").append(val);
            }
        }
        return fchar + sb.toString().substring(1);
    }

    public static String toQueryString(String fchar, Map<String, String> fields) {
        if (fields == null || fields.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> field : fields.entrySet()) {
            String key = field.getKey();
            String val = field.getValue();
            try {
                sb.append("&").append(key).append("=").append(URLEncoder.encode(val, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                sb.append("&").append(key).append("=").append(val);
            }
        }
        return fchar + sb.toString().substring(1);
    }

    public static String toQueryStringArray(String fchar, Map<String, String[]> fields) {
        if (fields == null || fields.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> field : fields.entrySet()) {
            String key = field.getKey();
            String[] val = field.getValue();
            int count = 0;
            for (String v : val) {
                try {
                    sb.append("&").append(key).append("[").append(count++).append("]").append("=").append(URLEncoder.encode(v, "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    sb.append("&").append(key).append("=").append(v);
                }
            }
        }
        return fchar + sb.toString().substring(1);
    }

    public static <T> List<Entity<T>> getEntityItem(T typeOfT, String ENTITY, String FILTER, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/entity.item.get.json"
                    + "?ENTITY=" + ENTITY
                    + "&FILTER=" + FILTER
                    + "&auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ArrayList<Entity<T>>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<Entity<T>>) new Gson().fromJson(response, new TypeToken<ResultMany<Entity<T>>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return new ArrayList<Entity<T>>();
    }

    public static <T> ResultMany<Entity<T>> getEntityItem(T typeOfT, String ENTITY, String FILTER, int start, auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/entity.item.get.json"
                    + "?ENTITY=" + ENTITY
                    + "&FILTER=" + FILTER
                    + "&start=" + start
                    + "&auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ResultMany<Entity<T>>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return (ResultMany<Entity<T>>) new Gson().fromJson(response, new TypeToken<ResultMany<Entity<T>>>() {
            }.getType());
        } catch (IOException e) {
        }
        return new ResultMany<Entity<T>>();
    }

    public static Integer addTaskItem(auth oauth, Task task) {
        HashSet<String> temp = new HashSet<String>();
        String[] tags = task.getTAGS().split(",");
        for (String item : tags) {
            if (!temp.contains(item)) {
                temp.add(item);
            }
        }
        task.setTAGS(StringUtils.join(temp.toArray(), ','));
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/task.item.add.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(task.toQueryString()
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return -1;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Integer>) new Gson().fromJson(response, new TypeToken<ResultOne<Integer>>() {
            }.getType())).result;
        } catch (IOException ex) {
        }
        return -1;
    }

    public static boolean addFileToTaskItem(auth oauth, String Filename, String file, String id) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/task.item.addfile.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("TASK_ID=" + id
                    + "&FILE[NAME]=" + URLEncoder.encode(Filename, "UTF-8")
                    + "&FILE[CONTENT]=" + URLEncoder.encode(file, "UTF-8")
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            return true;
        } catch (IOException ex) {
        }
        return false;
    }

    public static boolean updateTaskItem(auth oauth, int taskId, String filedName, String filedVal) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/task.item.update.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("0=" + taskId
                    + "&1[" + filedName + "]=" + filedVal
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            return true;
        } catch (IOException ex) {
        }
        return false;
    }

    public static List<String> getTaskItemTags(auth oauth, int taskId) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/task.item.gettags.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("0=" + taskId
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ArrayList<String>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<String>) new Gson().fromJson(response, new TypeToken<ResultMany<String>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return new ArrayList<String>();
    }

    public static Task getTaskItemData(auth oauth, int taskId) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/task.item.getdata.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("0=" + taskId
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new Task();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Task>) new Gson().fromJson(response, new TypeToken<ResultOne<Task>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return new Task();
    }

    public static boolean deleteTaskItem(auth oauth, int taskId) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/task.item.delete.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("0=" + taskId
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            return true;
        } catch (IOException e) {
        }
        return false;
    }
    
    public static List<Task> getTaskItemsList(auth oauth, String filter) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/task.items.getlist.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("0[ID]=desc"
                    //+ "&" + filter
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ArrayList<Task>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<Task>) new Gson().fromJson(response, new TypeToken<ResultMany<Task>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return new ArrayList<Task>();
    }

    public static String appInfoStr(auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/app.info.json"
                    + "?auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return "";
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return response;
        } catch (IOException e) {
        }
        return "";
    }
    
    public static AppInfo appInfo(auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/app.info.json"
                    + "?auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new AppInfo();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<AppInfo>) new Gson().fromJson(response, new TypeToken<ResultOne<AppInfo>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return new AppInfo();
    }

    public static List<Event> eventGet(auth oauth) throws MalformedURLException, ProtocolException, IOException {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/event.get.json"
                    + "?auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return new ArrayList<Event>();
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<Event>) new Gson().fromJson(response, new TypeToken<ResultMany<Event>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return new ArrayList<Event>();
    }

    public static boolean eventBind(auth oauth, Event event) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/event.bind.json"
                    + "?auth=" + oauth.access_token
                    + "&event=" + event.event
                    + "&handler=" + event.handler
                    + "&auth_type=" + event.auth_type).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Boolean>) new Gson().fromJson(response, new TypeToken<ResultOne<Boolean>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return false;
    }

    public static boolean eventUnbind(auth oauth, Event event) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/event.unbind.json"
                    + "?auth=" + oauth.access_token
                    + "&event=" + event.event
                    + "&handler=" + event.handler
                    + "&auth_type=" + event.auth_type).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return "1".equals(((ResultOne<LinkedTreeMap<String, String>>) new Gson().fromJson(response,
                    new TypeToken<ResultOne<LinkedTreeMap<String, String>>>() {
                    }.getType())).result.get("count"));
        } catch (IOException e) {
        }
        return false;
    }

    public static Integer addBlogpostLog(auth oauth, String POST_TITLE, String POST_MESSAGE, BXPerm perms) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/log.blogpost.add.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("auth=" + oauth.access_token
                    + "&POST_MESSAGE=" + URLEncoder.encode(POST_MESSAGE, "UTF-8")
                    + "&POST_TITLE=" + URLEncoder.encode(POST_TITLE, "UTF-8")
                    + "&" + perms.toQueryString());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return 0;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Integer>) new Gson().fromJson(response, new TypeToken<ResultOne<Integer>>() {
            }.getType())).result;
        } catch (IOException e) {
        }
        return 0;
    }

    public static List<LinkedTreeMap<String, Object>> getBlogpostLog(auth oauth) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/log.blogpost.get.json"
                    + "?auth=" + oauth.access_token).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return null;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<LinkedTreeMap<String, Object>>) new Gson().fromJson(response,
                    new TypeToken<ResultMany<LinkedTreeMap<String, Object>>>() {
                    }.getType())).result;
        } catch (IOException e) {
        }
        return null;
    }

    public static boolean imNotify(auth oauth, String to, String message) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/im.notify.json"
                    + "?auth=" + oauth.access_token
                    + "&to=" + to
                    + "&type=SYSTEM"
                    + "&message=" + URLEncoder.encode(message, "UTF-8")).openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
            return true;
        } catch (IOException e) {
        }
        return false;
    }

    public static File uploadFileToDiskFolder(auth oauth, String id, String name, byte[] file) {
        if (file.length == 0) {
            return null;
        }
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/disk.folder.uploadfile.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("id=" + id
                    + "&data[NAME]=" + URLEncoder.encode(name, "UTF-8")
                    + "&fileContent[0]=" + URLEncoder.encode(name, "UTF-8")
                    + "&fileContent[1]=" + URLEncoder.encode(new Base64().encodeToString(file), "UTF-8")
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.err.println(IOUtils.toString(con.getErrorStream(), "UTF-8"));
                return null;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<File>) new Gson().fromJson(response, new TypeToken<ResultOne<File>>() {
            }.getType())).result;
        } catch (IOException ex) {
        }
        return null;
    }

    public static Folder makeStorageFolder(auth oauth, String storageId, String name) {
        if (name.isEmpty()) {
            return null;
        }
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/disk.storage.addfolder.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("id=" + storageId
                    + "&data[NAME]=" + URLEncoder.encode(name, "UTF-8")
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.err.println(IOUtils.toString(con.getErrorStream(), "UTF-8"));
                return null;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultOne<Folder>) new Gson().fromJson(response, new TypeToken<ResultOne<Folder>>() {
            }.getType())).result;
        } catch (IOException ex) {
        }
        return null;
    }

    public static Folder makeSubFolder(auth oauth, String folderId, String name) throws Exception {
        if (name == null || name.isEmpty()) {
            throw new Exception("name is empty");
        }
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/disk.folder.addsubfolder.json").openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("id=" + folderId
                + "&data[NAME]=" + URLEncoder.encode(name, "UTF-8")
                + "&auth=" + oauth.access_token);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("response code is " + responseCode);
        }
        String response = IOUtils.toString(con.getInputStream(), "UTF-8");
        return ((ResultOne<Folder>) new Gson().fromJson(response, new TypeToken<ResultOne<Folder>>() {
        }.getType())).result;
    }

    public static List<Folder> getSubFolders(auth oauth, String folderId, String name) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/disk.folder.getchildren.json").openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("id=" + folderId
                + ((name == null || name.isEmpty()) ? "" : "&filter[NAME]=" + URLEncoder.encode(name, "UTF-8"))
                + "&auth=" + oauth.access_token);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("response code " + responseCode);
        }
        String response = IOUtils.toString(con.getInputStream(), "UTF-8");
        return ((ResultMany<Folder>) new Gson().fromJson(response, new TypeToken<ResultMany<Folder>>() {
        }.getType())).result;
    }

    public static List<Folder> getFolders(auth oauth, String storageId, String name) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/disk.storage.getchildren.json").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("id=" + storageId
                    + ((name == null || name.isEmpty()) ? "" : "&filter[NAME]=" + URLEncoder.encode(name, "UTF-8"))
                    + "&auth=" + oauth.access_token);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.err.println(IOUtils.toString(con.getErrorStream(), "UTF-8"));
                return null;
            }
            String response = IOUtils.toString(con.getInputStream(), "UTF-8");
            return ((ResultMany<Folder>) new Gson().fromJson(response, new TypeToken<ResultMany<Folder>>() {
            }.getType())).result;
        } catch (IOException ex) {
        }
        return null;
    }

    public static List<Storage> getStorage(auth oauth, String type) throws Exception {
        if (type == null || type.isEmpty()) {
            throw new Exception("type is empty");
        }
        HttpURLConnection con = (HttpURLConnection) new URL("https://" + oauth.domain + "/rest/disk.storage.getlist.json").openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("filter[ENTITY_TYPE]=" + type
                + "&auth=" + oauth.access_token);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("response code " + responseCode + "\n" + IOUtils.toString(con.getErrorStream()));
        }
        String response = IOUtils.toString(con.getInputStream(), "UTF-8");
        return ((ResultMany<Storage>) new Gson().fromJson(response, new TypeToken<ResultMany<Storage>>() {
        }.getType())).result;
    }
}
