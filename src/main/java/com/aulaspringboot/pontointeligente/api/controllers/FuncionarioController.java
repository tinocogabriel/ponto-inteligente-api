package com.aulaspringboot.pontointeligente.api.controllers;


import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aulaspringboot.pontointeligente.api.dtos.FuncionarioDto;
import com.aulaspringboot.pontointeligente.api.entities.Funcionario;
import com.aulaspringboot.pontointeligente.api.response.Response;
import com.aulaspringboot.pontointeligente.api.services.FuncionarioService;
import com.aulaspringboot.pontointeligente.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins="*")
public class FuncionarioController {
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public FuncionarioController() {
		
	}
	
	/**
	 * Atualiza os dados de um funcionario
	 * @param id
	 * @param funcionarioDto
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<FuncionarioDto>> atualizar(@PathVariable("id") Long id, @Valid @RequestBody FuncionarioDto funcionarioDto, BindingResult result) throws NoSuchAlgorithmException{
		log.info("Atualizando funcionario: {}", funcionarioDto.toString());
		Response<FuncionarioDto> response = new Response<FuncionarioDto>();
		
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(id);
		
		if(!funcionario.isPresent()) {
			result.addError(new ObjectError("funcionario", "Funcionario não encontrado"));
		}
		
		this.atualizarDadosFuncionario(funcionario.get(), funcionarioDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando funcionario {}", result.getAllErrors());
			result.getAllErrors().forEach(error->response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.funcionarioService.persistir(funcionario.get());
		response.setData(this.converterFuncionarioDto(funcionario.get()));
		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualiza os dados do funcionario com base nos dados encontrados no funcionario DTO
	 * @param funcionario
	 * @param funcionarioDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	private void atualizarDadosFuncionario(Funcionario funcionario, FuncionarioDto funcionarioDto, BindingResult result) throws NoSuchAlgorithmException{
		funcionario.setNome(funcionarioDto.getNome());
		
		if(!funcionario.getEmail().equals(funcionarioDto.getEmail())) {
			this.funcionarioService.buscarPorEmail(funcionarioDto.getEmail()).ifPresent(func->result.addError(new ObjectError("email","Email já existente")));
			funcionario.setEmail(funcionarioDto.getEmail());
		}
		
		funcionario.setQtdHorasAlmoco(null);
		funcionarioDto.getQtdHorasAlmoco().ifPresent(qtdHorasAlmoco->funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
		
		funcionario.setQtdHorasTrabalhoDia(null);
		funcionarioDto.getQtdHorasTrabalhoDia().ifPresent(qtdHorasTrabalhoDia->funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabalhoDia)));
		
		funcionario.setValorHora(null);
		funcionarioDto.getValorHora().ifPresent(valorHora->funcionario.setValorHora(new BigDecimal(valorHora)));
		
		if(funcionarioDto.getSenha().isPresent()) {
			funcionario.setSenha(PasswordUtils.geraBCrypt(funcionarioDto.getSenha().get()));
		}
	}
	
	/**
	 * Retorna um DTO com os dados de um funcionario
	 * 
	 * @param funcionario
	 * @return
	 */
	private FuncionarioDto converterFuncionarioDto(Funcionario funcionario) {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setId(funcionario.getId());
		funcionarioDto.setEmail(funcionario.getEmail());
		funcionarioDto.setNome(funcionario.getNome());
		funcionario.getQtdHorasAlmocoOpt().ifPresent(qtdHorasAlmoco->funcionarioDto.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
		funcionario.getQtdHorasTrabalhoDiaOpt().ifPresent(qtdHorasDia->funcionarioDto.setQtdHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasDia))));
		return funcionarioDto;
	}
}
