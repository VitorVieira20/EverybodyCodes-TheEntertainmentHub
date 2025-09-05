package dev.vitorvieira.quests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Quest02 {
    private List<String> balloons;
    private String[] fluffbolts = { "R", "G", "B" };

    private void parseFile( String filePath ) throws IOException {
        List<String> lines = Files.readAllLines( Paths.get( filePath ) );
        balloons = new ArrayList<>( Arrays.asList( lines.get( 0 ).split( "(?!^)" ) ) );
    }


    private List<String> createBallonsCircle( List<String> balloons, int n ) {
        List<String> balloonsCircle = new ArrayList<>( );

        for ( int i = 0; i < n; i++ ) {
            for ( String ballon: balloons ) {
                balloonsCircle.add( ballon );
            }
        }

        return balloonsCircle;
    }


    private String createBallonsCircleString( List<String> balloons, int n ) {
        StringBuilder sb = new StringBuilder( );
        for ( int i = 0; i < n; i++ ) {
            for ( String b : balloons ) {
                sb.append( b );
            }
        }

        return sb.toString( );
    }


    public int solvePart1( String filePath ) throws IOException {
        parseFile( filePath );

        int fluffboltIdx = 0;
        int rounds = 0;

        while ( !balloons.isEmpty( ) ) {
            String fluffbolt = fluffbolts[fluffboltIdx];

            while ( !balloons.isEmpty( ) && balloons.get( 0 ).equals( fluffbolt ) ) {
                balloons.remove (0 );
            }

            if ( !balloons.isEmpty( ) && !balloons.get( 0 ).equals( fluffbolt ) ) {
                balloons.remove( 0 );
            }

            rounds++;
            fluffboltIdx = ( fluffboltIdx + 1 ) % fluffbolts.length;
        }

        return rounds;
    }

    public int solvePart2( String filePath, int n ) throws IOException {
        parseFile( filePath );

        List<String> balloonsCircle = createBallonsCircle( balloons, n );

        int fluffboltIdx = 0;
        int rounds = 0;

        while ( !balloonsCircle.isEmpty( ) ) {
            String fluffbolt = fluffbolts[fluffboltIdx];

            if ( balloonsCircle.get( 0 ).equals( fluffbolt ) ) {
                if ( balloonsCircle.size( ) % 2 == 0 ) {
                    int middleIdx = balloonsCircle.size( ) / 2;
                    balloonsCircle.remove( middleIdx );
                }
            }

            balloonsCircle.remove( 0 );

            rounds++;
            fluffboltIdx = ( fluffboltIdx + 1 ) % fluffbolts.length;
        }


        return rounds;
    }


    public int solvePart3( String filePath, int n ) throws IOException {
        parseFile( filePath );

        String balloonsCircle = createBallonsCircleString( balloons, n );
        int half = balloonsCircle.length( ) / 2;


        Deque<String> left = new ArrayDeque<>( );
        Deque<String> right = new ArrayDeque<>( );

        for ( int i = 0; i < half; i++ ) left.addLast( String.valueOf( balloonsCircle.charAt( i ) ) );
        for ( int i = half; i < balloonsCircle.length( ); i++ ) right.addLast( String.valueOf( balloonsCircle.charAt( i ) ) );

        int rounds = 0;
        int fluffboltIdx = 0;

        while ( !left.isEmpty( ) || !right.isEmpty( ) ) {
            String fluffbolt = fluffbolts[fluffboltIdx];

            if ( left.size( ) != right.size( ) ) {
                left.pollFirst( );
            } else if ( left.peekFirst( ).equals( fluffbolt ) ) {
                left.pollFirst( );
                right.pollFirst( );
            } else {
                left.pollFirst( );
                left.addLast( right.pollFirst( ) );
            }

            rounds++;
            fluffboltIdx = ( fluffboltIdx + 1 ) % fluffbolts.length;
        }

        return rounds;
    }
}
