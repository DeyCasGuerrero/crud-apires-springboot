package com.api.app.rest.Service;

import com.api.app.rest.Model.Noticias;

import java.util.List;

public interface NoticiasService {
    public List<Noticias> getAllNews();
    public  Noticias saveNews(Noticias noticias);
    public Noticias getByIdNews(int id);
    public  void  deleteService(int id);
}
