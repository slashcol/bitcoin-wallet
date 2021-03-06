package net.dirtyfilthy.bitcoin.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import net.dirtyfilthy.bitcoin.util.MyHex;



public class TxIn  implements ByteArrayable, Cloneable {
	private byte[] outpointHash=new byte[32];
	private long outpointIndex=0;
	private Script script=new Script();
	private long sequence=0;
	
	
	

	public TxIn(DataInputStream in) throws IOException{
		in.read(outpointHash);
		System.out.println("outpoint: "+MyHex.encode(outpointHash));
		this.outpointIndex=((long) Integer.reverseBytes(in.readInt())) & 0xffffffffL;
		System.out.println("index: "+outpointIndex);
		this.script=new Script(in);
		this.sequence=((long) Integer.reverseBytes(in.readInt())) & 0xffffffffL;
	}
	
	
	
	public TxIn() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		String s="outpointHash: "+MyHex.encode(outpointHash)+"\n";
		s+="outpointIndex: "+this.outpointIndex+"\n";
		s+="Script:\n"+script.toString()+"\n";
		s+="Sequence: "+sequence+"\n";
		return s;
	}

	public TxIn clone(){
		try {
			return (TxIn) super.clone();
		} catch (CloneNotSupportedException e) {
			
			throw new RuntimeException(e);
		}
	}
	

	public void setOutpointHash(byte[] outpointHash) {
		this.outpointHash = outpointHash;
	}


	public byte[] getOutpointHash() {
		return outpointHash;
	}
	
	 
	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public void setOutpointIndex(int outpointIndex) {
		this.outpointIndex = outpointIndex;
	}


	public long getOutpointIndex() {
		return outpointIndex;
	}


	public void setScript(Script script) {
		this.script = script;
	}


	public Script getScript() {
		return script;
	}
	

	public Script script() {
		return getScript();
	}
	
	


	public void setTransactionVersion(int transactionVersion) {
		this.sequence = transactionVersion;
	}


	public long getTransactionVersion() {
		return sequence;
	}
	
	public byte[] toByteArray(){
		byte[] rawScript=this.script.toByteArray();
		ByteBuffer dataBuffer=ByteBuffer.allocate(rawScript.length+40);
		dataBuffer.order(ByteOrder.LITTLE_ENDIAN);
		dataBuffer.put(this.outpointHash);
		dataBuffer.putInt((int) this.outpointIndex);
		dataBuffer.put(rawScript);
		dataBuffer.putInt((int) this.sequence);
		return dataBuffer.array();
	}

	public void setOutpointIndex(long outpointIndex) {
		this.outpointIndex = outpointIndex;
	}
	
	
	
	
}
