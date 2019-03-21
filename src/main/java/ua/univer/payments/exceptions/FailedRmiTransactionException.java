package ua.univer.payments.exceptions;

public class FailedRmiTransactionException extends RuntimeException {

	public FailedRmiTransactionException() {
		super();
	}

	public FailedRmiTransactionException(String message) {
		super(message);
	}	
}
