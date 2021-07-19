package com.aulaspringboot.pontointeligente.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aulaspringboot.pontointeligente.api.entities.Lancamento;
import com.aulaspringboot.pontointeligente.api.repositories.LancamentoRepository;
import com.aulaspringboot.pontointeligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private static Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired 
	private LancamentoRepository lancamentoRepository;
	
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest){
		log.info("Buscando lancamentos para o funcionário ID {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando um lancamento pelo ID {}", id);
		return this.lancamentoRepository.findById(id);
	}

	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo o lancamento: {}", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	public void remover(Long id) {
		log.info("Removendo o lancamento ID {}", id);
		this.lancamentoRepository.deleteById(id);
	}
}
