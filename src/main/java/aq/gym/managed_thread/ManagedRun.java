package aq.gym.managed_thread;

public class ManagedRun implements Runnable {

	private boolean isStop, isPause;
	private long index;

	@Override
	public void run() {
		while(true) {
			if(isStop) {
				break;
			}
			synchronized(this) {
				if(isPause) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {					
					System.out.printf("%s: %-4d" + " ", Thread.currentThread().getName(), index++);
					if(index % 10 == 0) {
						System.out.println();
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public synchronized void stop() {
		notify();
		isStop = true;
	}

	public synchronized void pause() {
		isPause = true;
	}

	public synchronized void resume() {
		notify();
		isPause = false;
	}
}
