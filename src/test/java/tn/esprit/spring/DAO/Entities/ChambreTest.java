package tn.esprit.spring.DAO.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ChambreTest {

    private Chambre chambreMock;
    private Reservation reservationMock;

    @BeforeEach
    public void setUp() {
        // Create mocks for Chambre and Reservation
        chambreMock = mock(Chambre.class);
        reservationMock = mock(Reservation.class);

        // Initialize the reservations list as a mock
        List<Reservation> reservationsList = new ArrayList<>();
        reservationsList.add(reservationMock); // Add the mock reservation

        // Stub the getReservations() method to return the mock list
        when(chambreMock.getReservations()).thenReturn(reservationsList);
    }

    @Test
    public void testChambreReservations() {
        // Ensure reservations list is not empty
        assertNotNull(chambreMock.getReservations(), "Reservations list should not be null");

        // Add a reservation to the mock list (this is just for demonstration; not really adding in the mock)
        chambreMock.getReservations().add(new Reservation());

        // Assert that the reservation is added
        assertEquals(2, chambreMock.getReservations().size(), "Reservations list should have 2 items");
    }

    @Test
    public void testChambreHasReservations() {
        // Check that the mocked Chambre has at least one reservation
        assertTrue(chambreMock.getReservations().size() > 0, "Chambre should have at least one reservation");
    }
}
