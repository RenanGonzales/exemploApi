package atividade01.produto.controller;

import atividade01.produto.entity.Produto;
import atividade01.produto.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    ProdutoService produtoService; //Essa é forma "manual" de criar a conexão de EstudanteService
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto){
        try {
            produto = produtoService.criarProduto(produto);
            return new ResponseEntity<>("Produto criado com sucesso!", HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>("Erro na criação do Produto!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("todos")
    public ResponseEntity<List<Produto>> buscarProdutos(){
        try {
            List<Produto> lista = produtoService.buscarProdutos();
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity("Erro na requsicao", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarProduto(@PathVariable("codigo") Long codigo){
        try {
            Produto produto = produtoService.buscarProduto(codigo);
            return new ResponseEntity(produto, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> excluirProduto(@PathVariable("codigo") Long codigo) {
        try {
            produtoService.excluirProduto(codigo);
            return new ResponseEntity("Produto excluido com sucesso!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{codigo}") //alterar apenas uma parte
    public ResponseEntity<?> atualizarNomeProduto(@PathVariable("codigo") Long codigo, @RequestBody Map<String, String> nomeAtualizado) {
        try {
            String novoNome = nomeAtualizado.get("nome");
            produtoService.atualizarNomeProduto(codigo, novoNome);
            return new ResponseEntity<>("Nome do produto atualizado com sucesso!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{codigo}") //alterar tudo
    public ResponseEntity<?> atualizarProduto(@PathVariable("codigo") Long codigo, @RequestBody Produto novoProduto) {
        try {
            produtoService.atualizarProduto(codigo, novoProduto);
            return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


} //Fim da classe ProdutoController
