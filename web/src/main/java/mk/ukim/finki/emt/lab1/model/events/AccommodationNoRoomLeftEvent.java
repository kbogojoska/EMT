package mk.ukim.finki.emt.lab1.model.events;

import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.Category;
import org.springframework.context.ApplicationEvent;

public class AccommodationNoRoomLeftEvent extends ApplicationEvent {
    public AccommodationNoRoomLeftEvent(Category source) {
        super(source);
    }
}
