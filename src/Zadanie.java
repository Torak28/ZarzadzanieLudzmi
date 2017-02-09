/**
 * Created by Torak28 on 31.12.2016.
 */
public class Zadanie {
	private int Typ;
	private int Norma;
	private Druzyna Wykonawcy;
	private int Wynik;
	private float Miara;

	public float getMiara(){
		return Miara;
	}
	public void setMiara(float m){
		Miara = m;
	}
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
		String out = "\t\tPrzodowy: " + getWykonawcy().getPrzodowy().getImie() + " " + getWykonawcy().getPrzodowy().getNazwisko() + " Ocena: " +getWykonawcy().getPrzodowy().getOcena();
		for (int i = 0; i < getWykonawcy().getPomocnicy().size(); i++) {
			out = out + "\n\t\tPomocnik(" + (i+1) +"): " + getWykonawcy().getPomocnicy().get(i).getImie() + " " + getWykonawcy().getPomocnicy().get(i).getNazwisko() + " Ocena: " +getWykonawcy().getPomocnicy().get(i).getOcena();
		}
		return out;
	}
	public int iluWykonawcow(){
		int zmienna = Wykonawcy.getPomocnicy().size();
		return 1 + zmienna;
	}
	public void Zadanie() {
	}
}
