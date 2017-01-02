/**
 * Created by Torak28 on 31.12.2016.
 */

import java.util.ArrayList;
import java.util.List;

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



	public void dodajDruzyne(){
		Druzyna d = new Druzyna();
		Pracownik p;
		d.dodajPrzodowego(d);
		d.dodajPomocnika(d);
		p = d.getPrzodowy();
		WszyscyPracownicy.add(WszyscyPracownicy.size(),p);
		for (int i = 0; i < d.getPomocnicy().size(); i++) {
			p = d.getPomocnicy().get(i);
			WszyscyPracownicy.add(WszyscyPracownicy.size(),p);
		}
	}
	public void wyswietlPracownikow(Aplikacja ap){
		System.out.println("Lista Pracownikow: ");
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
