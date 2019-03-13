package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {

    private static final User NO_LOGGED_USER = null;

    @Test
    void should_throw_exception_when_user_not_logged_in() {
        assertThrows(UserNotLoggedInException.class, () -> getTestableTripService(NO_LOGGED_USER).getTripsByUser(null));
    }

    @Test
    void should_return_empty_trips_when_no_friends() {
        TripService tripService = getTestableTripService(new User());
        User userWithNoFriends = new User();

        List<Trip> tripsByUser = tripService.getTripsByUser(userWithNoFriends);

        assertThat(tripsByUser, is(empty()));
    }

    @Test
    void should_() {
        User loggedUser = new User();
        TripService tripService = getTestableTripService(loggedUser);
        User userWithFriends = new User();
        userWithFriends.addFriend(loggedUser);

        List<Trip> tripsByUser = tripService.getTripsByUser(userWithFriends);

    }

    private TripService getTestableTripService(User loggedUser) {
        return new TripService() {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }
        };
    }
}
