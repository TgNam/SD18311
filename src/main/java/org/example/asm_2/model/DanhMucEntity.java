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
@Table(name = "DanhMuc", schema = "dbo", catalog = "DB_Video")
public class DanhMucEntity extends  BaseEntity {
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "danhMuc")
    List<VideoEntity> videoEntityList;
}
