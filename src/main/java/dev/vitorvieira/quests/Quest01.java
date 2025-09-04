package dev.vitorvieira.quests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Quest01 {

    private List<List<Character>> map = new ArrayList<>( );
    private List<String> sequences = new ArrayList<>( );

    private void parseFile( String filePath ) throws IOException {
        map.clear( );
        sequences.clear( );

        List<String> lines = Files.readAllLines( Paths.get( filePath ) );

        boolean readingMap = true;
        for ( String line : lines ) {
            if ( line.isEmpty( ) ) {
                readingMap = false;
                continue;
            }

            if ( readingMap ) {
                List<Character> row = new ArrayList<>( );
                for ( char c : line.toCharArray( ) ) {
                    row.add( c );
                }
                map.add( row );
            } else {
                sequences.add( line.trim( ) );
            }
        }
    }


    private int simulateToken( String sequence, int startSlotIndex ) {
        char[] moves = sequence.toCharArray( );

        final int H = map.size( );
        final int W = map.get( 0 ).size( );

        int x = startSlotIndex * 2;
        int y = 0;
        int mi = 0;

        while ( true ) {
            if ( map.get( y ).get( x ) == '*' ) {
                char mv = ( mi < moves.length ) ? moves[mi++] : 'L';

                if ( x == 0 ) {
                    x = 1;
                } else if ( x == W - 1 ) {
                    x = W - 2;
                } else {
                    x += ( mv == 'L' ) ? -1 : 1;
                }
            }

            if ( y == H - 1 ) break;

            y += 1;
        }

        int finalSlot = ( x / 2 ) + 1;
        int tossSlot  = startSlotIndex + 1;
        int coins = ( finalSlot * 2 ) - tossSlot;
        return Math.max( coins, 0 );
    }

    public int solvePart1( String filePath ) throws IOException {
        parseFile( filePath );

        int total = 0;
        for ( int i = 0; i < sequences.size( ); i++ ) {
            int score = simulateToken( sequences.get( i ), i );
            total += score;
        }

        return total;
    }

    public int solvePart2( String filePath ) throws IOException {
        parseFile( filePath );

        int total = 0;
        int numSlots = ( map.get(0).size( ) + 1 ) / 2;

        for ( int i = 0; i < sequences.size( ); i++ ) {
            int best = 0;
            for ( int j = 0; j < numSlots; j++ ) {
                best = Math.max(best, simulateToken(sequences.get(i), j));
            }
            total += best;
        }

        return total;
    }


    public String solvePart3( String filePath ) throws IOException {
        parseFile( filePath );

        int numTokens = sequences.size( );
        int numSlots = ( map.get( 0 ).size( ) + 1 ) / 2;
        int[][] coins = new int[numTokens][numSlots];

        for ( int t = 0; t < numTokens; t++ ) {
            for ( int s = 0; s < numSlots; s++ ) {
                coins[t][s] = simulateToken( sequences.get( t ), s );
            }
        }

        final int[] maxCoins = { 0 };
        final int[] minCoins = { Integer.MAX_VALUE };

        boolean[] usedSlots = new boolean[numSlots];

        dfsPart3( 0, numTokens, numSlots, coins, usedSlots, 0, maxCoins, minCoins );

        return minCoins[0] + " " + maxCoins[0];
    }

    private void dfsPart3( int tokenIndex, int numTokens, int numSlots, int[][] coins,
                          boolean[] usedSlots, int sumSoFar, int[] maxCoins, int[] minCoins ) {
        if ( tokenIndex == numTokens ) {
            maxCoins[0] = Math.max(maxCoins[0], sumSoFar);
            minCoins[0] = Math.min(minCoins[0], sumSoFar);
            return;
        }

        for ( int s = 0; s < numSlots; s++ ) {
            if ( !usedSlots[s] ) {
                usedSlots[s] = true;
                dfsPart3( tokenIndex + 1, numTokens, numSlots, coins, usedSlots,
                        sumSoFar + coins[tokenIndex][s], maxCoins, minCoins );
                usedSlots[s] = false;
            }
        }
    }
}
