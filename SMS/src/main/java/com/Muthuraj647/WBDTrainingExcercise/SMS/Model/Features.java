package com.Muthuraj647.WBDTrainingExcercise.SMS.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscription_features")

public class SubscriptionFeatures {

	@Id
	private int feature_id;
	
	private String feature;

	public SubscriptionFeatures(int feature_id, String feature) {
		super();
		this.feature_id = feature_id;
		this.feature = feature;
	}

	public SubscriptionFeatures() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFeature_id() {
		return feature_id;
	}

	public void setFeature_id(int feature_id) {
		this.feature_id = feature_id;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}
	
}
