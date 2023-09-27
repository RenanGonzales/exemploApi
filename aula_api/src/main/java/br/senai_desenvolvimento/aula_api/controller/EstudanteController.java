package br.senai_desenvolvimento.aula_api.controller;

import br.senai_desenvolvimento.aula_api.entity.Estudante;
import br.senai_desenvolvimento.aula_api.service.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudante")
public class EstudanteController {
    /*@Autowired   //Essa anotação serve para não precisar instanciar(criar)
                //um objeto de estudanteService, ela traz para o contexto.
    EstudanteService estudanteService; //Precisamos dessa classe para buscar e add estudantes.*/

    EstudanteService estudanteService; //Essa é forma "manual" de criar a conexão de EstudanteService
    public EstudanteController(EstudanteService estudanteService){
        this.estudanteService = estudanteService;
    }

    @GetMapping("todos")
    public ResponseEntity<List<Estudante>> buscarEstudantes(){
        try {
            List<Estudante> lista = estudanteService.buscarEstudantes();
            return new ResponseEntity(lista, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity("Erro na requisicao", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("add")
    public ResponseEntity<?> criarEstudante(@RequestBody Estudante estudante) {
        try {
            estudante = estudanteService.criarEstudante(estudante);
            return new ResponseEntity<>("Estudante criado com sucesso.", HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity("Erro na criacao de estudante", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarEstudante(@PathVariable("codigo") Long codigo){
        try {
            Estudante estudante = estudanteService.buscarEstudante(codigo);
            return new ResponseEntity(estudante, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}//Fim da classe EstudanteController
