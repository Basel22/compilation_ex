package exercise1;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws Exception{
		if (args.length != 1){
			System.err.println("[Main]: Error- must enter one argument only.");
			System.exit(1);
		}
		FileReader buffer = null;
		try{
			int getId;
			buffer = new FileReader(args[0]);
			Lexer lex = new Lexer(buffer);
			Container container = null;
			container = lex.next_token();
			while (container != null){
				getId = container.getId();
				switch(getId){
				case sym.STRING:
				case sym.INTEGER:
				case sym.CLASS_ID:
				case sym.ID:
					System.out.println(String.format("%s: %s(%s)",
							container.getLine(),
							container.getTag(),
							container.getValue()));
					break;
				default:
					System.out.println(String.format("%s: %s",
							getId==sym.EOF?container.getLine()+1:container.getLine(),
							container.getTag()));
					break;
				}
				if (getId == sym.EOF) break;
				container = lex.next_token();
			}
		}
		catch (IllegalException e){
			System.err.println(String.format("%d: Lexical error: %s '%s'", e.getLine(),
																			e.getMessage(),							
																			e.getValue()));
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
