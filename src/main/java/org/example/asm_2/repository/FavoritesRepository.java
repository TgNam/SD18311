package org.example.asm_2.repository;

import java.util.List;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.asm_2.model.VideoEntity;

public class FavoritesRepository extends Repository{
    public static List<VideoEntity> videoFavorites(int idUser){
        String jpql = "SELECT v " +
                "FROM FavoritesEntity f " +
                "JOIN f.video v " +
                "WHERE f.user.id = :idUser AND f.likes = 1";
        TypedQuery<VideoEntity> query = em.createQuery(jpql, VideoEntity.class);
        query.setParameter("idUser", idUser);
        return query.getResultList();
    }

}
