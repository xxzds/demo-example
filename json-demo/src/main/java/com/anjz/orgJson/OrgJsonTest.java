package com.anjz.orgJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * org-json test
 * @author shuai.ding
 *
 * @date 2017年8月12日下午7:24:52
 */
public class OrgJsonTest {
	
	/**
	 * JSONObject的用法
	 * @author shuai.ding
	 * @throws JSONException
	 */
	@Test
	public void testJsonObject() throws JSONException {  
        JSONObject jo = new JSONObject();  
        jo.put("username", "IluckySi");  
        jo.put("age", 27);  
        jo.put("sex", true);  
        Map<String, String> skill = new HashMap<String, String>();  
        skill.put("java", "不错");  
        skill.put("javascript", "凑合");  
        skill.put("jquery", "挺好");  
        jo.put("skill", skill);  
        String username = jo.getString("username");  
        int age = jo.getInt("age");  
        boolean sex = jo.getBoolean("sex");  
        JSONObject skill2 =  jo.getJSONObject("skill");
        System.out.println(username + ", 年龄 = " + age + ", 性别 = " + (sex == true ? "男" : "女") + ", 技能如下:");  
        String[] names = JSONObject.getNames(skill2);  
        for(String name : names) {  
            System.out.println(name + ": " + skill2.getString(name));  
        }  
    }  
	
	
	/**
	 * JSONArray的用法
	 * @author shuai.ding
	 * @throws JSONException
	 */
	@Test
	public void testJsonArray() throws JSONException {  
        JSONArray ja = new JSONArray();  
        JSONObject jo = new JSONObject();  
        jo.put("username", "IluckySi");  
        jo.put("age", 27);  
        jo.put("sex", true);  
        ja.put(jo);  
        JSONObject jo2 = new JSONObject();  
        jo2.put("username", "IluckySi2");  
        jo2.put("age", 28);  
        jo2.put("sex", false);  
        ja.put(jo2);  
        for(int i = 0; i < ja.length(); i++) {  
            JSONObject j = (JSONObject)ja.get(i);  
            String username = j.getString("username");  
            int age = j.getInt("age");  
            boolean sex = j.getBoolean("sex");  
            System.out.println(username + ", 年龄 = " + age + ", 性别 = " + (sex == true ? "男" : "女"));  
        }  
          
    }  
	
	
	/**
	 * JSONObject,JSONArray和集合的复杂用法.
	 * @author shuai.ding
	 * @throws JSONException
	 */
	@Test
	 public void testComplex() throws JSONException {  
	        JSONObject jo = new JSONObject();  
	        jo.put("result", "success");  
	        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();  
	        Map<Object, Object> user1 = new HashMap<Object, Object>();  
	        user1.put("name", "name1");  
	        user1.put("password", "password1");  
	        list.add(user1);  
	        Map<Object, Object> user2 = new HashMap<Object, Object>();  
	        user2.put("name", "name2");  
	        user2.put("password", "password2");  
	        list.add(user2);  
	        jo.put("message", list);  
	        String send = jo.toString();  
	        System.out.println("要测试的消息" + send);  
	        send(send);  
	    }  
	
		private void send(String send) throws JSONException {  
	        JSONObject jo = new JSONObject(send);  
	        String result = jo.getString("result");  
	        System.out.println("result=" + result);        
	        JSONArray ja =  jo.getJSONArray("message");  
	        for(int i = 0; i < ja.length(); i++) {  
	            JSONObject j = (JSONObject)ja.get(i);  
	            String name = j.getString("name");  
	            String password = j.getString("password");  
	            System.out.println("name = " + name + ", password = " + password);  
	        }  
	    }  
	      
		/**
		 * JSONObject,JSONArray和集合的复杂用法.
		 * @author shuai.ding
		 * @param send
		 * @throws JSONException
		 */
		@Test
	    public void testComplex2() throws JSONException {      
	        JSONObject jo = new JSONObject();  
	        jo.put("result", "success");  
	        JSONObject message = new JSONObject();  
	        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();  
	        Map<Object, Object> user1 = new HashMap<Object, Object>();  
	        user1.put("name", "name1");  
	        user1.put("password", "password1");  
	        list.add(user1);  
	        Map<Object, Object> user2 = new HashMap<Object, Object>();  
	        user2.put("name", "name2");  
	        user2.put("password", "password2");  
	        list.add(user2);  
	        message.put("message", list);  
	        jo.put("message", message.toString());//注意:如果直接写message,也不会有转义字符.  
	        String send = jo.toString();  
	        System.out.println("要测试的消息" + send);  
	        send2(send);  
	    }  
	      
	    private void send2(String send) throws JSONException {  
	        JSONObject jo = new JSONObject(send);  
	        String result = jo.getString("result");  
	        System.out.println("result=" + result);  
	       
	        //获取字符串->对象
	        JSONObject message = new JSONObject(jo.getString("message"));  	        
	        JSONArray message2 = message.getJSONArray("message");  
	        for(int i = 0; i < message2.length(); i++) {  
	            JSONObject j = (JSONObject)message2.get(i);  
	            String name = j.getString("name");  
	            String password = j.getString("password");  
	            System.out.println("name = " + name + ", password = " + password);  
	        }  
	    } 
}
