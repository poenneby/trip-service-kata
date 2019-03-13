package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private static final User NO_LOGGED_USER = null;
    private TripDAO tripDAO;
    private TripService tripService;

    @BeforeEach
    void setUp() {
        tripDAO = Mockito.mock(TripDAO.class);
        tripService = new TripService(tripDAO);
    }

    @Test
    void should_throw_exception_when_user_not_logged_in() {
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(null, null));
    }

    @Test
    void should_return_empty_trips_when_no_friends() {
        User userWithNoFriends = new User();

        List<Trip> tripsByUser = tripService.getTripsByUser(userWithNoFriends, new User());

        assertThat(tripsByUser, is(empty()));
    }

    @Test
    void should_return_trips_when_user_is_friend_with_logged_user() {
        User loggedUser = new User();
        User userWithFriends = new User();
        userWithFriends.addFriend(loggedUser);

        when(tripDAO.findTripsByUser(userWithFriends)).thenReturn(singletonList(new Trip()));

        List<Trip> tripsByUser = tripService.getTripsByUser(userWithFriends, loggedUser);

        assertThat(tripsByUser, is(not(empty())));
    }
}
