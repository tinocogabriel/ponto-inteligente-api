package com.aulaspringboot.pontointeligente.api.dtos;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class FuncionarioDto {
	private Long id;
	private String nome;
	private String email;
	private Optional<String> senha = Optional.empty();
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> qtdHorasAlmoco = Optional.empty();
	private Optional<String> qtdHorasTrabalhoDia = Optional.empty();
	
	public FuncionarioDto() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message="Nome não pode ser vazio.")
	@Length(min=3, max=200, message="Nome deve conter entre 3e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotBlank(message="Email não pode ser vazio.")
	@Length(min=5, max=200, message="Email deve conter entre 5 e 200 caracteres.")
	@Email(message="Email inválido")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<String> getSenha() {
		return senha;
	}

	public void setSenha(Optional<String> senha) {
		this.senha = senha;
	}

	public Optional<String> getValorHora() {
		return valorHora;
	}

	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}

	public Optional<String> getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Optional<String> qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}
	
	public Optional<String> getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}

	public void setQtdHorasTrabalhoDia(Optional<String> qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}

	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", valorHora="
				+ valorHora + ", qtdHorasAlmoco=" + qtdHorasAlmoco + "]";
	}
}
