package exercise2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Tester {
	public static void main(String[] argv){
		Matrix matrix = new Matrix();
		matrix.importMatrixFromFile(argv[0]);
		matrix.executeOpFile(argv[1]);
		//matrix.addRowFactor(1, 3, new Scalar(1,1));
		//System.out.println("this looks promising");
		//List <OpRow> x = OpRow.parseOpFile(argv[0]);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(argv[2],"UTF-8");
			writer.write(matrix.matrixPrettyFormat());
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally{
			if (writer != null){
				writer.close();
			}
		}
	}
}
