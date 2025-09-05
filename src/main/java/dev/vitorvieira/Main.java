package dev.vitorvieira;

import java.io.IOException;

import dev.vitorvieira.quests.Quest01;
import dev.vitorvieira.quests.Quest02;
import dev.vitorvieira.quests.Quest03;


public class Main {
    public static void main( String[] args ) throws IOException {

        System.out.println( "\n\n===== Solving Quest 01 =====" );
        Quest01 quest01 = new Quest01( );
        System.out.println( "Part 1: " + quest01.solvePart1( "inputs/quest01/input1.txt" ) );
        System.out.println( "Part 2: " + quest01.solvePart2( "inputs/quest01/input2.txt" ) );
        System.out.println( "Part 3: " + quest01.solvePart3( "inputs/quest01/input3.txt" ) );


        System.out.println( "\n\n===== Solving Quest 02 =====" );
        Quest02 quest02 = new Quest02( );
        System.out.println( "Part 1: " + quest02.solvePart1( "inputs/quest02/input1.txt" ) );
        System.out.println( "Part 2: " + quest02.solvePart2( "inputs/quest02/input2.txt", 100 ) );
        System.out.println( "Part 3: " + quest02.solvePart3( "inputs/quest02/input3.txt", 100000 ) );


        System.out.println( "\n\n===== Solving Quest 03 =====" );
        Quest03 quest03 = new Quest03( );
        System.out.println( "Part 1: " + quest03.solvePart1( "inputs/quest03/input1.txt" ) );
        System.out.println( "Part 2: " + quest03.solvePart2( "inputs/quest03/input2.txt" ) );
        System.out.println( "Part 3: " + quest03.solvePart3( "inputs/quest03/input3.txt" ) );
    }
}
