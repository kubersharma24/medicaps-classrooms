package com.kuber.medicapclassrooms.controller.TeacherContoller;

import com.kuber.medicapclassrooms.model.dtos.QuizIdDto;
import com.kuber.medicapclassrooms.services.Serviceimpl;
import com.kuber.medicapclassrooms.utils.RequestResponseMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/teacher/classrooms/quiz/status/off")
public class DeActivateQuiz extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper ;

    public DeActivateQuiz() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        QuizIdDto quizIdDto = (QuizIdDto) mapper.getRequestObject(resp,req, QuizIdDto.class);
        if(service.setQuizStatusToOFF(quizIdDto)){
            out.print(mapper.setResponseObject("200"));
        }else{
            out.print(mapper.setResponseObject("500"));
        }

    }
}
