import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by paulomenezes on 25/05/17.
 */
public class Similaridade {
    private static WordNet wordNetParser = null;
    private static Word2Vec word2VecParser = null;

    private static List<Double> values = new ArrayList<Double>();

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        wordNetParser = new WordNet("WordNet", "/3.0/dict");

        File f = new File("word2vecModel.txt");
        if (!f.exists()) {
            TreinandoModelo.word2vec();
        }
        word2VecParser = WordVectorSerializer.loadFullModel("word2vecModel.txt");

        double similaridade = similarity("sources close to the sale said was keeping", "sources close to the sale said was keeping");

        System.out.println("Similaridade: " + similaridade);
    }

    private static double similarity(String sentence1, String sentence2) throws FileNotFoundException {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Annotation document1 = new Annotation(sentence1);
        Annotation document2 = new Annotation(sentence2);

        pipeline.annotate(document1);
        pipeline.annotate(document2);

        List<CoreMap> sentences1 = document1.get(CoreAnnotations.SentencesAnnotation.class);
        List<CoreMap> sentences2 = document2.get(CoreAnnotations.SentencesAnnotation.class);

        List<String> words01 = new ArrayList<String>();
        for (CoreMap sentence : sentences1) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                words01.add(token.word());
            }
        }

        List<String> words02 = new ArrayList<String>();
        for (CoreMap sentence : sentences2) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                words02.add(token.word());
            }
        }

        int x = words01.size();
        int y = words02.size();

        double[][] matrix = new double[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                double wordNet = wordNet(words01.get(i), words02.get(j));
                double word2vec = word2vec(words01.get(i), words02.get(j));

                if (wordNet == 0)
                    matrix[i][j] = word2vec;
                else
                    matrix[i][j] = (wordNet + word2vec) / 2;
            }
        }

        if (reduceMatrix(matrix) == null) {
            double media = 0;
            for (int i = 0; i < values.size(); i++) {
                media += values.get(i);
            }

            return media / values.size();
        }

        return 0;
    }

    private static double[][] reduceMatrix(double[][] matrix) {
        double bigger = 0;
        int row = 0;
        int column = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > bigger) {
                    bigger = matrix[i][j];

                    row = i;
                    column = j;
                }
            }
        }

        values.add(bigger);

        if (matrix.length - 1 == 0 || matrix[0].length - 1 == 0) {
            return null;
        } else {
            double[][] newMatrix = new double[matrix.length - 1][matrix[0].length - 1];

            int x = 0;
            int y = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (i == row) {
                    continue;
                }

                for (int j = 0; j < matrix[i].length; j++) {
                    if (j == column) {
                        continue;
                    }
                    newMatrix[x][y] = matrix[i][j];

                    ++y;
                }

                ++x;
                y = 0;
            }

            return reduceMatrix(newMatrix);
        }
    }

    private static double wordNet(String word1, String word2) {
        return wordNetParser.calcularSimilaridadeLIN(word1, word2);
    }

    private static double word2vec(String word1, String word2) {
        try {
            return word2VecParser.similarity(word1, word2);
        } catch (Exception e) {
            return 0;
        }
    }
}
