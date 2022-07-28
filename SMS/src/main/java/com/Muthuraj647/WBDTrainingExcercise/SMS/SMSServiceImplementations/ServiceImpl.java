package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSServiceImplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.MemberType;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionFeatures;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSRepository.MemberTypeRepo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSRepository.SubscribtionFeauturesRepo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSRepository.SubscribtionInfoRepo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService.ServiceInterface;

@Service
public class ServiceImpl implements ServiceInterface {
	
	@Autowired
	private SubscribtionInfoRepo repo;
	
	@Autowired
	private MemberTypeRepo memberTypeRepo;

	@Autowired
	private SubscribtionFeauturesRepo feauturesRepo;
	
	@Override
	public List<SubscriptionInfo> getAllSubscriptionDetails() {
		
		return repo.findAll();
	}

	@Override
	public SubscriptionInfo addSubscription(SubscriptionInfo info) {
		return repo.save(info);
	}

	@Override
	public MemberType addMemberType(MemberType memberType) {
		return memberTypeRepo.save(memberType);
	}

	@Override
	public SubscriptionFeatures addFeatures(SubscriptionFeatures features) {
		
		return feauturesRepo.save(features);
	}

	@Override
	public List<MemberType> getAllMemberTypes() {
		
		return memberTypeRepo.findAll();
	}

	@Override
	public List<SubscriptionFeatures> getAllFeatures() {
		
		return feauturesRepo.findAll();
	}

}
