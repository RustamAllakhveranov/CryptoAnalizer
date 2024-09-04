package cryptography;


public enum CommandContainer {
    ENCODE(new Encode()),
    DECODE(new Decode()),
    BRUTE_FORCE(new BruteForce()),
    ANALYZE(new StatisticalAnalyzer());
    private final Command command;

    CommandContainer(Command command) {
        this.command = command;
    }

    public static Command getCommand(String commandName) {
        try {
            CommandContainer commandContainer = CommandContainer.valueOf(commandName);
            return commandContainer.command;
        } catch (IllegalArgumentException e) {
            System.err.println();
            throw new RuntimeException(e);
        }
    }
}