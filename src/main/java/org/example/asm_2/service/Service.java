package org.example.asm_2.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface Service<T> {
    List<T> findAll();
    void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, T entity) throws ServletException, IOException;
    void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, T entity) throws ServletException, IOException;
    void remove(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int id) throws ServletException, IOException;
    T find(int id);
}
