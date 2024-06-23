package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.example.asm_2.model.DanhMucEntity;
import org.example.asm_2.model.VideoEntity;
import org.example.asm_2.repository.VideoRepository;
import org.example.asm_2.service.DanhMucService;
import org.example.asm_2.service.Imple.DanhMucImple;
import org.example.asm_2.service.Imple.VideoImple;
import org.example.asm_2.service.VideoService;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({
        "/video",
        "/video/add",
        "/video/update",
        "/video/detail/*",
        "/video/delete/*",
        "/video/hien-thi",

})
@MultipartConfig

public class QLVideo extends HttpServlet {
    public VideoService videoService =new VideoImple();
    public DanhMucService danhMucService = new DanhMucImple();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("delete")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            videoService.remove(req,resp,id);
            resp.sendRedirect("/video/hien-thi");
        } else if (URI.contains("hien-thi")){
            Map<Integer, String> map = new HashMap<>();
            List<VideoEntity> videoList= videoService.findAll();
            for (VideoEntity videoEntity : videoList) {
                if (videoEntity.getImg() !=null){
                    byte[] imageData = videoEntity.getImg();
                    String base64Image = Base64.getEncoder().encodeToString(imageData);
                    map.put(videoEntity.getId(),base64Image);
                }
            }
            req.setAttribute("myMap", map);
            req.setAttribute("Item",videoService.findAll());
            req.getRequestDispatcher("/webs/MovieWeb.jsp").forward(req,resp);
        }
        else if (URI.contains("add")){
            req.setAttribute("DanhMuc",danhMucService.findAll());
            req.getRequestDispatcher("/uploatPhoto.jsp").forward(req,resp);
        }
        else if (URI.contains("update")){
            req.getRequestDispatcher("/webs/UpdateMovie.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setStatus(1);
        try {
            videoEntity.setTitle(req.getParameter("title"));
            videoEntity.setMaVd(req.getParameter("maVd"));
            videoEntity.setDescription(req.getParameter("description"));
            videoEntity.setDanhMuc((DanhMucEntity) danhMucService.find(Integer.parseInt(req.getParameter("danhMuc"))));
            Part imagePart = req.getPart("img");
            byte[] imageData = imagePart.getInputStream().readAllBytes();
            videoEntity.setImg(imageData);
            if (URI.contains("add")) {
                videoService.create(req,resp,videoEntity);
                VideoRepository.create(videoEntity);
                resp.sendRedirect("/video/hien-thi");
            }else if (URI.contains("update")) {
                videoEntity.setId(Integer.parseInt(req.getParameter("id")));
                videoService.update(req,resp,videoEntity);
                resp.sendRedirect("/video/hien-thi");
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
