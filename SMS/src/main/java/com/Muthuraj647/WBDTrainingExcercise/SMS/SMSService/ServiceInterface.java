package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService;

import java.util.List;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.MemberType;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionFeaturesDetails;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.Features;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionInfo;

public interface ServiceInterface {

	List<SubscriptionInfo> getAllSubscriptionDetails();

	SubscriptionInfo addSubscription(SubscriptionInfo info);

	MemberType addMemberType(MemberType memberType);

	Features addFeatures(Features features);

	List<MemberType> getAllMemberTypes();

	List<Features> getAllFeatures();

	SubscriptionFeaturesDetails mergeFeaturesWithMembers(SubscriptionFeaturesDetails subscriptionFeaturesDetails);

	List<SubscriptionFeaturesDetails> getAllFeaturesDetails();

	String deleteFeatureMapping(int feature_no);

	SubscriptionInfo getSubscriptionInfoByID(int id);

	String deleteSubscriptionInfoByID(int id);

}
