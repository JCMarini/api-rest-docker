package com.jcmc.demo;


import com.jcmc.demo.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ClienteServiceTest {

    @Disabled
    @Test
    void testGetClients() {
        ClienteService clienteService = new ClienteService();
        Assertions.assertNotNull(clienteService.obtenerTodos());
    }

}
