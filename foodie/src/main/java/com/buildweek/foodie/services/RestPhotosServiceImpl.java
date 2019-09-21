package com.buildweek.foodie.services;

import com.buildweek.foodie.exceptions.ResourceNotFoundException;
import com.buildweek.foodie.models.RestPhotos;
import com.buildweek.foodie.repository.RestPhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "restphotos")
public class RestPhotosServiceImpl implements RestPhotosService
{
    @Autowired
    RestPhotosRepository rprepo;

    @Override
    public List<RestPhotos> findAll()
    {
        List<RestPhotos> restphotosList = new ArrayList<>();
        rprepo.findAll()
                .iterator()
                .forEachRemaining(restphotosList::add);
        return restphotosList;
    }

    @Override
    public RestPhotos findPhotoById(long id)
    {
        return rprepo.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Photo id " + id + " not found!"));
    }

    @Override
    public void delete(long id)
    {
        if (rprepo.findById(id).isPresent())
        {
            rprepo.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Override
    public RestPhotos save(RestPhotos restphotos)
    {
        RestPhotos newPhoto = new RestPhotos();
        newPhoto.setPhoto(restphotos.getPhoto());

        return rprepo.save(newPhoto);
    }

    @Override
    public RestPhotos update(RestPhotos restphotos, long id)
    {
        RestPhotos currentPhoto = rprepo.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if(restphotos.getPhoto() != null)
        {
            currentPhoto.setPhoto(restphotos.getPhoto());
        }

        return rprepo.save(currentPhoto);
    }
}
