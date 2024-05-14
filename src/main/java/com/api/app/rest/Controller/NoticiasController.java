package com.api.app.rest.Controller;

import com.api.app.rest.Model.Noticias;
import com.api.app.rest.ServiceImplementation.NoticiasImplementation;
import com.api.app.rest.ServiceImplementation.UsuarioImplementation;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/noticias")
@Validated

public class NoticiasController {

    private final NoticiasImplementation implementation;

    @Autowired
    public  NoticiasController(NoticiasImplementation implementation){
        this.implementation=implementation;
    }

    @GetMapping("/all")
    public List<Noticias> getAll(){
        return implementation.getAllNews();
    }

    @PostMapping("/guardar/noticias")
    public ResponseEntity<Noticias> save(@RequestBody Noticias noticias){
        Noticias nueva_noticia=implementation.saveNews(noticias);
        return new ResponseEntity<>(nueva_noticia, HttpStatus.CREATED);
    }

    @PutMapping("actualizar/noticias")
    public ResponseEntity<Noticias> updateNews(@PathVariable int id, @RequestBody Noticias noticias){
        Noticias actualizarNoticias = implementation.getByIdNews(id);
        actualizarNoticias.setTitular(noticias.getTitular());
        actualizarNoticias.setLink(noticias.getLink());
        actualizarNoticias.setContenido(noticias.getContenido());

        Noticias noticiaActualizada = implementation.saveNews(actualizarNoticias);
        return new ResponseEntity<>(noticiaActualizada, HttpStatus.CREATED);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<HashMap<String,Boolean>> deleteNews(@PathVariable int id){
        this.implementation.deleteService(id);
        HashMap<String, Boolean> estadoNoticias= new HashMap<>();
        estadoNoticias.put("Noticia elimina", true);
        return ResponseEntity.ok(estadoNoticias);
    }
}
