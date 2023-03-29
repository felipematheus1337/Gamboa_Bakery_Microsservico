package br.com.alurafood.avaliacao.avaliacao.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import reactor.util.annotation.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("media")

public class Media  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Nullable
    private Double notaMedia;

    @NotNull
    private String pedidoId;

    @Nullable
    private Integer quantidadeAvaliacao;
}
