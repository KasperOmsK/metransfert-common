package com.metransfert.common;

import com.packeteer.network.PacketInputStream;
import com.packeteer.network.PacketOutputStream;

public class AsyncTransfert extends Thread {
	protected PacketInputStream in;
	protected PacketOutputStream out;
	protected boolean finished = false;
		
	protected float throughput = 0.0f;
	protected int expectedBytes = 0;
	protected int transferedBytes = 0;
	
	protected AsyncTransfert(PacketInputStream pis, PacketOutputStream pos){
		in = pis;
		out = pos;
	}
	
	public int expectedBytes(){
		return this.expectedBytes;
	}
	
	public int transferedBytes(){
		return this.transferedBytes;
	}
	
	public float throughput(){
		return this.throughput;
	}
	
	public boolean isFinished(){
		return this.finished;
	}
}
