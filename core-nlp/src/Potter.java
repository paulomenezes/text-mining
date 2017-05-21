class Potter {
    private String word;
    private String newWord;
    private String rule;

    String process(String word) {
        this.word = word;
        this.newWord = null;
        this.rule = "";

        // Regra 2
        endReplaceSize("ational", "ate");
        endReplaceSize("tional", "tion");
        endReplaceSize("enci", "ence");
        endReplaceSize("anci", "ance");
        endReplaceSize("izer", "ize");
        endReplaceSize("abli", "able");
        endReplaceSize("alli", "al");
        endReplaceSize("entli", "ent");
        endReplaceSize("eli", "e");
        endReplaceSize("ousli", "ous");
        endReplaceSize("ization", "ize");
        endReplaceSize("ation", "ate");
        endReplaceSize("ator", "ate");
        endReplaceSize("alism", "al");
        endReplaceSize("iveness", "ive");
        endReplaceSize("fulness", "ful");
        endReplaceSize("ousness", "ous");
        endReplaceSize("aliti", "al");
        endReplaceSize("iviti", "ive");
        endReplaceSize("biliti", "ble");

        // Regra 3
        endReplaceSize("icate", "ic");
        endReplaceSize("ative", "");
        endReplaceSize("alize", "al");
        endReplaceSize("iciti", "ic");
        endReplaceSize("ical", "ic");
        endReplaceSize("ful", "");
        endReplaceSize("ness", "");

        // Regra 4
        endReplaceSize("al", 0);
        endReplaceSize("ance", 0);
        endReplaceSize("ence", 0);
        endReplaceSize("er", 0);
        endReplaceSize("ic", 0);
        endReplaceSize("able", 0);
        endReplaceSize("ible", 0);
        endReplaceSize("ant", 0);
        endReplaceSize("ement", 0);
        endReplaceSize("ment", 0);
        endReplaceSize("ent", 0);
        endReplace4(); //
        endReplaceSize("ou", 0);
        endReplaceSize("ism", 0);
        endReplaceSize("ate", 0);
        endReplaceSize("iti", 0);
        endReplaceSize("ous", 0);
        endReplaceSize("ive", 0);
        endReplaceSize("ize", 0);

        // Regra 5A
        endReplaceSize("e", 1);
        endReplace5A();
        endReplace5B();

        // Regra 1A
        endReplace("sses", "ss");
        endReplace("ies", "i");
        endReplace("ss", "ss");
        endReplace("s", "");

        // Regra 1B
        endReplaceSize("eed", "ee");
        endReplaceVowel("ed", "");
        endReplaceVowel("ing", "");

        if (rule.equals("1B")) {
            String oldWorld = newWord;
            newWord = null;

            this.word = oldWorld;

            endReplace("at", "ate");
            endReplace("bl", "ble");
            endReplace("iz", "ize");

            endReplace1B1();
            endReplace1B2();

            if (newWord == null) {
                newWord = oldWorld;
            }
        }

        // Regra 1C
        endReplaceVowel("y", "i");

        if (newWord == null)
            newWord = word;

        return newWord;
    }

    private void endReplace(String end, String replace) {
        if (newWord == null && word.endsWith(end)) {
            newWord = word.replace(end, replace);
            rule = "1A";
        }
    }

    private void endReplaceSize(String end, String replace) {
        if (newWord == null && word.endsWith(end) && m(word.substring(0, word.indexOf(end))) > 0) {
            newWord = word.replace(end, replace);
            rule = "1B";
        }
    }

    private void endReplaceSize(String end, int size) {
        if (newWord == null && word.endsWith(end) && m(word.substring(0, word.indexOf(end))) > size) {
            newWord = word.replace(end, "");
            rule = "1B";
        }
    }

    private void endReplaceVowel(String end, String replace) {
        if (newWord == null && word.endsWith(end) && containsVowel(word.substring(0, word.indexOf(end)))) {
            newWord = word.replace(end, replace);
            rule = "1B";
        }
    }

    private void endReplace1B1() {
        char[] wordChar = word.toCharArray();

        if (newWord == null && consoant(word.length() - 1) &&
            consoant(word.length() - 2) && wordChar[wordChar.length - 1] == wordChar[wordChar.length - 2] &&
            !(wordChar[wordChar.length - 1] == 'l' || wordChar[wordChar.length - 1] == 's' || wordChar[wordChar.length - 1] == 'z')) {
            newWord = word.substring(0, wordChar.length - 1);
            rule = "1B";
        }
    }

    private void endReplace1B2() {
        char[] wordChar = word.toCharArray();

        if (newWord == null && m(word) == 1 && consoant(word.length() - 1) &&
            !consoant(word.length() -2) && consoant(word.length() - 3) &&
                !(wordChar[wordChar.length - 1] == 'w' || wordChar[wordChar.length - 1] == 'x' || wordChar[wordChar.length - 1] == 'y')) {
            newWord = word + "e";
            rule = "1B";
        }
    }

    private void endReplace4() {
        if (newWord == null && m(word) > 1 && (word.endsWith("sion") || word.endsWith("tion"))) {
            newWord = word.replace("ion", "");
            rule = "1B";
        }
    }

    private void endReplace5A() {
        if (newWord == null && m(word) == 1 && word.charAt(word.length() - 2) != 'o' && word.charAt(word.length() - 1) == 'e') {
            newWord = word.replace("e", "");
            rule = "1B";
        }
    }

    private void endReplace5B() {
        char[] wordChar = word.toCharArray();

        if (newWord == null && m(word) > 1 && consoant(word.length() - 1) &&
            consoant(word.length() - 2) && wordChar[wordChar.length - 1] == wordChar[wordChar.length - 2] &&
            word.charAt(word.length() - 1) == 'l') {

            newWord = word.replace("ll", "l");
            rule = "1B";
        }
    }

    private int m(String word) {
        int totalCount = 0;
        int lastVowel = -1;

        char[] wordLetter = word.toCharArray();

        for (int i = 0; i < wordLetter.length; i++) {
            if (!consoant(i)) {
                lastVowel = i;
                continue;
            }

            if (consoant(i) && lastVowel > -1) {
                totalCount++;
                lastVowel = -1;
            }
        }

        return totalCount;
    }

    private boolean consoant(int index) {
        switch (word.charAt(index)) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return false;
            case 'y':
                return index == 0 || !consoant(index - 1);
            default:
                return true;
        }
    }

    private boolean consoant(String word, int index) {
        switch (word.charAt(index)) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return false;
            case 'y':
                return index == 0 || !consoant(index - 1);
            default:
                return true;
        }
    }

    private boolean containsVowel(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!consoant(word, i)) {
                return true;
            }
        }

        return false;
    }
}
