package com.apianimal.perro.service;

import com.apianimal.perro.dto.PerroDto;
import com.apianimal.perro.model.Perro;
import com.apianimal.perro.repository.Repository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PerroService {

    private static final Logger LOG = LoggerFactory.getLogger(PerroService.class);

    @Autowired
    Repository repository;

    //-------------------POST----------------------------

    private Perro convertirDtoAEntity(PerroDto dto) {
        Perro entity = new Perro();
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setRaza(dto.getRaza());
        entity.setColor(dto.getColor());
        entity.setPeso(dto.getPeso());
        entity.setId(dto.getId());
        return entity;
    }

    public void registrar(PerroDto request) {
        Perro entity = convertirDtoAEntity(request);
        repository.save(entity);
    }

    //-------------------GET---------------------------

    private PerroDto convertirEntityADto(Perro entity) {
        PerroDto dto = new PerroDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setRaza(entity.getRaza());
        dto.setColor(entity.getColor());
        dto.setPeso(entity.getPeso());
        dto.setEdad(entity.getEdad());
        return dto;
    }

    //Metodo para listar por Pagina
    public Page<PerroDto> listarTodos(Pageable pageable) {
        Page<Perro> entities = repository.findAll(pageable);
        List<PerroDto> dtos = entities.getContent().stream().map(this::convertirEntityADto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    //Metodo para listar todos

    public List<PerroDto> listarTodos() {
        List<PerroDto> dtos = new ArrayList<>();
        repository.findAll().forEach(perro -> dtos.add(this.convertirEntityADto(perro)));
        return dtos;
    }

    //Metodo para listar determinada raza

    public List<Perro> findByRaza(String raza) {
        List<Perro> listaRazaDePerro = repository.findByRaza(raza);
        if (repository.findByRaza(raza).isEmpty()) {
            LOG.error("El perro con raza {} no existe", raza);
            throw new EntityNotFoundException("El perro con raza " + raza + " no existe");
        }
        return listaRazaDePerro;
    }

    //Metodo para Guardar 3 perros de la misma raza
    public void guardarTresPerrosDeLaMismaRaza(String raza) {
        repository.countHastaTresDeIgualRaza(raza);
    }


}
