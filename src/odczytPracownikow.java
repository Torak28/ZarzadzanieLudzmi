import java.io.*;
import java.util.*;
/**
 * Created by Torak28 on 04.01.2017.
 */
public class odczytPracownikow {
	private Scanner x;
	public void otworzPlik(){
		try {
			x = new Scanner(new File("src/txt/Pracownicy.txt"));
		} catch (Exception e){
			System.out.println("Nie ma pliczku :c");
		}
	}
	public void odczyt(){
		while(x.hasNext()){
			String i = x.next();
			String n = x.next();

			System.out.printf("%s %s\n",i,n);
		}
	}
	public void zamknij(){
		x.close();
	}
}

