package live.anvill.ws.entities;

import live.anvill.ws.sharedto.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="addresses")
public class AddressEntity implements  Serializable {
    private  static final long SerialVersionId = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length = 25)
    private String addressId;

    @Column(nullable=false, length = 25)
    private String city;

    @Column(nullable=false, length = 25)
    private String country;

    @Column(nullable=false, length = 25)
    private String streetName;

    @Column(nullable=false, length = 25)
    private String postalCode;

    @Column(nullable=false, length = 25)
    private String type;

    @ManyToOne
    @JoinColumn(name="Users_Id")
    private UserEntity userDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }
}
