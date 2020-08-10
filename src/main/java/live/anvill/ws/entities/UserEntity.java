package live.anvill.ws.entities;

import live.anvill.ws.sharedto.AddressDto;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name="users")
public class UserEntity implements Serializable {
    private  static final long SerialVersionId = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length = 100)
    private String userId;

    @Column(nullable=false, length = 100)
    private String firstName;

    @Column(nullable=false, length = 25)
    private String lastName;

    private String emailVerificationToken;

    @Column(nullable=false)
    private String Password;

    @Column(nullable=false, unique = true)
    private String email;

    private String encryptedPassword;

    @Column(nullable=true, columnDefinition = "boolean default false")
    private Boolean emailVerificationStatus ;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private List <AddressEntity> addresses;

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }

    public static long getSerialVersionId() {
        return SerialVersionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
}
