/**
 * Created by Torak28 on 31.12.2016.
 */
public class Zadanie {
	private int Typ;
	private int Norma;
	private Druzyna Wykonawcy;
	private int Wynik;

	public int getTyp(){
		return Typ;
	}
	public void setTyp(int t){
		Typ = t;
	}
	public int getNorma(){
		return Norma;
	}
	public void setNorma(int n){
		Norma = n;
	}
	public Druzyna getWykonawcy(){
		return Wykonawcy;
	}
	public void setWykonawcy(Druzyna d){
		Wykonawcy = d;
	}
	public int getWynik(){
		return Wynik;
	}
	public void setWynik(int w) {
		Wynik = w;
	}
	public String wyswietlWykonawcow(){
		String out = "\tPrzodowy: " + getWykonawcy().getPrzodowy().getImie() + " " + getWykonawcy().getPrzodowy().getNazwisko();
		for (int i = 0; i < getWykonawcy().getPomocnicy().size(); i++) {
			out = out + "\tPomocnik(" + i +"): " + getWykonawcy().getPomocnicy().get(i).getImie() + " " + getWykonawcy().getPomocnicy().get(i).getNazwisko();
		}
		return out;
	}
	public void Zadanie() {

	}
}
