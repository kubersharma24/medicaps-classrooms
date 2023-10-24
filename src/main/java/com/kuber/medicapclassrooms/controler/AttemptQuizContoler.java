package com.kuber.medicapclassrooms.controler;

import com.kuber.medicapclassrooms.model.dtos.QuizIdDto;
import com.kuber.medicapclassrooms.model.dtos.QuizSubmitDto;
import com.kuber.medicapclassrooms.model.quizAttemptDetailsResponse;
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
import java.util.*;

@WebServlet("/students/classrooms/quizzes/quiz")
public class AttemptQuizContoler extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper;

    public AttemptQuizContoler() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }


    @Override// get delails for attempting the quiz by quiz id
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        QuizIdDto quizId = (QuizIdDto) mapper.getRequestObject(resp,req,QuizIdDto.class);
        List <quizAttemptDetailsResponse> list = service.getAttemptQuizDetails(quizId);
        resp.setContentType(MediaType.APPLICATION_JSON);
        out.print(mapper.setResponseObject(list));
    }

    @Override// submit the quiz response and will generate the quiz score
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        QuizSubmitDto quizSubmit = (QuizSubmitDto) mapper.getRequestObject(resp,req,QuizSubmitDto.class);
        resp.setContentType(MediaType.APPLICATION_JSON);
        if(service.submitQuizResponse(quizSubmit)){
            if(service.setScoreOfthequiz(quizSubmit)){
                out.print(mapper.setResponseObject(quizSubmit));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
