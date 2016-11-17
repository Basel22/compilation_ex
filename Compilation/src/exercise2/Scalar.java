package exercise2;

import java.math.BigInteger;

public class Scalar {
	
	private int mDenum;
	private int mEnum; 
	
	public Scalar(int Enum, int Denum){
		mDenum = Denum;
		mEnum = Enum;
	}
	
	public static Scalar execute_op(Scalar scalar1, Scalar scalar2, ops op){
		Scalar result = null;
		switch(op){
		case ADD:
			result = new Scalar(scalar1.getEnum()*scalar2.getDenum()+scalar2.getEnum()*scalar1.getDenum(),
					scalar1.getDenum()*scalar2.getDenum());
			break;
		case MUL:
			result = new Scalar(scalar1.getEnum()*scalar2.getEnum(), scalar1.getDenum()*scalar2.getDenum());
			break;
		case SUB:
			result = execute_op(scalar1, new Scalar(-scalar2.getEnum(), scalar2.getDenum()), ops.ADD);
			break;
		case DIV:
			result = execute_op(scalar1, new Scalar(scalar2.mDenum, scalar2.getEnum()), ops.MUL);
			break;
		default:
			break;
		}
		if(result != null){
			result.simplifyScalar();
		}
		return result;
	}
	
	public void simplifyScalar(){
		int factor, Enum, Denum;
		Enum = this.getEnum();
		Denum = this.getDenum();
		BigInteger bEnum = BigInteger.valueOf(Enum);
	    BigInteger bDenum = BigInteger.valueOf(Denum);
	    BigInteger gcd = bEnum.gcd(bDenum);
	    factor = gcd.intValue();
		mEnum = mEnum/factor;
		mDenum = mDenum/factor;
	}
	
	public int getDenum() {
		return mDenum;
	}

	public int getEnum() {
		return mEnum;
	}
	
	public void printValue(){
		System.out.println(String.format("%s/%s", mEnum, mDenum));
	}
}
