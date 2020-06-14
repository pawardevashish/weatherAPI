package com.phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.service.WeatherService;

@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value="/weather", produces=MediaType.APPLICATION_JSON_VALUE)
	public String getDataById(@RequestParam("cityid") String cityId, @RequestParam("data") 
							  String data, Model model) {
		return(weatherService.getDataById(cityId, data));
		
	}
}
