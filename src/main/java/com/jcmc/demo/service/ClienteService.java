package com.jcmc.demo.service;

import com.jcmc.demo.core.Logger;
import com.jcmc.demo.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger LOG = Logger.getLogger(ClienteService.class);

    private List<Cliente> clientes = new ArrayList<>();

    // Constructor para agregar algunos clientes por defecto
    public ClienteService() {
        clientes.add(new Cliente(1L, "Juan Pérez", "juan.perez@example.com"));
        clientes.add(new Cliente(2L, "María López", "maria.lopez@example.com"));
    }

    // Obtener todos los clientes
    public List<Cliente> obtenerTodos() {
        LOG.warn("Se regresan todos los registros de clientes", null);

        return clientes;
    }

    // Obtener un cliente por ID
    public Optional<Cliente> obtenerPorId(Long id) {
        LOG.info("Se regresan el cliente con id " + id, null);
        return clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst();
    }

    // Crear un nuevo cliente
    public Cliente crear(Cliente cliente) {
        LOG.info("Se crea un cliente nuevo con nombre : " + cliente.getNombre(), null);
        clientes.add(cliente);
        return cliente;
    }

    // Actualizar un cliente existente
    public Cliente actualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteExistente = obtenerPorId(id);
        if (clienteExistente.isPresent()) {
            Cliente clienteParaActualizar = clienteExistente.get();
            clienteParaActualizar.setNombre(cliente.getNombre());
            clienteParaActualizar.setEmail(cliente.getEmail());
            return clienteParaActualizar;
        }
        return null; // Si el cliente no existe
    }

    // Eliminar un cliente
    public boolean eliminar(Long id) {
        Optional<Cliente> cliente = obtenerPorId(id);
        if (cliente.isPresent()) {
            clientes.remove(cliente.get());
            LOG.info(String.format("Se elimina el cliente con id : %d", id), null);
            return true;
        }
        return false;
    }
}
