package br.senai_desenvolvimento.aula_api.controller;

import br.senai_desenvolvimento.aula_api.entity.Estudante;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/")
public class AulaController {
    @GetMapping("estudante")
    public String estudante(){
        return "Estudante!";
    }

    @PostMapping("add")
    private String criarEstudante(@RequestBody Estudante estudante){
        return "Estudante "+estudante.getNome()+" Criado.";
    }

    @PutMapping("update")
    private String alterarEstudante(){
        return "Estudante alterado.";
    }

    @DeleteMapping("delete")
    private String deletarEstudante(){
        return "Estudante deletado.";
    }


}
