package cryptography;

import entity.Result;

public interface Command {
    Result execute(String[] parameters);
}