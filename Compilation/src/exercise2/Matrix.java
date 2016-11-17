package exercise2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Matrix {
	List<List<Scalar>> mMatrix;
	
	public Matrix(){
		
	}
	
	/***
	 * this method imports a matrix text data file into a Matrix object
	 * @param path
	 * 		the real path where the matrix data file is located
	 */
	public void importMatrixFromFile(String path){
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			String line;
			while ((line = reader.readLine()) != null) {
				/*process the line and add it to first row in Matrix object*/
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * this method switches between two rows in the matrix
	 * @param row_src
	 * 		number of the row in the matrix to be switched
	 * @param row_dst
	 * 		number of the row in the matrix to be switched with
	 */
	public void switchRows(int row_src, int row_dst){
		
	}
	
	/***
	 * this method multiplies all values in a specified row of the matrix by a scalar
	 * @param row_src
	 * 		number of the row in the matrix to be multiplied by scalar
	 * @param scalar
	 * 		scalar value to multiply
	 */
	public void multiByScalar(int row_src, int scalar){
		
	}
	
	/***
	 * 
	 * @param row_src
	 * 		number of the row in the matrix that will be modified
	 * @param row_dst
	 * 		number of the row in the matrix that will be used to calculate the offset
	 * @param factor
	 * 		integer value to be used in offset multiply
	 */
	public void addRowFactor(int row_src, int row_dst, int factor){
		
	}
}
