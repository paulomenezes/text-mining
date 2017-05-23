WNSim in Java
Cognitive Computation Group
---------------------------

- There are two important things to do before initialize WNSim:
  (i) Download and decompress WordNet to your local system:
      Link: http://wordnetcode.princeton.edu/3.0/WordNet-3.0.tar.gz
  (ii) Modify the path to WordNet in the file 'wordnet.xml' to point to the WordNet folder:
      <param name="dictionary_path" value="../WordNet-3.0/dict"/>
      (Assume that you put WordNet at the same level of WNSim --- not inside)

- Command line example:
  java -Xmx1G -cp lib/*:WNSim.jar edu.illinois.cs.cogcomp.wnsim.WNSim
  Input 2 words, used 'Enter' to input.

- Code example:

	public static void main(String[] args) throws IOException, JWNLException {
		String pathWordNet = "../WordNet-3.0";
		String pathWordNetConfigFile = "wordnet.xml";

		WNSim wnsim = WNSim.getInstance(pathWordNet, pathWordNetConfigFile);

		String word1 = "";
		String word2 = "";

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter two nouns (_ to end): ");
		word1 = in.readLine();
		if (word1.equals("_"))
			return;
		word2 = in.readLine();

		do {
			double sim = wnsim.getWupSimilarity(word1, word2);
			System.out.println("Similarity: " + sim);
			System.out.print("Enter two nouns (_ to end): ");
			word1 = in.readLine();
			if (word1.equals("_"))
				return;
			word2 = in.readLine();
		} while (!word2.equals("_"));

	}

- There are two main similarity functions in WNSim now:
  (i)  public double getWupSimilarity(String word1, String word2)
  (ii) public double getWupSimilarity(String word1, String partOfSpeech1,
			String word2, String partOfSpeech2)

