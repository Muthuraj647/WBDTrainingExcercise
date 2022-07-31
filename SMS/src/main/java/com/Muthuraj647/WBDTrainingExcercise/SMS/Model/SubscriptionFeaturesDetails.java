package com.Muthuraj647.WBDTrainingExcercise.SMS.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "features_details")
public class SubscriptionFeaturesDetails{

	@Id
	private int feature_no;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "feature_id")
	private Features feature;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id")
	@JsonIgnoreProperties(value = "features")
	private SubscriptionInfo info;

	
	
	
	public SubscriptionFeaturesDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubscriptionFeaturesDetails(int feature_no, Features feature, SubscriptionInfo info) {
		super();
		this.feature_no = feature_no;
		this.feature = feature;
		this.info = info;
	}
	
	

	public Features getFeature() {
		return feature;
	}

	public void setFeature(Features feature) {
		this.feature = feature;
	}

	public SubscriptionInfo getInfo() {
		return info;
	}

	public void setInfo(SubscriptionInfo info) {
		this.info = info;
	}

	public int getFeature_no() {
		return feature_no;
	}

	public void setFeature_no(int feature_no) {
		this.feature_no = feature_no;
	}

	
}
