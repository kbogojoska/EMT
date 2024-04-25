package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> listAll();
    List<Accommodation> filterByCategory(String category);
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> create(String name, Category category, Long hostId, Integer numRooms);
    Optional<Accommodation> delete(Long id);
    Optional<Accommodation> lowerNumRooms(Long id);
    Optional<Accommodation> update(Long id, String name, Category category, Long hostId, Integer numRooms);
    void onOccupyRoom();
}
