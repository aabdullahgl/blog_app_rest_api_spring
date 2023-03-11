package com.egitim.blogapi.core.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resouceName;
	private String fielName;
	private int fieldValue;

	public ResourceNotFoundException(String resouceName, String fielName, int fieldValue) {
		super(String.format("%s not found with %s : %s", resouceName, fielName, fieldValue));
		this.resouceName = resouceName;
		this.fielName = fielName;
		this.fieldValue = fieldValue;
	}

}
