import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by paulomenezes on 21/05/17.
 */
class EnglishPLN {
    private StanfordCoreNLP pipeline;

    EnglishPLN() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
        pipeline = new StanfordCoreNLP(props);
    }

    List<String> tokenize(String text) {
        Annotation document = new Annotation(text);

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        List<String> tokens = new ArrayList<>();
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                tokens.add(token.toString());
            }
        }

        return tokens;
    }

    List<String> ssplit(String text) {
        Annotation document = new Annotation(text);

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        List<String> set = new ArrayList<>();
        for (CoreMap sentence : sentences) {
            set.add(sentence.toString());
        }

        return set;
    }

    List<String> lemmas(String text) {
        Annotation document = new Annotation(text);

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        List<String> lemmas = new ArrayList<>();
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                lemmas.add(token.lemma());
            }
        }

        return lemmas;
    }

    List<String> ner(String text) {
        Annotation document = new Annotation(text);

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        List<String> ner = new ArrayList<>();
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                ner.add(token.get(CoreAnnotations.NamedEntityTagAnnotation.class));
            }
        }

        return ner;
    }

    List<Node> tree(String text) {
        Annotation document = new Annotation(text);

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        List<Node> nodeTree = new ArrayList<>();
        for (CoreMap sentence : sentences) {
            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);

            nodeTree.add(getChildren(null, tree));
        }

        return nodeTree;
    }

    private Node getChildren(Node parent, Tree tree) {
        Node node = new Node(tree.value(), parent, null);

        for (int i = 0; i < tree.children().length; i++) {
            Tree children = tree.children()[i];

            if (children.isLeaf()) {
                node.addChild(new Node(children.value(), node, null));
            } else {
                node.addChild(getChildren(node, children));
            }
        }

        return node;
    }

    String removeStopwords(String text) throws IOException {
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        StringBuilder newText = new StringBuilder();

        List<String> stopwords = new ArrayList<>();
        Files.lines(Paths.get("stopwords.txt")).forEach(stopwords::add);

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                boolean insert = true;
                for (String word : stopwords) {
                    if (token.word().toLowerCase().equals(word.toLowerCase())) {
                        insert = false;
                        break;
                    }
                }

                if (insert) {
                    newText.append(token.word()).append(" ");
                }
            }
        }

        return newText.toString();
    }

    List<String> stemmer(String text) {
        Annotation document = new Annotation(text);
        Potter potter = new Potter();

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        List<String> tokens = new ArrayList<>();
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                tokens.add(potter.process(token.word().toLowerCase()));
            }
        }

        return tokens;
    }
}