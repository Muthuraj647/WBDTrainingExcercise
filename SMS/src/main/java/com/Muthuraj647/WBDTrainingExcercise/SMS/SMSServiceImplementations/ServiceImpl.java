package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSServiceImplementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.MemberType;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionFeaturesDetails;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.Features;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSRepository.FeatureMergeRepo;
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
	
	@Autowired
	private FeatureMergeRepo featureMergeRepo;
	
	@Override
	public MemberType addMemberType(MemberType memberType) {
		return memberTypeRepo.save(memberType);
	}

	@Override
	public Features addFeatures(Features features) {
		
		return feauturesRepo.save(features);
	}

	@Override
	public List<MemberType> getAllMemberTypes() {
		
		return memberTypeRepo.findAll();
	}

	@Override
	public List<Features> getAllFeatures() {
		
		return feauturesRepo.findAll();
	}
	
	@Override
	public List<SubscriptionInfo> getAllSubscriptionDetails() {
		
		return repo.findAll();
	}

	@Override
	public SubscriptionInfo addSubscription(SubscriptionInfo info) {
		MemberType memberType=memberTypeRepo.findById(info.getMemberType().getMember_id()).get();
		
		if(memberType!=null) {
			info.setMemberType(memberType);
			//System.out.println("MemberType-->"+memberType.getMember_type());
		}
		
		
		return repo.save(info);
	}

	@Override
	public SubscriptionFeaturesDetails mergeFeaturesWithMembers(
			SubscriptionFeaturesDetails subscriptionFeaturesDetails) {
		
		Features features=feauturesRepo.findById(subscriptionFeaturesDetails.getFeature().getFeature_id()).get();
		
		if(features!=null) {
			subscriptionFeaturesDetails.setFeature(features);
		}
		
		SubscriptionInfo info= repo.findById(subscriptionFeaturesDetails.getInfo().getSubscription_id()).get();
		
		if(info!=null) {
			subscriptionFeaturesDetails.setInfo(info);
		}
		
		return featureMergeRepo.save(subscriptionFeaturesDetails);
	}

	@Override
	public List<SubscriptionFeaturesDetails> getAllFeaturesDetails() {
		return featureMergeRepo.findAll();
	}

	@Override
	public String deleteFeatureMapping(int feature_no) {
		try {
			featureMergeRepo.deleteById(feature_no);
			return "Deleted";
			
		} catch (Exception e) {
			System.out.println("Execption");
		}
		
		return "Can't Delete";
	}

	@Override
	public SubscriptionInfo getSubscriptionInfoByID(int id) {
		return repo.findById(id).get();
	}

	@Override
	public String deleteSubscriptionInfoByID(int id) {

		List<SubscriptionFeaturesDetails> details=repo.findById(id).get().getFeatures();
		
		for (Iterator iterator = details.iterator(); iterator.hasNext();) {
			SubscriptionFeaturesDetails subscriptionFeaturesDetails = (SubscriptionFeaturesDetails) iterator.next();
			try {
				featureMergeRepo.deleteById(subscriptionFeaturesDetails.getFeature_no());
				System.out.println("Unmerged the Feature");
			} catch (Exception e) {
				return "Internal Server Error";
			}
		}
		
		try {
			repo.deleteById(id);
			return "Deleted";
		} catch (Exception e) {
			System.out.println("Error");
		}
		return "Can't Delete";
	}
	
	

}
