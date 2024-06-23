package org.example.asm_2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.asm_2.model.UserEntity;

@WebServlet(name = "Menu", value = "/menu")
public class Menu extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("UserEntity");
        boolean checkAdmin = false;
        if (userEntity.getAdmin()==1){
            checkAdmin=true;
        }
        request.setAttribute("checkAdmin",checkAdmin);
        request.getRequestDispatcher("/menu.jsp").forward(request,response);
    }

}