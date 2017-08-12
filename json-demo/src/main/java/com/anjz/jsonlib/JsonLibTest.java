package com.anjz.jsonlib;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

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
	        System.out.println("Class c1");
	        System.out.println(jsonObject2);
	    }

}
