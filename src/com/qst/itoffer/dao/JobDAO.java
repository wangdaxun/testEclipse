package com.qst.itoffer.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.qst.itoffer.bean.Company;
import com.qst.itoffer.bean.Job;
import com.qst.itoffer.dao.impl.JobDAOImpl;
import com.qst.itoffer.util.CRUDTemplate;

public class JobDAO implements JobDAOImpl{

	@Override
	public ArrayList<Job> getJobListByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		ArrayList<Job> list = new ArrayList<Job>();
		ResultSet rs = null;
		String sql = "select * from tb_job where company_id=?";
		try {
			rs = CRUDTemplate.excuteSelect(sql, companyId);
			while(rs.next()) {
				Job job = new Job();
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobEnddate(rs.getTimestamp("job_enddate"));
				job.setApplyNum(rs.getInt("job_applynum"));
				list.add(job);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		JobDAO jobDao = new JobDAO();
		ArrayList<Job> list = jobDao.getJobListByCompanyId(3);
		for(Job job : list) {
			System.out.println(job.getJobArea());
		}
	}
	@Override
	public Job getJobById(int jobid) {
		// TODO Auto-generated method stub
		Job job = new Job();
		ResultSet rs = null;
		String sql = "select tb_job.*,company_pic "
				+ "from tb_job inner join tb_company "
				+ "on tb_job.company_id=tb_company.company_id "
				+ "where job_id=?";
		rs = CRUDTemplate.excuteSelect(sql, jobid);
		try {
			while(rs.next()){
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobHiringnum(rs.getInt("job_hiringnum"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobDesc(rs.getString("job_desc"));
				job.setJobEnddate(rs.getTimestamp("job_enddate"));
				job.setJobState(rs.getInt("job_state"));
				Company company = new Company();
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyPic(rs.getString("company_pic"));
				job.setCompany(company);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return job;
	}

}
