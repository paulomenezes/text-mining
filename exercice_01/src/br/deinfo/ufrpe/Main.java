package br.deinfo.ufrpe;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by paulomenezes on 11/05/17.
 */
public class Main {
    public static void main(String[] args) {
        LinkedHashMap<String, String> words = new LinkedHashMap<>();

        words.put("caresses", "caress");
        words.put("ponies", "poni");
        words.put("caress", "caress");
        words.put("cats", "cat");
        words.put("feed", "feed");
        words.put("agreed", "agree");
        words.put("plastered", "plaster");
        words.put("bled", "bled");
        words.put("motoring", "motor");
        words.put("sing", "sing");
        words.put("conflated", "conflate");
        words.put("troubled", "trouble");
        words.put("sized", "size");
        words.put("hopping", "hop");
        words.put("tanned", "tan");
        words.put("falling", "fall");
        words.put("hissing", "hiss");
        words.put("fizzed", "fizz");
        words.put("failing", "fail");
        words.put("filing", "file");
        words.put("happy", "happi");
        words.put("sky", "sky");
        words.put("relational", "relate");
        words.put("conditional", "condition");
        words.put("valenci", "valence");
        words.put("hesitanci", "hesitance");
        words.put("digitizer", "digitize");
        words.put("conformabli", "conformable");
        words.put("radicalli", "radical");
        words.put("differentli", "different");
        words.put("vileli", "vile");
        words.put("analogousli", "analogous");
        words.put("vietnamization", "vietnamize");
        words.put("predication", "predicate");
        words.put("operator", "operate");
        words.put("feudalism", "feudal");
        words.put("decisiveness", "decisive");
        words.put("hopefulness", "hopeful");
        words.put("callousness", "callous");
        words.put("formaliti", "formal");
        words.put("sensitiviti", "sensitive");
        words.put("sensibiliti", "sensible");
        words.put("triplicate", "triplic");
        words.put("formative", "form");
        words.put("formalize", "formal");
        words.put("electriciti", "electric");
        words.put("electrical", "electric");
        words.put("hopeful", "hope");
        words.put("goodness", "good");
        words.put("revival", "reviv");
        words.put("allowance", "allow");
        words.put("inference", "infer");
        words.put("airliner", "airlin");
        words.put("gyroscopic", "gyroscop");
        words.put("adjustable", "adjust");
        words.put("defensible", "defens");
        words.put("irritant", "irrit");
        words.put("replacement", "replac");
        words.put("adjustment", "adjust");
        words.put("dependent", "depend");
        words.put("adoption", "adopt");
        words.put("homologou", "homolog");
        words.put("communism", "commun");
        words.put("activate", "activ");
        words.put("angulariti", "angular");
        words.put("homologous", "homolog");
        words.put("effective", "effect");
        words.put("bowdlerize", "bowdler");
        words.put("probate", "probat");
        words.put("rate", "rate");
        words.put("cease", "ceas");
        words.put("controll", "control");
        words.put("roll", "roll");

        Potter potter = new Potter();

        final int[] i = {0};
        words.forEach((key, value) -> {
            if (potter.process(key).equals(value)) {
                System.out.println(i[0] + ": " + value);
            } else {
                System.out.println(i[0] + ": " + potter.process(key) + " \t{" + value + "}");
            }

            i[0]++;
        });
    }
}
