package org.exam.repository;

import java.util.ArrayList;
import java.util.List;

import org.exam.model.SubjectModel;

public class SubjectRepository extends DBConfig {
	List<String> list = new ArrayList<String>();
	// For Adding Subject in Database
	public boolean isAddSubject(SubjectModel model)
	{
		try {
			stmt = conn.prepareStatement("insert into subject values('0',?)");
			stmt.setString(1,model.getName());
			int value=stmt.executeUpdate();
			if(value>0)
			{
				return true;
			}else {
				return false;
			}
		}catch(Exception ex)
		{
			System.out.println("Error is ex "+ex);
			return false;
		}
	}
	
	// To check Duplicate Subject is Present 
	
	public boolean isSubjectPresent(String subName)
	{
		try {
			stmt = conn.prepareStatement("select *from subject where Subname = ? ");
			stmt.setString(1, subName);
			rs=stmt.executeQuery();
			return rs.next();
		}catch(Exception ex)
		{
			return false;
		}
	}
	
	public List<String> getAllSubjects(){
		try {
			stmt = conn.prepareStatement("select Subname from subject");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				list.add(rs.getString("Subname"));
			}
			return list.size()>0?list:null;
		}catch(Exception ex)
		{
			System.out.println("Error is : "+ex);
			return null;
		}
	}
}
