package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.example.asm_2.model.UserEntity;
import org.example.asm_2.service.Imple.UserImple;
import org.example.asm_2.service.UserService;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
        "/user",
        "/user/add",
        "/user/hien-thi",
        "/user/delete/*",
        "/user/update/*"
})
public class UserServlet extends HttpServlet {
    public UserService userService = new UserImple();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("delete")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            userService.remove(req,resp,id);
            resp.sendRedirect("/user/hien-thi");
        }
        if (URI.contains("hien-thi")){
            req.setAttribute("Item",userService.findAll());
            req.getRequestDispatcher("/webs/UserWeb.jsp").forward(req,resp);
        }
        else if (URI.contains("add")){
            req.getRequestDispatcher("/webs/AddUser.jsp").forward(req,resp);
        }
        else if (URI.contains("update")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            req.setAttribute("form",userService.find(id));
            req.getRequestDispatcher("/webs/UpdateUser.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        UserEntity userEntity =new UserEntity();

        try {
            BeanUtils.populate(userEntity,req.getParameterMap());
            String password = req.getParameter("Password");
            userEntity.setPassword(password);
            userEntity.setStatus(1);
            if (URI.contains("add")) {
                    if (userEntity.getPassword().isEmpty() || userEntity.getEmail().isEmpty() || userEntity.getFullname().isEmpty()) {
                        resp.sendRedirect("/user/add?error=emptyField");
                        return;
                    }
                    if (password.length() < 6 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
                        resp.sendRedirect("/user/add?error=invalidPassword");
                        return;
                    }
                    userService.create(req,resp,userEntity);
                    resp.sendRedirect("/user/hien-thi");
            }
            else if (URI.contains("update")) {
                String[] part = URI.split("/");
                String id = part[part.length - 1];
                String email = req.getParameter("email");
                String fullname = req.getParameter("fullname");

                // Kiểm tra validate các trường bắt buộc
                if (email.isEmpty() || fullname.isEmpty()) {
                    String location = "/user/update/" + id + "?error=emptyField";
                    resp.sendRedirect(location);
                    return;
                }
                userService.update(req,resp,userEntity);
                resp.sendRedirect("/user/hien-thi");
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
