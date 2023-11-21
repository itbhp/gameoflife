package it.twinsbrain.dojo.gameoflife;

import it.twinsbrain.dojo.gameoflife.GameOfLife.X;
import it.twinsbrain.dojo.gameoflife.GameOfLife.Y;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GameOfLifeTest {

    @Nested
    class Parse {
        @Test
        void one_cell_world_dead_cell() {
            var gameOfLife = GameOfLife.from(".");
            assertThat(gameOfLife.isAliveCellAt(X.of(0), Y.of(0)), is(false));
        }

        @Test
        void one_cell_world_alive_cell() {
            var gameOfLife = GameOfLife.from("*");
            assertThat(gameOfLife.isAliveCellAt(X.of(0), Y.of(0)), is(true));
        }

        @Test
        void unidimensional_world() {
            var gameOfLife = GameOfLife.from("*.*");
            assertThat(gameOfLife.isAliveCellAt(X.of(0), Y.of(0)), is(true));
            assertThat(gameOfLife.isAliveCellAt(X.of(1), Y.of(0)), is(false));
            assertThat(gameOfLife.isAliveCellAt(X.of(2), Y.of(0)), is(true));
        }

        @Test
        void two_dimensional_world() {
            var gameOfLife = GameOfLife.from("*.*\n.**");
            assertThat(gameOfLife.isAliveCellAt(X.of(0), Y.of(1)), is(true));
            assertThat(gameOfLife.isAliveCellAt(X.of(1), Y.of(1)), is(false));
            assertThat(gameOfLife.isAliveCellAt(X.of(2), Y.of(1)), is(true));

            assertThat(gameOfLife.isAliveCellAt(X.of(0), Y.of(0)), is(false));
            assertThat(gameOfLife.isAliveCellAt(X.of(1), Y.of(0)), is(true));
            assertThat(gameOfLife.isAliveCellAt(X.of(2), Y.of(0)), is(true));
        }
    }

    @Nested
    class NextGeneration {

        @ParameterizedTest(name = "{0} becomes {1}")
        @CsvSource({
                "., .",
                "*, .",
                ".***., ..*..",
                "..**.., ......"
        })
        void up_to_unidimensional_next_generation_works(String start, String expectedNext) {
            var gameOfLife = GameOfLife.from(start);
            assertThat(gameOfLife.nextGeneration(), is(expectedNext));
        }

        @Test
        void two_dimensional_should_work() {
            var gameOfLife = GameOfLife.from(
                    "..**..\n" +
                            "..*..."
            );
            assertThat(gameOfLife.nextGeneration(), is(
                    "..**..\n" +
                            "..**.."
            ));
        }

        @Test
        void acceptance_test() {
            var gameOfLife = GameOfLife.from(
                    """
                            ........
                            ....*...
                            ...**...
                            ........"""
            );
            assertThat(gameOfLife.nextGeneration(), is(
                    """
                            ........
                            ...**...
                            ...**...
                            ........"""
            ));
        }
    }
}