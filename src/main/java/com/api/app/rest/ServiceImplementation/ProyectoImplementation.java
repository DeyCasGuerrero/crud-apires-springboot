package com.api.app.rest.ServiceImplementation;

import com.api.app.rest.Model.Proyecto;
import com.api.app.rest.Repository.ProyectoRepository;
import com.api.app.rest.Service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoImplementation implements ProyectoService {
    @Autowired
    ProyectoRepository repository;

    @Override
    public List<Proyecto> getAllProjects(){
        return (repository.findAll());
    }

    @Override
    public Proyecto saveProjects(Proyecto proyecto){
        return (repository.save(proyecto));
    }
    @Override
    public Proyecto getByIdProject(int id) {
        return (repository.findById(id).orElse(null));
    }

    @Override
    public void deleteService(int id) {
        repository.deleteById(id);

    }
}
