package com.aulaspringboot.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.aulaspringboot.pontointeligente.api.entities.Lancamento;

public interface LancamentoService {
	
	/**
	 * Retorna todos os lancamentos de um funcionário com paginação
	 * @param funcionarioId
	 * @param pageRequest
	 * @return
	 */
	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);
	
	/**
	 * Retorna um lancamento por ID
	 * @param id
	 * @return
	 */
	Optional<Lancamento> buscarPorId(Long id);
	
	/**
	 * Persiste um lancamento na base de dados
	 * @param lancamento
	 * @return
	 */
	Lancamento persistir(Lancamento lancamento);
	
	/**
	 * Remove um lancamento da base de dados
	 * @param id
	 */
	void remover(Long id);
}
