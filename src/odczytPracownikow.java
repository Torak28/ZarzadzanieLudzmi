import java.io.*;
import java.util.*;
/**
 * Created by Torak28 on 04.01.2017.
 */
public class odczytPracownikow {
	private Scanner x;
	public int ilosc;
	public void otworzPlik(){
		try {
			x = new Scanner(new File("src/txt/Pracownicy.txt"));
		} catch (Exception e){
			System.out.println("Nie ma pliczku :c");
		}
	}
	public void zamknij(){
		x.close();
	}
	public String odczyt(){
		while(x.hasNext()){
			String i = x.next();
			String n = x.next();

			String out = i + " " + n;
			return out + "\n";
		}
		return "\n";
	}
	public int Ilu(){
		otworzPlik();
		ilosc = 0;
		while(x.hasNext()){
			String i = x.next();
			String n = x.next();

			ilosc += 1;
		}
		zamknij();
		return ilosc;
	}
}