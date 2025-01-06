package com.example.springboot.multiplication.domain;

/**
 * The multiplication class
 */
public class Multiplication {
	
	private int argA;
	private int argB;
	
	//the result of the calculation
	private int result;

	public Multiplication(int argA, int argB) {
		super();
		this.argA = argA;
		this.argB = argB;
		this.result = argA * argB;
	}

	@Override
	public String toString() {
		return "Multiplication [argA=" + argA + ", argB=" + argB + ", result=" + result + "]";
	}

	public int getArgA() {
		return argA;
	}

	public void setArgA(int argA) {
		this.argA = argA;
	}

	public int getArgB() {
		return argB;
	}

	public void setArgB(int argB) {
		this.argB = argB;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	

}
