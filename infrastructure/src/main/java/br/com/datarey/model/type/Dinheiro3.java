package br.com.datarey.model.type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Dinheiro3 extends StdDecimal<Dinheiro3> {
	private static final long serialVersionUID = -3185253020149165439L;

	@Override
	protected int getScale() { return 3; }

	public static final Dinheiro3 ZERO = new Dinheiro3(BigDecimal.ZERO); 
	public static final Dinheiro3 HALF = new Dinheiro3(BigDecimal.valueOf(0.5)); 
	public static final Dinheiro3 ONE = new Dinheiro3(BigDecimal.ONE); 
	public static final Dinheiro3 TEN = new Dinheiro3(BigDecimal.TEN); 
	public static final Dinheiro3 HUNDRED = new Dinheiro3(BigDecimal.valueOf(100)); 
	
	public static Dinheiro3 valueOf(double val) {
		return new Dinheiro3(BigDecimal.valueOf(val));
	}
	
	public static Dinheiro3 valueOf(long val) {
		return new Dinheiro3(BigDecimal.valueOf(val));
	}
	
	protected Dinheiro3() { super(); }

	public Dinheiro3(BigDecimal val) {
		super(val);
	}
	
	public Dinheiro3(BigInteger val) {
		super(val);
	}
	
	public Dinheiro3(double val) {
		super(val);
	}
	
	public Dinheiro3(long val) {
		super(val);
	}
	
	public Dinheiro3(int val) {
		super(val);
	}
	
	public Dinheiro3(char[] val) {
		super(val);
	}
	
	public Dinheiro3(String val) {
		super(val);
	}
	
	public Dinheiro3(Object val) {
		super(val);
	}
}