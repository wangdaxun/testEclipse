package com.qst.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.qst.itoffer.bean.Company;
import com.qst.itoffer.bean.Job;
import com.qst.itoffer.dao.impl.CompanyDAOImpl;
import com.qst.itoffer.util.CRUDTemplate;
import com.qst.itoffer.util.JdbcUtil;

public class CompanyDAO implements CompanyDAOImpl{

	@Override
	public ArrayList<Company> getCompanyList() {
		// TODO Auto-generated method stub
		ArrayList<Company> list = new ArrayList<Company>();
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select TB_COMPANY.COMPANY_ID,COMPANY_PIC,JOB_ID,JOB_NAME,"
					+ "JOB_SALARY,JOB_AREA,JOB_ENDDATE from TB_COMPANY "
					+ "left outer join TB_JOB on TB_JOB.COMPANY_ID=TB_COMPANY.COMPANY_ID "
					+ "where COMPANY_STATE=1 and JOB_ID in("
					+ "select MAX(job_id) from TB_JOB where JOB_STATE=1 group by COMPANY_ID"
					+ ")";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Company company = new Company();	
				Job job = new Job();
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyPic(rs.getString("company_pic"));
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobEnddate(rs.getTimestamp("job_enddate"));
				job.setJobArea(rs.getString("job_area"));
				company.getJobs().add(job);
				list.add(company);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public static void main(String[] args) {
		CompanyDAO companyDAO = new CompanyDAO();
		ArrayList<Company> company = companyDAO.getCompanyPageList(1,4);
		for(Company c : company) {
			System.out.println(c);	
		}
		System.out.println(companyDAO.getAllNum());
	}



	@Override
	public Company getCompanyById(int companyId) {
		// TODO Auto-generated method stub
		Company company = new Company();
		ResultSet rs = null;
		String sql = "select * from tb_company"
				+ " where company_id=?";
		rs = CRUDTemplate.excuteSelect(sql, companyId);
		try {
			while(rs.next()) {
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyArea(rs.getString("company_area"));
				company.setCompanyBrief(rs.getString("company_brief"));
				company.setCompanyPic(rs.getString("company_pic"));
				company.setCompanySize(rs.getString("company_size"));
				company.setCompanyType(rs.getString("company_type"));
				company.setCompanyViewnum(rs.getInt("company_viewnum"));
				company.setCompanyName(rs.getString("company_name"));
				//System.out.println(rs.getString("company_name"));
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return company;
	}



	@Override
	public ArrayList<Company> getCompanyPageList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		ArrayList<Company> list = new ArrayList<Company>();
		// 定义本页记录索引值
		int firstIndex = pageSize * (pageNo-1);
		String sql = "select * from (select a.*, ROWNUM rn from( "
				+ "select tb_company.company_id,company_pic,job_id,"
				+ "job_name,job_salary,job_area,job_enddate "
				+ "from tb_company "
				+ "left outer join tb_job on tb_company.company_id=tb_job.company_id "
				+ "where company_state=1 and job_id in("
				+ "select MAX(job_id) from tb_job where job_state=1 group by company_id"
				+ ")) a where ROWNUM<=?) where rn>? ";
		rs = CRUDTemplate.excuteSelect(sql, firstIndex+pageSize, firstIndex);
		try {
			while(rs.next()) {
				Company company = new Company();
				Job job = new Job();
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyPic(rs.getString("company_pic"));
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobEnddate(rs.getTimestamp("job_enddate"));
				job.setJobArea(rs.getString("job_area"));
				company.getJobs().add(job);
				list.add(company);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}



	@Override
	public int getAllNum() {
		// TODO Auto-generated method stub
		int recordCount = 0;
		ResultSet rs = null;
		String sql = "select count(*) as num from tb_company "
				+ "left outer join tb_job on tb_job.company_id=tb_company.company_id "
				+ "where company_state=1 and job_id in ("
				+ "select MAX(job_id) from tb_job where job_state=1 group by company_id"
				+ ")";
		rs = CRUDTemplate.excuteSelect(sql);
		try {
			if(rs.next()) {
				recordCount = rs.getInt("num");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return recordCount;
	}



	@Override
	public void updateCompanyViewCount(int id) {
		// TODO Auto-generated method stub
		String sql = "update tb_company set "
				+ "company_viewnum=company_viewnum+1 "
				+ "where company_id=?";
		CRUDTemplate.excuteUpdate(sql, id);
	}
	
	
	

}
