package org.example.asm_2.service.Imple;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.asm_2.model.DanhMucEntity;
import org.example.asm_2.model.FavoritesEntity;
import org.example.asm_2.model.VideoEntity;
import org.example.asm_2.repository.FavoritesRepository;
import org.example.asm_2.service.FavoritesService;

import java.io.IOException;
import java.util.List;

public class FavoritesImple implements FavoritesService {
    public FavoritesRepository favoritesRepository = new FavoritesRepository();
    @Override
    public List findAll() {
        try{
            List<FavoritesEntity> list = favoritesRepository.findAll(FavoritesEntity.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = favoritesRepository.create(entity);
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
            boolean check = favoritesRepository.update(entity);
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
            boolean check = favoritesRepository.remove(FavoritesEntity.class,id);
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
            return favoritesRepository.find(FavoritesEntity.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<VideoEntity> videoFavorites(int idUser) {
        try{
            return favoritesRepository. videoFavorites(idUser);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
