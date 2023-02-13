package com.joanmoncho.frasesintelij.controllers;

import com.joanmoncho.frasesintelij.models.Autor;
import com.joanmoncho.frasesintelij.models.Usuario;
import com.joanmoncho.frasesintelij.repo.IAutorDao;
import com.joanmoncho.frasesintelij.repo.IUsuarioDao;
import com.joanmoncho.frasesintelij.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private IUsuarioDao repo;

    @GetMapping("/all")
    public List<Usuario> getUsuarios() {
        return repo.findAll();
    }

    @GetMapping("/all/{offset}")
    public List<Usuario> getUsuariosLimit(@PathVariable("offset") int offset) {
        return repo.getUsuariosLimit(offset);
    }


    @GetMapping(value = "/{id}")
    public Optional<Usuario> getUsuario(@PathVariable("id") Integer id) {
        return repo.findById(id);
    }

    @PostMapping("/add")
    public boolean addUsuario(@RequestBody Usuario usuario) {
        try {
            Log.i("Nuevo Usuario: ", usuario.toString());
            repo.save(usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PutMapping("/update")
    public boolean updateUsuario(@RequestBody Usuario usuario) {
        try {
            Log.i("Update Usuario: ", usuario.toString());
            repo.save(usuario);
            return true;
        } catch (Exception e){
            Log.e("Update Usuario", e.getMessage());
            return false;
        }
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteUsuario(@PathVariable("id") Integer id) {
        try {
            repo.deleteById(id);
            System.out.println("Usuario eliminado correctamente");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
