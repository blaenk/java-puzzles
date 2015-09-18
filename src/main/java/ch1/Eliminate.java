package puzzle.ch1;

import java.util.BitSet;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class Eliminate {
    public static void eliminate(int mat[], int rows, int cols) {
        BitSet eliminatedRows = new BitSet(rows);
        BitSet eliminatedCols = new BitSet(cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (mat[col + row * cols] == 0) {
                    eliminatedRows.set(row);
                    eliminatedCols.set(col);
                }
            }
        }

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                if (eliminatedRows.get(row) || eliminatedCols.get(col))
                    mat[col + row * cols] = 0;
    }

    @Test
    public void testEliminate() {
        int eliminateAr[] = {1, 2, 3,
                             0, 5, 6,
                             7, 8, 9};

        eliminate(eliminateAr, 3, 3);

        int eliminateArT[] = {0, 2, 3,
                              0, 0, 0,
                              0, 8, 9};

        assertThat(eliminateAr, is(eliminateArT));

        int eliminateAr2[] = {1, 2, 3,
                              4, 0, 6,
                              7, 8, 9};

        eliminate(eliminateAr2, 3, 3);

        int eliminateArT2[] = {1, 0, 3,
                               0, 0, 0,
                               7, 0, 9};

        assertThat(eliminateAr2, is(eliminateArT2));
    }
}
