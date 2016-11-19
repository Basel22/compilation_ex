package exercise2;

import java.util.List;

public class Tester {
	public static void main(String[] argv){
		Matrix matrix = new Matrix();
		matrix.importMatrixFromFile(argv[0]);
		System.out.println(matrix.matrixPrettyFormat());
		matrix.executeOpFile(argv[1]);
		//matrix.addRowFactor(1, 3, new Scalar(1,1));
		//System.out.println("this looks promising");
		//List <OpRow> x = OpRow.parseOpFile(argv[0]);
		System.out.println(matrix.matrixPrettyFormat());
	}
}
