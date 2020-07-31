package live.anvill.ws.controllers;


import live.anvill.ws.requests.UserRequest;
import live.anvill.ws.services.UserServices;
import live.anvill.ws.sharedto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("index")
public class IndexView {

    @Autowired
    UserServices userServices;

    @GetMapping
    public String getUser(){
        return "got user";
    }

    @RequestMapping
    public UserRequest createUser(@RequestBody UserRequest userRequest){
        UserRequest returnValue =new UserRequest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest,userDto);
        UserDto createdUser = userServices.createUser(userDto);
        BeanUtils.copyProperties(createdUser,returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser(){
        return "user updated";
    }

    @DeleteMapping
    public String deleteUser(){
        return "user deleted";
    }
}
