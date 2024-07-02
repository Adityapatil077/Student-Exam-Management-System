package org.exam.repository;

import java.util.ArrayList;
import java.util.List;

import org.exam.model.ExamModel;
import org.exam.model.ScheduleModel;

public class ExamRepository extends DBConfig {
	List<ExamModel> listExam = new ArrayList<ExamModel>();
	public boolean isExamPresent(String examName)
	{
		try {
			stmt = conn.prepareStatement("select *from examtable where examName = ?");
			stmt.setString(1,examName);
			rs = stmt.executeQuery();
			return rs.next();
		}catch(Exception ex)
		{
			System.out.println("Error is "+ex);
			return false;
		}
	}
	
	public boolean isAddExam(ExamModel model) {
		try {
			stmt = conn.prepareStatement("insert into examtable values('0',?,?,?)");
			stmt.setString(1,model.getName());
			stmt.setInt(2,model.getTotalMarks());
			stmt.setInt(3,model.getPassingMarks());
			int value=stmt.executeUpdate();
			if(value>0)
			{
				return true;
			}else {
				return false;
			}
			
		}catch(Exception ex)
		{
			System.out.println("Error is "+ex);
			return false;
		}
	}
	public List<ExamModel> getAllExams()
	{
		try {
			stmt = conn.prepareStatement("select *from examtable");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				ExamModel model = new ExamModel();
				model.setId(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setTotalMarks(rs.getInt(3));
				model.setPassingMarks(rs.getInt(4));
				listExam.add(model);
			}
			return listExam.size()>0?listExam:null;
			
		}catch(Exception ex)
		{
			System.out.println("Error is "+ ex);
			return null;
		}
	}
	public ExamModel getExamIdByName(String name)
	{
		try {
			stmt = conn.prepareStatement("select * from examtable where examname = '"+name+"'");
			rs=stmt.executeQuery();
			ExamModel model = null;
			if(rs.next())
			{
				model = new ExamModel();
				model.setId(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setTotalMarks(rs.getInt(3));
				model.setPassingMarks(rs.getInt(4));
				
			}
			return model!=null?model:null;
		}catch(Exception ex)
		{
			System.out.println("Error is : "+ex);
			return null;
		}
	}
	
	int getScheduleId()
	{
		int count = 0;
		try {
			stmt = conn.prepareStatement("select max(schid) from schedule");
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			++count;
			return count;
		}catch(Exception ex)
		{
			System.out.println("Error is "+ex);
			return 0;
		}
	}
	public boolean isSetSchedule(ExamModel model)
	{
		try {
			int schid = this.getScheduleId();
			if(schid!=0)
			{
				ScheduleModel smodel=model.getScheduleModel();
				String examDate = smodel.getExamDate().toLocaleString();
				System.out.println(examDate);
			}else {
				System.out.println("Some Problem is there...");
			}
			return true;
		}catch(Exception ex)
		{
			System.out.println("Error is "+ ex);
			return false;
		}
	}
}
