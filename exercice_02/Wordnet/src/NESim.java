import java.util.HashMap;
import edu.illinois.cs.cogcomp.entityComparison.core.EntityComparison;


public class NESim {

	public static void main(String[] args) {
		
		EntityComparison e = new EntityComparison();
		HashMap<String,String> h;
		
		h = e.compareNames("Bill Gates", "William H. Gates");
		
		System.out.println(h);
	}
	
}
