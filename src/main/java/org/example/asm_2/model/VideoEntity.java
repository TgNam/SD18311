package org.example.asm_2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Video", schema = "dbo", catalog = "DB_Video")
public class VideoEntity extends   BaseEntity{
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "img")
    private byte[] img;
    @Basic
    @Column(name = "maVD")
    private String maVd;

    @OneToMany(mappedBy = "video")
    List<FavoritesEntity> favoritesEntityList;
    @ManyToOne
    @JoinColumn(name = "danhmuc_id")
    private DanhMucEntity danhMuc;
}
