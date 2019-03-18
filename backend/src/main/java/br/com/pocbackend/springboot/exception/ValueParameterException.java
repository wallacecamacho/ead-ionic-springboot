package br.com.pocbackend.springboot.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public class ValueParameterException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final HttpStatus status;
	private final String message;

	public ValueParameterException(HttpStatus status) {
		this(status, null, null);
	}

	public ValueParameterException(HttpStatus status, @Nullable String message) {
		this(status, message, null);
	}

	public ValueParameterException(HttpStatus status, @Nullable String message, @Nullable Throwable cause) {
		super(null, cause);
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
