/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumassolver;

/**
 *
 * @author andresmovilla
 */
public class Silk implements Runnable{

	private Thread thread;
	private boolean value;
	
	public Silk() {
		this.thread = new Thread(this);
		this.value = true;
	}
	
	public void startThread() {
		this.value = true;
		this.thread.start();
	} 
	
	public void stopThread() {
		this.value = false;
	}

	@Override
	public void run() {
		while (true) {
			Main.doStep();
			try {
				Thread.sleep(10);
			} catch (Exception e) { }
		}
	}
}
