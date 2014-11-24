package testThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

	private static int produceTaskSleepTime = 1;
	private static int produceTaskMaxNumber = 10;

	/**
	 * �̳߳�ִ�е�����
	 */
	public static class ThreadPoolTask implements Runnable {
		// ������������Ҫ������
		private Object threadPoolTaskData;

		ThreadPoolTask(Object tasks) {
			this.threadPoolTaskData = tasks;
		}

		public void run() {
			// ����һ����������Ĵ���ʽ̫���ˣ�������һ����ӡ���
			System.out.println("start .." + threadPoolTaskData);
			try {
				// ���ڹ۲죬�ȴ�һ��ʱ��
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// threadPoolTaskData = null;

			System.out.println("end .." + threadPoolTaskData);
		}

		public Object getTask() {
			return this.threadPoolTaskData;
		}
	}

	public static void main(String[] args) {
		// ����һ���̳߳�
		/*
		 * ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,
		 * TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), new
		 * ThreadPoolExecutor.DiscardOldestPolicy());
		 */
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());

		// BlockingQueue<Runnable> blockingQueue=new
		// ArrayBlockingQueue<Runnable>(3);

		for (int i = 1; i <= produceTaskMaxNumber; i++) {
			try {
				// ����һ�����񣬲�������뵽�̳߳�
				String task = "task@ " + i;
				System.out.println("put " + task);

				threadPool.execute(new ThreadPoolTask(task));

				// ���ڹ۲죬�ȴ�һ��ʱ��
				Thread.sleep(produceTaskSleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("active:" + threadPool.getActiveCount() + " queue:" + threadPool.getQueue().size());

		try {
			threadPool.shutdown();
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
