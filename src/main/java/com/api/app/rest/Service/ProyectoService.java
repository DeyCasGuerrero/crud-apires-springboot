package com.api.app.rest.Service;

import com.api.app.rest.Model.Proyecto;

import java.util.List;

public interface ProyectoService {
    List<Proyecto> getAllProjects();
    public Proyecto saveProjects(Proyecto proyecto);
    public Proyecto getByIdProject(int id);
    public void deleteService(int id);
}
