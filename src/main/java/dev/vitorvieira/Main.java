package dev.vitorvieira;

import dev.vitorvieira.quests.Quest01;
import java.util.Scanner;

public class Main {
    public static void main( String[] args ) {
            Scanner sc = new Scanner(System.in);

            while ( true ) {
                System.out.println( "====== Quests Menu ======" );
                System.out.println( "1 - Quest 1" );
                System.out.println( "0 - Exit" );
                System.out.print( "Choose quest: " );

                int questChoice = sc.nextInt( );

                try {
                    switch ( questChoice ) {
                        case 1 -> {
                            Quest01 quest01 = new Quest01( );
                            System.out.println( "Part 1: " + quest01.solvePart1( "inputs/quest01/input1.txt" ) );
                            System.out.println( "Part 2: " + quest01.solvePart2( "inputs/quest01/input2.txt" ) );
                            System.out.println( "Part 3: " + quest01.solvePart3( "inputs/quest01/input3.txt" ) );

                            while ( true ) {
                                System.out.println( "\nOptions:" );
                                System.out.println( "1 - Back to Main Menu" );
                                System.out.println( "0 - Exit" );
                                System.out.print( "Choose option: " );
                                int option = sc.nextInt( );
                                if ( option == 1 ) {
                                    for ( int i = 0; i < 20; i++ ) {
                                        System.out.println( " " );
                                    }
                                    break;
                                }
                                else if ( option == 0 ) {
                                    System.out.println( "Exiting..." );
                                    sc.close( );
                                    return;
                                } else System.out.println( "Invalid option!" );
                            }
                        }
                        case 0 -> {
                            System.out.println("Exiting...");
                            sc.close( );
                            return;
                        }
                        default -> System.out.println("Invalid Quest!");
                    }
                } catch ( Exception e ) {
                    e.printStackTrace( );
                }
            }
    }
}
