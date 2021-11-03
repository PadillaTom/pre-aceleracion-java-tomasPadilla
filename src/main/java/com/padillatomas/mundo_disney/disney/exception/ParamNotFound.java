package com.padillatomas.mundo_disney.disney.exception;

@SuppressWarnings("serial")
public class ParamNotFound extends RuntimeException {

	public ParamNotFound(String error) {
		super(error);
	}
}
