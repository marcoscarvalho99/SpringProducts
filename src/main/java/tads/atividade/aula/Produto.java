package tads.atividade.aula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    //notblank =não haver espaço em branco
    //Size letras quantidade
    //@email tambem.

    @NotBlank(message = "Não pode está em branco")
    @Size(min =5, max =10, message= Palavras.tamanho) //palavras é uma classe  de mensagens staticas.
    String nome;

    @NotBlank(message = "A categoria não pode está em branco!")
    String categoria;

    @NotBlank(message = "A descrição não pode está em branco!")
    String descricao;

    Integer vencimento;

}
