package com.anjz.jackson;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson提供了一系列注解，方便对JSON序列化和反序列化进行控制，下面介绍一些常用的注解。
 * 
 * @JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性。
 * 
 * @JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern =
 *             "yyyy-MM-dd HH-mm-ss")。
 * 
 * @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，
 *               @JsonProperty("name")。
 * @author shuai.ding
 *
 * @date 2017年8月9日下午5:09:25
 */
public class User2 {

	private String name;

	// 不JSON序列化年龄属性
	@JsonIgnore
	private Integer age;

	// 格式化日期属性
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date birthday;

	// 序列化email属性为mail
	@JsonProperty("mail")
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User2 [name=" + name + ", age=" + age + ", birthday=" + birthday + ", email=" + email + "]";
	}
}
