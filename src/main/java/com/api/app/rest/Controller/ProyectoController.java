package com.api.app.rest.Controller;

import com.api.app.rest.Model.Proyecto;
import com.api.app.rest.ServiceImplementation.ProyectoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/proyecto")
@Validated
public class ProyectoController {
    private final ProyectoImplementation implementation;

    @Autowired
    public  ProyectoController(ProyectoImplementation implementation){
        this.implementation=implementation;
    }

    @GetMapping("/all")
    public List<Proyecto> getAll(){
        return implementation.getAllProjects();
    }

    @PostMapping("/guardar/eventos")
    public ResponseEntity<Proyecto> saveProject(@RequestBody Proyecto proyecto){
        Proyecto nuevo_proyecto=implementation.saveProjects(proyecto);
        return new ResponseEntity<>(nuevo_proyecto, HttpStatus.CREATED);
    }

    @PutMapping("actualizar/eventos/{id}")
    public ResponseEntity<Proyecto> updateProject(@PathVariable int id, @RequestBody Proyecto proyecto){
        Proyecto actualizarProyecto=implementation.getByIdProject(id);
        actualizarProyecto.setTitulo(proyecto.getTitulo());
        actualizarProyecto.setContexto(proyecto.getContexto());
        actualizarProyecto.setImagen(proyecto.getImagen());
        actualizarProyecto.setLinkRepositorio(proyecto.getLinkRepositorio());

        Proyecto eventoActualizado = implementation.saveProjects(actualizarProyecto);
        return new ResponseEntity<>(eventoActualizado, HttpStatus.CREATED);
    }

    /*@PatchMapping("/actualizar/imagen/{id}")
    public  ResponseEntity<Proyecto> updateImage(@PathVariable int id, @RequestParam("imagen")MultipartFile imagen){
        try {
            Proyecto imagenExiste = implementation.getByIdProject(id);
            if (!imagen.isEmpty()){
                imagenExiste.setImagen(imagen.getBytes());
            }
            Proyecto imagenEvento_actualizado = implementation.saveProjects(imagenExiste);
            return new ResponseEntity<>(imagenEvento_actualizado, HttpStatus.CREATED);
        }catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarEvento(@PathVariable int id){
        this.implementation.deleteService(id);
        HashMap<String,Boolean> estadoEventos=new HashMap<>();
        estadoEventos.put("Evento eliminado", true);
        return  ResponseEntity.ok(estadoEventos);
    }
}
