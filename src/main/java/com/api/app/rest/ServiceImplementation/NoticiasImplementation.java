package com.api.app.rest.ServiceImplementation;

import com.api.app.rest.Model.Noticias;
import com.api.app.rest.Repository.NoticiasRepository;
import com.api.app.rest.Service.NoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiasImplementation implements NoticiasService {

    @Autowired
    NoticiasRepository repository;

    @Override
    public List<Noticias> getAllNews(){
        return  (repository.findAll());
    }

    @Override
    public Noticias saveNews(Noticias noticias){
        return(repository.save(noticias));
    }

    @Override
    public  Noticias getByIdNews(int id){
        return (repository.findById(id).orElse(null));
    }

    @Override
    public void deleteService(int id){
        repository.deleteById(id);
    }
}
