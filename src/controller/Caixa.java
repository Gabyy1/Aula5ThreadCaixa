package controller;

import java.util.concurrent.Semaphore;

public class Caixa extends Thread {
	private int conta;
	private double saldo;
	private double transacao;
	private double saque;
	private Semaphore semaforo;
	public int id;
	
	public  Caixa (Semaphore semaforo, int conta, double saldo, double transacao, double saque, int id) {
		this.conta = conta;
		this.saldo = saldo;
		this.semaforo = semaforo;
		this.transacao = transacao;
		this.saque = saque;
		this.id = id;
	}
	
	public void dados () {
		
		conta = (int) ((Math.random()*5000)+1000);
		saldo = (int) ((Math.random()*2000));
		saque = (int) ((Math.random()*2000));
		transacao = (int) ((Math.random()*2000));
		
	}
	public void verifica () {
		int t = 0;
		
		t = (int) ((Math.random()*2)+1);
		
		try {
			semaforo.acquire();
			 
			 if(t % 2 == 0) {
			   Deposito();
			   sleep(1000);
			}
				
			else {
				Saque();
				sleep(1000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		  semaforo.release();
		}
		
	}
	
	

	public void run() {
	   verifica ();
}
	

	public void Deposito () {
		dados();
		saldo += transacao;
		System.out.println("\n Pessoa " + id + " Conta: " + conta +  "\n Realizou depósito de: " + transacao +  " Novo saldo: " + saldo);
		
	}
	
	public void Saque () {
		dados();
		if (saldo >= saque) {
			saldo -= saque;
			System.out.println("\n Pessoa " + id +  " Conta: " + conta + "\n Realizou saque de: " + saque + " Novo saldo: " + saldo);
			
		}
		else {
			
			System.out.println("\n Pessoa " + id + " Conta: " + conta + "\n Não foi possível realizar saque de: " + saque + " seu saldo é de: " + saldo);
		}
		
		
	}
}
