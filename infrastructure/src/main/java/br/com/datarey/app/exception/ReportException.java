package br.com.datarey.app.exception;


import br.com.datarey.exception.BaseException;

public class ReportException extends BaseException {
	private static final long serialVersionUID = 1849365493422371908L;

	public ReportException(String message) {
		super(message);
	}

	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}
}
