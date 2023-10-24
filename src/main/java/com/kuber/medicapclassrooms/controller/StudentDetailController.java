package com.kuber.medicapclassrooms.controller;

import com.kuber.medicapclassrooms.model.QuizResponseToStudent;
import com.kuber.medicapclassrooms.model.dtos.CLassCodeDto;
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
@WebServlet("/students/classrooms/quizzes")
public class StudentDetailController extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper;

    public StudentDetailController() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }
    @Override// return all quizzes in class of student bases on teacher id
            // classId-> quizinclass-> return tile and descriptin
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        CLassCodeDto cLassCode = (CLassCodeDto) mapper.getRequestObject(resp,req,CLassCodeDto.class);
        List<QuizResponseToStudent> list = service.getAllQuizOfStudentInClass(cLassCode);
        resp.setContentType(MediaType.APPLICATION_JSON);
        out.print(mapper.setResponseObject(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
