package org.example.asm_2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Favorites", schema = "dbo", catalog = "DB_Video")
public class FavoritesEntity  extends  BaseEntity{
    @Basic
    @Column(name = "likes")
    private Integer likes;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "video_id")
    private VideoEntity video;

}
