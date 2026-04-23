package com.snhu.sslserver;

import java.security.MessageDigest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ChecksumController {
	
	@GetMapping("/hash")
	public String myHash() {
		//Adding my name
		String data = "Crystal Walker";
		//calling method to generate hash
		String hashValue = generateHash(data);
		
		//what I want the browser to say
		return "<p>data: " + data + "</p>" + "<p>SHA-256 CheckSum Value: " + hashValue + "</p>";
	}
	
	//method to create SHA-256 hash from input string
	public String generateHash(String input) {
		try {
			//Using messageDigest
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			//Converting input string, my name, into bytes and hashing it
			byte[] hashBytes = messageDigest.digest(input.getBytes());
			return bytesToHex(hashBytes);
			//Catch to show if there is an error with SHA-256
		} catch (NoSuchAlgorithmException e ) {
			return "Error";
		}
	}
	
	//Method to convert hash bytes to hex string
	public String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
		//Loop through the bytes and convert them to a 2 digit hex value
		for (byte b : bytes) {
			hexString.append(String.format("%02x", b));
		}
		//return hex string
		return hexString.toString();
	}
}