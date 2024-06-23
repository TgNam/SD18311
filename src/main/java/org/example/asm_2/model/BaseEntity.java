package org.example.asm_2.model;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date ngtao;
    private Date ngsua;
    private Integer status;

    @PrePersist
    public void setNgTaoDate() {
        this.ngtao = new java.sql.Date(new java.util.Date().getTime());
        this.ngsua = new java.sql.Date(new java.util.Date().getTime());
    }

    @PreUpdate
    public void setNgSuaDate() {
        this.ngsua = new java.sql.Date(new java.util.Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgTao() {
        return ngtao;
    }

    public Date getNgSua() {
        return ngsua;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
