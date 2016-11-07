package exercise1;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws Exception{
		if(args.length != 1){
			System.err.println("[Main]: Error- must enter one argument only.");
			System.exit(1);
		}
		FileReader buffer = null;
		try{
			buffer = new FileReader(args[0]);
			Lexer lex = new Lexer(buffer);
			Container container = null;
			container = lex.next_token();
			while(container != null){
				System.out.println(String.format("Token:[%s] - Tag:[%s] - Line:[%s] - Column:[%s]",
													container.getValue(),
													container.getTag(),
													container.getLine(),
													container.getColumn()));
				container = lex.next_token();
			}
		}
		catch (IllegalException e){
			System.err.println(String.format("[Lexer]: Error- <%s - (%s)> at line <%d>", e.getMessage(),
																						 e.getValue(),
																						 e.getLine()));
		}
		catch (FileNotFoundException e){
			System.err.println("[Main]: Error- File not found.");
			System.exit(1);
		}
		catch(IOException e){
			System.err.println("[Main]: Error- Failed to read file.");
			System.exit(1);
		}
		finally {
			if (buffer != null){
				buffer.close();
			}
		}
	}
}
