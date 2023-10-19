package edu.desafio.desafio.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.desafio.desafio.models.Cliente;
import edu.desafio.desafio.models.ClientRepository;
import edu.desafio.desafio.models.Endereco;
import edu.desafio.desafio.models.EnderecoRepository;
import edu.desafio.desafio.service.ClientService;
import edu.desafio.desafio.service.ViaCepService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    private ViaCepService viacepservice;


    @Override
    public Iterable<Cliente> buscarTodos() {
        return clientRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void inserir(Cliente client) {
       salvarClienteViaCep(client);
    }

    @Override
    public void atualizar(Long id, Cliente client) {
        Optional<Cliente> clientBd = clientRepository.findById(id);
        if(clientBd.isPresent()){
            salvarClienteViaCep(client);
        }
    }

    @Override
    public void deletar(Long id) {
        clientRepository.deleteById(id);
    }

    private void salvarClienteViaCep(Cliente client){
        String cep = client.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novoEndereco = viacepservice.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        client.setEndereco(endereco);
        clientRepository.save(client);
    }
    
}
