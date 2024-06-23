package org.example.asm_2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users", schema = "dbo", catalog = "DB_Video")
public class UserEntity extends BaseEntity{
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "fullname")
    private String fullname;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "admin")
    private Integer admin;
    @OneToMany(mappedBy = "user")
    List<FavoritesEntity> favoritesEntityList;
}
