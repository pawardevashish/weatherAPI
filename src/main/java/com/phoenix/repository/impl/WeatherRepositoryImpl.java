package com.phoenix.repository.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.phoenix.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	public String getDataById(String cityId) {
		
		final String appId = "07ac5b555d637b6f181d8393bf5d21be";
		String inline = "";
		try {
			String endPoint = "http://api.openweathermap.org/data/2.5/weather?id="+cityId+"&appid="+appId;

			URL url = new URL(endPoint);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.connect();
			int responseCode = conn.getResponseCode();
			if(responseCode!=200) {
				throw new RuntimeException("HttpResponseCode: "+responseCode);
			} else {
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()) {
					inline+=sc.nextLine();
				}
				sc.close();
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inline;
	}

}
