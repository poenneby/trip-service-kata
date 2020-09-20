package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {
    public static User loggedUser;
    @Test
    void should_throw_exception_when_user_not_loggedIn() {
        loggedUser = null;
        assertThrows(UserNotLoggedInException.class,() ->  new TestableTripService().getTripsByUser(loggedUser));
    }

    @Test
    void should_return_no_trips_when_user_is_not_friend_with_logged_user() {
        loggedUser = new User();
        User user = new User();
        user.addFriend(new User());
        List<Trip> tripsByUser = new TestableTripService().getTripsByUser(user);

        assertEquals(Collections.emptyList(), tripsByUser);
    }

    @Test
    void should_return_trips_when_user_is_friend_with_logged_user() {
        loggedUser = new User();
        User user = new User();
        user.addFriend(loggedUser);
        List<Trip> tripsByUser = new TestableTripService().getTripsByUser(user);

        assertEquals(1, tripsByUser.size());
    }

    public class TestableTripService extends TripService{
        @Override
        protected List<Trip> getTrips(User user) {
            return Arrays.asList(new Trip());
        }

        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }
    }
}

