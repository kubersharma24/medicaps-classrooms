package com.kuber.medicapclassrooms.controller.StudentTeacher;

import com.kuber.medicapclassrooms.model.ClassroomResponse;
import com.kuber.medicapclassrooms.model.dtos.CLassCodeDto;
import com.kuber.medicapclassrooms.model.dtos.StudentIdDto;
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

@WebServlet("/students")
public class StudentController extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper ;

    public StudentController() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }
    @Override// return list of all classes of student
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
//        StudentIdDto studentId = (StudentIdDto) mapper.getRequestObject(resp,req, StudentIdDto.class);
        StudentIdDto studentId = new StudentIdDto();
        studentId.setUserId(req.getParameter("userId"));

        List <ClassroomResponse> list = service.findAllClassOfStudent(studentId);
        resp.setContentType(MediaType.APPLICATION_JSON);
        if(list.size()>0){
        out.print(mapper.setResponseObject(list));
        }else{
            out.print(mapper.setResponseObject(new ArrayList<String>()));
        }
    }

    @Override// join student in class with class code
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        CLassCodeDto joinInclass = (CLassCodeDto) mapper.getRequestObject(resp,req, CLassCodeDto.class);
        resp.setContentType(MediaType.APPLICATION_JSON);
        if(service.checkIfuserHAsAllradyJoinedCLass(joinInclass)){
            out.print(mapper.setResponseObject("302"));
        }else{
            if(service.joinStudentInClass(joinInclass)){
                out.print(mapper.setResponseObject("200"));
            }else{
                out.print(mapper.setResponseObject("500"));
            }
        }

    }

    @Override// de-role from classroom
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        CLassCodeDto classCode = (CLassCodeDto) mapper.getRequestObject(resp,req,CLassCodeDto.class);
        resp.setContentType(MediaType.APPLICATION_JSON);
        if(service.De_roleFromClassByClassIdAndUserId(classCode)){
            out.print(mapper.setResponseObject("200"));
        }else{
            out.print(mapper.setResponseObject("500"));
        }
    }
}
