import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import net.didion.jwnl.JWNLException;


public class WNSim {
	
	public static void main(String[] args) throws IOException, JWNLException {
		String pathWordNet = "WordNet/3.0";
		String pathWordNetConfigFile = "wordnet.xml";

		edu.illinois.cs.cogcomp.wnsim.WNSim wnsim = edu.illinois.cs.cogcomp.wnsim.WNSim.getInstance(pathWordNet, pathWordNetConfigFile);

		String word1 = "USA";
		String word2 = "U.S.A.";


		double sim = wnsim.getWupSimilarity(word1, word2);
		System.out.println(sim);


	}


}
