/**
 * 
 */
package com.tch.activemq;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author hsadmin
 *
 */
public class Receiver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("my-queue");
			MessageConsumer messageConsumer = session.createConsumer(destination);
			int i=0;
//			while(i<3) {
			Date date = new Date();
			long startTime = date.getTime();
			while(true) {//忙循环(自旋)获取消息
				i++;
				TextMessage textMessage = (TextMessage) messageConsumer.receive();
				session.commit();
				long duration = date.getTime()-startTime;
				System.out.println("duration 收到消息:"+textMessage.getText());
				if(duration>20000) {
					break;
				}
			}
			session.close();
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
