package mk.ukim.finki.emt.lab1.web;

import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.Category;
import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.Host;
import mk.ukim.finki.emt.lab1.model.dto.AccommodationDto;
import mk.ukim.finki.emt.lab1.model.dto.HostDto;
import mk.ukim.finki.emt.lab1.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> getAllAccommodations() {
        return accommodationService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id) {
        return accommodationService.findById(id).map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return List.of(Category.values());
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> addAccommodation(@RequestBody AccommodationDto accommodationDto){
        return accommodationService.create(accommodationDto.getName(),
                        accommodationDto.getCategory(),
                        accommodationDto.getHostId(),
                        accommodationDto.getAvailableNights())
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Accommodation> editAccommodation(@PathVariable Long id,
                                                           @RequestBody AccommodationDto accommodationDto){
        return accommodationService.update(id,
                        accommodationDto.getName(),
                        accommodationDto.getCategory(),
                        accommodationDto.getHostId(),
                        accommodationDto.getAvailableNights())
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable Long id){
        return accommodationService.delete(id)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/occupyRoom/{id}")
    public ResponseEntity<Accommodation> occupyRoom(@PathVariable Long id) {
        return accommodationService.lowerNumRooms(id).map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public List<Accommodation> filterAccommodations(@RequestParam String filterByCategory) {
        return accommodationService.filterByCategory(filterByCategory);
    }
}
