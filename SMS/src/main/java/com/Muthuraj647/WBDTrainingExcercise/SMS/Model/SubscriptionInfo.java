package com.Muthuraj647.WBDTrainingExcercise.SMS.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "Subscription_info")

public class SubscriptionInfo {
	
	@Id
	private int subscription_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_type")
	private MemberType memberType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "feature")
	private SubscriptionFeatures features;

	public SubscriptionInfo(int subscription_id, MemberType memberType, SubscriptionFeatures features) {
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

	public SubscriptionFeatures getFeatures() {
		return features;
	}

	public void setFeatures(SubscriptionFeatures features) {
		this.features = features;
	}
	
	
}
