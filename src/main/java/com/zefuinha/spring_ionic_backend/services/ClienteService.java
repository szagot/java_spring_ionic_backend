package com.zefuinha.spring_ionic_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefuinha.spring_ionic_backend.domain.Cidade;
import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.domain.Endereco;
import com.zefuinha.spring_ionic_backend.domain.enums.TipoCliente;
import com.zefuinha.spring_ionic_backend.dto.ClienteDTO;
import com.zefuinha.spring_ionic_backend.dto.ClienteNewDTO;
import com.zefuinha.spring_ionic_backend.repositories.ClienteRepository;
import com.zefuinha.spring_ionic_backend.repositories.EnderecoRepository;
import com.zefuinha.spring_ionic_backend.services.exceptions.DataIntegrityException;
import com.zefuinha.spring_ionic_backend.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Integer id) {

		// O Optional evita que ocorra um NullException caso não seja encontrada a
		// cliente do id informado
		Optional<Cliente> cliente = repository.findById(id);

		// Retorna a cliente, ou então uma exceção
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado. Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	/**
	 * \@Transactional Garante que o endereço será salvo na mesma transação
	 */
	@Transactional
	public Cliente insert(Cliente cliente) {
		// Garante que o objeto inserido seja novo, sem ID
		cliente.setId(null);

		// Salva cliente e os endereços
		cliente = repository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());

		return cliente;
	}

	public Cliente update(Cliente clienteAtt) {
		// Garante que o ID exista. Se não existir, o método já emite a exceção
		Cliente cliente = findById(clienteAtt.getId());
		updateData(cliente, clienteAtt);

		return repository.save(cliente);
	}

	/**
	 * Auxiliar para atualizar um cliente
	 * 
	 * @param clienteOld
	 * @param cliente
	 */
	private void updateData(Cliente cliente, Cliente clienteAtt) {
		cliente.setNome(clienteAtt.getNome());
		cliente.setEmail(clienteAtt.getEmail());
	}

	public void delete(Integer id) {
		// Garante que o ID exista. Se não existir, o método já emite a exceção
		Cliente cliente = findById(id);

		try {
			repository.delete(cliente);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que tenha pedidos.");
		}
	}

	/**
	 * Auxiliar para paginação
	 * 
	 * @param page      Qt de páginas
	 * @param limit     Registros por página
	 * @param orderBy   Ordenar por qual campo
	 * @param direction Direção da ordenação
	 * @return
	 */
	public Page<Cliente> findPage(Integer page, Integer limit, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, limit, Direction.valueOf(direction), orderBy);

		return repository.findAll(pageRequest);
	}

	/**
	 * Auxiliar para converter de DTO para Entidade
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
	}

	/**
	 * Auxiliar para converter de DTO complexo para Entidade para criação
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(),
				TipoCliente.toEnum(dto.getPessoa()),
				// Senha encriptada
				passEncoder.encode(dto.getSenha()));

		Cidade cid = new Cidade(dto.getCidadeId(), null, null);

		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(),
				dto.getCep(), cid, cli);

		cli.getEnderecos().add(end);

		cli.getTelefones().add(dto.getTel());
		// Telefones opcionais
		if (dto.getTel2() != null) {
			cli.getTelefones().add(dto.getTel2());
		}
		if (dto.getTel3() != null) {
			cli.getTelefones().add(dto.getTel3());
		}

		return cli;
	}
}
