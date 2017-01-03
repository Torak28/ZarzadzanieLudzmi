/**
 * Created by Torak28 on 31.12.2016.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplikacja {
	private List<Pracownik> WszyscyPracownicy = new ArrayList<Pracownik>();
	private List<Zadanie> WszystkieZadania = new ArrayList<Zadanie>();
	private List<Druzyna> WszystkieDruzyny = new ArrayList<Druzyna>();

	public List<Pracownik> getWszyscyPracownicy(){
		return WszyscyPracownicy;
	}
	public void dodajPracownika(){
		String Imie, Nazwisko;
		Scanner in = new Scanner(System.in);
		System.out.print("Podaj Imie Pracownika: ");
		Imie = in.next();
		System.out.print("Podaj Nazwisko Pracownika: ");
		Nazwisko = in.next();
		int sterowanie = 0;
		if (WszyscyPracownicy.isEmpty()){
			sterowanie = 1;
		}else{
			for (int i = 0; i < WszyscyPracownicy.size(); i++) {
				if (WszyscyPracownicy.get(i).getImie().equals(Imie) && WszyscyPracownicy.get(i).getNazwisko().equals(Nazwisko)){
					sterowanie = 0;
				}else if(WszyscyPracownicy.get(i).getImie().equals(Imie) && !WszyscyPracownicy.get(i).getNazwisko().equals(Nazwisko)){
					sterowanie = 1;
				}else{
					sterowanie = 1;
				}
			}
		}
		if (sterowanie == 0){
			System.out.println("Taki delikwent już istnieje");
		}else if (sterowanie == 1){
			Pracownik p = new Pracownik();
			p.setImie(Imie);
			p.setNazwisko(Nazwisko);
			WszyscyPracownicy.add(p);
		}
	}
	public void usunPracownika(Pracownik p){
		WszyscyPracownicy.remove(p);
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
							for (int k = 0; k < d.getPomocnicy().size(); k++) {
								if (!d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)){
									pom = j;
								}else if(d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)){
									pom = j;
								}
							}
						}
					}else if (WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getImie()) && !WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getNazwisko()) ){
						if(d.getPomocnicy().isEmpty()){
							pom = j;
						}else{
							for (int k = 0; k < d.getPomocnicy().size(); k++) {
								if (!d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)){
									pom = j;
								}else if(d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)){
									pom = j;
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
		WszystkieDruzyny.add(d);
		return d;
	}
	public void usunDruzyne(Druzyna d){
		WszystkieDruzyny.remove(d);
	}
	public void dodajZadanieKotwienia(Zadanie z){
		int typ = 2;
		int norma = 12;
		int wynik = 0;
		z.setWynik(wynik);
		z.setTyp(typ);
		z.setNorma(norma);
	}
	public void dodajZadanieWiercenia(Zadanie z){
		int typ = 1;
		int norma = 6;
		int wynik = 0;
		z.setWynik(wynik);
		z.setTyp(typ);
		z.setNorma(norma);
	}
	public Zadanie dodajZadanie(Druzyna d){
		Zadanie z = new Zadanie();
		System.out.println("Jakie chcesz dodać zadanie?\n\t1 - Wiercenie\n\t2 - Kotwienie");
		Scanner in = new Scanner(System.in);
		int wybor = in.nextInt();
		if (wybor == 1){
			dodajZadanieWiercenia(z);
		}else{
			dodajZadanieKotwienia(z);
		}
		z.setWykonawcy(d);
		WszystkieZadania.add(z);
		return z;
	}
	public void usunZadanie(Zadanie z){
		WszystkieZadania.remove(z);
	}
	public void wyswietlZadanie(Zadanie z){
		String typZadania;
		String out;
		if (z.getTyp() == 1){
			typZadania = "Wiercenie";
		}else{
			typZadania = "Kotwienie";
		}
		out = "Zadanie:\n\tTyp: " + typZadania + "\n\tWykonawcy:\n" + z.wyswietlWykonawcow();
		if (z.getWynik() != 0){
			out = out + "\n\tWynik: " + z.getWynik() + "\n\tOcena: " + z.getMiara() + "\n";
		}
		System.out.println(out);
	}
	public void wyswietlZadania(){
		for (int i = 0; i < WszystkieZadania.size(); i++) {
			System.out.print((i+1) + ": ");
			wyswietlZadanie(WszystkieZadania.get(i));
		}
	}
	public void ocenZadanie(Zadanie z){
		int wykon;
		float miara;
		Scanner in = new Scanner(System.in);
		System.out.print("Ile wykonali: ");
		wykon = in.nextInt();
		z.setWynik(wykon);
		miara = z.getWynik() / z.iluWykonawcow();
		z.setMiara(miara);
		z.getWykonawcy().getPrzodowy().setOcena(miara + 1);
		for (int i = 0; i < z.getWykonawcy().getPomocnicy().size(); i++) {
			z.getWykonawcy().getPomocnicy().get(i).setOcena(miara);
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
	public void wyswietlOcenePracownikow(){
		if (WszyscyPracownicy.isEmpty()){
			System.out.println("Lista Pracownikow: ");
			System.out.println("\tlista pusta");
		}else{
			System.out.println("Lista " + getWszyscyPracownicy().size() + " Pracownikow: ");
		}
		for (int i = 0; i < getWszyscyPracownicy().size(); i++) {
			System.out.println("\t" + (i+1) +  ": " + getWszyscyPracownicy().get(i).getImie() + " " + getWszyscyPracownicy().get(i).getNazwisko() + " Ocena: " + getWszyscyPracownicy().get(i).getOcena());
		}
	}
	public void wyswietlPracownika(int index){
		System.out.println("\t" + getWszyscyPracownicy().get(index).getImie() + " " + getWszyscyPracownicy().get(index).getNazwisko() + " Ocena: " + getWszyscyPracownicy().get(index).getOcena());
		for (int i = 0; i < WszystkieDruzyny.size(); i++) {
			if(WszystkieDruzyny.get(i).getPrzodowy().getImie().equals(getWszyscyPracownicy().get(index).getImie()) && WszystkieDruzyny.get(i).getPrzodowy().getNazwisko().equals(getWszyscyPracownicy().get(index).getNazwisko()) ){
				System.out.println("\tJest przodowym w Druzynie " + (i+1));
			}else{
				//To nie działa
				for (int j = 0; j < WszystkieDruzyny.get(i).getPomocnicy().size(); j++) {
					if (WszystkieDruzyny.get(i).getPomocnicy().get(j).getImie().equals(getWszyscyPracownicy().get(index).getImie())
							&& WszystkieDruzyny.get(i).getPomocnicy().get(j).getNazwisko().equals(getWszyscyPracownicy().get(index).getNazwisko())){
						System.out.println("\tJest pomocnikiem w Druzynie " + (i+1));
					}
				}
			}
		}
	}
	public void wyswietlDruzyny(){
		for (int i = 0; i < WszystkieDruzyny.size(); i++) {
			System.out.print((i+1) + ": ");
			wyswietlDruzyne(WszystkieDruzyny.get(i));
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
		/*TODO:
		 * Lupoowanie niepoprawnych danych
		 * Sprawdzanie w ktorej druzynie jest dany delikwent
		 * Sprawdzanie wyniku konkretnego delikwenta
		 * Pracownicy z pliku tekstowego
		 * Zapis i czyszczenie Druzyn (?)
		 * Zapis i czyszczenie Zadan (?)
		 * Polaczenie zadan i druzyn w zapisie
		 */
		System.out.println("Program:");
		Aplikacja ap = new Aplikacja();
		Scanner in = new Scanner(System.in);
		int wybor;
		do {
			String menu = "Co chcesz zrobic?\n\t1 - Dodac Pracownika\n\t2 - Usunac Pracownika\n\t3 - Dodac Druzyne\n\t4 - Usunac Druzyne\n\t5 - Dodac Zadanie\n\t" +
					"6 - Usunac Zadanie\n\t7 - Wyswietlic pracownikow\n\t8 - Wyswietlic Druzyne\n\t9 - Wyswietlic Zadanie\n\t10 - Ocenic Zadanie\n\t11 - Ocena pracowników\n\t" +
					"12 - Informacje o pracowniku\n\t13 - koniec\nwybór: ";
			System.out.print(menu);
			wybor = in.nextInt();
			switch (wybor){
				case 1:
					ap.dodajPracownika();
					break;
				case 2:
					int index;
					System.out.println("Którego pracownika chcesz usunąć?");
					ap.wyswietlPracownikow();
					index = in.nextInt();
					ap.usunPracownika(ap.WszyscyPracownicy.get(index-1));
					break;
				case 3:
					ap.dodajDruzyne();
					break;
				case 4:
					int index2;
					System.out.println("Którą drużynę chcesz usunąć?");
					ap.wyswietlDruzyny();
					index2 = in.nextInt();
					ap.usunDruzyne(ap.WszystkieDruzyny.get(index2-1));
					break;
				case 5:
					int index3;
					System.out.println("Której drużynie przydzielić zadanie?");
					ap.wyswietlDruzyny();
					index3 = in.nextInt();
					ap.dodajZadanie(ap.WszystkieDruzyny.get(index3-1));
					break;
				case 6:
					int index4;
					System.out.println("Które zadanie chcesz usunąć?");
					ap.wyswietlZadania();
					index4 = in.nextInt();
					ap.usunZadanie(ap.WszystkieZadania.get(index4-1));
					break;
				case 7:
					ap.wyswietlPracownikow();
					break;
				case 8:
					ap.wyswietlDruzyny();
					break;
				case 9:
					ap.wyswietlZadania();
					break;
				case 10:
					int index5;
					System.out.println("Które zadanie chcesz ocenić?");
					ap.wyswietlZadania();
					index5 = in.nextInt();
					ap.ocenZadanie(ap.WszystkieZadania.get(index5-1));
					ap.wyswietlZadanie(ap.WszystkieZadania.get(index5-1));
					break;
				case 11:
					ap.wyswietlOcenePracownikow();
					break;
				case 12:
					System.out.println("Dane którego pracownika Cie interesuja");
					ap.wyswietlPracownikow();
					index5 = in.nextInt();
					ap.wyswietlPracownika(index5-1);
					break;
			}
		}while (wybor != 13);
		System.out.println("\nDzięki za prace!\n\tStworzył: Jarosław Ciołek-Żelechowski");
	}
}
