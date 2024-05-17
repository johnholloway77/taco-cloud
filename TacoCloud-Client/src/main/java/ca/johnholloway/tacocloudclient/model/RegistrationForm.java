package ca.johnholloway.tacocloudclient.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private final String username;
    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;

    public TacoUser toUser(PasswordEncoder passwordEncoder){
        return new TacoUser(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phoneNumber);

    }
}
