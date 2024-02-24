package _com0.storage.assignment.service;

import _com0.storage.assignment.entiy.Client;

import java.util.List;

public interface ClientService {

    Client saveClient(Client client);

    List<Client> getAllClients();

    Client updateClient(Client client, Long id);
}
