package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";

@RestController
class ServerController{
//FIXME:  Add hash function to return the checksum value for the data string that should contain your name. 
	
	//bytesToHex function
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte hashByte : bytes) {
            int intVal = 0xff & hashByte;
            if (intVal < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(intVal));
       }
        return sb.toString();
    }
    
	public static String hashFunction(String dataString) throws NoSuchAlgorithmException{
		String algorithem = "SHA-256";
		MessageDigest messageDigest = MessageDigest.getInstance(algorithem);
		byte[] digest = messageDigest.digest(dataString.getBytes());
		String hexString = bytesToHex(digest);
		
		return hexString;
	}
	
	
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "Hello David Gerardi!"; //placed name here. Not sure if this is what was meant
        String hash = hashFunction(data);
    	
        return "<p>Data: "+data+" | SHA-256:"+hash;
    }
}
