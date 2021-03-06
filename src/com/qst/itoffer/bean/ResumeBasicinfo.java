package com.qst.itoffer.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResumeBasicinfo {
	private Integer basicinfoId;
	private Date birthday;
	private String currentLoc;
	private String email;
	private String gender;
	private String headShot;
	private String jobExpeience;
	private String jobIntension;
	private String realName;
	private String residentLoc;
	private String telephone;
	private String strbirthday;
	
	public ResumeBasicinfo() {
		super();
	}

	public ResumeBasicinfo(Date birthday, String currentLoc, String email, String gender, String headShot,
			String jobExpeience, String jobIntension, String realName, String residentLoc, String telephone) {
		super();
		this.birthday = birthday;
		this.currentLoc = currentLoc;
		this.email = email;
		this.gender = gender;
		this.headShot = headShot;
		this.jobExpeience = jobExpeience;
		this.jobIntension = jobIntension;
		this.realName = realName;
		this.residentLoc = residentLoc;
		this.telephone = telephone;
	}

	public Integer getBasicinfoId() {
		return basicinfoId;
	}

	public void setBasicinfoId(Integer basicinfoId) {
		this.basicinfoId = basicinfoId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.strbirthday = sdf.format(birthday == null ? "" : birthday);
	}
	
	public String getStrBirthdat() {
		return strbirthday;
	}

	
	public void setStrBirthday(String strbirthday) {
		this.strbirthday = strbirthday;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthdayDate = null;
		try {
			birthdayDate = sdf.parse(strbirthday);
		}catch(Exception e) {
			birthdayDate = null;
		}
		this.setBirthday(birthdayDate);
	}
	
	public String getCurrentLoc() {
		return currentLoc;
	}

	public void setCurrentLoc(String currentLoc) {
		this.currentLoc = currentLoc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeadShot() {
		return headShot;
	}

	public void setHeadShot(String headShot) {
		this.headShot = headShot;
	}

	public String getJobExpeience() {
		return jobExpeience;
	}

	public void setJobExpeience(String jobExpeience) {
		this.jobExpeience = jobExpeience;
	}

	public String getJobIntension() {
		return jobIntension;
	}

	public void setJobIntension(String jobIntension) {
		this.jobIntension = jobIntension;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getResidentLoc() {
		return residentLoc;
	}

	public void setResidentLoc(String residentLoc) {
		this.residentLoc = residentLoc;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
