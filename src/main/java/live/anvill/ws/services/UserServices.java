package live.anvill.ws.services;

import live.anvill.ws.sharedto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService{
     UserDto createUser(UserDto userDto);
     UserDto getUser(String email);
}
