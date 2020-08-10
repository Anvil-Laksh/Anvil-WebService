package live.anvill.ws.controllers;


import live.anvill.ws.requests.UserRequest;
import live.anvill.ws.responses.OperationStatus;
import live.anvill.ws.exceptions.statuscodes.ResponseStatusCodes;
import live.anvill.ws.responses.ResponseStatusName;
import live.anvill.ws.responses.UserResponses;
import live.anvill.ws.services.UserServices;
import live.anvill.ws.sharedto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("index")
public class IndexView {

    @Autowired
    UserServices userServices;

    @GetMapping(path="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                               produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponses> getUser(@PathVariable String id){
        UserResponses returnValue =  new UserResponses();
        UserDto userDto = userServices.getUserByUserId(id);
//        BeanUtils.copyProperties(userDto, returnValue);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(userDto,UserResponses.class);
        return new ResponseEntity<UserResponses>(returnValue, HttpStatus.OK);
    }

       @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponses> createUser(@RequestBody UserRequest userRequest) throws Exception{
        UserResponses returnValue = new UserResponses();

//        UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(userRequest,userDto);

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        UserDto createdUser = userServices.createUser(userDto);

        returnValue = modelMapper.map(createdUser, UserResponses.class);

//        BeanUtils.copyProperties(createdUser,returnValue);

            return new ResponseEntity<UserResponses>(returnValue, HttpStatus.OK);

    }

    @PutMapping(path="/{Id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponses> updateUser(@PathVariable String Id, @RequestBody UserRequest updateRequest)throws Exception{
        UserDto userDto = new UserDto();
        UserResponses returnValue = new UserResponses();
        BeanUtils.copyProperties(updateRequest, userDto);
        UserDto updateUser =  userServices.updateUser(Id,userDto);
        BeanUtils.copyProperties(updateUser,returnValue);
        return new ResponseEntity<>(returnValue,HttpStatus.OK);
    }

    @DeleteMapping(path="/{Id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OperationStatus> deleteUser(@PathVariable String Id){
        OperationStatus returnValue = new OperationStatus();
        returnValue.setOperationName(ResponseStatusName.DELETE.name());
        userServices.deleteUser(Id);
        returnValue.setOperationResult(ResponseStatusCodes.SUCCESS.name());
        return new ResponseEntity<>(returnValue,HttpStatus.OK);
    }

    @GetMapping(produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public List <UserResponses> listUsers(@RequestParam (value = "page", defaultValue="0")int page,
                                          @RequestParam (value = "limit", defaultValue = "25")int limit ){
        List<UserResponses> returnValue = new ArrayList<>();
        List<UserDto> user = userServices.getAllUsers(page,limit);

        for (UserDto userDto:user) {
            UserResponses addUserResponse = new UserResponses();
            BeanUtils.copyProperties(userDto, addUserResponse);
            returnValue.add(addUserResponse);
        }

        return returnValue;
    }
}
