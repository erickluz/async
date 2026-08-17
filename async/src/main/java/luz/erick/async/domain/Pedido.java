package luz.erick.async.domain;

import java.time.LocalDateTime;

public record Pedido(Long id, Long clienteId, String descricao, Double valor, LocalDateTime dataCriacao) {
}
