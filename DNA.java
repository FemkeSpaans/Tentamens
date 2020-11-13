package Oefententamens.JavaKansEen20182019;

import java.util.Arrays;

public class DNA {
    static final String[] DNA = {"G", "C", "T", "A"}; // array of one letter code amino

    public static void not_dna(String symbol) throws NoValidSeq {
        if (!(Arrays.toString(DNA).contains(symbol))) {
            throw new NoValidSeq("This is not an existing nucleobase of DNA: " + symbol);
        }
    }

}

class NoValidSeq extends Exception {

    public NoValidSeq() {
        super();
    }

    public NoValidSeq(String err) {
        super(err);
    }
}
