package tads.atividade.aula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {


     private ProdutoRepositorioJpa produtoRepositorioJpa;
    @Autowired
    public  void  setProdutoRepositorioJpa(ProdutoRepositorioJpa produtoRepositorioJpa){
        this.produtoRepositorioJpa=produtoRepositorioJpa;
    }

    public List<Produto> findAll() {

        return produtoRepositorioJpa.findAll();

    }
    public  void addProduto(Produto produto){
        produtoRepositorioJpa.save(produto);
    }
    public  Produto get(Long id){
        return produtoRepositorioJpa.getOne(id);
    }
    public void delete(Long id){
        produtoRepositorioJpa.deleteById(id);
    }
}