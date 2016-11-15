package exercise1;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws Exception{
		if (args.length != 2){
			System.err.println("[Main]: Error- must enter two arguments only.");
			System.exit(1);
		}
		FileReader buffer = null;
		PrintWriter writer = null;
		try{
			int getId;
			buffer = new FileReader(args[0]);
			writer = new PrintWriter(args[1],"UTF-8");
			Lexer lex = new Lexer(buffer);
			Container container = null;
			container = lex.next_token();
			while (container != null){
				getId = container.getId();
				switch(getId){
				case sym.QUOTE:
				case sym.INTEGER:
				case sym.CLASS_ID:
				case sym.ID:
					/*System.out.println(String.format("%s: %s(%s)",
							container.getLine(),
							container.getTag(),
							container.getValue()));*/
					writer.write(String.format("%s: %s(%s)\n",
							container.getLine(),
							container.getTag(),
							container.getValue()));
					break;
				default:
					/*System.out.println(String.format("%s: %s",
							getId==sym.EOF?container.getLine()+1:container.getLine(),
							container.getTag()));*/
					writer.write(String.format("%s: %s%s",
							getId==sym.EOF?container.getLine()+1:container.getLine(),
							container.getTag(),
							getId==sym.EOF?"":"\n"));
					break;
				}
				if (getId == sym.EOF) {
					break;
				}
				container = lex.next_token();
			}
		}
		catch (IllegalException e){
			/*System.err.println(String.format("%d: Lexical error: %s '%s'", e.getLine(),
																			e.getMessage(),							
																			e.getValue()));*/
			writer.write(String.format("%d: Lexical error: %s '%s'", e.getLine(),
					e.getMessage(),							
					e.getValue()));
		}
		catch (FileNotFoundException e){
			/*System.err.println("[Main]: Error- File not found.");*/
			writer.write("[Main]: Error- File not found.");
		}
		catch(IOException e){
			/*System.err.println("[Main]: Error- Failed to read file.");*/
			writer.write("[Main]: Error- Failed to read file.");
		}
		finally {
			if (buffer != null){
				buffer.close();
			}
			if (writer != null){
				writer.close();
			}
		}
	}
}
