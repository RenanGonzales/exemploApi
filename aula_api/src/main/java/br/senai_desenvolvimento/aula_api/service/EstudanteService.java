package br.senai_desenvolvimento.aula_api.service;

import br.senai_desenvolvimento.aula_api.entity.Estudante;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {
    private List<Estudante> estudantes;

    public EstudanteService(){
        estudantes = new ArrayList<>();
    }

    public List<Estudante> buscarEstudantes(){
        return estudantes;
    }

    public Estudante criarEstudante(Estudante estudante) throws Exception{
        if(estudante.getIdade() <18)
            throw new Exception("Usuario menor de idade");
        estudantes.add(estudante);
        return estudante;
    }

    @GetMapping("/{codigo}")
    public String buscar(@PathVariable("codigo") Long codigo){
        return "Estudantes!!!";
    }

    public Estudante buscarEstudante(Long codigo) throws Exception{
        Optional<Estudante> estudante = estudantes.stream().filter(e -> e.getCodigo() == codigo).findFirst();
        //Optional<Estudante> Ele esconde um objeto, para evitar que acesse objetos nulos, entre as chaves coloca o tipo de objeto que está "escondendo"
        //ajuda a verificar se o objeto é nulo
        //.stream() é para converter List em string, precisa para percorrer o ararylist
        //-> arrowFunction, "aquele parametro vai ser usado naquela função"
        //.filter vai percorrer por todos elementos, buscando satisfazer a interação
        //findFirts retorna o primeiro da lista, retorna um Optional<Estudante>
        if(estudante.isPresent()){
            //isPresent() serve para descobrir se existe um objeto dentro do Optional<Estudante>
            //se ele encontrar um objeto, ele retorna
            return estudante.get();
        }else {
            //Caso ele não encontre, vai retornar uma exceção
            throw new Exception("Estudante nao encontrado");
        }
    }
} //Fim da classe EstudanteService
