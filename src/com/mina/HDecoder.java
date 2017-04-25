package com.mina;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;


public class HDecoder extends CumulativeProtocolDecoder {
	private final Charset charset;  
	  
    public HDecoder(Charset charset) {  
        this.charset = charset;  
    }  
	 @Override  
	    protected boolean doDecode(IoSession arg0, IoBuffer message,  
	            ProtocolDecoderOutput arg2) throws Exception {  
	    
			CharsetDecoder cd = charset.newDecoder();  
	        int id=0;  
	        if(message.remaining()>=4){  
	            id=message.getInt();  
	        }  
	        String name = message.getString(cd);  
	        String emailAdress=message.getString(cd);  
	        int sex=0;  
	        if(message.remaining()>=4){  
	            sex=message.getInt();  
	        }  
	          
	        Person paEntity = new Person();  
	          paEntity.setAge(21);
	          paEntity.setName("lsn");
	        arg2.write(paEntity);  
	        return true;  
	    }  

}
