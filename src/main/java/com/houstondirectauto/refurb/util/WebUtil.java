package com.houstondirectauto.refurb.util;

import java.util.Base64;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class WebUtil {
	private WebUtil() {
		throw new IllegalStateException("UTILITY_CLASS");
	}
	
	public static double parseStringToDouble(String param) {
		return Double.parseDouble(param);
	}
	public static Integer parseStringToInteger(String param) {
		return Integer.parseInt(param);
	}
	
	public static String generateFileName(MultipartFile multiPart) {
	    return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	public static String getFileNameFromUrl(String key) {
		if(key.isEmpty()) {
			return key.replace("https://s3.us-east-2.amazonaws.com/transportsite/","");
		}
		return"";
	}
	public static String encode(String key) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(key.getBytes());
	}

	public static String decode(String key) {
		Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(key));
	}
	

}
