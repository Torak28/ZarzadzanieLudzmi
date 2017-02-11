/**
 * Created by Torak28 on 31.12.2016.
 */

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aplikacja{


	private JPanel GlownyPanel;
	private JPanel ZPPanel;

	private JLabel opis;
	private JButton zarzPracownikami;
	private JButton zarzBrygadami;
	private JButton zarzZadaniami;
	private JButton ocenBrygade;
	private JButton raportDnia;
	private JButton Zapisz;

	public void setLabel1(String in){
		opis.setText(in);
	}


	public odczytPracownikow r = new odczytPracownikow();

	private List<Pracownik> WszyscyPracownicy = new ArrayList<Pracownik>();
	private List<Zadanie> WszystkieZadania = new ArrayList<Zadanie>();
	private List<Druzyna> WszystkieDruzyny = new ArrayList<Druzyna>();
	public List<Pracownik> getWszyscyPracownicy(){
		return WszyscyPracownicy;
	}

	/**
	 *Dodawanie Pracownika po Stringach: Imieniu i Nazwisku
	 **/
	public void dodajPracownika(String Imie, String Nazwisko){
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
	/**
	 * Usuwanie Pracownika o zadanym Imieniu i Nazwisku
	 **/
	public void usunPracownika(String Imie, String Nazwisko){
		int sterowanie = 0;
		int index = 2147483647;
		if (WszyscyPracownicy.isEmpty()){
			sterowanie = 0;
		}else{
			for (int i = 0; i < WszyscyPracownicy.size(); i++) {
				if (WszyscyPracownicy.get(i).getImie().equals(Imie) && WszyscyPracownicy.get(i).getNazwisko().equals(Nazwisko)){
					sterowanie = 1;
					index = i;
				}else if(WszyscyPracownicy.get(i).getImie().equals(Imie) && !WszyscyPracownicy.get(i).getNazwisko().equals(Nazwisko)){
					sterowanie = 0;
				}
			}
		}
		if (sterowanie == 0){
			System.out.println("Taki delikwent nie istnieje");
		}else if (sterowanie == 1 && index != 2147483647){
			WszyscyPracownicy.remove(index);
		}
	}
	/**
	 * Usuwanie zadanego jako argument pracownika
	 **/
	public void usunPracownika(Pracownik p){
		WszyscyPracownicy.remove(p);
	}
	/**
	 * Dodanie do drużyny d przodowego o zadanym Imieniu i Nazwisku
	 **/
	public void dodajPrzodowego(Druzyna d, String Imie, String Nazwisko){
		int pom = -1;
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
	/**
	 * Dodanie Przodowego o konkretnym indexie do Drużyny d
	 **/
	public void dodajPrzodowego(Druzyna d, int index){
		if (WszyscyPracownicy.isEmpty()){
			System.out.println("Nie ma takiego delikwenta");
		}else{
			d.setPrzodowy(WszyscyPracownicy.get(index));
		}
	}
	/**
	 * Dodawanie okreslonej ilosc Pomocników z Tablicy Imion i Nazwisk
	 **/
	public void dodajPomocnikow(Druzyna d, int ilosc, String[] Imiona, String[] Nazwiska){
		int pom = -1;
		for (int i = 0; i < ilosc; i++) {
			String Imie = Imiona[i];
			String Nazwisko = Nazwiska[i];
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
	/**
	 * Dodanie określonej ilości Pomocników o zadanych indeksach
	 **/
	public void dodajPomocnikow(Druzyna d, int[] indexy) {
		if (WszyscyPracownicy.isEmpty()){
			System.out.println("Nie ma takiego delikwenta");
		}else{
			for (int i = 0; i < indexy.length; i++) {
				d.setPomocnicy(WszyscyPracownicy.get(indexy[i]));
			}
		}
	}
	/**
	 * Dodanie 1 Pomocnika o zadanym Imieniu i Nazwisku
	 **/
	public void dodajPomocnika(Druzyna d, String Imie, String Nazwisko){
		int pom = -1;
		for (int j = 0; j < WszyscyPracownicy.size(); j++) {
			if (WszyscyPracownicy.get(j).getImie().equals(Imie) && WszyscyPracownicy.get(j).getNazwisko().equals(Nazwisko)) {
				if (!WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getImie()) && !WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getNazwisko())) {
					if (d.getPomocnicy().isEmpty()) {
						pom = j;
					} else {
						for (int k = 0; k < d.getPomocnicy().size(); k++) {
							if (!d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)) {
								pom = j;
							} else if (d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)) {
								pom = j;
							}
						}
					}
				} else if (WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getImie()) && !WszyscyPracownicy.get(j).getImie().equals(d.getPrzodowy().getNazwisko())) {
					if (d.getPomocnicy().isEmpty()) {
						pom = j;
					} else {
						for (int k = 0; k < d.getPomocnicy().size(); k++) {
							if (!d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)) {
								pom = j;
							} else if (d.getPomocnicy().get(k).getImie().equals(Imie) && !d.getPomocnicy().get(k).getNazwisko().equals(Nazwisko)) {
								pom = j;
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Dodanie 1 Pomocnika o zadanym indexie
	 **/
	public void dodajPomocnika(Druzyna d, int index){
		if (WszyscyPracownicy.isEmpty()){
			System.out.println("Nie ma takiego delikwenta");
		}else{
			d.setPomocnicy(WszyscyPracownicy.get(index));
		}
	}
	/**
	 * DoDanie druzyny z tablicami Imion Pomocnikow i ich Nazwisk
	 **/
	public Druzyna dodajDruzyne(String ImiePrzodowego, String NazwiskoPrzodowego, int iloscPomocnikow, String[] ImionaPomocnikow, String[] NazwiskaPomocnikow){
		Druzyna d = new Druzyna();
		dodajPrzodowego(d, ImiePrzodowego, NazwiskoPrzodowego);
		dodajPomocnikow(d, iloscPomocnikow, ImionaPomocnikow, NazwiskaPomocnikow);
		WszystkieDruzyny.add(d);
		return d;
	}
	/**
	 * Dodanie Druzyny z indeami
	 **/
	public Druzyna dodajDruzyne(int indexPrzodowego, int[] indexPomocnikow){
		Druzyna d = new Druzyna();
		dodajPrzodowego(d, indexPrzodowego);
		dodajPomocnikow(d, indexPomocnikow);
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
	/**
	 * Przeciazanie oceny Zadanie wykonem jako intem
	 **/
	public void ocenZadanie(Zadanie z, int wykon){
		float miara;
		z.setWynik(wykon);
		miara = z.getWynik() / z.iluWykonawcow();
		miara = miara - z.getNorma();
		z.setMiara(miara);
		if(miara >= z.getNorma()){
			z.getWykonawcy().getPrzodowy().setOcena(miara + 1);
		}else{
			z.getWykonawcy().getPrzodowy().setOcena(miara);
		}
		for (int i = 0; i < z.getWykonawcy().getPomocnicy().size(); i++) {
			z.getWykonawcy().getPomocnicy().get(i).setOcena(miara);
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
		miara = miara - z.getNorma();
		z.setMiara(miara);
		if(miara >= z.getNorma()){
			z.getWykonawcy().getPrzodowy().setOcena(miara + 1);
		}else{
			z.getWykonawcy().getPrzodowy().setOcena(miara);
		}
		for (int i = 0; i < z.getWykonawcy().getPomocnicy().size(); i++) {
			z.getWykonawcy().getPomocnicy().get(i).setOcena(miara);
		}
	}

	public int wybierzPracownika(){
		System.out.print("Wybierasz: ");
		Scanner in = new Scanner(System.in);
		wyswietlPracownikow();
		int index = in.nextInt();
		return (index - 1);
	}

	/**
	 * Wczytanie z pliku, zawsze na starcie apki
	 **/
	public void Wczytanie(){
		r.Ilu();
		r.otworzPlik();
		for (int i = 0; i < r.ilosc; i++) {
			String wynik = r.odczyt();
			String[] podzial = wynik.split(" ");
			String Imie = podzial[0];
			String Nazwisko = podzial[1];
			dodajPracownika(Imie, Nazwisko);
		}
		if(r.Jest){
			r.Ile();
			r.otworzPlik();
			for (int j = 0; j < r.iloscZadan; j++) {
				String Zadanie = r.odczytZadania();
				String[] podzialS = Zadanie.split(" ");
				String[] podzial = Arrays.copyOfRange(podzialS, 1, podzialS.length);
				int ilu = Integer.parseInt(podzial[podzial.length - 1]);
				String[] imionaPomocnikow = new String[ilu];
				String[] nazwiskaPomocnikow =  new String[ilu];
				int krok = 0;
				int sterowanie = 3;
				for (int i = 0; i < ilu; i++) {
					imionaPomocnikow[krok] = podzial[sterowanie];
					nazwiskaPomocnikow[krok] = podzial[sterowanie + 1];
					krok++;
					sterowanie += 2;
				}
				dodajDruzyne(podzial[1], podzial[2], ilu, imionaPomocnikow, nazwiskaPomocnikow);
				Zadanie z = new Zadanie();
				if(podzial[0].equals("Wiercenie")){
					dodajZadanieWiercenia(z);
					z.setWykonawcy(WszystkieDruzyny.get(j));
				}
				if(podzial[0].equals("Kotwienie")){
					dodajZadanieKotwienia(z);
					z.setWykonawcy(WszystkieDruzyny.get(j));
				}
				WszystkieZadania.add(z);
				ocenZadanie(WszystkieZadania.get(j), Integer.parseInt(podzial[podzial.length-2]));
			}
			r.zamknijY();
		}
		r.zamknij();
	}
	/**
	 * Zapis apki, zawsze przy zamknięciu
	 **/
	public void Zapis() throws FileNotFoundException{
		String out;
		if (WszyscyPracownicy.isEmpty()){
			out = "Lista Pracownikow:\n\tlista pusta";
		}else if(WszyscyPracownicy.size() == 1) {
			out = getWszyscyPracownicy().get(0).getImie() + " " + getWszyscyPracownicy().get(0).getNazwisko() + "\n";
		}
		else{
			out = getWszyscyPracownicy().get(0).getImie() + " " + getWszyscyPracownicy().get(0).getNazwisko() + "\n";
			for (int i = 1; i < (getWszyscyPracownicy().size() - 1); i++) {
				out += getWszyscyPracownicy().get(i).getImie() + " " + getWszyscyPracownicy().get(i).getNazwisko() + "\n";
			}
			out += getWszyscyPracownicy().get(getWszyscyPracownicy().size() - 1).getImie() + " " + getWszyscyPracownicy().get(getWszyscyPracownicy().size() - 1).getNazwisko();
		}
		r.zapis(out, wyswietlZadania(), wyswietlOcenePracownikow());
	}

	/*****wyświetlanie*****/
	public String wyswietlZadanie(Zadanie z){
		String typZadania;
		String out;
		if (z.getTyp() == 1){
			typZadania = "Wiercenie";
		}else{
			typZadania = "Kotwienie";
		}
		out = "Zadanie:\n\tTyp: " + typZadania + "\n\tWykonawcy:\n" + z.wyswietlWykonawcow();
		if (z.getWynik() != 0){
			out = out + "\n\tWynik: " + z.getWynik() + "\n";
		}
		return out;
	}
	public String wyswietlZadania(){
		String out = WszystkieZadania.size() + ":\n";
		if (WszystkieZadania.isEmpty()){
			out += "Nie ma żadnych zadan";
		}else {
			for (int i = 0; i < WszystkieZadania.size(); i++) {
				out += (i+1) + ": ";
				out += wyswietlZadanie(WszystkieZadania.get(i));
			}
		}
		return out;
	}
	public String wyswietlPracownikow(){
		String out;
		if (WszyscyPracownicy.isEmpty()){
			out = "Lista Pracownikow:\n\tlista pusta";
		}else{
			out = "Lista " + getWszyscyPracownicy().size() + " Pracownikow: ";
			for (int i = 0; i < getWszyscyPracownicy().size(); i++) {
				out += "\n\t" + (i+1) +  ": " + getWszyscyPracownicy().get(i).getImie() + " " + getWszyscyPracownicy().get(i).getNazwisko();
			}
		}
		return out + "\n";
	}
	public String wyswietlOcenePracownikow(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String out = df.format(date) + ":\n";
		if (WszyscyPracownicy.isEmpty()){
			out += "\tlista pusta";
		}else{
			for (int i = 0; i < getWszyscyPracownicy().size(); i++) {
				out += getWszyscyPracownicy().get(i).getImie() + " " + getWszyscyPracownicy().get(i).getNazwisko() + " Ocena: " + getWszyscyPracownicy().get(i).getOcena() + "\n";
			}
		}
		return out;
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
	public String wyswietlDruzyny(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String out = df.format(date) + ":\n";
		for (int i = 0; i < WszystkieDruzyny.size(); i++) {
			out += (i+1) + ": ";
			out += wyswietlDruzyne(WszystkieDruzyny.get(i));
		}
		return out;
	}
	public String wyswietlDruzyne(Druzyna d){
		String out = "Brygada: \n";
		out += "\tPrzodowy: " + d.getPrzodowy().getImie() + " " + d.getPrzodowy().getNazwisko() + " Ocena: " + d.getPrzodowy().getOcena() + "\n";
		if (d.getPomocnicy().isEmpty()){
			out += "\tNie ma pomocnikow";
		}else{
			for (int i = 0; i < d.getPomocnicy().size(); i++) {
				out += "\tPomocnicy(" + (i+1) +"): " + d.getPomocnicy().get(i).getImie() + " " + d.getPomocnicy().get(i).getNazwisko() + " Ocena: " + d.getPomocnicy().get(i).getOcena() + "\n";
			}
		}
		return out;
	}

	public void Aplikacja(){
	}

	public Aplikacja(){
		zarzPracownikami.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * Zrobie całość na dialogach a nie na kolejnym JPane :>
				 * http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
				 **/
			}
		});
		zarzBrygadami.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		zarzZadaniami.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		ocenBrygade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		raportDnia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		Zapisz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	public static void main(String[] args) throws FileNotFoundException{
		JFrame frame = new JFrame("Główny Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Aplikacja().GlownyPanel);
		frame.pack();
		frame.setVisible(true);

		/*TODO:
		* Przodowy nie może byc pomocnikiem xd
		* Okienka(Dialogii)
		* Statystyki
		* */


		Aplikacja ap = new Aplikacja();
		/*Scanner in = new Scanner(System.in);
		ap.Wczytanie();

		//MENU KONSOLOWE, DO SPRAWDZENIA
		int wybor;
		do {
			String menu = "Co chcesz zrobic?\n\t1 - Dodac Pracownika\n\t2 - Usunac Pracownika\n\t3 - Dodac Druzyne\n\t4 - Usunac Druzyne\n\t5 - Dodac Zadanie\n\t" +
					"6 - Usunac Zadanie\n\t7 - Wyswietlic pracownikow\n\t8 - Wyswietlic Brygade\n\t9 - Wyswietlic Zadanie\n\t10 - Ocenic Zadanie\n\t11 - Ocena pracowników\n\t" +
					"12 - Informacje o pracowniku\n\t13 - Zapis\n\t14 - Koniec\nwybór: ";
			System.out.print(menu);
			wybor = in.nextInt();
			switch (wybor){
				case 1:
					String Imie, Nazwisko;
					System.out.println("Podaj Imie: ");
					Imie = in.next();
					System.out.println("Podaj Nazwisko: ");
					Nazwisko = in.next();
					ap.dodajPracownika(Imie, Nazwisko);
					break;
				case 2:
					int index;
					System.out.println("Którego pracownika chcesz usunąć?");
					ap.wyswietlPracownikow();
					index = in.nextInt();
					ap.usunPracownika(ap.WszyscyPracownicy.get(index-1));
					break;
				case 3:
					int indexPrzodowego, ilosc;
					System.out.println("Wybór Przodowego: ");
					System.out.println(ap.wyswietlPracownikow());
					indexPrzodowego = ap.wybierzPracownika();
					System.out.println("Ilu pomocnikow: ");
					ilosc = in.nextInt();
					int[] indexPomocnikow = new int[ilosc];
					System.out.println("Wybor Pomocnikow: ");
					for (int i = 0; i < ilosc; i++) {
						System.out.println("\tWybór Pomocnika" + (i + 1) + ": ");
						indexPomocnikow[i] = ap.wybierzPracownika();
					}
					ap.dodajDruzyne(indexPrzodowego, indexPomocnikow);
					break;
				case 4:
					int index2;
					System.out.println("Którą brygade chcesz usunąć?");
					ap.wyswietlDruzyny();
					index2 = in.nextInt();
					ap.usunDruzyne(ap.WszystkieDruzyny.get(index2-1));
					break;
				case 5:
					int index3;
					System.out.println("Której brygadzie przydzielić zadanie?");
					ap.wyswietlDruzyny();
					index3 = in.nextInt();
					ap.dodajZadanie(ap.WszystkieDruzyny.get(index3-1));
					break;
				case 6:
					int index4;
					System.out.println("Które zadanie chcesz usunąć?");
					System.out.println(ap.wyswietlZadania());
					index4 = in.nextInt();
					ap.usunZadanie(ap.WszystkieZadania.get(index4-1));
					break;
				case 7:
					System.out.println(ap.wyswietlPracownikow());
					break;
				case 8:
					System.out.println(ap.wyswietlDruzyny());
					break;
				case 9:
					System.out.println(ap.wyswietlZadania());
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
					System.out.println(ap.wyswietlOcenePracownikow());
					break;
				case 12:
					System.out.println("Dane którego pracownika Cie interesuja");
					ap.wyswietlPracownikow();
					index5 = in.nextInt();
					ap.wyswietlPracownika(index5-1);
					break;
				case 13:
					ap.Zapis();
			}
		}while (wybor != 14);
		System.out.println("\nDzięki za prace!\n\tStworzył: Jarosław Ciołek-Żelechowski");*/
	}
}
