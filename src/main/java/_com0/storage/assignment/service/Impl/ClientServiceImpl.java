package _com0.storage.assignment.service.Impl;

import _com0.storage.assignment.entiy.Client;
import _com0.storage.assignment.exception.ResourceHandlerException;
import _com0.storage.assignment.repository.ClientRepository;
import _com0.storage.assignment.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        try {
            return clientRepository.findAll();
        } catch (Exception ex) {
            log.error("Error occurred while fetching all clients", ex);
            throw new RuntimeException("An error occurred while fetching all clients", ex);
        }
    }

    @Override
    public Client updateClient(Client client, Long id) {
        // we need to check whether Client with given id is exists in DB or not
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            // Retrieve the existing client from the Optional
            Client existingClient = clientOptional.get();

            // Update only the fields that are not null in the updated client
            if (client.getName() != null) {
                existingClient.setName(client.getName());
            }
            if (client.getLastName() != null) {
                existingClient.setLastName(client.getLastName());
            }
            if (client.getMobile() != null) {
                existingClient.setMobile(client.getMobile());
            }
            // Save the updated client
            return clientRepository.save(existingClient);
        } else {
            // If the client with the given ID does not exist, throw an exception or handle it appropriately
            throw new ResourceHandlerException("Client", "Id", id);
        }
    }
}
