package br.edu.infnet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class TokenDTO {
	private String type;
	private String token;

}
