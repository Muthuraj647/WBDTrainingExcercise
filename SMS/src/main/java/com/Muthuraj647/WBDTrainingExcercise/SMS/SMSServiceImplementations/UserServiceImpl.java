package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSServiceImplementations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionDuration;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.SubscriptionInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.UserInfo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSRepository.SubscribtionDurationRepo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSRepository.UserRepo;
import com.Muthuraj647.WBDTrainingExcercise.SMS.SMSService.UserInterface;

@Service
public class UserServiceImpl implements UserInterface{

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ServiceImpl subscriptionService;
	
	@Autowired
	private SubscribtionDurationRepo expireRepo;
	
	
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
		
		String expiresOn=durationCalculation(userInfo.getExpiration().getStarting_date(), userInfo.getExpiration().getDuration_in_days());
		
		SubscriptionDuration subsDuration=new SubscriptionDuration();
		
		subsDuration.setPlan_id(info.getSubscription_id());
		subsDuration.setPlan_type(info.getMemberType().getMember_type());
		subsDuration.setStarting_date(userInfo.getExpiration().getStarting_date());
		subsDuration.setDuration_in_days(userInfo.getExpiration().getDuration_in_days());
		subsDuration.setExpiring_date(expiresOn);
		
		UserInfo fromDB=repo.findById(userInfo.getUser_name()).get();
		if(fromDB!=null) {
			fromDB.setInfo(info);
			fromDB.setExpiration(subsDuration);
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
	
	@Override
	public UserInfo revokeSubscription(String user_name) {
		UserInfo userInfo=repo.findById(user_name).get();
		if(userInfo!=null) {
			SubscriptionInfo info=new SubscriptionInfo();
			try {
				info=subscriptionService.getSubscriptionInfoByID(0);
				System.out.println("Membership-->"+info.getMemberType().getMember_type());
			} catch (Exception e) {
				System.out.println("Error with finding Subscription Details");
				return null;
			}
			
			SubscriptionDuration subsDuration=new SubscriptionDuration();
			
			subsDuration.setPlan_id(info.getSubscription_id());
			subsDuration.setPlan_type(info.getMemberType().getMember_type());
			subsDuration.setStarting_date(userInfo.getExpiration().getExpiring_date());
			subsDuration.setDuration_in_days(0);
			subsDuration.setExpiring_date(null);
			
			userInfo.setInfo(info);
			userInfo.setExpiration(subsDuration);
			return repo.save(userInfo);
		}
		return null;
	}
	
	
	@Override
	public String getValidity(String user_name) {
		UserInfo info=repo.findById(user_name).get();
		if(info!=null) {
			return "Plan Expires at "+info.getExpiration().getDuration_in_days();
		}
		return null;
	}

	@Override
	public List<UserInfo> getValidityWithinPeriod(String date) {
		
		List<UserInfo> listFromDB=repo.findAll();
		List<UserInfo> list=new ArrayList<>();
		
		for (Iterator iterator = listFromDB.iterator(); iterator.hasNext();) {
			UserInfo userInfo = (UserInfo) iterator.next();
			if(userInfo.getExpiration().getExpiring_date()!=null && difference(userInfo.getExpiration().getExpiring_date(), date))
			{
				list.add(userInfo);
			}
		}
		return list;
	}
	
	
	//time difference
	
	public Boolean difference(String endDate,String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			Date d1=sdf.parse(endDate);
			Date d2=sdf.parse(date);
			long difference_In_Time= d1.getTime() - d2.getTime();
			if(difference_In_Time<=0) {
				return true;
			}
		} catch (ParseException e) {
			// TODO: handle exception
		}
		return false;
	}
	
	//calculate Expiration
	
	public String durationCalculation(String startAt, int duration) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
		
		Calendar cal = Calendar.getInstance();  
		System.out.println("Starting Date -->"+startAt);
		
		try {
			 cal.setTime(sdf.parse(startAt));  
		} catch (ParseException e) {
			System.out.println("Error with Parsing...");
			return null;
		}
		cal.add(Calendar.DAY_OF_MONTH, duration);  
		
		String endDate= sdf.format(cal.getTime());  
		System.out.println("Ending Date -->"+endDate);
		return endDate;
	}

	

	

}
