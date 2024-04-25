package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.Category;
import mk.ukim.finki.emt.lab1.model.events.AccommodationNoRoomLeftEvent;
import mk.ukim.finki.emt.lab1.model.exceptions.*;
import mk.ukim.finki.emt.lab1.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab1.service.AccommodationService;
import mk.ukim.finki.emt.lab1.service.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, ApplicationEventPublisher applicationEventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Accommodation> listAll() {
        return accommodationRepository.findAll();
    }
/*
ROOM,
    HOUSE,
    FLAT,
    APARTMENT,
    HOTEL,
    MOTEL
*/
    @Override
    public List<Accommodation> filterByCategory(String category) {
        if (category.toUpperCase().equals("ROOM")) {
            if (accommodationRepository.findAllByCategory(Category.ROOM)
                    .stream()
                    .mapToInt(Accommodation::getNumRooms)
                    .sum() == 0)
                applicationEventPublisher.publishEvent(new AccommodationNoRoomLeftEvent(Category.ROOM));
            return accommodationRepository.findAllByCategory(Category.ROOM);
        } else if (category.toUpperCase().equals("HOUSE")) {
            if (accommodationRepository.findAllByCategory(Category.HOUSE)
                    .stream()
                    .mapToInt(Accommodation::getNumRooms)
                    .sum() == 0)
                applicationEventPublisher.publishEvent(new AccommodationNoRoomLeftEvent(Category.HOUSE));
            return accommodationRepository.findAllByCategory(Category.HOUSE);
        } else if (category.toUpperCase().equals("FLAT")) {
            if (accommodationRepository.findAllByCategory(Category.FLAT)
                    .stream()
                    .mapToInt(Accommodation::getNumRooms)
                    .sum() == 0)
                applicationEventPublisher.publishEvent(new AccommodationNoRoomLeftEvent(Category.FLAT));
            return accommodationRepository.findAllByCategory(Category.FLAT);
        } else if (category.toUpperCase().equals("APARTMENT")) {
            if (accommodationRepository.findAllByCategory(Category.APARTMENT)
                    .stream()
                    .mapToInt(Accommodation::getNumRooms)
                    .sum() == 0)
                applicationEventPublisher.publishEvent(new AccommodationNoRoomLeftEvent(Category.APARTMENT));
            return accommodationRepository.findAllByCategory(Category.APARTMENT);
        } else if (category.toUpperCase().equals("HOTEL")) {
            if (accommodationRepository.findAllByCategory(Category.HOTEL)
                    .stream()
                    .mapToInt(Accommodation::getNumRooms)
                    .sum() == 0)
                applicationEventPublisher.publishEvent(new AccommodationNoRoomLeftEvent(Category.HOTEL));
            return accommodationRepository.findAllByCategory(Category.HOTEL);
        } else if (category.toUpperCase().equals("MOTEL")) {
            if (accommodationRepository.findAllByCategory(Category.MOTEL)
                    .stream()
                    .mapToInt(Accommodation::getNumRooms)
                    .sum() == 0)
                applicationEventPublisher.publishEvent(new AccommodationNoRoomLeftEvent(Category.MOTEL));
            return accommodationRepository.findAllByCategory(Category.MOTEL);
        } else {
            throw new InvalidCategoryException();
        }
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return Optional.of(accommodationRepository.findById(id))
                .orElseThrow(InvalidAccommodationIdException::new);
    }

    @Override
    public Optional<Accommodation> create(String name, Category category, Long hostId, Integer numRooms) {
        Accommodation accommodation = new Accommodation(name, category, hostService.findById(hostId).orElseThrow(InvalidHostIdException::new), numRooms);
        accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> delete(Long id) {
        Accommodation accommodation = findById(id).orElseThrow(InvalidAccommodationIdException::new);
        accommodationRepository.delete(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> lowerNumRooms(Long id) {
        Accommodation accommodation = findById(id).orElseThrow(InvalidAccommodationIdException::new);
        if (accommodation.getNumRooms() == 0)
            throw new NoRoomsLeftException();
        accommodation.setNumRooms(accommodation.getNumRooms() - 1);
        accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> update(Long id, String name, Category category, Long hostId, Integer numRooms) {
        Accommodation accommodation = findById(id).orElseThrow(InvalidAccommodationIdException::new);
        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setHost(hostService.findById(hostId).orElseThrow(InvalidHostIdException::new));
        accommodation.setNumRooms(numRooms);
        accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public void onOccupyRoom() {
        System.out.println("No rooms left for this accommodation!");
    }
}
