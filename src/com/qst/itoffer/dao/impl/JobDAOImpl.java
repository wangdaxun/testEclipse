package com.qst.itoffer.dao.impl;

import java.util.ArrayList;

import com.qst.itoffer.bean.Job;

public interface JobDAOImpl {
	public ArrayList<Job> getJobListByCompanyId(Integer companyId);
	public Job getJobById(int jobid);
}
