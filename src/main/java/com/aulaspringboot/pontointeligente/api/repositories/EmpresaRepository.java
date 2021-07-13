package com.aulaspringboot.pontointeligente.api.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulaspringboot.pontointeligente.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	@Transactional
	Empresa findByCnpj(String cnpj);
}
