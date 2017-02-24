import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

/**
 * Created by Torak28 on 04.01.2017.
 */
public class odczytPracownikow {
	private Scanner x;
	private Scanner y;
	private Scanner z;
	private Scanner q;
	public int ilosc;
	public int iloscZadan;
	public boolean Jest = true;
	public void otworzPlik(){
		try {
			String nazwa;
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			nazwa = "Zadania_" + df.format(date);
			x = new Scanner(new File("src/txt/Pracownicy.txt"));
			y = new Scanner(new File("src/txt/"+nazwa+".txt"));
		} catch (Exception e){
			System.out.println("Nie ma pliczku :c");
			Jest = false;
		}
	}
	public void zamknij(){
		x.close();
	}
	public void zamknijY(){
		y.close();
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
		int iloscPom = ilu;
		ilu = 0;
		return out + iloscPom;
	}
	public void zapis(String in, String in2, String in3){

		PrintWriter zapis;
		try{
			zapis = new PrintWriter("src/txt/Pracownicy.txt");
			zapis.print(in);
			zapis.close();
		} catch (FileNotFoundException e){
			System.out.print("Nie ma Pracownicy.txt");
		}

		String nazwa;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		nazwa = "Zadania_" + df.format(date);
		PrintWriter zapis2;
		try {
			zapis2 = new PrintWriter("src/txt/"+nazwa+".txt");
			zapis2.print(in2);
			zapis2.close();
		} catch (FileNotFoundException e) {
			System.out.print("Nie ma Zadania.txt");
		}

		String nazwa2 = "Oceny_" + df.format(date);
		PrintWriter zapis3;
		try{
			zapis3 = new PrintWriter("src/txt/"+nazwa2+".txt");
			zapis3.print(in3);
			zapis3.close();
		} catch (FileNotFoundException e){
			System.out.print("Nie ma Oceny.txt");
		}
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
		i = i.substring(0, i.length()-1);
		iloscZadan = Integer.parseInt(i);
		y.close();
		return iloscZadan;
	}
	public int odczytOceny(String Imie, String Nazwisko, String Data){
		try{
			String nazwa = "Oceny_" + Data;
			String out;
			z = new Scanner(new File("src/txt/" + nazwa + ".txt"));
			while(z.hasNext()){
				String i = z.next();
				if(i.equals(Imie)){
					i = z.next();
					if(i.equals(Nazwisko)){
						i = z.next();
						out = z.next();
						out = out.substring(0,out.length()-2);
						return Integer.parseInt(out);
					}
				}
			}
		}catch (FileNotFoundException e){
			return -1000;
		}
		return -1000;
	}
	public String poczatekOceny(){
		String out = "nic";
		boolean koniec = true;
		int ilosc = 0;
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date dzisiaj = new Date();
		while (ilosc < 100){
			try{
				String dzisiajS = df.format(dzisiaj);
				q = new Scanner(new File("src/txt/Oceny_" + dzisiajS + ".txt"));
				out = dzisiajS;
				dzisiaj = new Date(dzisiaj.getTime() - (1000 * 60 * 60 * 24));
			}catch (FileNotFoundException e){
				ilosc++;
				dzisiaj = new Date(dzisiaj.getTime() - (1000 * 60 * 60 * 24));
			}
		}
		return out;
	}
	public String koniecOceny(){
		String out;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		out = df.format(date);
		return out;
	}
}