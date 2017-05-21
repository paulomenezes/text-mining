import java.io.IOException;

/**
 * Created by paulomenezes on 10/05/17.
 */
public class main {
    public static void main(String[] args) throws IOException {
        String text= "The use of artificial intelligence and procedural content generation algorithms in mixed reality games is an un- explored space. We posit that these algorithms can en- hance the gameplay experience in mixed reality games. We present two prototype games that use procedural content generation to design levels that make use of the affordances in the players physical environment. The levels produced can be tailored to a user, customizing gameplay difficulty and affecting how the player moves around the real-world environment.";

        EnglishPLN pln = new EnglishPLN();

        System.out.println("Tokens");
        pln.tokenize(text).forEach(System.out::println);

        System.out.println("\n\nSetences");
        pln.ssplit(text).forEach(System.out::println);

        System.out.println("\n\nLemmas");
        pln.lemmas(text).forEach(System.out::println);

        System.out.println("\n\nNER");
        pln.ner(text).forEach(System.out::println);

        System.out.println("\n\nTree");
        pln.tree(text).forEach(Node::print);

        System.out.println("\n\nTree 2");
        pln.tree2(text).forEach(System.out::println);

        System.out.println("\n\nRemove stopwords");
        System.out.println(pln.removeStopwords(text));

        System.out.println("\n\nStemming");
        pln.stemmer(text).forEach(System.out::println);
    }
}
