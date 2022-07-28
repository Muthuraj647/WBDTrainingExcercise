package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.MemberType;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionFeatures;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService.ServiceInterface;

@RestController
public class SMSController {
	
	//for testing
	
	@Autowired
	private ServiceInterface service;

	@RequestMapping("/get-all/subscription-info")
	public List<SubscriptionInfo> getAllSubscriptionDetails() {
		return service.getAllSubscriptionDetails();
	}
	
	
	@PostMapping("/add/member-type")
	public MemberType addMemberType(@RequestBody MemberType memberType) {
		return service.addMemberType(memberType);
	}
	
	@GetMapping("/get-all/member-type")
	public List<MemberType> getAllMemberTypes() {
		return service.getAllMemberTypes();
	}
	
	@PostMapping("/add/features")
	public SubscriptionFeatures addFeatures(@RequestBody SubscriptionFeatures features) {
		return service.addFeatures(features);
	}
	
	public List<SubscriptionFeatures> getAllFeatures() {
		return service.getAllFeatures();
	}
	
	@PostMapping("/add-subscription-details")
	public SubscriptionInfo addSubscribtion(@RequestBody SubscriptionInfo info) {
		return service.addSubscription(info);
	}
}
