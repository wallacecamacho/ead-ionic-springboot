package br.com.pocbackend.springboot.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.lang.Nullable;

public class CodigoExistenteException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String message;


	public CodigoExistenteException(@Nullable String message, @Nullable Throwable cause) {
		super(message, cause);
		this.message = message;
	}


	@Override
	public String getMessage() {
		return this.message;
	}
}
