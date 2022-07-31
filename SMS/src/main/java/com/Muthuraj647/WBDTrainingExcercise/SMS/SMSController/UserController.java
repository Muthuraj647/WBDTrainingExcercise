package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.UserInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService.UserInterface;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserInterface service;

	@PostMapping("/add")
	public UserInfo addUser(@RequestBody UserInfo info) {
		return service.addUser(info);
	}
	
	@GetMapping("/get-all")
	public List<UserInfo> getAllUsers() {
		return service.getAllUsers();
	}
	
	@GetMapping("/get/{user_name}")
	public UserInfo getUserByUserName(@PathVariable String user_name) {
		return service.getUserByName(user_name);
	}
	
	@PutMapping("/update/membership")
	public UserInfo updateUserMembership(@RequestBody UserInfo userInfo) {
		return service.updateUserMembership(userInfo);
	}
	
	@DeleteMapping("/delete/{user_name}")
	public String deleteUser(@PathVariable String user_name) {
		return service.deleteUser(user_name);
	}
	
	@PutMapping("/revoke-subscription/{user_name}")
	public UserInfo revokeSubscription(@PathVariable String user_name) {
		return service.revokeSubscription(user_name);
	}
	
	@GetMapping("/{user_name}/validity")
	public String getValidity(@PathVariable String user_name) {
		return service.getValidity(user_name);
	}
	
	@GetMapping("/validity")
	public List<UserInfo> getExpiringUsers(@Param("date") String date) {
		System.out.println("date"+date);
		return service.getValidityWithinPeriod(date);
	}
}
