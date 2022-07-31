package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.MemberType;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionFeaturesDetails;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.Features;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService.ServiceInterface;

@RestController
public class SMSController {
	
	//for testing
	
	@Autowired
	private ServiceInterface service;

	@PostMapping("/add/member-type")
	public MemberType addMemberType(@RequestBody MemberType memberType) {
		return service.addMemberType(memberType);
	}
	
	@GetMapping("/get-all/member-type")
	public List<MemberType> getAllMemberTypes() {
		return service.getAllMemberTypes();
	}
	
	@PostMapping("/add/features")
	public Features addFeatures(@RequestBody Features features) {
		return service.addFeatures(features);
	}
	@GetMapping("/get-all/features")
	public List<Features> getAllFeatures() {
		return service.getAllFeatures();
	}
	
	@PostMapping("/merge/features-with-member-type")
	public SubscriptionFeaturesDetails mergeDetails(@RequestBody SubscriptionFeaturesDetails subscriptionFeaturesDetails) {
		return service.mergeFeaturesWithMembers(subscriptionFeaturesDetails);
	}
	
	@GetMapping("/get-all/features-with-member-type")
	public List<SubscriptionFeaturesDetails> getAllFeaturesDetails() {
		return service.getAllFeaturesDetails();
	}
	
	@DeleteMapping("/delete/features-with-member-type/{feature_no}")
	public String deleteFeatureMapping(@PathVariable int feature_no)
	{
		return service.deleteFeatureMapping(feature_no);
	}
	
	@PostMapping("/add/subscription-details")
	public SubscriptionInfo addSubscribtion(@RequestBody SubscriptionInfo info) {
		return service.addSubscription(info);
	}
	
	@RequestMapping("/get-all/subscription-info")
	public List<SubscriptionInfo> getAllSubscriptionDetails() {
		return service.getAllSubscriptionDetails();
	}
	
	@GetMapping("/get/subscription-info/{id}")
	private SubscriptionInfo getSubscriptionInfoByID(@PathVariable int id) {
		return service.getSubscriptionInfoByID(id);
	}
	
	@DeleteMapping("/delete/subscription-info/{id}")
	public String deleteSubscriptionInfoByID(@PathVariable int id) {
		return service.deleteSubscriptionInfoByID(id);
	}
	
	
}
