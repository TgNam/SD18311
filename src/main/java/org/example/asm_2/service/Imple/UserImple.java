package org.example.asm_2.service.Imple;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.asm_2.model.FavoritesEntity;
import org.example.asm_2.model.UserEntity;
import org.example.asm_2.repository.UserRepository;
import org.example.asm_2.service.UserService;

import java.io.IOException;
import java.util.List;

public class UserImple implements UserService {
    public UserRepository userRepository = new UserRepository();
    @Override
    public List<UserEntity> findAll() {
        try{
            List<UserEntity> list = userRepository.findAll(UserEntity.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = userRepository.create(entity);
            if (check){
                httpServletRequest.setAttribute("message", "Bạn đã thêm thành công!");
            }else {
                httpServletRequest.setAttribute("message", "Bạn đã thêm thất bại!");
            }
        }catch (Exception e){
            e.printStackTrace();
            httpServletRequest.setAttribute("message", "Bạn đã thêm thất bại!");
        }
    }

    @Override
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = userRepository.update(entity);
            if (check){
                httpServletRequest.setAttribute("message", "Bạn đã thêm thành công!");
            }else {
                httpServletRequest.setAttribute("message", "Bạn đã thêm thất bại!");
            }
        }catch (Exception e){
            e.printStackTrace();
            httpServletRequest.setAttribute("message", "Bạn đã thêm thất bại!");
        }
    }

    @Override
    public void remove(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int id) throws ServletException, IOException {
        try{
            boolean check = userRepository.remove(UserEntity.class,id);
            if (check){
                httpServletRequest.setAttribute("message", "Bạn đã xóa thành công!");
            }else {
                httpServletRequest.setAttribute("message", "Bạn đã xóa thất bại!");
            }
        }catch (Exception e){
            e.printStackTrace();
            httpServletRequest.setAttribute("message", "Bạn đã xóa thất bại!");
        }
    }

    @Override
    public Object find(int id) {
        try{
            return userRepository.find(UserEntity.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserEntity findUser(String email, String password) {
        try{
            return userRepository.findUser(email,password);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
