package it.twinsbrain.dojo.gameoflife;

import it.twinsbrain.dojo.gameoflife.GameOfLife.X;
import it.twinsbrain.dojo.gameoflife.GameOfLife.Y;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

}