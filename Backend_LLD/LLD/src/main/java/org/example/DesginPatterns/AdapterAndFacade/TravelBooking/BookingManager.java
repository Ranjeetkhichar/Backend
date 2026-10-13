package org.example.DesginPatterns.AdapterAndFacade.TravelBooking;



import org.example.DesginPatterns.AdapterAndFacade.TravelBooking.models.*;
import org.example.DesginPatterns.AdapterAndFacade.TravelBooking.services.*;

import java.time.LocalDate;

public class BookingManager {
    private BookingFacade bookingFacade;

    public BookingManager(AvailabilityService availabilityService, PaymentService paymentService,
                          NotificationService notificationService, LoyaltyService loyaltyService,
                          AccommodationDetailsService accommodationDetailsService) {
        this.bookingFacade = new BookingFacade(availabilityService, paymentService, notificationService,loyaltyService, accommodationDetailsService);
    }

    public BookingResult bookAccommodation(String userId, String accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        return bookingFacade.bookAccommodation(userId, accommodationId, checkInDate, checkOutDate);
    }

}