package org.example.asm_2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.asm_2.model.UserEntity;
import org.example.asm_2.service.Imple.UserImple;
import org.example.asm_2.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/login"
})
public class Login extends HttpServlet {
    public  UserService userService = new UserImple();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Login/Login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("email");
        String pass =req.getParameter("password");
        boolean checktk = false;
        List<UserEntity> userEntityList = userService.findAll();
        UserEntity userEntity = new UserEntity();
        for (UserEntity entity :userEntityList) {
            if (entity.getEmail().equals(email) || entity.getPassword().equals(pass)){
                checktk =true;
                userEntity = entity;
            }
        }

        if (checktk == true){
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("UserEntity",userEntity);
            boolean checkAdmin = false;
            if (userEntity.getAdmin() == 1){
                checkAdmin = true;
            }
            req.setAttribute("checkAdmin",checkAdmin);
            req.getRequestDispatcher("/menu.jsp").forward(req,resp);
        } else {
            req.setAttribute("message", "Sai tài khoản và mật khẩu");
            req.getRequestDispatcher("/Login/Login.jsp").forward(req,resp);
        }
    }
}
