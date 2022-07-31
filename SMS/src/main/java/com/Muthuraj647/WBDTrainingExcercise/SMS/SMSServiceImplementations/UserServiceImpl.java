package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSServiceImplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.UserInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSRepository.UserRepo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService.UserInterface;

@Service
public class UserServiceImpl implements UserInterface{

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ServiceImpl subscriptionService;
	
	
	@Override
	public UserInfo addUser(UserInfo info) {
		return repo.save(info);
	}

	@Override
	public List<UserInfo> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public UserInfo getUserByName(String user_name) {
		
		return repo.findById(user_name).get();
	}

	@Override
	public UserInfo updateUserMembership(UserInfo userInfo) {
		SubscriptionInfo info=new SubscriptionInfo();
		try {
			info=subscriptionService.getSubscriptionInfoByID(userInfo.getInfo().getSubscription_id());
			System.out.println("Membership-->"+info.getMemberType().getMember_type());
		} catch (Exception e) {
			System.out.println("Error with finding Subscription Details");
			return null;
		}
		
		
		UserInfo fromDB=repo.findById(userInfo.getUser_name()).get();
		if(fromDB!=null) {
			fromDB.setInfo(info);
			return repo.save(fromDB);
		}
		else {
			System.out.println("Can't Update");
		}
		return null;
	}

	@Override
	public String deleteUser(String user_name) {
		try {
			repo.deleteById(user_name);
			System.out.println("User "+user_name+" deleted...");
			return "User "+user_name+" deleted...";
		} catch (Exception e) {
			System.out.println("Can't Delete the User");
			return "Can't Delete";
		}
		
	}

}
