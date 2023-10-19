package edu.desafio.desafio.service;

import edu.desafio.desafio.models.Cliente;

public interface ClientService {
    
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserir(Cliente client);
    void atualizar(Long id, Cliente client);
    void deletar(Long id);
}
