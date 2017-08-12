package com.anjz.jsonlib;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JApplet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;
import net.sf.json.xml.XMLSerializer;

/**
 * json-lib test
 * http://blog.csdn.net/zk673820543/article/details/51332529
 * @author shuai.ding
 *
 * @date 2017年8月9日下午5:45:15
 */
public class JsonLibTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonLibTest.class);

	@Test
	public void array2json() {
		int[] intArray = new int[] { 1, 2, 3, 4, 5 };
		JSONArray jsonArray = JSONArray.fromObject(intArray);
		LOGGER.info(jsonArray.toString());

		boolean[] boolArray = new boolean[] { true, false, true };
		JSONArray jsonArray2 = JSONArray.fromObject(boolArray);
		LOGGER.info(jsonArray2.toString());

		int[][] int2Array = new int[][] { { 1, 2 }, { 3, 4 } };
		JSONArray jsonArray3 = JSONArray.fromObject(int2Array);
		LOGGER.info(jsonArray3.toString());

		float[] floatArray = new float[] { 0.1f, 0.2f, 0.3f };
		JSONArray jsonArray4 = JSONArray.fromObject(floatArray);
		LOGGER.info(jsonArray4.toString());

		String[] strArray = new String[] { "hello", "hebut", "xiapi" };
		JSONArray jsonArray5 = JSONArray.fromObject(strArray);
		LOGGER.info(jsonArray5.toString());
	}

	@Test
	public void collections2json() {
		List<String> list1 = new ArrayList<String>();
		list1.add("first");
		list1.add("second");
		JSONArray jsonArray1 = JSONArray.fromObject(list1);
		LOGGER.info("List list1:{}", jsonArray1);

		List<Student> list2 = new ArrayList<Student>();
		list2.add(new Student("xiapi1", "男", 10));
		list2.add(new Student("xiapi2", "女", 11));
		list2.add(new Student("xiapi3", "男", 12));
		JSONArray jsonArray2 = JSONArray.fromObject(list2);
		LOGGER.info("List<Student> list2:{}", jsonArray2);
	}

	@Test
	public void map2json() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "json");
		map1.put("bool", Boolean.TRUE);
		map1.put("int", new Integer(1));
		map1.put("arr", new String[] { "a", "b" });
		map1.put("func", "function(i){ return this.arr[i]; }");
		JSONObject jsonObject1 = JSONObject.fromObject(map1);
		LOGGER.info("Map map1:{}", jsonObject1);

		Map<String, Student> map2 = new HashMap<String, Student>();
		map2.put("k1", new Student("xiapi1", "男", 10));
		map2.put("k2", new Student("xiapi2", "女", 12));
		map2.put("k3", new Student("xiapi3", "男", 13));
		JSONObject jsonObject2 = JSONObject.fromObject(map2);
		LOGGER.info("Map<String,Student> map2:{}",jsonObject2);
	}
	
	
	 @Test
	    public void bean2json(){
	        Student s1 = new Student("xiapi","男",22);
	        JSONObject jsonObject1 = JSONObject.fromObject(s1);
	        LOGGER.info("Student s1:{}",jsonObject1);
	       
	        Class c1 = new Class();
	        c1.setName("计算机应用1班");
	        c1.setDate(new Date());
	        JsonConfig config=new JsonConfig();
	        //设置循环策略为忽略
	        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
	        //设置 json转换的处理器用来处理日期类型
	        //凡是反序列化Date类型的对象，都会经过该处理器进行处理
	        config.registerJsonValueProcessor(Date.class,
	                                    new JsonValueProcessor() {
	            //参数1 ：属性名参数2：json对象的值参数3：jsonConfig对象
	            public Object processObjectValue(String arg0, Object arg1,JsonConfig arg2) {
	                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                Date d=(Date) arg1;
	                return sdf.format(d);
	            }
	            public Object processArrayValue(Object arg0, JsonConfig arg1) {
	                return null;
	            }
	        });
	        List<Student> students = new ArrayList<Student>();
	        students.add(new Student("xiapi1","男",10));
	        students.add(new Student("xiapi2","女",11));
	        students.add(new Student("xiapi3","男",12));
	        c1.setStudents(students);
	        JSONObject jsonObject2 = JSONObject.fromObject(c1,config);
	        LOGGER.info("Class c1:{}",jsonObject2);
	    }
	 
	 @Test
	    public void xml2json(){
	        String s="<student><name id='n1'>xiapi</name><sex class='s1'>男</sex><age>20</age></student>";
	        XMLSerializer x =new XMLSerializer();
	        JSON json = x.read(s);
	        LOGGER.info("XmlToJson:{}",json.toString());
	    }
	 
	 @Test
	    public void json2arrays(){
	        String json1 = "['first','second']";
	        JSONArray jsonArray1 = (JSONArray) JSONSerializer.toJSON(json1);
	        JsonConfig jsonConfig = new JsonConfig();
	        jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
	        Object[] output1 = (Object[]) JSONSerializer.toJava(jsonArray1, jsonConfig);
	        LOGGER.info("output1:{}",output1.toString());
	       
	        String json2 ="[[1,2],[3,4]]";
	        JSONArray jsonArray2 = JSONArray.fromObject(json2);
	        Object[][] output2 = (Object[][])JSONArray.toArray(jsonArray2);
	        LOGGER.info("Object[][]:{}",output2.toString());
	    }
	 
	 @Test
	    public void json2collections(){
	        String json1 = "['first','second']";
	        JSONArray jsonArray1 = (JSONArray) JSONSerializer.toJSON(json1);
	        List<?> output1 = (List<?>) JSONSerializer.toJava(jsonArray1);
	        System.out.println("List");
	        System.out.println(output1.get(0));
	       
	        String json2 = "[{'age':10,'sex':'男','userName':'xiapi1'},{'age':11,'sex':'女','userName':'xiapi2'}]";
	        JSONArray jsonArray2 = JSONArray.fromObject(json2);
	        List<Student> output2 = JSONArray.toList(jsonArray2,Student.class);
	        System.out.println("List<Student>");
	        System.out.println(output2.size());
	        System.out.println(output2.get(0));
	        System.out.println(output2.get(0).getUserName());
	    }
	 
	 @Test
	    public void json2map(){
	        String json1 ="{'arr':['a','b'],'int':1,'name':'json','bool':true}";
	        JSONObject jsonObject1 = JSONObject.fromObject(json1);
	        Map<String,Object> typeMap1 = new HashMap<String,Object>();
	        typeMap1.put("arr", String[].class);
	        typeMap1.put("int", Integer.class);
	        typeMap1.put("name", String.class);
	        typeMap1.put("bool", Boolean.class);
	        Map<String,Object> output1 = (Map<String,Object>)JSONObject.toBean(jsonObject1, Map.class,typeMap1);
	        System.out.println("Map");
	        System.out.println(output1.size());
	        System.out.println(output1.get("name"));
	        System.out.println(output1.get("arr"));
	       
	        String json2 ="{'k1':{'age':10,'sex':'男','userName':'xiapi1'},'k2':{'age':12,'sex':'女','userName':'xiapi2'}}";
	        JSONObject jsonObject2 = JSONObject.fromObject(json2);
	        Map<String,java.lang.Class<?>> typeMap2 =new HashMap<String,java.lang.Class<?>>();
	        Map<String,Student> output2 = (Map<String,Student>)JSONObject.toBean(jsonObject2,Map.class,typeMap2);
	        System.out.println("Map<String,Student>");
	        System.out.println(output2.size());
	        System.out.println(output2.get("k1"));
	       
	        //先往注册器中注册变换器，需要用到ezmorph包中的类
	        MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
	        Morpher dynaMorpher = new BeanMorpher(Student.class,morpherRegistry);
	        morpherRegistry.registerMorpher(dynaMorpher);
	 
	        System.out.println(((Student)morpherRegistry.morph(Student.class,output2.get("k2"))).getUserName());
	    }
	 
	 @Test
	    public void json2bean(){
	        //简单对象
	        String json1 = "{'age':22,'sex':'男','userName':'xiapi'}";
	        JSONObject jsonObject1 = JSONObject.fromObject(json1);
	        Student output1 = (Student)JSONObject.toBean(jsonObject1,Student.class);
	        System.out.println("Student");
	        System.out.println(output1.getUserName());
	       
	        //复杂对象
	        String json2 = "{'date':'2012-05-21 13:03:11','name':'计算机应用1班','students':[{'age':10,'sex':'男','userName':'xiapi1'},{'age':11,'sex':'女','userName':'xiapi2'}]}";
	        //转为日期
	        String[] DATE_FORMAT = { "yyyy-MM-dd HH:mm:ss" };
	        MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
	        morpherRegistry.registerMorpher(new DateMorpher(DATE_FORMAT));
	        JSONObject jsonObject2 = JSONObject.fromObject(json2);
	        Map<String, Object> typeMap1 = new HashMap<String, Object>();
	        typeMap1.put("date", Date.class);
	        typeMap1.put("name",String.class);
	        typeMap1.put("students", Student.class);
	        Class output2 = (Class)JSONObject.toBean(jsonObject2,Class.class,typeMap1);
	        System.out.println("Class");
	        System.out.println(output2.getName());
	        System.out.println(output2.getDate());
	        System.out.println(output2.getStudents().get(0).getUserName());
	    }
	 
	 @Test
	    public void json2xml(){
	        String json1 = "{'age':22,'sex':'男','userName':'xiapi'}";
	        JSONObject jsonObj = JSONObject.fromObject(json1);
	        XMLSerializer x = new XMLSerializer();
	        String xml = x.write(jsonObj);
	        System.out.println("XML");
	        System.out.println(xml);
	    }
	 
	 @Test
	    public void testJSONArray() {
	        JSONArray jsonArray = new JSONArray();
	        jsonArray.add(0,"第一个值");
	        jsonArray.add(1,"第二个值");
	        jsonArray.add(2,"第三个值");
	        System.out.print(jsonArray.toString());
	    }
	 
	 @Test
	    public void testJSONObject() {
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("name", "xiapi");
	        jsonObject.put("age", 20);
	        jsonObject.put("sex", "男");
	        JSONArray jsonArray = new JSONArray();
	        jsonArray.add("唱歌");
	        jsonArray.add("摄影");
	        jsonArray.add("象棋");
	        jsonObject.element("hobby",jsonArray);
	        System.out.println(jsonObject);
	    }

}
