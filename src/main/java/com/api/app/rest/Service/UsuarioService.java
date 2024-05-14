package com.api.app.rest.Service;

import com.api.app.rest.Model.Usuario;

import java.util.List;

public interface UsuarioService {
    public List<Usuario> getAllUsers();
    public Usuario saveUser (Usuario usuario);
    public Usuario getByIdUser (int id);
    public Usuario getByEmailUser (String email);
    public Usuario getByNameUser(String nombre);
    public void deleteService(int id);

}
