/**
 * Created by Torak28 on 31.12.2016.
 */

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Aplikacja{


	private JPanel GlownyPanel;

	private JLabel opis;
	private JButton zarzPracownikami;
	private JButton zarzBrygadami;
	private JButton zarzZadaniami;
	private JButton ocenBrygade;
	private JButton raportDnia;
	private JButton Zapisz;
	private JButton wykresButton;

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
				}else if(sterowanie != 1 && WszyscyPracownicy.get(i).getImie().equals(Imie) && !WszyscyPracownicy.get(i).getNazwisko().equals(Nazwisko)){
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
	public void usunDruzyne(String ImiePrzodowego, String NazwiskoPrzodowego){
		int sterowanie = 0;
		int index = 2147483647;
		if (WszystkieDruzyny.isEmpty()){
			sterowanie = 0;
		}else{
			for (int i = 0; i < WszystkieDruzyny.size(); i++) {
				if (WszystkieDruzyny.get(i).getPrzodowy().getImie().equals(ImiePrzodowego) && WszystkieDruzyny.get(i).getPrzodowy().getNazwisko().equals(NazwiskoPrzodowego)){
					sterowanie = 1;
					index = i;
				}
			}
		}
		if((sterowanie == 1) && (index != 2147483647)){
			WszystkieDruzyny.remove(WszystkieDruzyny.get(index));
		}
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
	public void usunZadanie(String ImiePrzodowego, String NazwiskoPrzodowego){
		int sterowanie = 0;
		int index = 2147483647;
		if (WszystkieZadania.isEmpty()){
			sterowanie = 0;
		}else{
			for (int i = 0; i < WszystkieZadania.size(); i++) {
				if (WszystkieZadania.get(i).getWykonawcy().getPrzodowy().getImie().equals(ImiePrzodowego) && WszystkieZadania.get(i).getWykonawcy().getPrzodowy().getNazwisko().equals(NazwiskoPrzodowego)){
					sterowanie = 1;
					index = i;
				}
			}
		}
		if((sterowanie == 1) && (index != 2147483647)){
			WszystkieZadania.remove(WszystkieZadania.get(index));
		}
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
	public void ocenZadanie(String ImiePrzodowego, String NazwiskoPrzodowego, int wykon){
		int sterowanie = 0;
		int index = 2147483647;
		if (WszystkieZadania.isEmpty()){
			sterowanie = 0;
		}else{
			for (int i = 0; i < WszystkieZadania.size(); i++) {
				if (WszystkieZadania.get(i).getWykonawcy().getPrzodowy().getImie().equals(ImiePrzodowego) && WszystkieZadania.get(i).getWykonawcy().getPrzodowy().getNazwisko().equals(NazwiskoPrzodowego)){
					sterowanie = 1;
					index = i;
				}
			}
		}
		if((sterowanie == 1) && (index != 2147483647)){
			float miara;
			WszystkieZadania.get(index).setWynik(wykon);
			miara = WszystkieZadania.get(index).getWynik() / WszystkieZadania.get(index).iluWykonawcow();
			miara = miara - WszystkieZadania.get(index).getNorma();
			WszystkieZadania.get(index).setMiara(miara);
			if(miara >= WszystkieZadania.get(index).getNorma()){
				WszystkieZadania.get(index).getWykonawcy().getPrzodowy().setOcena(miara + 1);
			}else{
				WszystkieZadania.get(index).getWykonawcy().getPrzodowy().setOcena(miara);
			}
			for (int i = 0; i < WszystkieZadania.get(index).getWykonawcy().getPomocnicy().size(); i++) {
				WszystkieZadania.get(index).getWykonawcy().getPomocnicy().get(i).setOcena(miara);
			}
		}
	}

	public int wybierzPracownika(){
		System.out.print("Wybierasz: ");
		Scanner in = new Scanner(System.in);
		wyswietlPracownikow();
		int index = in.nextInt();
		return (index - 1);
	}
	public int indexPracownicka(String Imie, String Nazwisko){
		int out = 100000;
		for (int i = 0; i < WszyscyPracownicy.size(); i++) {
			if (WszyscyPracownicy.get(i).getImie().equals(Imie) && WszyscyPracownicy.get(i).getNazwisko().equals(Nazwisko)){
				out = i;
			}
		}
		return out;
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
				try{
					ocenZadanie(WszystkieZadania.get(j), Integer.parseInt(podzial[podzial.length-2]));
				}catch(NumberFormatException e){
					//Bardzo naiwan obsługa
				}

			}
			r.zamknijY();
		}
		r.zamknij();
	}
	/**
	 * Zapis apki, zawsze przy zamknięciu
	 **/
	public void Zapis(){
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

	public XYDataset stworzDataset(String Imie, String Nazwisko, String Poczatek, String Koniec){
		TimeSeries s1 = new TimeSeries(Imie + " " + Nazwisko);
		try{
			String format = "dd.MM.yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date dataPoczatek = sdf.parse(Poczatek);
			Date dataKoniec = sdf.parse(Koniec);

			long diff = dataKoniec.getTime() - dataPoczatek.getTime();
			int roznica =  (int) (diff / (24* 1000 * 60 * 60));
			roznica++;
			int[] Oceny = new int[roznica];
			for (int i = 0; i < roznica; i++) {
				int outDzis = r.odczytOceny(Imie, Nazwisko, sdf.format(dataPoczatek));
				if(outDzis != -1000){
					Oceny[i] = outDzis;
					String aktualnaData = sdf.format(dataPoczatek);
					String[] czesci = aktualnaData.split("\\.");
					s1.add(new Day(Integer.parseInt(czesci[0]),Integer.parseInt(czesci[1]),Integer.parseInt(czesci[2])), outDzis);
				}else {
					String aktualnaData = sdf.format(dataPoczatek);
					String[] czesci = aktualnaData.split("\\.");
					s1.add(new Day(Integer.parseInt(czesci[0]), Integer.parseInt(czesci[1]), Integer.parseInt(czesci[2])), 0);
				}
				dataPoczatek = new Date(dataPoczatek.getTime() + (1000 * 60 * 60 * 24));
			}
		}catch (ParseException e){
		}
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		dataset.setXPosition(TimePeriodAnchor.MIDDLE);
		return dataset;
	}
	public XYDataset stworzDataset(String Imie, String Nazwisko, String Poczatek, String Koniec, int ilu, String[] Imiona, String[] Nazwiska){
		TimeSeries s1 = new TimeSeries(Imie + " " + Nazwisko);
		ArrayList<TimeSeries> listaPorwnania = new ArrayList<TimeSeries>();
		for (int i = 0; i < ilu; i++) {
			listaPorwnania.add(new TimeSeries(Imiona[0] + " " + Nazwiska[0]));
		}
		try{
			String format = "dd.MM.yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date dataPoczatek = sdf.parse(Poczatek);
			Date dataKoniec = sdf.parse(Koniec);

			long diff = dataKoniec.getTime() - dataPoczatek.getTime();
			int roznica =  (int) (diff / (24* 1000 * 60 * 60));
			roznica++;
			int[] Oceny = new int[roznica];
			ArrayList<Integer> ocenyPorownanie = new ArrayList<Integer>();
			for (int i = 0; i < roznica; i++) {
				//dla pierwszego
				int outDzis = r.odczytOceny(Imie, Nazwisko, sdf.format(dataPoczatek));
				if(outDzis != -1000){
					Oceny[i] = outDzis;
					String aktualnaData = sdf.format(dataPoczatek);
					String[] czesci = aktualnaData.split("\\.");
					s1.add(new Day(Integer.parseInt(czesci[0]),Integer.parseInt(czesci[1]),Integer.parseInt(czesci[2])), outDzis);
				}else {
					String aktualnaData = sdf.format(dataPoczatek);
					String[] czesci = aktualnaData.split("\\.");
					s1.add(new Day(Integer.parseInt(czesci[0]), Integer.parseInt(czesci[1]), Integer.parseInt(czesci[2])), 0);
				}
				//dla innych
				for (int j = 0; j < ilu; j++) {
					int outDzisPorownanie = r.odczytOceny(Imiona[j], Nazwiska[j], sdf.format(dataPoczatek));
					if(outDzisPorownanie != -1000){
						ocenyPorownanie.add(outDzisPorownanie);
						String aktualnaData = sdf.format(dataPoczatek);
						String[] czesci = aktualnaData.split("\\.");
						listaPorwnania.get(j).add(new Day(Integer.parseInt(czesci[0]),Integer.parseInt(czesci[1]),Integer.parseInt(czesci[2])), outDzisPorownanie);
					}else {
						String aktualnaData = sdf.format(dataPoczatek);
						String[] czesci = aktualnaData.split("\\.");
						listaPorwnania.get(j).add(new Day(Integer.parseInt(czesci[0]),Integer.parseInt(czesci[1]),Integer.parseInt(czesci[2])), 0);
					}
				}
				dataPoczatek = new Date(dataPoczatek.getTime() + (1000 * 60 * 60 * 24));
			}
		}catch (ParseException e){
		}
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		for (int i = 0; i < ilu; i++) {
			dataset.addSeries(listaPorwnania.get(i));
		}
		dataset.setXPosition(TimePeriodAnchor.MIDDLE);
		return dataset;
	}

	public String raportZakres(String Poczatek, String Koniec){
		String out = "Raport od " + Poczatek + " do " + Koniec + ":\n";
		try{
			String format = "dd.MM.yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date dataPoczatek = sdf.parse(Poczatek);
			Date dataKoniec = sdf.parse(Koniec);

			long diff = dataKoniec.getTime() - dataPoczatek.getTime();
			int roznica =  (int) (diff / (24* 1000 * 60 * 60));
			roznica++;
			int[][] Oceny = new int[WszyscyPracownicy.size()][1];
			for (int j = 0; j < WszyscyPracownicy.size(); j++) {
				for (int i = 0; i < roznica; i++) {
					int outDzis = r.odczytOceny(WszyscyPracownicy.get(j).getImie(), WszyscyPracownicy.get(j).getNazwisko(), sdf.format(dataPoczatek));
					if(outDzis != -1000){
						Oceny[j][0] += outDzis;
						String aktualnaData = sdf.format(dataPoczatek);
					}
					dataPoczatek = new Date(dataPoczatek.getTime() + (1000 * 60 * 60 * 24));
				}
				dataPoczatek = sdf.parse(Poczatek);
			}
			for (int i = 0; i < WszyscyPracownicy.size(); i++) {
				out += WszyscyPracownicy.get(i).getImieNazwisko() + " Ocena(całość): " + Oceny[i][0] + "\n";
			}
		}catch (ParseException e){
		}
		return out;
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
	public String raportDnia(){
		String out = "ZADANIA:\nIlość zadań: " + WszystkieZadania.size() + "\n";
		if (WszystkieZadania.isEmpty()){
			out += "Nie ma żadnych zadan\n\n";
		}else {
			for (int i = 0; i < WszystkieZadania.size(); i++) {
				out += wyswietlZadanie(WszystkieZadania.get(i));
			}
			out += "\n\n";
		}
		out += "PRACOWNICY:\n";
		if (WszyscyPracownicy.isEmpty()){
			out += "\tlista pusta\n\n";
		}else{
			for (int i = 0; i < getWszyscyPracownicy().size(); i++) {
				out += getWszyscyPracownicy().get(i).getImie() + " " + getWszyscyPracownicy().get(i).getNazwisko() + " Ocena: " + getWszyscyPracownicy().get(i).getOcena() + "\n";
			}
			out += "\n\n";
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
	public String[] wyswietlPracownikowString(){
		String[] ListaPrac = new String[WszyscyPracownicy.size()];
		for (int i = 0; i < WszyscyPracownicy.size(); i++) {
			ListaPrac[i] = WszyscyPracownicy.get(i).getImieNazwisko();
		}
		return ListaPrac;
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
	public String wyswietlPracownika(int index){
		String out;
		out = "\t" + getWszyscyPracownicy().get(index).getImie() + " " + getWszyscyPracownicy().get(index).getNazwisko() + " Ocena: " + getWszyscyPracownicy().get(index).getOcena() + "\n";
		for (int i = 0; i < WszystkieDruzyny.size(); i++) {
			if(WszystkieDruzyny.get(i).getPrzodowy().getImie().equals(getWszyscyPracownicy().get(index).getImie()) && WszystkieDruzyny.get(i).getPrzodowy().getNazwisko().equals(getWszyscyPracownicy().get(index).getNazwisko()) ){
				out +="\tJest przodowym w Druzynie " + (i+1) + "\n";
			}else{
				for (int j = 0; j < WszystkieDruzyny.get(i).getPomocnicy().size(); j++) {
					if (WszystkieDruzyny.get(i).getPomocnicy().get(j).getImie().equals(getWszyscyPracownicy().get(index).getImie())
							&& WszystkieDruzyny.get(i).getPomocnicy().get(j).getNazwisko().equals(getWszyscyPracownicy().get(index).getNazwisko())){
						out += "\tJest pomocnikiem w Druzynie " + (i+1) + "\n";
					}
				}
			}
		}
		return out;
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

	public String[] usunPrac(String[] tablica, String s) {
		ArrayList<String> out = new ArrayList<String>();
		for(String item : tablica)
			if(!s.equals(item))
				out.add(item);
		return out.toArray(tablica);
	}

	public Aplikacja(){
		Wczytanie();
		zarzPracownikami.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] opcje = {"Dodaj Pracownika", "Usuń Pracownika", "Cancel"};
				int n = JOptionPane.showOptionDialog(GlownyPanel, "Co chcesz zrobić?", "Zarządzanie Pracownikami", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcje, "");
				if(n == 0){
					try{
						String ImieNazwisko = JOptionPane.showInputDialog(GlownyPanel, "Podaj Imie i Nazwisko", "Dodaj Pracownika", JOptionPane.PLAIN_MESSAGE);
						if(ImieNazwisko.length() > 0){
							String[] czesci = ImieNazwisko.split(" ");
							dodajPracownika(czesci[0], czesci[1]);
						}
					}catch (NullPointerException e1){
					}
				}else if (n == 1) {
					try{
						if(WszyscyPracownicy.isEmpty()){
							JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych pracowników do usunięcia", "Błąd", JOptionPane.ERROR_MESSAGE);
						}else{
							String[] prac = new String[WszyscyPracownicy.size()];
							for (int i = 0; i < WszyscyPracownicy.size(); i++) {
								prac[i] = WszyscyPracownicy.get(i).getImieNazwisko();
							}
							String s = (String) JOptionPane.showInputDialog(GlownyPanel, "Kogo chcesz usunąć?", "Usuwanie Pracownika", JOptionPane.PLAIN_MESSAGE, null, prac, prac[0]);
							if ((s != null) && (s.length() > 0)){
								String[] czesci = s.split(" ");
								usunPracownika(czesci[0], czesci[1]);
							}
						}
					}catch (NullPointerException e1){
					}
				}
			}
		});
		zarzBrygadami.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] ImieNazwiskoPrzodowego = new String[2];
				String[] opcje = {"Dodaj Brygadę", "Usuń Brygadę", "Cancel"};
				int n = JOptionPane.showOptionDialog(GlownyPanel, "Co chcesz zrobić?", "Zarządzanie Pracownikami", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcje, "");
				if (n == 0){
					try{
						if (WszyscyPracownicy.isEmpty()){
							JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych pracowników do utworzenia Drużyny", "Błąd", JOptionPane.ERROR_MESSAGE);
						}else{
							String[] prac = new String[WszyscyPracownicy.size()];
							for (int i = 0; i < WszyscyPracownicy.size(); i++) {
								prac[i] = WszyscyPracownicy.get(i).getImieNazwisko();
							}
							String s = (String) JOptionPane.showInputDialog(GlownyPanel, "Kto jest przodowym?", "Dodawanie Brygady", JOptionPane.PLAIN_MESSAGE, null, prac, prac[0]);
							if ((s != null) && (s.length() > 0)){
								prac = usunPrac(prac,s);
								ImieNazwiskoPrzodowego = s.split(" ");
								String iluPomocnikowS = JOptionPane.showInputDialog(GlownyPanel, "Ilu pomocników", "Dodawanie Brygady", JOptionPane.PLAIN_MESSAGE);
								int iluPomocnikow = Integer.parseInt(iluPomocnikowS);
								String[] ImionaPomocnikow = new String[iluPomocnikow];
								String[] NazwiskaPomocnikow = new String[iluPomocnikow];
								for (int i = 0; i < iluPomocnikow; i++) {
									String s1 = (String) JOptionPane.showInputDialog(GlownyPanel, "Kto jest pomocnikiem?", "Dodawanie Brygady", JOptionPane.PLAIN_MESSAGE, null, prac, prac[0]);
									if ((s1 != null) && (s1.length() > 0)){
										prac = usunPrac(prac,s1);
										String[] czesci = s1.split(" ");
										ImionaPomocnikow[i] = czesci[0];
										NazwiskaPomocnikow[i] = czesci[1];
									}
								}
								dodajDruzyne(ImieNazwiskoPrzodowego[0], ImieNazwiskoPrzodowego[1], iluPomocnikow, ImionaPomocnikow, NazwiskaPomocnikow);
							}
						}
					}catch (NullPointerException e1){
					}
				}else if(n == 1){
					try{
						if(WszystkieDruzyny.isEmpty()){
							JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych drużyn do usunięcia", "Błąd", JOptionPane.ERROR_MESSAGE);
						}else{
							int index;
							String[] dru = new String[WszystkieDruzyny.size()];
							for (int i = 0; i < WszystkieDruzyny.size(); i++) {
								dru[i] = WszystkieDruzyny.get(i).getDruzyna();
							}
							String s = (String)JOptionPane.showInputDialog(GlownyPanel, "Którą chcesz usunąć?", "Usuwanie Brygady", JOptionPane.PLAIN_MESSAGE, null, dru, dru[0]);
							String[] czesci = new String[3];
							czesci = s.split(" ");
							usunDruzyne(czesci[1], czesci[2]);
						}
					}catch (NullPointerException e1){
					}
				}
			}
		});
		zarzZadaniami.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] opcje1 = {"Dodaj Zadnie", "Usuń Zadanie", "Cancel"};
				int n1 = JOptionPane.showOptionDialog(GlownyPanel, "Co chcesz zrobić?", "Zarządzanie Zadaniami", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcje1, "");
				if(n1 == 0){
					try{
						String[] opcje = {"Wiercenia", "Kotwienia", "Cancel"};
						int n = JOptionPane.showOptionDialog(GlownyPanel, "Jakie zadanie dodać?", "Zarządzanie Zadaniami", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcje, "");
						Zadanie z = new Zadanie();
						int sterowanie = 0;
						if (n == 0){
							if(WszystkieDruzyny.isEmpty()){
								JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych drużyn do ustawienia", "Błąd", JOptionPane.ERROR_MESSAGE);
							}else{
								dodajZadanieWiercenia(z);
								int index;
								String[] dru = new String[WszystkieDruzyny.size()];
								for (int i = 0; i < WszystkieDruzyny.size(); i++) {
									dru[i] = WszystkieDruzyny.get(i).getDruzyna();
								}
								String s = (String)JOptionPane.showInputDialog(GlownyPanel, "Której brygadzie przydzielasz to zadanie?", "Zarządzanie Zadaniami", JOptionPane.PLAIN_MESSAGE, null, dru, dru[0]);
								String[] czesci = new String[3];
								czesci = s.split(" ");
								if(czesci[3].equals("bez")){
									czesci[2] = czesci[2].substring(0, czesci[2].length() - 1);
								}
								int index2 = 2147483647;
								for (int i = 0; i < WszystkieDruzyny.size(); i++) {
									if (WszystkieDruzyny.get(i).getPrzodowy().getImie().equals(czesci[1]) && WszystkieDruzyny.get(i).getPrzodowy().getNazwisko().equals(czesci[2])){
										index2 = i;
									}
								}
								if (index2 != 2147483647){
									z.setWykonawcy(WszystkieDruzyny.get(index2));
									sterowanie = 1;
								}
							}
						}else if (n == 1){
							dodajZadanieKotwienia(z);
							int index;
							String[] dru = new String[WszystkieDruzyny.size()];
							for (int i = 0; i < WszystkieDruzyny.size(); i++) {
								dru[i] = WszystkieDruzyny.get(i).getDruzyna();
							}
							String s = (String)JOptionPane.showInputDialog(GlownyPanel, "Której brygadzie przydzielasz to zadanie?", "Zarządzanie Zadaniami", JOptionPane.PLAIN_MESSAGE, null, dru, dru[0]);
							String[] czesci = new String[3];
							czesci = s.split(" ");
							int index2 = 2147483647;
							for (int i = 0; i < WszystkieDruzyny.size(); i++) {
								if (WszystkieDruzyny.get(i).getPrzodowy().getImie().equals(czesci[1]) && WszystkieDruzyny.get(i).getPrzodowy().getNazwisko().equals(czesci[2])){
									index2 = i;
								}
							}
							if (index2 != 2147483647){
								z.setWykonawcy(WszystkieDruzyny.get(index2));
								sterowanie = 1;
							}
						}
						if(sterowanie == 1){
							WszystkieZadania.add(z);
						}
					}catch (NullPointerException e1){
					}
				}else if (n1 == 1){
					try{
						if(WszystkieZadania.isEmpty()){
							JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych zadań do usunięcia", "Błąd", JOptionPane.ERROR_MESSAGE);
						}else{
							int index;
							String[] zad = new String[WszystkieZadania.size()];
							for (int i = 0; i < WszystkieZadania.size(); i++) {
								zad[i] = WszystkieZadania.get(i).getZadanie();
							}
							String s = (String)JOptionPane.showInputDialog(GlownyPanel, "Które zadanie chcesz usunąć?", "Usuwanie Zadań", JOptionPane.PLAIN_MESSAGE, null, zad, zad[0]);
							String[] czesci = new String[6];
							czesci = s.split(" ");
							usunZadanie(czesci[3], czesci[4]);
						}
					}catch (NullPointerException e1){
					}
				}
			}
		});
		ocenBrygade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index;
				if (WszystkieZadania.size() == 0){
					JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych zadań do ocenienia", "Błąd", JOptionPane.ERROR_MESSAGE);
				}else{
					String[] zad = new String[WszystkieZadania.size()];
					for (int i = 0; i < WszystkieZadania.size(); i++) {
						zad[i] = WszystkieZadania.get(i).getZadanie();
					}
					String s = (String)JOptionPane.showInputDialog(GlownyPanel, "Którą Brygadę chcesz ocenić?", "Ocenianie Brygady", JOptionPane.PLAIN_MESSAGE, null, zad, zad[0]);
					if((s != null) && (s.length() > 0)){
						String ocenaS = JOptionPane.showInputDialog(GlownyPanel, "Jaki mieli wykon(liczba całkowita)", "Ocenianie Brygady", JOptionPane.PLAIN_MESSAGE);
						int ocena = Integer.parseInt(ocenaS);
						String[] czesci = new String[6];
						czesci = s.split(" ");
						if(czesci[5].equals("bez")){
							czesci[4] = czesci[4].substring(0, czesci[4].length() - 1);
						}
						ocenZadanie(czesci[3],czesci[4],ocena);
					}
				}
			}
		});
		raportDnia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] opcje = {"Raport Dnia", "Raport z...", "Raport miesięczny", "Raport o prarcowniku", "Cancel"};
				int n = JOptionPane.showOptionDialog(GlownyPanel, "Co chcesz zrobić?", "Raporty", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcje, "");
				if(n == 0){
					try{
						String out = raportDnia();
						JOptionPane.showMessageDialog(GlownyPanel, out);
					}catch (NullPointerException e1){
					}
				}else if (n == 1){
					try{
						//zakres
						if(WszyscyPracownicy.isEmpty()){
							JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych pracowników", "Błąd", JOptionPane.ERROR_MESSAGE);
						}else {
							String Poczatek, Koniec;
							Poczatek = JOptionPane.showInputDialog(GlownyPanel, "Podaj poczatek", "Wykresy", JOptionPane.PLAIN_MESSAGE);
							if(Poczatek.length() > 0){
								try{
									Koniec = JOptionPane.showInputDialog(GlownyPanel, "Podaj koniec", "Wykresy", JOptionPane.PLAIN_MESSAGE);
									if(Koniec.length() > 0){
										String out = raportZakres(Poczatek, Koniec);
										JOptionPane.showMessageDialog(GlownyPanel, out);
									}
								}catch (NullPointerException e1){
								}
							}
						}
					}catch (NullPointerException e1){
					}
				}else if (n == 2){
					try{
						//miesieczny
						if(WszyscyPracownicy.isEmpty()){
							JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych pracowników", "Błąd", JOptionPane.ERROR_MESSAGE);
						}else {
							SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
							Calendar czas = Calendar.getInstance();
							czas.set(Calendar.DATE, czas.getActualMinimum(Calendar.DATE));
							Date PoczatekD = czas.getTime();
							String Poczatek = df.format(PoczatekD);
							czas.set(Calendar.DAY_OF_MONTH, czas.getActualMaximum(Calendar.DATE));
							Date KoniecD = czas.getTime();
							String Koniec = df.format(KoniecD);
							String out = raportZakres(Poczatek, Koniec);
							JOptionPane.showMessageDialog(GlownyPanel, out);
						}
					}catch (NullPointerException e1){
					}
				}else if (n == 3){
					try{
						//o pracowniku
						if(WszyscyPracownicy.isEmpty()){
							JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych pracowników", "Błąd", JOptionPane.ERROR_MESSAGE);
						}else {
							String[] prac = new String[WszyscyPracownicy.size()];
							for (int i = 0; i < WszyscyPracownicy.size(); i++) {
								prac[i] = WszyscyPracownicy.get(i).getImieNazwisko();
							}
							String s = (String) JOptionPane.showInputDialog(GlownyPanel, "Kogo wybierasz?", "Raport o Pracowniku", JOptionPane.PLAIN_MESSAGE, null, prac, prac[0]);
							if ((s != null) && (s.length() > 0)) {
								String[] czesci = s.split(" ");
								int index = indexPracownicka(czesci[0],czesci[1]);
								if(index != 100000){
									String out = wyswietlPracownika(index);
									JOptionPane.showMessageDialog(GlownyPanel, out);
								}
							}
						}
					}catch (NullPointerException e1){
					}
				}
			}
		});
		Zapisz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				Zapis();
				JOptionPane.showMessageDialog(GlownyPanel, "Zapisano");
			}
		});
		wykresButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Imie, Nazwisko;
				try{
					if(WszyscyPracownicy.isEmpty()){
						JOptionPane.showMessageDialog(GlownyPanel, "Nie ma żadnych pracowników do sprawdzenia", "Błąd", JOptionPane.ERROR_MESSAGE);
					}else{
						String[] prac = new String[WszyscyPracownicy.size()];
						for (int i = 0; i < WszyscyPracownicy.size(); i++) {
							prac[i] = WszyscyPracownicy.get(i).getImieNazwisko();
						}
						String s = (String) JOptionPane.showInputDialog(GlownyPanel, "Czyj wykres chcesz zobaczyć?", "Generowanie Wykresu Pracownika", JOptionPane.PLAIN_MESSAGE, null, prac, prac[0]);
						if ((s != null) && (s.length() > 0)){
							String[] czesci = s.split(" ");
							Imie = czesci[0];
							Nazwisko = czesci[1];
							prac = usunPrac(prac,s);
							//Sterowanie
							String[] opcje = {"Całość", "Zakres Dat", "Cancel"};
							int n = JOptionPane.showOptionDialog(GlownyPanel, "Z jakiego okresu chcesz zebrać dane?", "Wykresy", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcje, "");
							if(n == 0){
								//Calość
								String[] opcje1 = {"Porówanaj z...", "Nie porównuj", "Cancel"};
								int n1 = JOptionPane.showOptionDialog(GlownyPanel, "Co chcesz zrobić?", "Wykresy", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcje1, "");
								if(n1 == 0){
									//Całość porównanie z...
									String Poczatek = r.poczatekOceny();
									String Koniec = r.koniecOceny();
									int iluPorownanie;
									String iluPorownanieS = JOptionPane.showInputDialog(GlownyPanel, "Z iloma osobami chcesz porównać", "Wykresy", JOptionPane.PLAIN_MESSAGE);
									iluPorownanie = Integer.parseInt(iluPorownanieS);
									String[] Imiona = new String[iluPorownanie];
									String[] Nazwiska = new String[iluPorownanie];
									for (int i = 0; i < iluPorownanie; i++) {
										String s1 = (String) JOptionPane.showInputDialog(GlownyPanel, "Z kim porównujesz?", "Wykresy", JOptionPane.PLAIN_MESSAGE, null, prac, prac[0]);
										if ((s1 != null) && (s1.length() > 0)){
											prac = usunPrac(prac,s1);
											String[] czesci1 = s1.split(" ");
											Imiona[i] = czesci1[0];
											Nazwiska[i] = czesci1[1];
										}
									}

									String title = "Zakres dat(" + Poczatek + " " + Koniec + "): " + Imie + " " + Nazwisko + " reszta";
									XYDataset dataset = stworzDataset(Imie, Nazwisko, Poczatek, Koniec, iluPorownanie, Imiona, Nazwiska);
									Wykresy wykres = new Wykresy(title, title, dataset);
									wykres.pack();
									RefineryUtilities.centerFrameOnScreen(wykres);
									wykres.setVisible(true);
								}else if (n1 == 1){
									//Całość nie porównuj
									String Poczatek = r.poczatekOceny();
									String Koniec = r.koniecOceny();
									try{
										String title = "Zakres dat(" + Poczatek + " " + Koniec + "): " + Imie + " " + Nazwisko;
										XYDataset dataset = stworzDataset(Imie, Nazwisko, Poczatek, Koniec);
										Wykresy wykres = new Wykresy(title, title, dataset);
										wykres.pack();
										RefineryUtilities.centerFrameOnScreen(wykres);
										wykres.setVisible(true);
									}catch (NullPointerException e1){
									}
								}
							}else if(n == 1) {
								//Zakres
								String[] opcje1 = {"Porówanaj z...", "Nie porównuj", "Cancel"};
								int n1 = JOptionPane.showOptionDialog(GlownyPanel, "Co chcesz zrobić?", "Wykresy", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcje1, "");
								if (n1 == 0) {
									//Zakres dat porównanie z...
									String Poczatek, Koniec;
									int iluPorownanie;
									try{
										String iluPorownanieS = JOptionPane.showInputDialog(GlownyPanel, "Z iloma osobami chcesz porównać", "Wykresy", JOptionPane.PLAIN_MESSAGE);
										iluPorownanie = Integer.parseInt(iluPorownanieS);
										String[] Imiona = new String[iluPorownanie];
										String[] Nazwiska = new String[iluPorownanie];
										for (int i = 0; i < iluPorownanie; i++) {
											String s1 = (String) JOptionPane.showInputDialog(GlownyPanel, "Z kim porównujesz?", "Wykresy", JOptionPane.PLAIN_MESSAGE, null, prac, prac[0]);
											if ((s1 != null) && (s1.length() > 0)){
												prac = usunPrac(prac,s1);
												String[] czesci1 = s1.split(" ");
												Imiona[i] = czesci1[0];
												Nazwiska[i] = czesci1[1];
											}
										}
										Poczatek = JOptionPane.showInputDialog(GlownyPanel, "Podaj poczatek", "Wykresy", JOptionPane.PLAIN_MESSAGE);
										if(Poczatek.length() > 0){
											try{
												Koniec = JOptionPane.showInputDialog(GlownyPanel, "Podaj koniec", "Wykresy", JOptionPane.PLAIN_MESSAGE);
												if(Koniec.length() > 0){
													String title = "Zakres dat(" + Poczatek + " " + Koniec + "): " + Imie + " " + Nazwisko + " reszta";
													XYDataset dataset = stworzDataset(Imie, Nazwisko, Poczatek, Koniec, iluPorownanie, Imiona, Nazwiska);
													Wykresy wykres = new Wykresy(title, title, dataset);
													wykres.pack();
													RefineryUtilities.centerFrameOnScreen(wykres);
													wykres.setVisible(true);
												}
											}catch (NullPointerException e1){
											}
										}
									}catch (NullPointerException e1){
									}
								} else if (n1 == 1) {
									//Zakres dat nie porównuj
									String Poczatek, Koniec;
									try{
										Poczatek = JOptionPane.showInputDialog(GlownyPanel, "Podaj poczatek", "Wykresy", JOptionPane.PLAIN_MESSAGE);
										if(Poczatek.length() > 0){
											try{
												Koniec = JOptionPane.showInputDialog(GlownyPanel, "Podaj koniec", "Wykresy", JOptionPane.PLAIN_MESSAGE);
												if(Koniec.length() > 0){
													String title = "Zakres dat(" + Poczatek + " " + Koniec + "): " + Imie + " " + Nazwisko;
													XYDataset dataset = stworzDataset(Imie, Nazwisko, Poczatek, Koniec);
													Wykresy wykres = new Wykresy(title, title, dataset);
													wykres.pack();
													RefineryUtilities.centerFrameOnScreen(wykres);
													wykres.setVisible(true);
												}
											}catch (NullPointerException e1){
											}
										}
									}catch (NullPointerException e1){
									}
								}
							}

						}
					}
				}catch (NullPointerException e1){
				}
			}
		});
	}

	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){
		}

		Aplikacja ap = new Aplikacja();

		JFrame frame = new JFrame("Główny Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Aplikacja().GlownyPanel);
		frame.pack();
		frame.setVisible(true);

		/*TODO:
		* */

		/*Scanner in = new Scanner(System.in);

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
				case 15:
					String Imie2, Nazwisko2;
					System.out.println("Podaj Imie: ");
					Imie2 = in.next();
					System.out.println("Podaj Nazwisko: ");
					Nazwisko2 = in.next();
					ap.usunPracownika(Imie2, Nazwisko2);
					break;
			}
		}while (wybor != 14);
		System.out.println("\nDzięki za prace!\n\tStworzył: Jarosław Ciołek-Żelechowski");*/
	}
}