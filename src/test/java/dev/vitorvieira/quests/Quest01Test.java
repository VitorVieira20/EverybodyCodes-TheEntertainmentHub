package dev.vitorvieira.quests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Quest01Test {

    private static Quest01 quest01;

    @BeforeAll
    static void setup( ) {
        quest01 = new Quest01( );
    }


    @Test
    @DisplayName( "Test Part 1" )
    void test_part1( ) throws IOException {
        int solved = quest01.solvePart1( "inputs/tests/quest01/part1.txt" );
        assertEquals( 26, solved );
    }


    @Test
    @DisplayName( "Test Part 2" )
    void test_part2( ) throws IOException {
        int solved = quest01.solvePart2( "inputs/tests/quest01/part2.txt" );
        assertEquals( 115, solved );
    }


    @Test
    @DisplayName( "Test Part 3" )
    void test_part3( ) throws IOException {
        String solved1 = quest01.solvePart3( "inputs/tests/quest01/part3_test1.txt" );
        String solved2 = quest01.solvePart3( "inputs/tests/quest01/part3_test2.txt" );
        String solved3 = quest01.solvePart3( "inputs/tests/quest01/part3_test3.txt" );
        assertEquals( "13 43", solved1 );
        assertEquals( "25 66", solved2 );
        assertEquals( "39 122", solved3 );
    }
}
