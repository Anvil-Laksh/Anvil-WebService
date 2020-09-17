package live.anvill.ws.services;

import live.anvill.ws.sharedto.AddressDto;

import java.util.List;


public interface AddressServices {
    List<AddressDto> listAddress(String Id);

}
