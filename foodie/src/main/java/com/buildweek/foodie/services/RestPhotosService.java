package com.buildweek.foodie.services;



import com.buildweek.foodie.models.RestPhotos;

import java.util.List;

public interface RestPhotosService
{
    List<RestPhotos> findAll();

    RestPhotos findPhotoById(long id);

    void delete(long id);

    RestPhotos save(RestPhotos restphotos);

    RestPhotos update(RestPhotos restphotos, long id);
}
