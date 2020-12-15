package com.metransfert.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import com.packeteer.network.PacketHeader;
import com.packeteer.network.PacketInputStream;
import com.packeteer.network.PacketOutputStream;
import com.packeteer.network.PacketUtils;

public class FileTransfert {
	
	private static int BUFFER_SIZE = 10*1024;
	
	@SuppressWarnings("unused")
	private PacketInputStream in;
	private PacketOutputStream out;
	
	public FileTransfert(PacketInputStream file_in, PacketOutputStream file_out){
		
		if(file_in == null)
			throw new IllegalArgumentException("Argument 'PacketInputStream' cannot be null");
		
		if(file_out == null)
			throw new IllegalArgumentException("Argument 'PacketOutputStream' cannot be null");
		
		this.in = file_in;
		this.out = file_out;
	}
	
	public void upload(File file) throws IOException{
		//create and send appropriate header
		
		if(file == null)
			throw new IllegalArgumentException("File argument cannot be null");
		
		FileInputStream fis = null;
		
		try{
			PacketHeader fileHeader = new PacketHeader( (int)(file.length() + PacketUtils.calculateNetworkStringLength(file.getName())) , 
					PacketTypes.FILEUPLOAD);
			
			out.write(fileHeader);
			
			//send file name
			out.write(file.getName());
			
			//send file data
			fis = new FileInputStream(file);
			byte[] data = new byte[BUFFER_SIZE];
			int count;
	        while ((count = fis.read(data)) > 0) {
	        	out.write(data, 0, count);
	            out.flush();
	        }
		}
		catch(IOException e){
			throw e;
		}
		finally{
			if(fis != null)
				fis.close();			
		}
	}
	
	public File download(Path saveDirectory) throws IOException{
		
		if(saveDirectory == null)
			throw new IllegalArgumentException("Argument 'Path' cannot be null");
				
		throw new RuntimeException("Not implemented yet");
	}
	
}
