package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.Category;
import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> listAll();
    Optional<Host> findById(Long id);
    Optional<Host> create(String name, String surname, Long countryId);
    Optional<Host> delete(Long id);
    Optional<Host> update(Long id, String name, String surname, Long countryId);
}
