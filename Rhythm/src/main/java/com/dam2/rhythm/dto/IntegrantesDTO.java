package com.dam2.rhythm.dto;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "integrantes")
@XmlAccessorType(XmlAccessType.FIELD)
public class IntegrantesDTO {
	private List<IntegranteDTO> integrante;

	public IntegrantesDTO() {
	}

	public IntegrantesDTO(List<IntegranteDTO> integrantes) {

		this.integrante = integrantes;
	}

	public List<IntegranteDTO> getList() {
		return integrante;
	}

	public void setIntegrantes(List<IntegranteDTO> integrantes) {
		this.integrante = integrantes;
	}

}
