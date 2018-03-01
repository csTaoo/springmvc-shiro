package com.shitao.sys.exception;

public class ForbidLoginException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForbidLoginException(String mes)
	{
		super(mes);
	}

}
