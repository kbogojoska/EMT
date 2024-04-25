package mk.ukim.finki.emt.lab1.model.listeners;

import mk.ukim.finki.emt.lab1.model.events.AccommodationNoRoomLeftEvent;
import mk.ukim.finki.emt.lab1.service.AccommodationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccommodationEventListener {
    private final AccommodationService accommodationService;

    public AccommodationEventListener(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @EventListener
    public void onOccupyRoom(AccommodationNoRoomLeftEvent accommodationNoRoomLeftEvent)
    {
        this.accommodationService.onOccupyRoom();
    }
}
