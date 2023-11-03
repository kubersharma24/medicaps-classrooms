package com.kuber.medicapclassrooms.controller.TeacherContoller;

import com.kuber.medicapclassrooms.model.dtos.CLassCodeDto;
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
@WebServlet("/teachers/classrooms/classroom")
public class CLassControlerForOneCLass extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper ;

    public CLassControlerForOneCLass() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        CLassCodeDto cLassCodeDto = new CLassCodeDto();
        cLassCodeDto.setClassCode(req.getParameter("classId"));
        resp.setContentType(MediaType.APPLICATION_JSON);
        out.print(mapper.setResponseObject(service.getCLassDetails(cLassCodeDto)));
    }
}
