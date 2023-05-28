package cl.copeuch.challenge.services.user;

import cl.copeuch.challenge.dto.user.UserDto;
import cl.copeuch.challenge.exceptions.NotFoundRecordException;

public interface UserService {
    UserDto loginUser(String username, String password) throws NotFoundRecordException;
}
