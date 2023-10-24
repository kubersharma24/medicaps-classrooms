package com.kuber.medicapclassrooms.controler;

import com.kuber.medicapclassrooms.services.Serviceimpl;
import com.kuber.medicapclassrooms.utils.RequestResponseMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ScoreBoardControllerStudent extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper;

    public ScoreBoardControllerStudent() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
