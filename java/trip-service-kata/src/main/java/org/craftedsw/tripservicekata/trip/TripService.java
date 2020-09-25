package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	private TripDAO tripDao;

	public TripService(TripDAO tripDao) {
		this.tripDao = tripDao;
	}

	public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
		if (loggedUser != null) {
			if (isFriendWithLoggedUser(user, loggedUser)) {
				return tripDao.findTripsByUser(user);
			}
			return new ArrayList<Trip>();
		} else {
			throw new UserNotLoggedInException();
		}
	}

	private boolean isFriendWithLoggedUser(User user, User loggedUser) {
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				return true;
			}
		}
		return false;
	}

}
