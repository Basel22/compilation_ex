package exercise2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Parser {
	public static void main(String[] argv){
		Matrix matrix = new Matrix();
		matrix.importMatrixFromFile(argv[0]);
		matrix.executeOpFile(argv[1]);
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
