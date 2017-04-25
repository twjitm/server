package com.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class HEncoder implements ProtocolEncoder{

	public HEncoder(Charset charSet) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encode(IoSession arg0, Object arg1, ProtocolEncoderOutput arg2) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
