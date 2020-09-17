package live.anvill.ws.implementations;

import live.anvill.ws.entities.AddressEntity;
import live.anvill.ws.entities.UserEntity;
import live.anvill.ws.repository.AddressRepository;
import live.anvill.ws.repository.UserRepository;
import live.anvill.ws.services.AddressServices;
import live.anvill.ws.sharedto.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImplmnt  implements AddressServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDto> listAddress(String Id) {
        List<AddressDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(Id);
        if(userEntity==null) return returnValue;

        Iterable<AddressEntity> address = addressRepository.findAllByUserDetails(userEntity);
        for(AddressEntity addressEntity: address){
            returnValue.add(modelMapper.map(addressEntity,AddressDto.class));
        }

        return returnValue;
    }
}
