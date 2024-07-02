package org.exam.client;

import org.exam.model.ExamModel;
import org.exam.model.QuestionModel;
import org.exam.model.ScheduleModel;
import org.exam.model.SubjectModel;
import org.exam.service.ExamService;
import org.exam.service.QuestionService;
import org.exam.service.SubjectService;
import java.util.*;
public class ExamClientApplication {

	public static void main(String[] args) {
		SubjectService sv = new SubjectService();
		QuestionService qService = new QuestionService();
		ExamService examService = new ExamService();
		do {
			System.out.println("1. Add New Subject : ");
			System.out.println("2. Add Single Question : ");
			System.out.println("3. Add Bulk Question : ");
			System.out.println("4. Add Exam");
			System.out.println("5. Create Exam Schedule");
			System.out.println("Enter your choice : ");
			Scanner xyz = new Scanner(System.in);
			int choice = xyz.nextInt();
			switch(choice)
			{
			case 1:
				xyz.nextLine();
				System.out.println( "Enter Subject name : ");
				String subName = xyz.nextLine();
				SubjectModel model = new SubjectModel();
				model.setName(subName);
				int result=sv.addSubject(model);
				if(result==1)
				{
					System.out.println("Subject added Sucess.....");
				}else if(result==-1)
				{
					System.out.println("Subject Alredy Present");
				}else {
					System.out.println("Some Problems is there..");
				}
				break;
			case 2:
				
				xyz.nextLine();
				System.out.println("Enter a Question : ");
				String question = xyz.nextLine();
				System.out.println("Enter Option 1 : ");
				String op1 = xyz.nextLine();
				System.out.println("Enter Option 2 : ");
				String op2 = xyz.nextLine();
				System.out.println("Enter Option 3 : ");
				String op3 = xyz.nextLine();
				System.out.println("Enter Option 4 : ");
				String op4 = xyz.nextLine();
				System.out.println("Enter Option number as answer : ");
				int ans = xyz.nextInt();
				xyz.nextLine();
				System.out.println("Enter Subject Name : ");
				subName = xyz.nextLine();
				
				QuestionModel qModel = new QuestionModel(question ,op1,op2,op3,op4,ans);
				boolean b = qService.isAddQuestion(qModel, subName);
				if(b)
				{
					System.out.println("Question Added Successfully....");
				}else {
					System.out.println("Some Problem is there....");
				}
				break;
			case 3:
				xyz.nextLine();
				System.out.println("Enter a subject name for store question bulk : ");
				String subName1 = xyz.nextLine();
				qService.uploadBulkQuestion(subName1);
				break;
			case 4:
				 xyz.nextLine();
				 System.out.println("Enter exam name total marks and passing marks : ");
				 String examName = xyz.nextLine();
				 int totalMarks = xyz.nextInt();
				 int passMarks = xyz.nextInt();
				 ExamModel examModel = new ExamModel(examName,totalMarks,passMarks);
				 result = examService.isAddExam(examModel);
				 if(result== 1) {
					 System.out.println("Exam Added Successfully...");
				 }else if(result==-1)
				 
					 System.out.println("Exam Already Present..");
				 else{
					 System.out.println("Some Problem is there");
				 }
				break;
			case 5:
				xyz.nextLine();
				List<ExamModel> list = examService.getAllExams();
				System.out.println("List of Exam and Select one for schedule");
				System.out.println("----------------------------");
				for(ExamModel m : list)
				{
					System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getTotalMarks()+"\t"+m.getPassingMarks());
				}
				System.out.println("-----------------------------\n");
				System.out.println("Enter exam name for schedule : ");
				examName = xyz.nextLine();
				ExamModel emodel = examService.getExamIdByName(examName);
				if(emodel!=null)
				{
					System.out.println("Enter exam date start-time and endtime");
					String dateFormate = xyz.nextLine();
					Date d1 = new Date(dateFormate);
					String  startTime = xyz.nextLine();
					String endTime = xyz.nextLine();
					ScheduleModel smodel = new ScheduleModel();
					smodel.setExamDate(d1);
					smodel.setStartTime(startTime);
					smodel.setEndTime(endTime);
					smodel.setExamid(emodel.getId());
					emodel.setScheduleModel(smodel);
					b = examService.isSetSchedule(emodel);
					
				}else {
					System.out.println("Enter a correct exam..");
				}
				break;
			case 6:
				
				break;
			default : 
				System.out.println("Wrong choice");
				break;
			}
		}while(true);
	}

}
// 17 -- 13:00
