package com.kuber.medicapclassrooms.services;
import com.kuber.medicapclassrooms.model.*;
import com.kuber.medicapclassrooms.model.dtos.*;
import com.kuber.medicapclassrooms.utils.DBExecuter;

import java.util.List;

public class Serviceimpl{
	DBExecuter dBexecuter;
	public Serviceimpl() {
		dBexecuter = new DBExecuter();
	}


	public boolean createAccount(Signup signup) {
		return DBExecuter.createAccount(signup);
	}

	public List<Classroom> findAllClass(String teacher) {
		return DBExecuter.findAllClassroom(teacher);
	}

	public Classroom creatNewClass(ClassRoomCreationRequestDto classroom) {
		Classroom newClassroom = new Classroom();
		newClassroom.setEmail(classroom.getEmail());
		newClassroom.setSubject(classroom.getSubject());
		newClassroom.setName(classroom.getName());
		newClassroom.setDescription(classroom.getDescription());
		newClassroom.setId(getUniqueCode());
		return DBExecuter.createNewClass(newClassroom);
	}

	private String getUniqueCode() {
		return DBExecuter.generateClassCode();
	}

	public boolean createNewQuiz(QuizCreationDto newQuiz) {
		return DBExecuter.createNewQuiz(newQuiz);
	}

    public List<QuizRespounseDto> findAllQuiz(QuizRequestDto quizRequest) {
		return DBExecuter.findAllQuiz(quizRequest);
    }

	public boolean deleteQuizById(QuizDeleteReqDto quizDeleteReq) {
		return DBExecuter.deleteQuizById(quizDeleteReq);
	}

	public boolean attachQuestionsToQuiz(QuestionsDto questions) {
		return DBExecuter.attachQuestionsToQuiz(questions);
	}

	public QuestionListResponseDto getQuizDetails(QuizRespounseDto quizRespounse) {
		return DBExecuter.getQuizDetails(quizRespounse);
	}

	public boolean joinStudentInClass(CLassCodeDto joinInclass) {
		return DBExecuter.joinInClass(joinInclass);
	}

    public List<Student> getAllStudentsInClass(CLassCodeDto classCode) {
		return DBExecuter.getAllStudentsInClass(classCode);
    }

	public List<ClassroomResponse> findAllClassOfStudent(StudentIdDto studentId) {
		return DBExecuter.findAllClassOfStudent(studentId);
	}

	public List<QuizResponseToStudent> getAllQuizOfStudentInClass(CLassCodeDto cLassCode) {
		return DBExecuter.getAllQuizOfStudentInClass(cLassCode);
	}

	public List<quizAttemptDetailsResponse> getAttemptQuizDetails(QuizIdDto quizId) {
		return DBExecuter.getAttemptQuizDetails(quizId);
	}

	public boolean submitQuizResponse(QuizSubmitDto quizSubmit) {
		return DBExecuter.submitQuizResponse(quizSubmit);
	}

	public boolean setScoreOfthequiz(QuizSubmitDto quizSubmit) {
		return DBExecuter.setScoreOfthequiz(quizSubmit);
	}

	public List<ScoreResponse> getScoresOfStudentAttendedTheQuiz(QuizIdDto quizId) {
		return DBExecuter.getScoresOfStudentAttendedTheQuiz(quizId);
	}
}
