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
	public void dodajPracownika(){
		Pracownik p = new Pracownik();
		String Imie, Nazwisko;
		Scanner in = new Scanner(System.in);
		System.out.print("Podaj Imie Pracownika: ");
		Imie = in.next();
		System.out.print("Podaj Nazwisko Pracownika: ");
		Nazwisko = in.next();
		p.setImie(Imie);
		p.setNazwisko(Nazwisko);
		WszyscyPracownicy.add(p);
	}
	public void dodajPrzodowego(Druzyna d){
		wyswietlPracownikow();
		int pom = -1;
		Scanner in = new Scanner(System.in);
		String Imie, Nazwisko;
		System.out.print("Podaj Imie Przodowego: ");
		Imie = in.next();
		System.out.print("Podaj Nazwisko Przodowego: ");
		Nazwisko = in.next();
		System.out.println();
		if (WszyscyPracownicy.isEmpty()){
			System.out.println("Nie ma takiego delikwenta");
		}else{
			for (int i = 0; i < WszyscyPracownicy.size(); i++) {
				if (WszyscyPracownicy.get(i).getImie() == Imie){
					pom = i;
				}else{
					System.out.println("Nie ma takiego delikwenta");
				}
			}
		}
		if (pom != -1){
			d.setPrzodowy(WszyscyPracownicy.get(pom));
		}
	}
	public void dodajPomocnika(Druzyna d) {
		int pom = -1;
		Scanner in = new Scanner(System.in);
		String Imie, Nazwisko;
		int ilosc;
		System.out.print("Podaj Ilu Pomocniow: ");
		ilosc = in.nextInt();
		for (int i = 0; i < ilosc; i++) {
			System.out.print("Podaj Imie Pomocnika: ");
			Imie = in.next();
			System.out.print("Podaj Nazwisko Pomocnika: ");
			Nazwisko = in.next();
			for (int j = 0; j < WszyscyPracownicy.size(); j++) {
				if (WszyscyPracownicy.get(j).getImie() == Imie && WszyscyPracownicy.get(j).getNazwisko() == Nazwisko){
					pom = j;
				}
			}
			if (pom != -1){
				d.setPomocnicy(WszyscyPracownicy.get(pom));
			}
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
	public void wyswietlPracownikow(){
		if (WszyscyPracownicy.isEmpty()){
			System.out.println("Lista Pracownikow: ");
			System.out.println("\tlista pusta");
		}else{
			System.out.println("Lista " + getWszyscyPracownicy().size() + " Pracownikow: ");
		}
		for (int i = 0; i < getWszyscyPracownicy().size(); i++) {
			System.out.println("\t" + (i+1) +  ": " + getWszyscyPracownicy().get(i).getImie() + " " + getWszyscyPracownicy().get(i).getNazwisko());
		}
	}
	public void Aplikacja(){

	}

	public static void main(String[] args) {
		System.out.println("Hello, World");
		System.out.println("Działa wszystko!");

		Aplikacja ap = new Aplikacja();
		ap.dodajPracownika();
		ap.dodajDruzyne();
		ap.wyswietlPracownikow();

	}

}
