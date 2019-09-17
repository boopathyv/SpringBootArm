package com.aram.connect.Exception;

public class AramException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AramException(String s,Throwable e) 
    { 
        super(s,e); 
    } 
    
    public AramException(String s) 
    { 
        super(s); 
    } 
    
    public AramException(Throwable e) 
    { 
        super(e); 
    } 

}
