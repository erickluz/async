package luz.erick.async.service;

import org.springframework.stereotype.Service;
import luz.erick.async.dto.ClienteDto;
import luz.erick.async.domain.Cliente;
import luz.erick.async.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public Optional<ClienteDto> findById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public ClienteDto create(ClienteDto dto) {
        Cliente domain = toDomain(dto);
        Cliente saved = repository.save(domain);
        return toDto(saved);
    }

    public Optional<ClienteDto> update(Long id, ClienteDto dto) {
        return repository.findById(id).map(existing -> {
            Cliente updated = new Cliente(id, dto.nome(), dto.email());
            Cliente saved = repository.save(updated);
            return toDto(saved);
        });
    }

    public boolean delete(Long id) {
        Optional<Cliente> found = repository.findById(id);
        if (found.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private ClienteDto toDto(Cliente c) {
        return new ClienteDto(c.id(), c.nome(), c.email());
    }

    private Cliente toDomain(ClienteDto dto) {
        return new Cliente(dto.id(), dto.nome(), dto.email());
    }
}
