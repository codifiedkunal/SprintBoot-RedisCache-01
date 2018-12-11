package com.kworld.bootredis.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import io.swagger.annotations.ApiModelProperty;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long eid;
	private String firstName;
	private String lastName;
	private SEX sex;
	
	@ApiModelProperty(example="21-03-2013")
	@JsonFormat(pattern="dd-MM-yyyy")
	private String dob;
	private Double salary;
	public Employee() {}
	
	private Employee(Builder builder) {
		this.eid = builder.eid;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.sex = builder.sex;
		this.dob = builder.dob;
		this.salary = builder.salary;
	}
	
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public SEX getSex() {
		return sex;
	}
	public void setSex(SEX sex) {
		this.sex = sex;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	/**
	 * Creates builder to build {@link Employee}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link Employee}.
	 */
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
	public static final class Builder {
		private Long eid;
		private String firstName;
		private String lastName;
		private SEX sex;
		private String dob;
		private Double salary;

		private Builder() {
		}

		public Builder eid(Long eid) {
			this.eid = eid;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder sex(SEX sex) {
			this.sex = sex;
			return this;
		}

		public Builder dob(String dob) {
			this.dob = dob;
			return this;
		}

		public Builder salary(Double salary) {
			this.salary = salary;
			return this;
		}

		public Employee build() {
			return new Employee(this);
		}
	}
}
