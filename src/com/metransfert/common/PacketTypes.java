package com.metransfert.common;

//TODO : Change packet type constants to match with protocol definition (see google drive /protocol)
public class PacketTypes {
	
	public static final byte RESERVED = 0;
	
	/**
	 * Type ID of a file packet
	 * <pre>
	 * File packet:
	 * 	ID : 1
	 * 	Direction : Client <---> Server
	 *	Description : This packet will encapsulate file transfered between client and server (For uploads and downloads)
	 * 
	 * 'File' packet data layout : 
	 *<============ payload_len bytes ==============> (N < 2^4)
	 *+-----------------+----------------------------+
	 *|  Str fileName   |        Byte[] data         |
	 *+-----------------+----------------------------+
	 *<====n bytes=====><======= N-n bytes =========>
	 * </pre>
	 */
	public static final byte FILEUPLOAD = 1;
	
	
	public static final byte UPLOADRESULT = 3;
	
	/**
	 * Type ID of a get packet
	 */
	public static final byte REQFILE = 2;
	
	
	public static final byte REQINFO = 4;
	
	public static final byte INFORESULT = 5;
	
	public static final byte ERROR = 9;
	
	public static final byte PING = 6;
	public static final byte PONG = 7;
	
	//update protocol packet types
	public static final byte REQVERSION = 100;
	public static final byte REQLATEST = 101;
	public static final byte REQLATEST_RESULT = 102;
}
