package com.kuber.medicapclassrooms.controller.RegistrationController;

import com.kuber.medicapclassrooms.model.Signup;
import com.kuber.medicapclassrooms.services.Serviceimpl;
import com.kuber.medicapclassrooms.utils.RequestResponseMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterationController extends HttpServlet {
    public Serviceimpl service;
    public RequestResponseMapper mapper ;

    public RegisterationController() {
        this.service = new Serviceimpl();
        this.mapper = new RequestResponseMapper();
    }

    public void doPost(HttpServletRequest req , HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();
        Signup signup = (Signup) mapper.getRequestObject(resp ,req, Signup.class);
        resp.setContentType(MediaType.APPLICATION_JSON);
        if (service.createAccount(signup)) {
            out.print(mapper.setResponseObject(signup));
        }else{
            out.print(mapper.setResponseObject("Account already exist "));
        }
    }
}
