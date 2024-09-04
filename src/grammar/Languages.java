package grammar;

public enum Languages {
    RUSSIAN(new RussianGrammar());
    private final Grammar grammar;

    Languages(Grammar grammar) {
        this.grammar = grammar;
    }

    public static Grammar getGrammar(String language) {
        return Languages.valueOf(language).grammar;
    }
}
