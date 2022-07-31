package com.Muthuraj647.WBDTrainingExcercise.SMS.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table (name = "Subscription_info")

public class SubscriptionInfo{
	
	@Id
	private int subscription_id;
	
	@OneToOne()
	@JoinColumn(name = "member_id")
	private MemberType memberType;
	
	@OneToMany(mappedBy = "info", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = "info")
	private List<SubscriptionFeaturesDetails> features;

	public SubscriptionInfo(int subscription_id, MemberType memberType, List<SubscriptionFeaturesDetails> features) {
		super();
		this.subscription_id = subscription_id;
		this.memberType = memberType;
		this.features = features;
	}

	public SubscriptionInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(int subscription_id) {
		this.subscription_id = subscription_id;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public List<SubscriptionFeaturesDetails> getFeatures() {
		return features;
	}

	public void setFeatures(List<SubscriptionFeaturesDetails> features) {
		this.features = features;
	}
	
	
}
