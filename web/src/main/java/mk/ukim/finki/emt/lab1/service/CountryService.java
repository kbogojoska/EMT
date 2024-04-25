package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.Host;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> create(String name, String continent);
    Optional<Country> delete(Long id);
    Optional<Country> update(Long id, String name, String continent);
}
