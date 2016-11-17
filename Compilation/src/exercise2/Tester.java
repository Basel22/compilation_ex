package exercise2;

public class Tester {
	public static void main(String[] argv){
		Scalar scalar1 = new Scalar(4,3);
		Scalar scalar2 = new Scalar(4,3);
		Scalar result = Scalar.execute_op(scalar1, scalar2, ops.ADD);
		result.printValue();
	}
}
