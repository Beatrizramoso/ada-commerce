package com.desenvolva_mais.ada_commerce.dto.converter;

import com.desenvolva_mais.ada_commerce.dto.ClienteCompletoDTO;
import com.desenvolva_mais.ada_commerce.dto.CriarClienteDTO;
import com.desenvolva_mais.ada_commerce.model.Cliente;

public class ClienteConverter {
    
    public static Cliente toModel(CriarClienteDTO dto) {
        Cliente model = new Cliente();
        
        model.setNome(dto.getNome());
        model.setDocumento(dto.getDocumento());
        model.setEmail(dto.getEmail());
        
        return model;
    }

    public static Cliente toModel(ClienteCompletoDTO dto) {
        Cliente model = new Cliente();

        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setDocumento(dto.getDocumento());
        model.setEmail(dto.getEmail());

        return model;
    }
    
    public static ClienteCompletoDTO toDTO(Cliente model) {
        ClienteCompletoDTO modelCompletoDTO = new ClienteCompletoDTO();
        
        modelCompletoDTO.setId(model.getId());
        modelCompletoDTO.setNome(model.getNome());
        modelCompletoDTO.setDocumento(model.getDocumento());
        modelCompletoDTO.setEmail(model.getEmail());
        
        return modelCompletoDTO;
    }
    
}
