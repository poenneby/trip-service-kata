package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import static java.util.Collections.*;

public class TripService {

    private TripDAO tripDao;

    public TripService(TripDAO tripDao) {
        this.tripDao = tripDao;
    }

    public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        if (user.isFriendWith(loggedUser)) {
            return tripDao.findTripsByUser(user);
        }
        return emptyList();
    }
}
