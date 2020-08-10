package live.anvill.ws.implementations;

import live.anvill.ws.entities.UserEntity;
import live.anvill.ws.exceptions.UserServiceException;
import live.anvill.ws.repository.UserRepository;
import live.anvill.ws.responses.ErrorMessages;
import live.anvill.ws.services.UserServices;
import live.anvill.ws.sharedto.AddressDto;
import live.anvill.ws.sharedto.UserDto;
import live.anvill.ws.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplmnt implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail())!= null)throw new RuntimeException("Record already exist");

    for(int i=0; i<userDto.getAddresses().size(); i++){
        AddressDto address =  userDto.getAddresses().get(i);
        address.setUserDetails(userDto);
        address.setAddressId(utils.addressId(25));
        userDto.getAddresses().set(i,address);
    }

    ModelMapper modelMapper = new ModelMapper();
    UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

    String publicUserId = utils.generateRandomId(25);
    userEntity.setUserId(publicUserId);
    userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    UserEntity savedDetails = userRepository.save(userEntity);
    UserDto returnValue = modelMapper.map(savedDetails, UserDto.class);
    return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        live.anvill.ws.entities.UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity ==null) throw new UsernameNotFoundException(email);
        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(userEntity, returnVal);          /*copying storeval to return val for O/P*/
        return returnVal;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto returnVal = new UserDto();
        live.anvill.ws.entities.UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity== null)throw new UsernameNotFoundException(userId);

        BeanUtils.copyProperties(userEntity,returnVal);
        return returnVal;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserDto returnVal = new UserDto();
        live.anvill.ws.entities.UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity== null)throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        System.out.println("id found");
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        live.anvill.ws.entities.UserEntity updatedDetails = userRepository.save(userEntity);
        System.out.println("saved");
        BeanUtils.copyProperties(updatedDetails, returnVal);
        return returnVal;
    }

    @Override
    public void deleteUser(String userId) {
        live.anvill.ws.entities.UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity== null)throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers(int page, int limit) {
        List<UserDto> returnVal = new ArrayList<>();
        Pageable pagableRequest = PageRequest.of(page,limit);
        Page<live.anvill.ws.entities.UserEntity> usersPage = userRepository.findAll(pagableRequest);
        List<live.anvill.ws.entities.UserEntity>user = usersPage.getContent();

        for(live.anvill.ws.entities.UserEntity userEntity: user){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            returnVal.add(userDto);
        }

        return returnVal;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        live.anvill.ws.entities.UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity ==null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
