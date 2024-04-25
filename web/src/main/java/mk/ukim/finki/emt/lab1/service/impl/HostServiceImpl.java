package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Host;
import mk.ukim.finki.emt.lab1.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab1.model.exceptions.InvalidHostIdException;
import mk.ukim.finki.emt.lab1.repository.HostRepository;
import mk.ukim.finki.emt.lab1.service.CountryService;
import mk.ukim.finki.emt.lab1.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return Optional.of(hostRepository.findById(id))
                .orElseThrow(InvalidHostIdException::new);
    }

    @Override
    public Optional<Host> create(String name, String surname, Long countryId) {
        Host host = new Host(name, surname, countryService.findById(countryId).orElseThrow(InvalidCountryIdException::new));
        hostRepository.save(host);
        return Optional.of(host);
    }

    @Override
    public Optional<Host> delete(Long id) {
        Host host = findById(id).orElseThrow(InvalidHostIdException::new);
        hostRepository.delete(host);
        return Optional.of(host);
    }

    @Override
    public Optional<Host> update(Long id, String name, String surname, Long countryId) {
        Host host = findById(id).orElseThrow(InvalidHostIdException::new);
        host.setName(name);
        host.setSurname(surname);
        host.setCountry(countryService.findById(countryId).orElseThrow(InvalidCountryIdException::new));
        hostRepository.save(host);
        return Optional.of(host);
    }
}
