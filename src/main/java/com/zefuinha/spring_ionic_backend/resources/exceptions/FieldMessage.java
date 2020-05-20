package com.zefuinha.spring_ionic_backend.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filedName;
	private String message;
}