package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab1.repository.CountryRepository;
import mk.ukim.finki.emt.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(countryRepository.findById(id))
                .orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        Country country = new Country(name, continent);
        countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> delete(Long id) {
        Country country = findById(id).orElseThrow(InvalidCountryIdException::new);
        countryRepository.delete(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country = findById(id).orElseThrow(InvalidCountryIdException::new);
        country.setName(name);
        country.setContinent(continent);
        countryRepository.save(country);
        return Optional.of(country);
    }
}
