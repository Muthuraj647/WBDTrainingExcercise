package com.Muthuraj647.WBDTrainingExcercise.SMS.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "features")

public class Features {

	@Id
	private int feature_id;
	
	private String feature;

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

	public Features(int feature_id, String feature) {
		super();
		this.feature_id = feature_id;
		this.feature = feature;
	}

	public Features() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
