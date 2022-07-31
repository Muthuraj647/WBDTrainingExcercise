package com.Muthuraj647.WBDTrainingExcercise.SMS.SMSRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Muthuraj647.WBDTrainingExcercise.SMS.Model.UserInfo;

public interface UserRepo  extends JpaRepository<UserInfo, String>{

}
