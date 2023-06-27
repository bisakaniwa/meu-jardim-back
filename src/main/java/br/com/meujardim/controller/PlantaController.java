package br.com.meujardim.controller;

import br.com.meujardim.model.Planta;
import br.com.meujardim.service.PlantaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/plantas")
public class PlantaController {

    private final PlantaService plantaService;

    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @GetMapping
    ResponseEntity<List<Planta>> buscarTodasAsPlantas() {
        return new ResponseEntity<>(plantaService.buscarTodasAsPlantas(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Planta> cadastrarPlanta(@RequestBody @Valid Planta planta, long userId) {
        return new ResponseEntity<>(plantaService.cadastrarPlanta(planta, userId), HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<Planta> editarPlanta(@RequestBody @Valid Planta plantaEditada, long userId) {
        return new ResponseEntity<>(plantaService.editarPlanta(plantaEditada, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{plantaId}")
    ResponseEntity<Planta> excluirPlanta(@PathVariable long plantaId, long userId) {
        plantaService.excluirPlanta(plantaId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
