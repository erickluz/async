package luz.erick.async.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import luz.erick.async.domain.Pedido;
import luz.erick.async.dto.PedidoDto;
import luz.erick.async.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    @Async
    public void create(PedidoDto dto) {
        Pedido domain = new Pedido(
            null,
            dto.clienteId(),
            dto.descricao(),
            dto.valor(),
            LocalDateTime.now()
        );
        repository.save(domain);
    }

    public Optional<PedidoDto> findById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    private PedidoDto toDto(Pedido p) {
        return new PedidoDto(p.id(), p.clienteId(), p.descricao(), p.valor(), p.dataCriacao());
    }
}
