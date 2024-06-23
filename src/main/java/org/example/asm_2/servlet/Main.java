package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.asm_2.model.FavoritesEntity;
import org.example.asm_2.model.UserEntity;
import org.example.asm_2.model.VideoEntity;
import org.example.asm_2.service.DanhMucService;
import org.example.asm_2.service.FavoritesService;
import org.example.asm_2.service.Imple.DanhMucImple;
import org.example.asm_2.service.Imple.FavoritesImple;
import org.example.asm_2.service.Imple.UserImple;
import org.example.asm_2.service.Imple.VideoImple;
import org.example.asm_2.service.UserService;
import org.example.asm_2.service.VideoService;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({
        "/home",
        "/home/Like/*",
        "/home/Disike/*",
        "/home/search",
        "/home/home-page",
        "/home/favorites",
        "/home/detail/*"
})
public class Main extends HttpServlet {

    public UserService userService = new UserImple();
    public FavoritesService favoritesService =new FavoritesImple();
    public VideoService videoService = new VideoImple();
    public DanhMucService danhMucService = new DanhMucImple();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        this.userSession(req,resp);
        if (URI.contains("home-page")){
            Map<Integer, String> map = new HashMap<>();
            List<VideoEntity> videoList= videoService.findAll();
            for (VideoEntity videoEntity : videoList) {
                if (videoEntity.getImg() !=null){
                    byte[] imageData = videoEntity.getImg();
                    String base64Image = Base64.getEncoder().encodeToString(imageData);
                    map.put(videoEntity.getId(),base64Image);
                }
            }
            req.setAttribute("checkSession", userSession(req,resp));
            req.setAttribute("myMap", map);
            req.setAttribute("formDanhMuc",danhMucService.findAll());
            req.setAttribute("formVideo",videoService.findAll());
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }else if (URI.contains("favorites")){
            if (userSession(req,resp) !=null){
                Map<Integer, String> map = new HashMap<>();
                UserEntity user = userSession(req,resp);
                List<VideoEntity> videoList= favoritesService.videoFavorites(user.getId());
                for (VideoEntity videoEntity : videoList) {
                    if (videoEntity.getImg() !=null){
                        byte[] imageData = videoEntity.getImg();
                        String base64Image = Base64.getEncoder().encodeToString(imageData);
                        map.put(videoEntity.getId(),base64Image);
                    }
                }
                req.setAttribute("myMap", map);
                req.setAttribute("formDanhMuc",danhMucService.findAll());
                req.setAttribute("formVideo",favoritesService.videoFavorites(user.getId()));
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }else{
                resp.sendRedirect("/login");
            }
        }else if (URI.contains("detail")) {
            boolean checkFavorites = false;
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length - 1]);
            VideoEntity videoEntity = (VideoEntity) videoService.find(id);
            Map<Integer, String> map = new HashMap<>();
            if (videoEntity.getImg() != null) {
                byte[] imageData = videoEntity.getImg();
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                map.put(videoEntity.getId(), base64Image);
            }
            if (userSession(req,resp) !=null) {
                List<FavoritesEntity> favoritesEntity = favoritesService.findAll();
                for (FavoritesEntity entity : favoritesEntity) {
                    if (entity.getVideo().getId() == id && entity.getUser().getId() == userSession(req, resp).getId()) {
                        checkFavorites = true;
                    }
                }
                req.setAttribute("checkFavorites", checkFavorites);
            }
            req.setAttribute("myMap", map);
            req.setAttribute("User", userSession(req,resp));
            req.setAttribute("formDanhMuc", danhMucService.findAll());
            req.setAttribute("videoDetail",videoEntity);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }else if (URI.contains("Disike")) {
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length - 1]);
            List<FavoritesEntity> favoritesEntity = favoritesService.findAll();
            for (FavoritesEntity entityfavorites : favoritesEntity) {
                if (entityfavorites.getVideo().getId() == id && entityfavorites.getUser().getId() == userSession(req,resp).getId()){
                    favoritesService.remove(req,resp,entityfavorites.getId());
                }
            }
            resp.sendRedirect("/home/home-page");
        } else if (URI.contains("Like")) {
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length - 1]);
            VideoEntity entity = (VideoEntity) videoService.find(id);
            UserEntity user = userSession(req,resp);
            FavoritesEntity favoritesEntity = new FavoritesEntity();
            favoritesEntity.setVideo(entity);
            favoritesEntity.setUser(user);
            favoritesEntity.setLikes(1);
            favoritesEntity.setStatus(1);
            favoritesService.create(req,resp,favoritesEntity);
            resp.sendRedirect("/home/home-page");
        }
    }

    private UserEntity userSession(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("UserEntity");
        return userEntity;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        try {
            if (URI.contains("search")){
                String search = req.getParameter("search");
                System.out.println(search);
                if (!search.isEmpty()){
                    Map<Integer, String> map = new HashMap<>();
                    List<VideoEntity> videoList= videoService.search(search);
                    for (VideoEntity videoEntity : videoList) {
                        if (videoEntity.getImg() !=null){
                            byte[] imageData = videoEntity.getImg();
                            String base64Image = Base64.getEncoder().encodeToString(imageData);
                            map.put(videoEntity.getId(),base64Image);
                        }
                    }
                    req.setAttribute("myMap", map);
                    req.setAttribute("formDanhMuc",danhMucService.findAll());
                    req.setAttribute("formVideoSearch",videoService.search(search));
                    req.getRequestDispatcher("/index.jsp").forward(req,resp);
                }else {
                    resp.sendRedirect("/home/home-page");
                }
            }
        }catch (Exception e){
                e.printStackTrace();
        }
    }
}
