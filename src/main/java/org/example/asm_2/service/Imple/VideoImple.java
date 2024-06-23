package org.example.asm_2.service.Imple;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.asm_2.model.DanhMucEntity;
import org.example.asm_2.model.VideoEntity;
import org.example.asm_2.repository.VideoRepository;
import org.example.asm_2.service.VideoService;

import java.io.IOException;
import java.util.List;

public class VideoImple implements VideoService {
    public VideoRepository videoRepository = new VideoRepository();
    @Override
    public List findAll() {
        try{
            List<VideoEntity> list = videoRepository.findAll(VideoEntity.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = videoRepository.create(entity);
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
            boolean check = videoRepository.update(entity);
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
            boolean check = videoRepository.remove(VideoEntity.class,id);
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
            return videoRepository.find(VideoEntity.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<VideoEntity> search(String keyword) {
        try{
            return videoRepository.search(keyword);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
