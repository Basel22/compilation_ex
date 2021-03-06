package exercise2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Matrix {
	private List<List<Scalar>> mMatrix = null;
	
	public Matrix(){
		mMatrix = new ArrayList<>();
	}
		
	/***
	 * this method imports a matrix text data file into a Matrix object
	 * @param path
	 * 		the real path where the matrix data file is located
	 */
	public void importMatrixFromFile(String path){
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			String getMatrixLine;
			String [] rowsData = null;
			while ((getMatrixLine = reader.readLine()) != null) {
				//remove the square brackets from the both sides
				getMatrixLine = getMatrixLine.substring(1, getMatrixLine.length()-1);
				
				//separate each line by semi colon
				rowsData = getMatrixLine.split(";");
		    }
			
			if (rowsData == null){return;}
			
			for (int i=0; i<rowsData.length; i++){
				//trim the row string before parsing
				rowsData[i] = rowsData[i].trim();
				
				//extract individual values from row string using space delimiter
				String[] currentLineValues = rowsData[i].split(" ");
				
				List<Scalar> row = new ArrayList<>();
				for (int j=0;j<currentLineValues.length; j++){
					Scalar scalarObj = null;
					if (currentLineValues[j].contains("/")){
						String[] scalarDecomp = currentLineValues[j].split("/");
						scalarObj = new Scalar(Integer.parseInt(scalarDecomp[0]), Integer.parseInt(scalarDecomp[1]));
					}
					else{
						scalarObj = new Scalar(Integer.parseInt(currentLineValues[j]), 1);
					}
					row.add(scalarObj);
				}
				mMatrix.add(row);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/***
	 * execute the row operations as instructed in a specified operations file
	 * @param path2file
	 * 		the path where the operations file is located on the local machine
	 */
	public void executeOpFile(String path2file){
		//parse the operatons file into a list of operations (OpRow objects)
		List<OpRow> rowOperations = OpRow.parseOpFile(path2file);
		
		//iterate over all operations in the list and determine what to do accordingly
		for (OpRow operation: rowOperations){
			rowOpType type = operation.getmType();
			switch(type){
			case MULTI:
				this.multiByScalar(operation.getmRowSrc(),
						operation.getmFactor());
				break;
			case FACTOR:
				this.addRowFactor(operation.getmRowSrc(),
						operation.getmRowDst(),
						operation.getmFactor());
				break;
			case SWITCH:
				this.switchRows(operation.getmRowSrc(),
						operation.getmRowDst());
				break;
			default:
				break;
			}
		}
	}
	
	/***
	 * this method switches between two rows in the matrix
	 * @param row_src
	 * 		number of the row in the matrix to be switched
	 * 		note that user is prompted to insert row number starting from 1 and not 0
	 * @param row_dst
	 * 		number of the row in the matrix to be switched with
	 */
	public void switchRows(int row_src, int row_dst){
		if (mMatrix == null){return;}
		row_src--;
		row_dst--;
		List<Scalar> row1 = mMatrix.get(row_src);
		List<Scalar> row2 = mMatrix.get(row_dst);
		mMatrix.set(row_src, row2);
		mMatrix.set(row_dst, row1);
	}
	
	/***
	 * this method multiplies all values in a specified row of the matrix by a scalar
	 * @param row_src
	 * 		number of the row in the matrix to be multiplied by scalar
	 * @param scalar
	 * 		scalar value to multiply
	 */
	public void multiByScalar(int row_src, Scalar scalar){
		if (mMatrix == null){return;}
		row_src--;
		List<Scalar> productRow = new ArrayList<>();
		List<Scalar> currentRow = mMatrix.get(row_src);
		for (Scalar item: currentRow){
			productRow.add(Scalar.execute_op(item, scalar, ops.MUL));
		}
		mMatrix.set(row_src, productRow);
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
	public void addRowFactor(int row_src, int row_dst, Scalar factor){
		if (mMatrix == null){return;}
		row_src--;
		row_dst--;
		List<Scalar> productRow = new ArrayList<>();
		List<Scalar> factorRow = new ArrayList<>();
		List<Scalar> currentRow = mMatrix.get(row_src);
		List<Scalar> toAddRow = mMatrix.get(row_dst);
		
		//calculate the factor of the row to add
		for (Scalar item: toAddRow){
			factorRow.add(Scalar.execute_op(item, factor, ops.MUL));
		}
		
		for (int i=0; i<factorRow.size(); i++){
			productRow.add(Scalar.execute_op(currentRow.get(i), factorRow.get(i), ops.ADD));
		}
		mMatrix.set(row_src, productRow);
	}
	
	/***
	 * this method renders the current state of the matrix into a correct format
	 * @return
	 * 		a render version of the matrix into a string object
	 */
	public String matrixPrettyFormat(){
		String matrixRepr = "[";
		for (List<Scalar> row : mMatrix){
			for (Scalar column: row){
				matrixRepr += column.getDenum()==1?
						String.format("%s ",
									column.getEnum()):
							String.format("%s/%s ",
									column.getEnum(),
									column.getDenum());
			}
			matrixRepr = matrixRepr.trim();
			matrixRepr += "; ";
		}
		matrixRepr = matrixRepr.trim();
		matrixRepr = matrixRepr.substring(0, matrixRepr.length()-1);
		matrixRepr += "]";
		return matrixRepr;
	}
}
