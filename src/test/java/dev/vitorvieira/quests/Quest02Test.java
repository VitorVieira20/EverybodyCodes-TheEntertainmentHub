package dev.vitorvieira.quests;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Quest02Test {

    private static Quest02 quest02;

    @BeforeAll
    static void setup( ) {
        quest02 = new Quest02( );
    }


    @Test
    @DisplayName( "Test Part 1" )
    void test_part1( ) throws IOException {
        int solved = quest02.solvePart1( "inputs/tests/quest02/part1.txt" );
        assertEquals( 7, solved );
    }


    @Test
    @DisplayName( "Test Part 2" )
    void test_part2( ) throws IOException {
        int solved_test1 = quest02.solvePart2( "inputs/tests/quest02/part2_test1.txt", 5 );
        int solved_test2_01 = quest02.solvePart2( "inputs/tests/quest02/part2_test2.txt", 10 );
        int solved_test2_02 = quest02.solvePart2( "inputs/tests/quest02/part2_test2.txt", 50 );
        int solved_test1_03 = quest02.solvePart2( "inputs/tests/quest02/part2_test2.txt", 100 );
        assertEquals( 14, solved_test1 );
        assertEquals( 304, solved_test2_01 );
        assertEquals( 1464, solved_test2_02 );
        assertEquals( 2955, solved_test1_03 );
    }
}
