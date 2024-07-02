package org.exam.service;

import org.exam.helper.PathHelper;
import org.exam.model.QuestionModel;
import org.exam.repository.QuestionRepository;
import java.io.*;
import java.util.List;

public class QuestionService {
	QuestionRepository qRepo = new QuestionRepository();
	SubjectService ss = new SubjectService();
	public boolean isAddQuestion(QuestionModel qModel, String subName)
	{
		
		return qRepo.isAddQuestion(qModel, subName);
	}
	public boolean createFiles()
	{
		try {
			String path = "C:\\Users\\HP\\eclipse-workspace\\ConsleExamApplication\\bin\\questionBank";
			File f = new File(path);
			if(!f.exists())
			{
				f.mkdir();
			}
			List<String> subList = ss.getAllSubjects();
			if(subList!=null) {
				for(String subName:subList)
				{
					f = new File(path+"\\"+subName+".csv");
					if(!f.exists())
					{
						f.createNewFile();
					}
				}
			}else {
				System.out.println("Subject Not Found");
			}
			
		}catch(Exception ex)
		{
			System.out.println("Error is : "+ex);
		}
		return true;
	}
	
	public boolean uploadBulkQuestion(String subName)
	{
		boolean b = this.createFiles();
		if(b)
		{
			File f = new File("C:\\Users\\HP\\eclipse-workspace\\ConsleExamApplication\\bin\\questionBank");
			File []fileList = f.listFiles();
			boolean flag = false;
			for(File file:fileList)
			{
				if(file.isFile())
				{
					int index = file.toString().indexOf(subName);
					if(index!= -1) {
						flag = true;
						break;
					}
				}
			}
			if(flag)
			{
				try {
					FileReader fr = new FileReader(PathHelper.filePath+"\\"+subName+".csv");
					BufferedReader br = new BufferedReader(fr);
					String question;
					flag = false;
					while((question=br.readLine())!=null)
					{
						String qWithop[] = question.split(",");
						flag = qRepo.uploadBulkQuestion(qWithop,subName);
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}else {
				System.out.println("Subject file not found");
			}
//		System.out.println(flag?"Subject file found":"Subject file not found");
			
			return true;
		}else
			return false;
	}
}
