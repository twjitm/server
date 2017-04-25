package com.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.alibaba.fastjson.JSON;
/**
 * 服务端
 * @author Administrator
 *
 */
public class MinaService {

	public static void main(String[] args) {
		IoAcceptor acceptor = new NioSocketAcceptor();
		//添加日志过滤器
 		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		 DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();  
	        ProtocolCodecFilter filter= new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName( "UTF-8" )));   
//	        //添加编码过滤器 处理乱码问题、编码问题  
	        chain.addLast("objectFilter",filter);  
	        //设置核心消息业务处理器  
	        acceptor.setHandler(new DemoServerHandler());
	        try {
			acceptor.bind(new InetSocketAddress(9226));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("启动服务");
	}
	
	/**  
	 * @ClassName: DemoServerHandler  
	 * @Description: 负责session对象的创建和监听以及消息的创建和接收监听
	 * @author twj
	 * @date 
	 */
	static Map<Long, IoSession>currentsesson=new HashMap<>();//当前在线的session
	static Map<String, Message> nosendMessage=new HashMap<>();//没有发送的消息
	
	private static class DemoServerHandler extends IoHandlerAdapter{
		
		//服务器与客户端创建连接
		@Override
		public void sessionCreated(IoSession session) throws Exception {
			System.out.println("服务器与客户端创建连接...");
			super.sessionCreated(session);
		}

		
		@Override
		public void sessionOpened(IoSession session) throws Exception {
			currentsesson.put(session.getId(), session);
			System.out.println("服务器与客户端连接打开...");
			System.out.println("用户="+session.getId()+"上线了");
			System.out.println("目前在线人数已经达到了"+currentsesson.size()+"个");
			super.sessionOpened(session);
		}
		
		//消息的接收处理
		@Override
		public void messageReceived(IoSession session, Object message)throws Exception {
			System.out.println("*********服务端接收数据**********************"+message.toString());
			super.messageReceived(session, message);
			/**
			 * 离线消息发送
			 * 
			 */
			//判断用户是否在线
//			Message msg=(Message) JSON.toJSON(message.toString());//获取到消息
//			String sendId =msg.getSendId();//接收人的id
//		    IoSession onlinesession=currentsesson.get(sendId);//获取在线用户
//		    if(onlinesession!=null){//用户在线
//		    Message nosendmsg = nosendMessage.get(sendId);//查看是否存在没有发送的消息
//		    	if(nosendmsg!=null){//存在没有发送的消息
//		    		onlinesession.write(nosendmsg);
//		    		nosendMessage.remove(sendId);//清除本条离线消息
//		    	}
//		    	onlinesession.write(msg);//发送在线消息
//		    }else{//接收方离线
//		    	nosendMessage.put(sendId, msg);
//		     }
			
			
//		   Person person = (Person) JSON.toJSON(message.toString());
		   Person newp=new Person();
		   newp.setAge(111);
		   newp.setName("lsn");
			for(Map.Entry<Long, IoSession>son:currentsesson.entrySet()){
				IoSession session1=son.getValue();
				if(session.getId()!=session1.getId()){
					session1.write(message.toString());
				}else{
				 WriteFuture wt = session.write("发送成功");
				 if(wt.isWritten()){
					 System.out.println("数据发送出去");
				 }else{
					 System.out.println("数据没有发送出去");
				 }
				}
				
			}
		     System.out.println("***************服务端转发数据完成*************");
			
		}
		/**
		 * 消息发送到达那些地方
		 */
		@Override
		public void messageSent(IoSession session, Object message)
				throws Exception {
			System.out.println("---------messageSent"+session.getId()+message.toString());
			super.messageSent(session, message);
		}
		
		@Override
		public void sessionClosed(IoSession session) throws Exception {
			// TODO Auto-generated method stub
			System.out.println("断开连接************");
			if(currentsesson!=null){
				currentsesson.remove(session.getId());
				System.out.println("用户="+session.getId()+"下线了");
			}
			super.sessionClosed(session);
		}
	}
}
