package com.motoservice.service;

import com.motoservice.entidades.Moto;
import com.motoservice.repositorio.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MotoService {
    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }
    public Moto getMotoById(int id){
        return motoRepository.findById(id).orElse(null);
    }

    public Moto save(Moto usuario){
        Moto nuevoMoto = motoRepository.save(usuario);
        return nuevoMoto;
    }
    public List<Moto> byUsuarioId(int usuarioId){
        return motoRepository.findByUsuarioId(usuarioId);
    }
}
