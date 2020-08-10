package live.anvill.ws.requests;

import java.util.ArrayList;
import java.util.List;

public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressDetailsRequest> addresses;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressDetailsRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDetailsRequest> addresses) {
        this.addresses = addresses;
    }
}
