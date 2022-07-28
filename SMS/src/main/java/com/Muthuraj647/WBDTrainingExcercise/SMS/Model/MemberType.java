package com.Muthuraj647.WBDTrainingExcercise.SMS.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member_type")
public class MemberType {
	
	@Id
	private int member_id;

	private String member_type;

	public MemberType(int member_id, String member_type) {
		super();
		this.member_id = member_id;
		this.member_type = member_type;
	}

	public MemberType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	
	
	
}
