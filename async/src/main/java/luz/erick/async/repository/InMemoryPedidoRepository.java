package luz.erick.async.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import luz.erick.async.domain.Pedido;

@Repository
public class InMemoryPedidoRepository implements PedidoRepository {
    private final Map<Long, Pedido> storage = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Pedido save(Pedido pedido) {
        simularDemora(4000);
        Pedido pedidoWithId = pedido.id() == null 
            ? new Pedido(idCounter.getAndIncrement(), pedido.clienteId(), pedido.descricao(), pedido.valor(), pedido.dataCriacao())
            : pedido;
        storage.put(pedidoWithId.id(), pedidoWithId);
        return pedidoWithId;
    }

    private void simularDemora(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
}
