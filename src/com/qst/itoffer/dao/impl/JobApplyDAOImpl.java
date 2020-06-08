package com.qst.itoffer.dao.impl;

import java.util.ArrayList;

import com.qst.itoffer.bean.JobApply;

public interface JobApplyDAOImpl {
	public void save(int jobid, int applicantId);
	public ArrayList<JobApply> getJobApplyList(int applicantId);
}
