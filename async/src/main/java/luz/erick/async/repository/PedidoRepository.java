package luz.erick.async.repository;

import java.util.Optional;
import luz.erick.async.domain.Pedido;

public interface PedidoRepository {
    Pedido save(Pedido pedido);
    Optional<Pedido> findById(Long id);
}
