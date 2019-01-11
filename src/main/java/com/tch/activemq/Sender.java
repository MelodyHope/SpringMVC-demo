/**
 * 
 */
package com.tch.activemq;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author hsadmin
 *
 */
public class Sender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("my-queue");
			MessageProducer messageProducer = session.createProducer(destination);
//			for(int i=0;i<3;i++) {
			while(true) {
				Scanner scanner = new Scanner(System.in);
				int i = scanner.nextInt();
				TextMessage textMessage = session.createTextMessage("发送测试消息:"+i);
				Thread.sleep(3000);
				messageProducer.send(textMessage);
				session.commit();
				if(i==0) {
					break;
				}
			}
			session.close();
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
