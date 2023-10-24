package com.kuber.medicapclassrooms.controler;

import com.kuber.medicapclassrooms.model.dtos.QuizCreationDto;
import com.kuber.medicapclassrooms.model.dtos.QuizDeleteReqDto;
import com.kuber.medicapclassrooms.model.dtos.QuizRequestDto;
import com.kuber.medicapclassrooms.model.dtos.QuizRespounseDto;
import com.kuber.medicapclassrooms.services.Serviceimpl;
import com.kuber.medicapclassrooms.utils.RequestResponseMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/teacher/classrooms/quiz")
public class QuizController extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper ;

    public QuizController() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }

    @Override // return all available quiz
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        QuizRequestDto quizRequest = (QuizRequestDto) mapper.getRequestObject(resp,req,QuizRequestDto.class);
        List <QuizRespounseDto> list = service.findAllQuiz(quizRequest);
        resp.setContentType(MediaType.APPLICATION_JSON);
        out.print(mapper.setResponseObject(list));
    }

    @Override // create a new quiz
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        QuizCreationDto NewQuiz = (QuizCreationDto) mapper.getRequestObject(resp,req, QuizCreationDto.class);
        resp.setContentType(MediaType.APPLICATION_JSON);
        if(service.createNewQuiz(NewQuiz)){
            out.print(mapper.setResponseObject(NewQuiz));
        }else{
            out.print("Bad Request");
        }

    }

    @Override// delete quiz
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        QuizDeleteReqDto quizDeleteReq= (QuizDeleteReqDto) mapper.getRequestObject(resp,req,QuizDeleteReqDto.class);
        resp.setContentType(MediaType.APPLICATION_JSON);
        if(service.deleteQuizById(quizDeleteReq)){
            out.print(mapper.setResponseObject("200 OK"));
        }else{
            out.print(mapper.setResponseObject("500"));
        }
    }
}
