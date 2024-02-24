package _com0.storage.assignment.controller;


import _com0.storage.assignment.entiy.Client;
import _com0.storage.assignment.exception.ResourceHandlerException;
import _com0.storage.assignment.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @GetMapping("/getAllClient")
    public ResponseEntity<?> getAllClient() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Client client,@PathVariable Long id) {
        try {
            Client updatedClient = clientService.updateClient(client, id);
            return ResponseEntity.ok(updatedClient);
        } catch (ResourceHandlerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the client not exits " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
