package luz.erick.async.dto;

import java.time.LocalDateTime;

public record PedidoDto(Long id, Long clienteId, String descricao, Double valor, LocalDateTime dataCriacao) {
}
