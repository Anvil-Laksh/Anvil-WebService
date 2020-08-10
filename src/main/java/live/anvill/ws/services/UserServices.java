package live.anvill.ws.services;

import live.anvill.ws.sharedto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServices extends UserDetailsService{
     UserDto createUser(UserDto userDto);
     UserDto getUser(String email);
     UserDto getUserByUserId(String userId);
     UserDto updateUser(String userId, UserDto userDto);
     void deleteUser(String userId);
     List<UserDto> getAllUsers(int page, int limit);

}
