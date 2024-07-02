package org.exam.service;

import java.util.Date;
import java.util.List;

import org.exam.model.ExamModel;
import org.exam.model.ScheduleModel;
import org.exam.repository.ExamRepository;

public class ExamService {
	ExamRepository examRepo = new ExamRepository();
	public int isAddExam(ExamModel model)
	{
		if(examRepo.isExamPresent(model.getName()))
		{
			return -1;
		}else if(examRepo.isAddExam(model))
		{
			return 1;
		}else {
			return 0;
		}
	}
	public List<ExamModel> getAllExams()
	{
		return examRepo.getAllExams();
	}
	public ExamModel getExamIdByName(String name)
	{
		return examRepo.getExamIdByName(name);
	}
	public boolean isSetSchedule(ExamModel model)
	{
		Date d = new Date();
		String []s=d.toLocaleString().split(",");
		String dsplit[] =s[0].split("-");
		ScheduleModel smodel = model.getScheduleModel();
		Date userDate = smodel.getExamDate();
		String userDateArr[] = userDate.toLocaleString().split(",");
		String userDates[] = userDateArr[0].split("-");
		if(Integer.parseInt(userDates[2])>=Integer.parseInt(dsplit[2]) && userDates[1].equals(dsplit[1]))
		{
			if(Integer.parseInt(userDates[0])>=Integer.parseInt(dsplit[0]))
			{
				System.out.println("You can Schedule Exam...");
				boolean b = examRepo.isSetSchedule(model);
			}else {
				System.out.println("You may be insert Month date before system..");
			}
			
		}else {
			System.out.println("you may be insert Year date before system");
		}
		return false;
	}
}

// 17-- 9.55