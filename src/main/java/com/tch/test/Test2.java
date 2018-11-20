package com.tch.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;


public class Test2 {
//	private static int COUNTDOWN = 5;
//	
//	private final static byte[] lock = new byte[0];
//	
//	private static int[] arr = new int[10];
//	
//	public static void main(String[] args) {
//		new Thread(new Runnable() {
//			
//			public void run() {
//				// TODO 自动生成的方法存根
//				synchronized (arr) {
//					try {
//						for (int i = 0; i < arr.length; i++) {
//							arr[i] = i;
//							System.out.println(new Date().toLocaleString()+"i="+i+"循环中，准备休眠1s");
//							Thread.sleep(1000);
//						}
//						synchronized (lock) {
//							System.out.println(new Date().toLocaleString()+"循环结束，准备唤醒");
//							lock.notify();// 必须先获取锁才能通知
//						}
//					} catch (InterruptedException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//		
//		while(COUNTDOWN>0) {
//			System.out.println(new Date().toLocaleString()+"进入循环等待");
//			synchronized (lock) {
//				try {
//					System.out.println(new Date().toLocaleString()+"进入同步块，等待3s");
//					lock.wait(3000);//wait释放锁资源
////					Thread.currentThread().sleep(3000);//sleep不释放锁资源
//				} catch (InterruptedException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
//			}
//			if(arr[9]!=9) {
//				COUNTDOWN--;
//				System.out.println(new Date().toLocaleString()+"第"+(5-COUNTDOWN)+"次等待结束");
//			}else {
//				System.out.println(new Date().toLocaleString()+"COUNTDOWN="+COUNTDOWN+"结束，准备打印");
//				COUNTDOWN = 0;
//				String s = StringUtils.join(arr, ',');
//				System.out.println(s);
//			}
//		}
//	}
	
	private final static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				// TODO 自动生成的方法存根
				for(int i=0;i<20;i++) {
					System.out.println("-->"+i);
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(i == 10) {
						System.out.println("park");
						LockSupport.park();
					}
				}
			}
		}, "t1");
		
		try {
			t1.start();
			Thread.currentThread().sleep(1000);
			System.out.println("unpark");
			LockSupport.unpark(t1);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(int i=0;i<5;i++) {
			new Thread(new Runnable() {
				
				public void run() {
					// TODO 自动生成的方法存根
					try {
						LockSupport.unpark(Thread.currentThread());
						System.out.println(Thread.currentThread()+"初始化，开始争夺锁");
						lock.lock();
						System.out.println(Thread.currentThread()+"拿到资源");
						Thread.currentThread().sleep(1*1000);
						System.out.println(Thread.currentThread()+"休眠结束，当前等待节点个数："+lock.getQueueLength());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					} finally {
						lock.unlock();
					}
				}
			}, "MyThread"+i).start();
			
//			try {
//				Thread.currentThread().sleep(3*1000);
//			} catch (InterruptedException e) {
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
		}
		
		final CountDownLatch countDown = new CountDownLatch(10);
		for(int i=0;i<5;i++) {
			new Thread(new Runnable() {
				
				public void run() {
					// TODO 自动生成的方法存根
					try {
						Thread.sleep(100);
						System.out.println("countDown未完成："+countDown.getCount());
						countDown.countDown();
						countDown.countDown();
						System.out.println("countDown未完成："+countDown.getCount());
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}).start();
		}
		try {
			System.out.println("countDown条件等待！");
			countDown.await();
			System.out.println("countDown条件达成！");
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
			
			public void run() {
				// TODO 自动生成的方法存根
				System.out.println("cyclicBarrier所有线程都已完成，执行cyclicBarrier提供的额外操作");
			}
		});
		for(int i=0;i<5;i++) {
			new Thread(new Runnable() {
				
				public void run() {
					// TODO 自动生成的方法存根
					try {
						Thread.sleep(100);
						System.out.println("cyclicBarrier等待其他线程：目前已完成数量："+cyclicBarrier.getNumberWaiting());
						cyclicBarrier.await();
						System.out.println("cyclicBarrier所有线程均完成：目前已完成数量："+cyclicBarrier.getNumberWaiting());
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}).start();
		}
		
	}
	
}

