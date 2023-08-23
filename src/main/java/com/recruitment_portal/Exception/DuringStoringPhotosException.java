package com.recruitment_portal.Exception;

import java.lang.RuntimeException;

public class DuringStoringPhotosException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DuringStoringPhotosException(String massage) {
		
	}

	public DuringStoringPhotosException(String massage,Throwable cause) {
		super(massage,cause);
	}

}
