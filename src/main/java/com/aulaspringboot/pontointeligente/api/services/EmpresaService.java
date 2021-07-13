package com.aulaspringboot.pontointeligente.api.services;

import java.util.Optional;

import com.aulaspringboot.pontointeligente.api.entities.Empresa;

public interface EmpresaService {
	
	/**
	 * Retorna uma empresa dado um CNPJ
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	
	Empresa persistir(Empresa empresa);

}
