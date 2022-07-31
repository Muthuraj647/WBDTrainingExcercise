package com.Muthuraj647.WBDTrainingExcercise.SMS.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plan_durations")
public class SubscriptionDuration {

	@Id
	private int plan_id;
	
	private String plan_type;
	
	private int duration_in_days;
	
	private String starting_date;
	
	private String expiring_date;

	

	public SubscriptionDuration(int plan_id, String plan_type, int duration_in_days, String starting_date,
			String expiring_date) {
		super();
		this.plan_id = plan_id;
		this.plan_type = plan_type;
		this.duration_in_days =duration_in_days;
		this.starting_date = starting_date;
		this.expiring_date = expiring_date;
	}

	public SubscriptionDuration() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public int getDuration_in_days() {
		return duration_in_days;
	}

	public void setDuration_in_days(int duration_in_days) {
		this.duration_in_days = duration_in_days;
	}

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public String getPlan_type() {
		return plan_type;
	}

	public void setPlan_type(String plan_type) {
		this.plan_type = plan_type;
	}

	public String getStarting_date() {
		return starting_date;
	}

	public void setStarting_date(String starting_date) {
		this.starting_date = starting_date;
	}

	public String getExpiring_date() {
		return expiring_date;
	}

	public void setExpiring_date(String expiring_date) {
		this.expiring_date = expiring_date;
	}
	
	
}
