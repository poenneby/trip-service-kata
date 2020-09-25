package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {
    @Test
    void should_throw_exception_when_user_not_loggedIn() {
        TripDAO tripDao = Mockito.mock(TripDAO.class);
        User loggedUser = null;
        User user = new User();
        assertThrows(UserNotLoggedInException.class,() ->  new TripService(tripDao).getTripsByUser(user, loggedUser));
    }

    @Test
    void should_return_no_trips_when_user_is_not_friend_with_logged_user() {
        User user = new User();
        user.addFriend(new User());
        TripDAO tripDao = Mockito.mock(TripDAO.class);
        User loggedUser = new User();
        List<Trip> tripsByUser = new TripService(tripDao).getTripsByUser(user, loggedUser);

        assertEquals(Collections.emptyList(), tripsByUser);
    }

    @Test
    void should_return_trips_when_user_is_friend_with_logged_user() {
        User user = new User();
        User loggedUser = new User();
        user.addFriend(loggedUser);
        TripDAO tripDao = Mockito.mock(TripDAO.class);
        Mockito.when(tripDao.findTripsByUser(user)).thenReturn(Arrays.asList(new Trip()));
        List<Trip> tripsByUser = new TripService(tripDao).getTripsByUser(user, loggedUser);

        assertEquals(1, tripsByUser.size());
    }

}

