package br.com.datarey.model.type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FatorConversao extends StdDecimal<FatorConversao> {
	private static final long serialVersionUID = -3185253020149165439L;

	@Override
	protected int getScale() { return 8; }

	public static final FatorConversao ZERO = new FatorConversao(BigDecimal.ZERO); 
	public static final FatorConversao HALF = new FatorConversao(BigDecimal.valueOf(0.5)); 
	public static final FatorConversao ONE = new FatorConversao(BigDecimal.ONE); 
	public static final FatorConversao TEN = new FatorConversao(BigDecimal.TEN); 
	public static final FatorConversao HUNDRED = new FatorConversao(BigDecimal.valueOf(100)); 
	
	public static FatorConversao valueOf(double val) {
		return new FatorConversao(BigDecimal.valueOf(val));
	}
	
	public static FatorConversao valueOf(long val) {
		return new FatorConversao(BigDecimal.valueOf(val));
	}
	
	protected FatorConversao() { super(); }

	public FatorConversao(BigDecimal val) {
		super(val);
	}
	
	public FatorConversao(BigInteger val) {
		super(val);
	}
	
	public FatorConversao(double val) {
		super(val);
	}
	
	public FatorConversao(long val) {
		super(val);
	}
	
	public FatorConversao(int val) {
		super(val);
	}
	
	public FatorConversao(char[] val) {
		super(val);
	}
	
	public FatorConversao(String val) {
		super(val);
	}
	
	public FatorConversao(Object val) {
		super(val);
	}
}