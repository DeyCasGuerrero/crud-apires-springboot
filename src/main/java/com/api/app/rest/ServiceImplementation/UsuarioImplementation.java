package com.api.app.rest.ServiceImplementation;

import com.api.app.rest.Model.Usuario;
import com.api.app.rest.Repository.UsuarioRepository;
import com.api.app.rest.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioImplementation implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public List<Usuario> getAllUsers(){
        return (repository.findAll());
    }

    @Override
    public Usuario saveUser(Usuario usuario){
        return(repository.save(usuario));
    }

    @Override
    public Usuario getByIdUser(int id){
        return(repository.findById(id).orElse(null));
    }

    @Override
    public Usuario getByEmailUser(String email){
        return(repository.findByEmail(email));
    }

    @Override
    public Usuario getByNameUser(String nombre){
        return (repository.findByNombre(nombre));
    }

    @Override
    public void deleteService(int id){
        repository.deleteById(id);
    }
}
