package edu.desafio.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.desafio.desafio.models.Cliente;
import edu.desafio.desafio.service.ClientService;

@RestController
@RequestMapping("Clients")
public class ClientRestController {

    @Autowired
    private ClientService clientService;
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        return ResponseEntity.ok(clientService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(clientService.buscarPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente client){
        clientService.inserir(client);
        return ResponseEntity.ok(client);
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente client){
        clientService.atualizar(id, client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping
    public ResponseEntity<Cliente> deletar(@PathVariable Long id){
        clientService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
