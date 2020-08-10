package live.anvill.ws.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new Random();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRTSUVWXYZabcdefghijklmnopqrstuvwxyz";


    public String generateRandomId(int length){
        return generatedString(length);
    }

    public String addressId(int length){
        return generatedString(length);
    }

    private String generatedString(int length) {
        StringBuilder returnVal= new StringBuilder(length);

        for(int x=0;x<length;x++){
            returnVal.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnVal);
    }
}
