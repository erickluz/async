package luz.erick.async.repository;

import java.util.List;
import java.util.Optional;
import luz.erick.async.domain.Cliente;

public interface ClienteRepository {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
}
