package com.aram.connect.Exception;

import java.io.IOException;

public class FileStorageException extends RuntimeException {

	public FileStorageException(String message, IOException ex) {
		super(message,ex);
		System.out.println(message);
		
	}
	
	public FileStorageException(String message) {
		super(message);
		System.out.println(message);
		
		
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
