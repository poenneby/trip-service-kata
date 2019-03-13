package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {

    private static final User NO_LOGGED_USER = null;

    @Test
    public void should_throw_exception_when_user_not_logged_in() {
        assertThrows(UserNotLoggedInException.class, () -> getTestableTripService().getTripsByUser(null));
    }

    private TripService getTestableTripService() {
        return new TripService() {
            @Override
            protected User getLoggedUser() {
                return NO_LOGGED_USER;
            }
        };
    }
}
