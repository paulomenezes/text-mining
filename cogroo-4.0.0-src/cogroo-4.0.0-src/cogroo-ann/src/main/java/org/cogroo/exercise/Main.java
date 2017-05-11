package org.cogroo.exercise;

import org.cogroo.analyzer.Analyzer;
import org.cogroo.analyzer.ComponentFactory;
import org.cogroo.checker.CheckDocument;
import org.cogroo.checker.GrammarChecker;
import org.cogroo.text.Document;
import org.cogroo.text.impl.DocumentImpl;
import org.cogroo.text.tree.Leaf;
import org.cogroo.text.tree.TreeElement;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = "Fazendo o exercício da dispclina de Tópicos Avançados em Inteligência Artificial";

        System.out.println("Lemmas");
        getLemas(input).forEach(System.out::println);

        System.out.println("\n\nPOS");
        getPOS(input).forEach(System.out::println);

        System.out.println("\n\nTree");
        getTree(input).print();
    }

    private static ArrayList<String> getLemas(String input) throws IOException {
        ComponentFactory factory = ComponentFactory.create(new Locale("pt", "BR"));

        Analyzer pipe = factory.createPipe();

        Document document = new DocumentImpl();
        document.setText(input);
        pipe.analyze(document);

        ArrayList<String> lemmas = new ArrayList<>();

        document.getSentences().forEach(sentence -> sentence.getTokens().forEach(token -> Collections.addAll(lemmas, token.getLemmas())));

        return lemmas;
    }

    private static ArrayList<String> getPOS(String input) {
        ComponentFactory factory = ComponentFactory.create(new Locale("pt", "BR"));

        Analyzer pipe = factory.createPipe();

        Document document = new DocumentImpl();
        document.setText(input);
        pipe.analyze(document);

        ArrayList<String> pos = new ArrayList<>();

        document.getSentences().forEach(sentence -> sentence.getTokens().forEach(token -> pos.add(token.getPOSTag())));

        return pos;
    }

    private static Node getTree(String text) throws IOException {
        ComponentFactory factory = ComponentFactory.create(new Locale("pt", "BR"));

        Analyzer pipe = factory.createPipe();
        GrammarChecker gc = new GrammarChecker(pipe);

        Document document = new DocumentImpl();
        document.setText(text);
        pipe.analyze(document);

        CheckDocument document2 = new CheckDocument(text);

        gc.analyze(document2);
        org.cogroo.text.tree.Node tree = document.getSentences().get(0).asTree();

        Node root = new Node(tree.getSyntacticTag(), null, null);
        root.addChild(getChildren(root, tree));

        return root;
    }

    private static Node getChildren(Node parent, org.cogroo.text.tree.Node tree) {
        Node n = new Node(tree.getSyntacticTag(), parent, null);

        for (int i = 0; i < tree.getElems().size(); i++) {
            TreeElement treeElement = tree.getElems().get(i);

            if (treeElement instanceof org.cogroo.text.tree.Node) {
                n.addChild(getChildren(n, ((org.cogroo.text.tree.Node) treeElement)));
            } else {
                Node leaf = new Node(treeElement.getMorphologicalTag(), n, null);
                leaf.addChild(new Node(((Leaf) treeElement).getLexeme(), n, null));

                n.addChild(leaf);
            }
        }

        return n;
    }
}
