import java.util.ArrayList;
import java.util.List;

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
	public Druzyna(){

	}
}
