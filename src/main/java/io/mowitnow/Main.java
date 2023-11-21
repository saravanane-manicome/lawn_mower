package io.mowitnow;

import io.mowitnow.lawn_mower.LineBasedApplication;
import io.mowitnow.lawn_mower.core.exception.InvalidInputException;

public class Main {
    public static void main(String[] args) throws InvalidInputException {
        if(args.length != 1) {
            throw new IllegalArgumentException("USAGE: THERE SHOULD BE EXACTLY ONE ARGUMENT, WHICH WOULD BE THE PATH OF THE FILE TO PARSE");
        }

        LineBasedApplication.run(args[0]);
    }
}