package luz.erick.async.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import luz.erick.async.dto.PedidoDto;
import luz.erick.async.service.PedidoService;

import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
    private final PedidoService service;

    public PedidoResource(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PedidoDto> registrar(@RequestBody PedidoDto dto) {
        service.create(dto);
        return ResponseEntity.created(URI.create("/pedidos/")).build();
    }
}
