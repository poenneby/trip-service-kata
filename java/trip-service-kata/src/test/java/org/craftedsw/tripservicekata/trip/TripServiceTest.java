package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {
    @Test
    void should_throw_exception_when_no_logged_user() {
        assertThrows(UserNotLoggedInException.class, () ->
                new TestableTripService().getTripsByUser(null));
    }

    

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return null;
        }
    }
}
