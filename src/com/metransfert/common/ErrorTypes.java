package com.metransfert.common;

public class ErrorTypes {
	
	//client error are positives
	public static final byte FILE_DOES_NOT_EXIST = 	1;
	public static final byte INVALID_FILENAME = 	2;
	public static final byte INVALID_REQUEST = 		3;
	
	//server errors are negative
	public static final byte SERVER_ERROR =  -1;
	
}

