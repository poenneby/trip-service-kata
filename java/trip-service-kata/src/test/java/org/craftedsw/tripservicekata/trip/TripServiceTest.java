package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {
    public static User loggedUser;

    @Test
    void should_throw_exception_when_no_logged_user() {
        loggedUser = null;
        assertThrows(UserNotLoggedInException.class, () ->
                new TestableTripService().getTripsByUser(null));
    }

    @Test
    void should_return_no_trips_when_user_has_friends() {
        loggedUser = new User();
        TestableTripService testableTripService = new TestableTripService();
        List<Trip> tripsByUser = testableTripService.getTripsByUser(new User());
        assertEquals(Collections.EMPTY_LIST, tripsByUser);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }
    }
}
