package br.com.datarey.model.type;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class StdDecimal<SD extends StdDecimal<SD>> extends Number implements Serializable, Comparable<SD> {
	private static final long serialVersionUID = -6168622750555345279L;

	protected abstract int getScale();
	protected static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

	protected BigDecimal value;

	@SuppressWarnings("unchecked")
	protected SD newDecimalInstance() {
		try {
			return ((Class<SD>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected StdDecimal() {}
	
	public StdDecimal(BigDecimal val) {
		setValue(val);
	}
	
	public StdDecimal(BigInteger val) {
		setValue(new BigDecimal(val));
	}
	
	public StdDecimal(double val) {
		setValue(new BigDecimal(val));
	}
	
	public StdDecimal(long val) {
		setValue(new BigDecimal(val));
	}
	
	public StdDecimal(int val) {
		setValue(new BigDecimal(val));
	}
	
	public StdDecimal(char[] val) {
		setValue(new BigDecimal(val));
	}
	
	public StdDecimal(String val) {
		setValue(new BigDecimal(val));
	}
	
	public StdDecimal(Object val) {
		setValue(new BigDecimal(val.toString()));
	}
	
	public BigDecimal getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	protected SD setValue(BigDecimal value) {
		this.value = value.setScale(getScale(), ROUNDING_MODE);
		return (SD) this;
	}

	public SD add(BigDecimal augend) {
		return newDecimalInstance().setValue(value.add(augend));  
	}
	
	public SD add(SD augend) {
		return add(augend.value);
	}
	
	public SD subtract(BigDecimal subtrahend) {
		return newDecimalInstance().setValue(value.subtract(subtrahend));  
	}
	
	public SD subtract(SD subtrahend) {
		return subtract(subtrahend.value);
	}
	
	public SD multiply(BigDecimal multiplicand) {
		return newDecimalInstance().setValue(value.multiply(multiplicand));  
	}

	public SD multiply(SD multiplicand) {
		return multiply(multiplicand.value);
	}
	
	public SD divide(BigDecimal divisor) {
		return newDecimalInstance().setValue(value.divide(divisor, getScale(), ROUNDING_MODE));  
	}
	
	public SD divide(SD divisor) {
		return divide(divisor.value);
	}
	
	public SD negate() {
		return newDecimalInstance().setValue(value.negate());  
	}
	
	public SD abs() {
		return newDecimalInstance().setValue(value.abs());  
	}
	
	public int compareTo(SD val) {
		return value.compareTo(val.value);
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof StdDecimal))
			return false;
		StdDecimal<?> castOther = (StdDecimal<?>) other;
		return new EqualsBuilder().append(value, castOther.value).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(value).toHashCode();
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public int intValue() {
		return this.value.intValue();
	}

	@Override
	public long longValue() {
		return this.value.longValue();
	}

	@Override
	public float floatValue() {
		return this.value.floatValue();
	}

	@Override
	public double doubleValue() {
		return this.value.doubleValue();
	}
	
	
}