package live.anvill.ws.services.implementations;

import com.fasterxml.jackson.databind.util.BeanUtil;
import live.anvill.ws.entities.UserEntity;
import live.anvill.ws.repository.UserRepository;
import live.anvill.ws.services.UserServices;
import live.anvill.ws.sharedto.UserDto;
import live.anvill.ws.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
  /* part of validations */
        if(userRepository.findByEmail(userDto.getEmail())!=null) throw new RuntimeException("record exist");
        /* userID encrypytion */
        String publicKey = utils.generateRandomId(30);

   /* base layer
        /* S1* dataclone*/                                    /*this shit brings the input vals from controller to here */
        UserEntity userEntity = new UserEntity();              /* have to save all fields including those that doesn't filled by goons so have to create an object from entity*/
        BeanUtils.copyProperties(userDto, userEntity);         /* to copy the vals from user provided dto to complete entity object, beanutils's copypropertes does that job (source, target)
                                                                in short BeanUtils.copyProperties(ctrl+c, ctrl+v)
        /* S2* automation*/
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));       /* Spring security feature to encrypt the user given password to random string   */
        userEntity.setUserId(publicKey);                        /*    generated random key from utils component */

        /* S3*  operation*/
        UserEntity storeval = userRepository.save(userEntity); /* the main thing... saves the vals through repository and also creates object of it to return the vals for o/p */

        /*S4* aftermath*/
        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(storeval, returnVal);          /*copying storeval to return val for O/P*/
        return returnVal;                                       /* return the val to controller*/
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity ==null) throw new UsernameNotFoundException(email);
        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(userEntity, returnVal);          /*copying storeval to return val for O/P*/
        return returnVal;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity ==null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
