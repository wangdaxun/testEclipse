package com.qst.itoffer.dao.impl;

import com.qst.itoffer.bean.ResumeBasicinfo;

public interface ResumeDAOImpl {
	public int add(ResumeBasicinfo basicinfo,int applicantID);
	public void updateHeadShot(int basicinfoId, String newFileName);
	public ResumeBasicinfo selectBasicinfoById(int applicantId);
	public int update(ResumeBasicinfo basicinfo);
}
