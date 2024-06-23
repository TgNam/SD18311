package org.example.asm_2.repository;

import jakarta.persistence.TypedQuery;
import org.example.asm_2.model.UserEntity;

import java.util.List;

public class UserRepository extends Repository{
    public static UserEntity findUser(String email,String password) {
        String jpql = "select u from UserEntity u where email =:email and password =:password";
        TypedQuery<UserEntity> query = em.createQuery(jpql, UserEntity.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
