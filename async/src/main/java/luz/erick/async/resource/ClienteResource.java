package luz.erick.async.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import luz.erick.async.dto.ClienteDto;
import luz.erick.async.service.ClienteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteDto> create(@RequestBody ClienteDto dto) {
        ClienteDto created = service.create(dto);
        return ResponseEntity.created(URI.create("/clientes/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id, @RequestBody ClienteDto dto) {
        return service.update(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
