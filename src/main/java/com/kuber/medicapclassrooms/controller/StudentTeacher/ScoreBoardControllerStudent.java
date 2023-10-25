package com.kuber.medicapclassrooms.controller.StudentTeacher;

import com.kuber.medicapclassrooms.model.ScoreResponse;
import com.kuber.medicapclassrooms.model.dtos.QuizIdDto;
import com.kuber.medicapclassrooms.model.dtos.StudentScoreDto;
import com.kuber.medicapclassrooms.services.Serviceimpl;
import com.kuber.medicapclassrooms.utils.RequestResponseMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/students/classrooms/quizzes/score")
public class ScoreBoardControllerStudent extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper;

    public ScoreBoardControllerStudent() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        StudentScoreDto studentScore = (StudentScoreDto) mapper.getRequestObject(resp,req, StudentScoreDto.class);
        resp.setContentType(MediaType.APPLICATION_JSON);
        out.print(mapper.setResponseObject(service.getScoresOfStudentAttendedTheQuizByQuizId(studentScore)));
    }
}
