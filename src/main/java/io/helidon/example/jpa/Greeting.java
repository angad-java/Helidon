package io.helidon.example.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Access(value = AccessType.FIELD)
@Entity(name = "Greeting")
@Table(name = "GREETING")
public class Greeting {

	@Id
	@Column(name = "SALUTATION")
	private String salutation;

	@Column(name = "RESPONSE")
	private String response;

	public Greeting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Greeting(String salutation, String response) {
		super();
		this.salutation = salutation;
		this.response = response;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Greeting [salutation=" + salutation + ", response=" + response + "]";
	}

}