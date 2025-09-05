package dev.vitorvieira.quests;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Quest03Test {
    private static Quest03 quest03;

    @BeforeAll
    static void setup( ) {
        quest03 = new Quest03( );
    }


    @Test
    @DisplayName( "Test Part 1" )
    void test_part1( ) throws IOException {
        int solved = quest03.solvePart1( "inputs/tests/quest03/part1.txt" );
        assertEquals( 844, solved );
    }


    @Test
    @DisplayName( "Test Part 2" )
    void test_part2( ) throws IOException {
        String solved = quest03.solvePart2( "inputs/tests/quest03/part2.txt" );
        assertEquals( "1,3,4,2", solved );
    }


    @Test
    @DisplayName( "Test Part 3" )
    void test_part3( ) throws IOException {
        int solved1 = quest03.solvePart3( "inputs/tests/quest03/part3_test1.txt" );
        int solved2 = quest03.solvePart3( "inputs/tests/quest03/part3_test2.txt" );
        assertEquals( 33, solved1 );
        assertEquals( 1125, solved2 );
    }
}
