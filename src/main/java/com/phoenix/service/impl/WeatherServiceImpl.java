package com.phoenix.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.repository.WeatherRepository;
import com.phoenix.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	
	public enum Response {
		coord,
		sys,
		main,
		wind,
		weather
	}

	public String getDataById(String cityId, String requestData) {
		String response = weatherRepository.getDataById(cityId);
	
		JSONObject json = new JSONObject(response);
		String data = "";
		
		JSONObject coord = json.getJSONObject("coord");
		JSONObject sys = json.getJSONObject("sys");
		JSONObject main = json.getJSONObject("main");
		JSONObject wind = json.getJSONObject("wind");
		JSONArray weather = json.getJSONArray("weather");
		
		Map<String, String> map = new HashMap<>();
		map.put("coord", coord.toString());
		map.put("sys", sys.toString());
		map.put("main", main.toString());
		map.put("wind", wind.toString());
		map.put("weather", weather.toString());
		
		
		
		for(Response r: Response.values()) {
			if(r.name().equals(requestData)) {
				 return map.get(requestData);
			}
		}
		if(requestData.equalsIgnoreCase("all")) {
			return response;
		}
		return "Invalid Request Data";
	}
	
	

}
