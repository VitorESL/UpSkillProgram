/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;

@JacksonXmlRootElement(localName = "organizacoes")

/**
 *
 * @author Grupo 3
 */
public class ListaOrganizacaoDTO {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "organizacao")
    
    private ArrayList<OrganizacaoDTO> organizacoes;

    public ListaOrganizacaoDTO() {
    }

    public ArrayList<OrganizacaoDTO> getOrganizacoes() {
        return organizacoes;
    }

    public void setOrganizacoes(ArrayList<OrganizacaoDTO> organizacoes) {
        this.organizacoes = organizacoes;
    }
}
