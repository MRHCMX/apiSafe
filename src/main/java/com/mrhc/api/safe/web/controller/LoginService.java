package com.mrhc.api.safe.web.controller;

import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mrhc.api.safe.domain.dto.User;
import com.mrhc.api.safe.domain.repository.UserDAO;

@RestController
@RequestMapping("/auth")

public class LoginService {
	@PostMapping()
	public ResponseEntity<String> validate(@RequestBody User user) {
		boolean status = UserDAO.validate(user.getUserName(), user.getPassword());
		if (status) {
			String HASH = "AHGC-12BD-1328-75HF-HD64";
			
			String json = "{\"token\":\"" + HASH + "\"}";
			return new ResponseEntity<String>(json, HttpStatus.CREATED);
		}
		
		String json = "{\"message\":\"Incorrect data\"}";
		return new ResponseEntity<String>(json, HttpStatus.UNAUTHORIZED);
	}
}
