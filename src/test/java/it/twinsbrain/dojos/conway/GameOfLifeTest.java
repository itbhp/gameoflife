package it.twinsbrain.dojos.conway;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class GameOfLifeTest {

    @Test
    public void oneByOneDead() {
        String oldGenerationAsString =
                "Generation 1: \n" +
                "1 1\n" +
                ".";

        Generation nextGeneration =
                new GameOfLife()
                .generationFrom(oldGenerationAsString)
                .next();

        String nextGenerationAsString =
                "Generation 2: \n" +
                "1 1\n" +
                ".";

        assertThat(nextGeneration.asString(), equalTo(nextGenerationAsString));
    }
}
