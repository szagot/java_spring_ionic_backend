package com.zefuinha.spring_ionic_backend.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timeStamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
