import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Torak28 on 31.12.2016.
 */
public class Druzyna {
	private Pracownik Przodowy;
	private List<Pracownik> Pomocnicy = new ArrayList<Pracownik>();

	public Pracownik getPrzodowy(){
		return Przodowy;
	}
	public void setPrzodowy(Pracownik p){
		Przodowy = p;
	}
	public List<Pracownik> getPomocnicy(){
		return Pomocnicy;
	}
	public void setPomocnicy(Pracownik p){
		Pomocnicy.add(Pomocnicy.size(),p);
	}
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
	}
	public void dodajDruzyne(){
	}
	public Druzyna(){

	}


}
