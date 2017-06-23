import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> stopwords;

    public static void main(String[] args) throws IOException {
        stopwords = Files.readAllLines(Paths.get("english-stop-words-large.txt"));

        String document01 = "Text mining also referred to as text data mining";
        String document02 = "Text analysis involves information retrieval";
        String document03 = "A typical application is to scan a set of information documents written in a natural language and either model the document set for predictive classification purposes or populate a database or search index with the information extracted text";

        List<String> documents = new ArrayList<>();
        documents.add(document01);
        documents.add(document02);
        documents.add(document03);

        List<List<String>> newDocuments = removeStopwords(documents);
        
        List<String> allWords = new ArrayList<>();
        for (List<String> newDocument : newDocuments) {
            for (String aNewDocument : newDocument) {
                boolean contains = false;
                for (String allWord : allWords) {
                    if (aNewDocument.equals(allWord)) {
                        contains = true;
                        break;
                    }
                }

                if (!contains) {
                    allWords.add(aNewDocument);
                }
            }
        }

        int[][] matrix = new int[documents.size()][allWords.size()];
        for (int j = 0; j < newDocuments.size(); j++) {
            for (int k = 0; k < newDocuments.get(j).size(); k++) {
                for (int i = 0; i < allWords.size(); i++) {
                    if (newDocuments.get(j).get(k).equals(allWords.get(i))) {
                        matrix[j][i] = 1;
                    }
                }
            }
        }

        String query = "text information";

        List<String> queryWords = queryStopwords(query);

        int[][] queryMatrix = new int[1][allWords.size()];
        for (int i = 0; i < allWords.size(); i++) {
            for (String queryWord : queryWords) {
                if (allWords.get(i).equals(queryWord)) {
                    queryMatrix[0][i] = 1;
                }
            }
        }

        List<String> documentResults = new ArrayList<>();

        for (int j = 0; j < documents.size(); j++) {
            int totalQuery = 0;
            int totalMatrix = 0;

            for (int i = 0; i < allWords.size(); i++) {
                if (queryMatrix[0][i] == 1) {
                    totalQuery++;
                    if (matrix[j][i] == 1) {
                        totalMatrix++;
                    }
                }
            }

            if (totalQuery == totalMatrix) {
                documentResults.add(documents.get(j));
            }
        }

        documentResults.forEach(d -> System.out.println("Documento encontrado: " + d));
    }

    private static List<String> queryStopwords(String query) {
        String[] words = query.split(" ");

        List<String> filteredWords = new ArrayList<>();

        for (String word : words) {
            word = word.toLowerCase();

            boolean insert = true;
            for (String stopword : stopwords) {
                if (word.equals(stopword)) {
                    insert = false;
                }
            }

            if (insert) {
                filteredWords.add(word);
            }
        }

        return filteredWords;
    }

    private static List<List<String>> removeStopwords(List<String> documents) {
        List<List<String>> allDocuments = new ArrayList<>();

        for (String document : documents) {
            List<String> withoutStopwords = new ArrayList<>();
            String[] words = document.split(" ");

            for (String word : words) {
                word = word.toLowerCase();

                boolean insert = true;
                for (String stopword : stopwords) {
                    if (word.equals(stopword)) {
                        insert = false;
                    }
                }

                if (insert) {
                    withoutStopwords.add(word);
                }
            }

            allDocuments.add(withoutStopwords);
        }

        return allDocuments;
    }
}
