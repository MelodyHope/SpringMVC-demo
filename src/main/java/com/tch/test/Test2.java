package com.tch.test;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;


public class Test2 {
	private static int COUNTDOWN = 5;
	
	private final static byte[] lock = new byte[0];
	
	private static int[] arr = new int[10];
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			public void run() {
				// TODO 自动生成的方法存根
				synchronized (arr) {
					try {
						for (int i = 0; i < arr.length; i++) {
							arr[i] = i;
							System.out.println(new Date().toLocaleString()+"i="+i+"循环中，准备休眠1s");
							Thread.sleep(1000);
						}
						synchronized (lock) {
							System.out.println(new Date().toLocaleString()+"循环结束，准备唤醒");
							lock.notify();// 必须先获取锁才能通知
						}
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		while(COUNTDOWN>0) {
			System.out.println(new Date().toLocaleString()+"进入循环等待");
			synchronized (lock) {
				try {
					System.out.println(new Date().toLocaleString()+"进入同步块，等待3s");
					lock.wait(3000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			if(arr[9]!=9) {
				COUNTDOWN--;
				System.out.println(new Date().toLocaleString()+"第"+(5-COUNTDOWN)+"次等待结束");
			}else {
				System.out.println(new Date().toLocaleString()+"COUNTDOWN="+COUNTDOWN+"结束，准备打印");
				COUNTDOWN = 0;
				String s = StringUtils.join(arr, ',');
				System.out.println(s);
			}
		}
	}
}

