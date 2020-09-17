package live.anvill.ws.controllers;


import live.anvill.ws.requests.UserRequest;
import live.anvill.ws.responses.AddressResponse;
import live.anvill.ws.responses.OperationStatus;
import live.anvill.ws.exceptions.statuscodes.ResponseStatusCodes;
import live.anvill.ws.responses.ResponseStatusName;
import live.anvill.ws.responses.UserResponses;
import live.anvill.ws.services.AddressServices;
import live.anvill.ws.services.UserServices;
import live.anvill.ws.sharedto.AddressDto;
import live.anvill.ws.sharedto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/index")
public class IndexView {

    @Autowired
    UserServices userServices;

    @Autowired
    AddressServices addressServices;

    static ModelMapper modelMapper = new ModelMapper();

    @GetMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponses> getUser(@PathVariable String id) {
        UserResponses returnValue = new UserResponses();
        UserDto userDto = userServices.getUserByUserId(id);
//        BeanUtils.copyProperties(userDto, returnValue);

        returnValue = modelMapper.map(userDto, UserResponses.class);
        return new ResponseEntity<UserResponses>(returnValue, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponses> createUser(@RequestBody UserRequest userRequest) throws Exception {
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

    @PutMapping(path = "/{Id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponses> updateUser(@PathVariable String Id, @RequestBody UserRequest updateRequest) throws Exception {
        UserDto userDto = new UserDto();
        UserResponses returnValue = new UserResponses();
        BeanUtils.copyProperties(updateRequest, userDto);
        UserDto updateUser = userServices.updateUser(Id, userDto);
        BeanUtils.copyProperties(updateUser, returnValue);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{Id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OperationStatus> deleteUser(@PathVariable String Id) {
        OperationStatus returnValue = new OperationStatus();
        returnValue.setOperationName(ResponseStatusName.DELETE.name());
        userServices.deleteUser(Id);
        returnValue.setOperationResult(ResponseStatusCodes.SUCCESS.name());
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponses> listUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserResponses> returnValue = new ArrayList<>();
        List<UserDto> user = userServices.getAllUsers(page, limit);

        for (UserDto userDto : user) {
            UserResponses addUserResponse = new UserResponses();
            BeanUtils.copyProperties(userDto, addUserResponse);
            returnValue.add(addUserResponse);
        }
        return returnValue;
    }
///
////

//        @GetMapping(path = "/{userId}/addresses/{addressId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//        public AddressResponse listAddresses (@PathVariable String userId, @PathVariable String addressId){
//            List<AddressResponse> returnValue = new ArrayList<>();
//            List<AddressDto> addressDto = addressServices.listAddress(userId);
//
////            Link addressLink = linkTo(methodOn(IndexView.class).listAddresses(userId, addressId)).withSelfRel();
////            Link userLink = linkTo(IndexView.class).slash(userId).withRel("user");
////            Link addressesLink = linkTo(methodOn(IndexView.class).getUser(userId)).withRel("addresses");
////
////            AddressResponse addressResponseModel = modelMapper.map(addressDto, AddressResponse.class);
////            addressResponseModel.add(addressesLink);
////            addressResponseModel.add(userLink);
//
////            if(addressDto != null && !addressDto.isEmpty()){
////                java.lang.reflect.Type listType = new TypeToken<List<AddressResponse>>() {}.getType();
////                returnValue  =  modelMapper.map(addressDto, listType);
////            }
//
//            return addressResponseModel;
//        }

}






