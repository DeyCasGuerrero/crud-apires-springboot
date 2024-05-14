package com.api.app.rest.Controller;

import com.api.app.rest.Model.Usuario;
import com.api.app.rest.ServiceImplementation.ProyectoImplementation;
import com.api.app.rest.ServiceImplementation.UsuarioImplementation;
import com.api.app.rest.utils.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Validated
public class UsuarioController {
    private final UsuarioImplementation implementation;

    @Autowired
    public  UsuarioController(UsuarioImplementation implementation){
        this.implementation=implementation;
    }

    @Autowired
    Validations validationsPost;

    @GetMapping("/all")
    public List<Usuario> getAll(){
        return implementation.getAllUsers();
    }

    @PostMapping("/guardar/usuario")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {

        Usuario usuarioExistente = implementation.getByEmailUser(usuario.getEmail());
        System.out.println("ESTO SE SDSAIODJASODOASDNADASNDAS"+usuarioExistente);

        if (usuarioExistente != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        usuarioExistente = implementation.getByNameUser(usuario.getNombre().toLowerCase());
        if (usuarioExistente != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (!validationsPost.isValidPassword(usuario.getPassword()) || !validationsPost.isValidEmail(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Usuario nuevoUsuario = implementation.saveUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PostMapping("/verificar")
    public ResponseEntity<Usuario> SignIn(@RequestBody Usuario usuario) {

        String verificarEmail=usuario.getEmail();
        String verificarPassword=usuario.getPassword();

        Usuario usuarioExistente = implementation.getByEmailUser(verificarEmail);
        System.out.println("Usuario obtenido de la base de datos: " + usuarioExistente);

        if (usuarioExistente != null && verificarPassword.equals(usuario.getPassword())) {

            System.out.println("SE ENCONTRO EL PERRO CORREO Y CONTRASEÑA");
            return ResponseEntity.ok(usuarioExistente);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PutMapping("/actualizar/usuario/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable int id, @RequestBody Usuario usuario){
        Usuario updateUser = implementation.getByIdUser(id);
        updateUser.setNombre(usuario.getNombre());
        updateUser.setEmail(usuario.getEmail());
        updateUser.setEmail(usuario.getEmail());
        updateUser.setDescripcion(usuario.getDescripcion());
        Usuario usuarioActualizado = implementation.saveUser(updateUser);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.CREATED);
    }

    @GetMapping("/obtener/{param}")
    public ResponseEntity<Usuario> getSomeParam(@PathVariable String param) {
        Usuario obtener;

        if (param.contains("@") && param.contains(".com")) {

            obtener = implementation.getByEmailUser(param);

        } else {
            obtener = implementation.getByNameUser(param);
        }

        if (obtener == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(obtener);
    }

    @DeleteMapping("/eliminar/{id}")
    public  ResponseEntity<HashMap<String, Boolean>> deleteUser(@PathVariable int id){
        this.implementation.deleteService(id);
        HashMap<String, Boolean> estadoUsuario = new HashMap<>();
        estadoUsuario.put("Usuario eliminado", true);
        return ResponseEntity.ok(estadoUsuario);
    }

}
