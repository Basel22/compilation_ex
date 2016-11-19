package exercise2;

import java.math.BigInteger;

public class Scalar {
	private int mEnum; 
	private int mDenum;
	
	public Scalar(int Enum, int Denum){
		mEnum = Enum;
		mDenum = Denum;
	}
	
	/***
	 * execute a single binary operation between two scalar objects
	 * @param scalar1
	 * @param scalar2
	 * 		scalar1 and scalar2 are the two ends of the binary operation
	 * @param op
	 * 		the binary operation itself (can be +,-,*,/)
	 * @return
	 * 		a new scalar object containing the results of the binary operation
	 */
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
	
	/***
	 * simplify a complex fraction into its simplest form by dividing by the great common division
	 * the method will consider Integers as a number with 1 as denum
	 */
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
	
	/***
	 * implementation of equals for scalar objects
	 */
	@Override
	public boolean equals(Object obj) {
		/*To be implemented*/
		return super.equals(obj);
	}
	
	public int getDenum() {
		return mDenum;
	}

	public int getEnum() {
		return mEnum;
	}
	
	/***
	 * pretty printer for a scalar object
	 */
	public void printValue(){
		System.out.println(String.format("%s/%s", mEnum, mDenum));
	}
}
