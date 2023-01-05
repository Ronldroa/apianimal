package com.apianimal.perro.controller;

import com.apianimal.perro.dto.PerroDto;
import com.apianimal.perro.model.Perro;
import com.apianimal.perro.service.PerroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("mascotas")
@AllArgsConstructor
public class PerroController {

    @Autowired
    PerroService service;

    @PostMapping()

    public ResponseEntity<Void> crear(@Valid @RequestBody PerroDto request) {
        service.registrar(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<PerroDto>> listarPorPagina(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(this.service.listarTodos(pageable), headers, HttpStatus.OK);
    }

    @GetMapping("/{listar-raza}")
    public List<Perro> getByRaza(@PathVariable("listar-raza") String raza) {
        return service.findByRaza(raza);
    }
}
