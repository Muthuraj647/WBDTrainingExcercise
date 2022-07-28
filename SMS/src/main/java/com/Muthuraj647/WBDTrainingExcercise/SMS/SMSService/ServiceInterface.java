package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService;

import java.util.List;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.MemberType;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionFeatures;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionInfo;

public interface ServiceInterface {

	List<SubscriptionInfo> getAllSubscriptionDetails();

	SubscriptionInfo addSubscription(SubscriptionInfo info);

	MemberType addMemberType(MemberType memberType);

	SubscriptionFeatures addFeatures(SubscriptionFeatures features);

	List<MemberType> getAllMemberTypes();

	List<SubscriptionFeatures> getAllFeatures();

}
