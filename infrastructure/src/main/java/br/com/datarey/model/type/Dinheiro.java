package br.com.datarey.model.type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Dinheiro extends StdDecimal<Dinheiro> {
	private static final long serialVersionUID = -3185253020149165439L;

	@Override
	protected int getScale() { return 2; }

	public static final Dinheiro ZERO = new Dinheiro(BigDecimal.ZERO); 
	public static final Dinheiro HALF = new Dinheiro(BigDecimal.valueOf(0.5)); 
	public static final Dinheiro ONE = new Dinheiro(BigDecimal.ONE); 
	public static final Dinheiro TEN = new Dinheiro(BigDecimal.TEN); 
	public static final Dinheiro HUNDRED = new Dinheiro(BigDecimal.valueOf(100)); 
	
	public static Dinheiro valueOf(double val) {
		return new Dinheiro(BigDecimal.valueOf(val));
	}
	
	public static Dinheiro valueOf(long val) {
		return new Dinheiro(BigDecimal.valueOf(val));
	}
	
	protected Dinheiro() { super(); }

	public Dinheiro(BigDecimal val) {
		super(val);
	}
	
	public Dinheiro(BigInteger val) {
		super(val);
	}
	
	public Dinheiro(double val) {
		super(val);
	}
	
	public Dinheiro(long val) {
		super(val);
	}
	
	public Dinheiro(int val) {
		super(val);
	}
	
	public Dinheiro(char[] val) {
		super(val);
	}
	
	public Dinheiro(String val) {
		super(val);
	}
	
	public Dinheiro(Object val) {
		super(val);
	}
}