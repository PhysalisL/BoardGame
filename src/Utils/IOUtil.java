package Utils;

import java.util.Scanner;

public class IOUtil {
	private Scanner sc = new Scanner(System.in);
	
	public void print(String string){
		System.out.print(string);
	}
	
	public void println(String string){
		System.out.println(string);
	}
	
	public String readString(){
		return sc.nextLine().trim();
	}
	
	public void close(){
		sc.close();
	}
	
	public int readInt(int exceptionReturn){
		try{
			return Integer.parseInt(sc.nextLine());
		}
		catch(NumberFormatException e){
			return exceptionReturn;
		}
	}
}
