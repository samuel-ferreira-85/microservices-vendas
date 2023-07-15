package com.samuel.vendas.online.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Cliente", description = "Cliente")
public class Cliente {
	
	@Id
	@Schema(description = "Indetificador único")
	private String id;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Nome", minLength = 1, maxLength = 50, nullable = false)
	private String nome;
	
	@NotNull
	@Indexed(unique = true, background = true)
	@CPF(message = "CPF inválido.")
	@Schema(description="CPF", nullable = false)
	private String cpf;
	
	@NotNull
	@Schema(description = "Telefone", nullable = false)
	private Long telefone;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Indexed(unique = true, background = true)
	@Schema(description = "E-mail", minLength = 1, maxLength = 50, nullable = false)
	@Email(message = "Email inválido.")
	private String email;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Endereço", minLength = 1, maxLength = 50, nullable = false)
	private String endereco;
	
	@NotNull
	@Schema(description = "Número residencial", nullable = false)
	private Integer numero;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Cidade", minLength = 1, maxLength = 50, nullable = false)
	private String cidade;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Estado", minLength = 1, maxLength = 50, nullable = false)
	private String estado;

}
