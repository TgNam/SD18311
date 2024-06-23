package org.example.asm_2.service;

import org.example.asm_2.model.VideoEntity;

import java.util.List;

public interface FavoritesService extends Service{
    List<VideoEntity> videoFavorites(int idUser);
}
