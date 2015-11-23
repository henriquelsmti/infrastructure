package br.com.datarey.model.type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Percentual extends StdDecimal<Percentual> {
	private static final long serialVersionUID = -3185253020149165439L;

	@Override
	protected int getScale() { return 2; }

	public static final Percentual ZERO = new Percentual(BigDecimal.ZERO); 
	public static final Percentual HALF = new Percentual(BigDecimal.valueOf(0.5)); 
	public static final Percentual ONE = new Percentual(BigDecimal.ONE); 
	public static final Percentual TEN = new Percentual(BigDecimal.TEN); 
	public static final Percentual HUNDRED = new Percentual(BigDecimal.valueOf(100)); 
	
	public static Percentual valueOf(double val) {
		return new Percentual(BigDecimal.valueOf(val));
	}
	
	public static Percentual valueOf(long val) {
		return new Percentual(BigDecimal.valueOf(val));
	}
	
	protected Percentual() { super(); }

	public Percentual(BigDecimal val) {
		super(val);
	}
	
	public Percentual(BigInteger val) {
		super(val);
	}
	
	public Percentual(double val) {
		super(val);
	}
	
	public Percentual(long val) {
		super(val);
	}
	
	public Percentual(int val) {
		super(val);
	}
	
	public Percentual(char[] val) {
		super(val);
	}
	
	public Percentual(String val) {
		super(val);
	}
	
	public Percentual(Object val) {
		super(val);
	}
}