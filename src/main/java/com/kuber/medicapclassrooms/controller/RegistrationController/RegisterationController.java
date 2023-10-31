package com.kuber.medicapclassrooms.controller.RegistrationController;

import com.kuber.medicapclassrooms.model.LoginResponse;
import com.kuber.medicapclassrooms.model.Logininfo;
import com.kuber.medicapclassrooms.model.Signup;
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

@WebServlet("/register/login")
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
            out.print("bad req");
        }

    }

    @Override// this get req return the role of the user trying to login to the the web app
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Logininfo logininfo = (Logininfo) mapper.getRequestObject(resp,req, Logininfo.class);
        resp.setContentType(MediaType.APPLICATION_JSON);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(logininfo.getEmail());
        loginResponse.setPassword(logininfo.getPassword());
        if (service.isValidUser(logininfo)) {
            if(service.getRole(logininfo).equalsIgnoreCase("Student")){
                loginResponse.setStatus("Student");
            }else{
                loginResponse.setStatus("Teacher");
            }
        } else {
            if (service.isValidEmail(logininfo)) {
                if (!service.isValidPassword(logininfo)) {
                    loginResponse.setStatus("Incorrect password");
                }
            } else {
                    loginResponse.setStatus("NO user found");
            }
        }
        out.print(mapper.setResponseObject(loginResponse));
    }
}
