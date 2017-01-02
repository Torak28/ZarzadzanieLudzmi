/**
 * Created by Torak28 on 31.12.2016.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplikacja {
	private List<Pracownik> WszyscyPracownicy = new ArrayList<Pracownik>();
	private List<Zadanie> WszystkieZadania = new ArrayList<Zadanie>();

	public List<Pracownik> getWszyscyPracownicy(){
		return WszyscyPracownicy;
	}
	public void setWszyscyPracownicy(){

	}
	public List<Zadanie> getWszystkieZadania(){
		return WszystkieZadania;
	}
	public void setWszystkieZadania(){

	}
	//Muszę jakoś dodać do wszystkich pracownikow tych ktorzy są w drużynie


	public void dodajPracownika(String i,String n){
		Pracownik p = new Pracownik();
		p.setImie(i);
		p.setNazwisko(n);
		WszyscyPracownicy.add(p);
	}
	//Dodanie przodowego i pomocnika
	public void dodajPrzodowego(Druzyna d){
		Pracownik pr = new Pracownik();
		String Imie, Nazwisko;
		Scanner in= new Scanner(System.in);
		System.out.print("Podaj Imie Przodowego: ");
		Imie = in.next();
		pr.setImie(Imie);
		System.out.print("Podaj Nazwisko Przodowego: ");
		Nazwisko = in.next();
		pr.setNazwisko(Nazwisko);
		d.setPrzodowy(pr);
	}
	public void dodajPomocnika(Druzyna d) {
		int ilosc;
		Scanner in= new Scanner(System.in);
		System.out.print("Ilu bedzie pomocnikow: ");
		ilosc = in.nextInt();
		for (int i = 0; i < ilosc; i++) {
			Pracownik po = new Pracownik();
			String Imie, Nazwisko;
			System.out.print("Podaj Imie Pomocnika: ");
			Imie = in.next();
			po.setImie(Imie);
			System.out.print("Podaj Nazwisko Pomocnika: ");
			Nazwisko = in.next();
			po.setNazwisko(Nazwisko);
			d.setPomocnicy(po);
		}
	}
	public void dodajDruzyne(){
		Druzyna d = new Druzyna();
		Pracownik p;
		dodajPrzodowego(d);
		dodajPomocnika(d);
		p = d.getPrzodowy();
		WszyscyPracownicy.add(WszyscyPracownicy.size(),p);
		for (int i = 0; i < d.getPomocnicy().size(); i++) {
			p = d.getPomocnicy().get(i);
			WszyscyPracownicy.add(WszyscyPracownicy.size(),p);
		}
	}
	public void wyswietlPracownikow(Aplikacja ap){
		System.out.println("Lista" + ap.getWszyscyPracownicy().size() + 1 + " Pracownikow: ");
		for (int i = 0; i < ap.getWszyscyPracownicy().size(); i++) {
			System.out.println(i+1 + ": " + ap.getWszyscyPracownicy().get(i).getImie() + " " + ap.getWszyscyPracownicy().get(i).getNazwisko());
		}
	}
	public void Aplikacja(){

	}

	public static void main(String[] args) {
		System.out.println("Hello, World");
		System.out.println("Działa wszystko!");

		Aplikacja ap = new Aplikacja();
		ap.dodajDruzyne();
		ap.wyswietlPracownikow(ap);

	}

}
