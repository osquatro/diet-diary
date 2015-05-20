package com.zsoft.dto;

import java.util.List;

public class StatusDTO {
	
	private Boolean validationError;
	private Boolean systemError;
	private List<String> errors;
	
	public Boolean getValidationError() {
		return validationError;
	}

	public void setValidationError(Boolean validationError) {
		this.validationError = validationError;
	}

	public Boolean getSystemError() {
		return systemError;
	}

	public void setSystemError(Boolean systemError) {
		this.systemError = systemError;
	}

	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
