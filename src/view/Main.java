package view;

import java.util.concurrent.Semaphore;

import controller.Caixa;

public class Main {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		int conta = 0;
		double saldo = 0;
		double transacao = 0;
		double saque = 0;
		
      for (int id = 1; id <= 20; id++) {
    	
		Thread Caixa = new Caixa (semaforo, conta, saldo, transacao, saque, id);
		 Caixa.start();

}
}
}