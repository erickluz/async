package luz.erick.async.repository;

import org.springframework.stereotype.Repository;
import luz.erick.async.domain.Cliente;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryClienteRepository implements ClienteRepository {
    private final Map<Long, Cliente> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(0);

    public InMemoryClienteRepository() {
        // populate with some random data
        for (int i = 1; i <= 5; i++) {
            long id = idGen.incrementAndGet();
            String nome = "Cliente " + i;
            String email = "cliente" + i + "@example.com";
            store.put(id, new Cliente(id, nome, email));
        }
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Cliente save(Cliente cliente) {
        Long id = cliente.id();
        if (id == null) {
            id = idGen.incrementAndGet();
        }
        Cliente toSave = new Cliente(id, cliente.nome(), cliente.email());
        store.put(id, toSave);
        return toSave;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
