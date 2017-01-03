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
				if (WszyscyPracownicy.get(i).getImie().equals(Imie)  && WszyscyPracownicy.get(i).getNazwisko().equals(Nazwisko)){
					pom = i;
				}
			}
		}
		if (pom != -1){
			d.setPrzodowy(WszyscyPracownicy.get(pom));
		}else {
			System.out.println("Nie ma takiego delikwenta");
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
				if (WszyscyPracownicy.get(j).getImie().equals(Imie) && WszyscyPracownicy.get(j).getNazwisko().equals(Nazwisko)){
					if (!WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getImie()) && !WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getNazwisko()) ){
						if(d.getPomocnicy().isEmpty()){
							pom = j;
						}else{
							for (int k = 0; k < pom; k++) {
								if (!d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)){
									pom = k;
								}else if(d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)){
									pom = k;
								}
							}
						}
					}else if (WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getImie()) && !WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getNazwisko()) ){
						if(d.getPomocnicy().isEmpty()){
							pom = j;
						}else{
							for (int k = 0; k < pom; k++) {
								if (!d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)){
									pom = k;
								}else if(d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)){
									pom = k;
								}
							}
						}
					}
				}
			}
			if (pom != -1){
				d.setPomocnicy(WszyscyPracownicy.get(pom));
			}else {
				System.out.println("Nie ma takiego delikwenta");
			}
		}
	}
	public Druzyna dodajDruzyne(){
		Druzyna d = new Druzyna();
		dodajPrzodowego(d);
		dodajPomocnika(d);
		return d;
	}
	public void dodajZadanieKotwienia(){

	}
	public void dodajZadanieWiercenia(){

	}
	public void dodajZadanie(){

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
	public void wyswietlDruzyne(Druzyna d){
		System.out.println("Druzyna: ");
		System.out.println("Przodowy: " + d.getPrzodowy().getImie() + " " + d.getPrzodowy().getNazwisko());
		if (d.getPomocnicy().isEmpty()){
			System.out.println("Nie ma pomocnikow");
		}else{
			for (int i = 0; i < d.getPomocnicy().size(); i++) {
				System.out.println("Pomocnicy(" + (i+1) +"): " + d.getPomocnicy().get(i).getImie() + " " + d.getPomocnicy().get(i).getNazwisko());
			}
		}
	}
	public void Aplikacja(){

	}

	public static void main(String[] args) {
		//Main bedzie mial liste druzyn do zapisu
		System.out.println("Hello, World");
		System.out.println("Działa wszystko!");

		Aplikacja ap = new Aplikacja();
		ap.dodajPracownika();
		ap.dodajPracownika();
		ap.dodajPracownika();

		Druzyna d = ap.dodajDruzyne();

		ap.wyswietlPracownikow();
		ap.wyswietlDruzyne(d);

	}

}
