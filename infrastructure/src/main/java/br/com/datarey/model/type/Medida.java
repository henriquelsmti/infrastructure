package br.com.datarey.model.type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Medida extends StdDecimal<Medida> {
	private static final long serialVersionUID = -3185253020149165439L;

	@Override
	protected int getScale() { return 4; }

	public static final Medida ZERO = new Medida(BigDecimal.ZERO); 
	public static final Medida HALF = new Medida(BigDecimal.valueOf(0.5)); 
	public static final Medida ONE = new Medida(BigDecimal.ONE); 
	public static final Medida TEN = new Medida(BigDecimal.TEN); 
	public static final Medida HUNDRED = new Medida(BigDecimal.valueOf(100)); 
	
	public static Medida valueOf(double val) {
		return new Medida(BigDecimal.valueOf(val));
	}
	
	public static Medida valueOf(long val) {
		return new Medida(BigDecimal.valueOf(val));
	}
	
	protected Medida() { super(); }

	public Medida(BigDecimal val) {
		super(val);
	}
	
	public Medida(BigInteger val) {
		super(val);
	}
	
	public Medida(double val) {
		super(val);
	}
	
	public Medida(long val) {
		super(val);
	}
	
	public Medida(int val) {
		super(val);
	}
	
	public Medida(char[] val) {
		super(val);
	}
	
	public Medida(String val) {
		super(val);
	}
	
	public Medida(Object val) {
		super(val);
	}
}