package tads.atividade.aula;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProdutoRepositorioJpa extends JpaRepository<Produto,Long> {

    List<Produto> findByDescricao(String descricao);
    List<Produto>findByVencimento(Integer ano);
}
