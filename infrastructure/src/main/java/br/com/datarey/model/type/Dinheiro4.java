package br.com.datarey.model.type;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Utiliza��o para valores monet�rios com 4 casas decimais.    
 *
 */
public class Dinheiro4 extends StdDecimal<Dinheiro4> {
	
	private static final long serialVersionUID = -8406704234514699012L;

	@Override
	protected int getScale() {		
		return 4;
	}
	
	public static final Dinheiro4 ZERO = new Dinheiro4(BigDecimal.ZERO); 
	public static final Dinheiro4 HALF = new Dinheiro4(BigDecimal.valueOf(0.5)); 
	public static final Dinheiro4 ONE = new Dinheiro4(BigDecimal.ONE); 
	public static final Dinheiro4 TEN = new Dinheiro4(BigDecimal.TEN); 
	public static final Dinheiro4 HUNDRED = new Dinheiro4(BigDecimal.valueOf(100)); 
	
	public static Dinheiro4 valueOf(double val) {
		return new Dinheiro4(BigDecimal.valueOf(val));
	}
	
	public static Dinheiro4 valueOf(long val) {
		return new Dinheiro4(BigDecimal.valueOf(val));
	}	
	
	protected Dinheiro4() { super(); }

	public Dinheiro4(BigDecimal val) {
		super(val);
	}
	
	public Dinheiro4(BigInteger val) {
		super(val);
	}
	
	public Dinheiro4(double val) {
		super(val);
	}
	
	public Dinheiro4(long val) {
		super(val);
	}
	
	public Dinheiro4(int val) {
		super(val);
	}
	
	public Dinheiro4(char[] val) {
		super(val);
	}
	
	public Dinheiro4(String val) {
		super(val);
	}
	
	public Dinheiro4(Object val) {
		super(val);
	}
}
