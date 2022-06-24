package com.example.api.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	private String name;

	@Column(nullable = false)
	@NotEmpty
	private String email;
	private String endereco;
	private String endereco2;
	private String endereco3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {return email;}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {return endereco;}

	public void setEndereco(String endereco) {this.endereco = endereco;}

	public String getEndereco2() {return endereco2;}

	public void setEndereco2(String endereco2) {this.endereco2 = endereco2;}

	public String getEndereco3() {return endereco3;}

	public void setEndereco3(String endereco3) {this.endereco3 = endereco3;}
}
