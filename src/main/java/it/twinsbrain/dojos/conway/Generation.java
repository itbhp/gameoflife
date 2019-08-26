package it.twinsbrain.dojos.conway;

public class Generation {
    public Generation next() {
        return new Generation();
    }

    public String asString() {
        return  "Generation 2: \n" +
                "1 1\n" +
                ".";
    }
}
