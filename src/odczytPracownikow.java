import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by Torak28 on 04.01.2017.
 */
public class odczytPracownikow {
	private Scanner x;
	private Scanner y;
	public int ilosc;
	public int iloscZadan;
	public void otworzPlik(){
		try {
			String nazwa;
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			nazwa = "Zadania_" + df.format(date);
			x = new Scanner(new File("txt/Pracownicy.txt"));
			y = new Scanner(new File("txt/"+nazwa+".txt"));
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
			return out;
		}
		return "Bląd";
	}
	public String odczytZadania(){
		String nazwa;
		int ilu = 0;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String out = df.format(date);
		while(y.hasNext()){
			String i = y.next();
			if(i.equals("Typ:")){
				out += " " + y.next() + " ";
			}
			if(i.equals("Przodowy:")){
				out += y.next() + " ";
				out += y.next() + " ";
			}
			if(i.equals("Pomocnik:")){
				out += y.next() + " ";
				out += y.next() + " ";
				ilu++;
			}if(i.equals("Wynik:")){
				out += y.next() + " ";
				int iloscPom = ilu;
				ilu = 0;
				return out + iloscPom;
			}
		}
		return "koniec";
	}
	public void zapis(String in, String in2, String in3) throws FileNotFoundException{
		PrintWriter zapis = new PrintWriter("txt/Pracownicy.txt");
		zapis.print(in);
		zapis.close();

		String nazwa;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		nazwa = "Zadania_" + df.format(date);

		PrintWriter zapis2 = new PrintWriter("txt/"+nazwa+".txt");
		zapis2.print(in2);
		zapis2.close();

		String nazwa2 = "Oceny_" + df.format(date);
		PrintWriter zapis3 = new PrintWriter("txt/"+nazwa2+".txt");
		zapis3.print(in3);
		zapis3.close();
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
	public int Ile(){
		otworzPlik();
		iloscZadan = 0;
		String i = y.next();
		iloscZadan = Integer.parseInt(i);
		y.close();
		return iloscZadan;
	}
}