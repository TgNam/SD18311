package org.example.asm_2.repository;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.asm_2.model.VideoEntity;

import java.util.List;

public class VideoRepository extends Repository{
    public static List<VideoEntity> search(String keyword) {
        String jpql = "select v from VideoEntity v where v.title like :keyword";
        Query q = em.createQuery(jpql,VideoEntity.class);
        q.setParameter("keyword","%" + keyword + "%");
        return q.getResultList();
    }

}
