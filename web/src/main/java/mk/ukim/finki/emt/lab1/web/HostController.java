package mk.ukim.finki.emt.lab1.web;

import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.Host;
import mk.ukim.finki.emt.lab1.model.dto.CountryDto;
import mk.ukim.finki.emt.lab1.model.dto.HostDto;
import mk.ukim.finki.emt.lab1.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/hosts")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> getAllHosts() {
        return hostService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHostById(@PathVariable Long id) {
        return hostService.findById(id).map(accomm -> ResponseEntity.ok().body(accomm))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Host> addHost(@RequestBody HostDto hostDto){
        return hostService.create(hostDto.getName(),
                        hostDto.getSurname(),
                        hostDto.getCountryId())
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Host> editHost(@PathVariable Long id,
                                         @RequestBody HostDto hostDto){
        return hostService.update(id, hostDto.getName(), hostDto.getSurname(), hostDto.getCountryId())
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Host> deleteHost(@PathVariable Long id){
        return hostService.delete(id)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
