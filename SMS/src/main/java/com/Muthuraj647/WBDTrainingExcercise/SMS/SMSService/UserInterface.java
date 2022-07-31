package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService;

import java.util.List;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.UserInfo;

public interface UserInterface {

	UserInfo addUser(UserInfo info);

	List<UserInfo> getAllUsers();

	UserInfo getUserByName(String user_name);

	UserInfo updateUserMembership(UserInfo userInfo);

	String deleteUser(String user_name);

	UserInfo revokeSubscription(String user_name);

	String getValidity(String user_name);

	List<UserInfo> getValidityWithinPeriod(String date);

}
