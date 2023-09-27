package atividade01.produto.service;

import atividade01.produto.entity.Produto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private List<Produto> produtos;

    public ProdutoService(){
        produtos = new ArrayList<>();
    }

    public Produto criarProduto(Produto produto) {
       produtos.add(produto);
       return produto;
    }

    public Produto buscarProduto(Long codigo) throws Exception{
        Optional<Produto> produto = produtos.stream().filter(p -> p.getCodigo() == codigo).findFirst();
        if (produto.isPresent()){
            //isPresent() serve para descobrir se existe um objeto dentro do Optional<Estudante>
            //se ele encontrar um objeto, ele retorna
            return produto.get();
        }else{
            //Caso ele não encontre, vai retornar uma exceção
            throw new Exception("Produto nao encontrado.");
        }
    }

    public List<Produto> buscarProdutos(){
        return produtos;
    }

    public void excluirProduto(Long codigo) throws Exception {
        Optional<Produto> produtoOptional = produtos.stream().filter(p -> p.getCodigo() == (codigo)).findFirst();
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produtos.remove(produto);
        } else {
            throw new Exception("Produto não encontrado.");
        }
    }

    public void atualizarNomeProduto(Long codigo, String novoNome) throws Exception {
        Optional<Produto> produtoOptional = produtos.stream().filter(p -> p.getCodigo() == (codigo)).findFirst();
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.setNome(novoNome);
        } else {
            throw new Exception("Produto não encontrado.");
        }
    }

    public void atualizarProduto(Long codigo, Produto novoProduto) throws Exception {
        // Encontra o produto existente com o código fornecido
        Optional<Produto> produtoOptional = produtos.stream().filter(p -> p.getCodigo() == (codigo)).findFirst();

        if (produtoOptional.isPresent()) {
            Produto produtoExistente = produtoOptional.get();
            produtoExistente.setNome(novoProduto.getNome());
            produtoExistente.setDescricao(novoProduto.getDescricao());
        } else {
            throw new Exception("Produto não encontrado.");
        }
    }


} //Fim da classe ProdutoService
