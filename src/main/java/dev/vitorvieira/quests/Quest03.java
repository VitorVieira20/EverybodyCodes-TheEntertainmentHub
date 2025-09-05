package dev.vitorvieira.quests;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Quest03 {

    private static class Die {
        public int id;
        public int[] faces;
        public int seed;
        public List<Integer> sequence;

        private long pulse;
        private long rollNumber;
        private int currentIndex;


        public Die( int id, int[] faces, int seed ) {
            this.id = id;
            this.faces = faces;
            this.seed = seed;
            this.pulse = seed;
            this.rollNumber = 1;
            this.currentIndex = 0;
            this.sequence = null;
        }

        public int roll( ) {
            long spin = rollNumber * pulse;
            currentIndex = ( int ) ( ( currentIndex + spin ) % faces.length );
            int result = faces[currentIndex];

            if ( sequence != null && !sequence.isEmpty( ) && sequence.get( 0 ) == result ) {
                sequence.remove( 0 );
            }

            pulse = pulse + spin;
            pulse = pulse % seed;
            pulse = pulse + 1 + rollNumber + seed;

            rollNumber++;
            return result;
        }


        public void setSequence( List<Integer> sequence ) {
            this.sequence = sequence;
        }


        public boolean isFinished( ) {
            return sequence.isEmpty( );
        }
    }


    List<Die> dice = new ArrayList<>( );
    List<Integer> sequence;

    private void parseFile( String filePath ) throws IOException {
        dice.clear( );
        List<String> lines = Files.readAllLines( Paths.get( filePath ) );

        for ( String line : lines ) {
            line = line.trim( );
            if ( line.isEmpty( ) ) continue;

            if ( line.contains( "faces=" ) && line.contains( "seed=" ) ) {
                String[] parts = line.split( ":" );
                int id = Integer.parseInt(parts [0].trim( ) );

                String rest = parts[1].trim( );
                String facesPart = rest.substring( rest.indexOf( "[" ) + 1, rest.indexOf( "]" ) );
                String[] facesStr = facesPart.split( "," );
                int[] faces = new int[facesStr.length];
                for ( int i = 0; i < facesStr.length; i++ ) {
                    faces[i] = Integer.parseInt( facesStr[i].trim( ) );
                }

                String seedPart = rest.substring( rest.lastIndexOf( "=" ) + 1 ).trim( );
                int seed = Integer.parseInt( seedPart );

                dice.add( new Die( id, faces, seed ) );
            } else {
                sequence = new ArrayList<>( );
                for ( char c : line.toCharArray( ) ) {
                    //System.out.println( "Value: " + Character.getNumericValue( c ) );
                    sequence.add( Character.getNumericValue( c ) );
                }

                for ( Die die : dice ) {
                    die.setSequence( new ArrayList<>( sequence ) );
                }
            }
        }

    }


    public List<List<Integer>> parseGrid( String filePath ) throws IOException {
        List<String> lines = Files.readAllLines( Paths.get( filePath ) );
        List<List<Integer>> grid = new ArrayList<>( );

        for ( String line : lines ) {
            line = line.trim( );
            if ( line.isEmpty( ) ) continue;

            if ( line.contains( "faces=" ) && line.contains( "seed=" ) ) continue;

            List<Integer> row = new ArrayList<>( );
            for ( char c : line.toCharArray( ) ) {
                if ( Character.isDigit( c ) ) {
                    row.add( Character.getNumericValue( c ) );
                }
            }
            grid.add( row );
        }

        return grid;
    }


    public int solvePart1( String filePath ) throws IOException {
        parseFile( filePath );

        int goal = 10000;
        int totalPoints = 0;
        int rollNumber = 0;

        while ( totalPoints < goal ) {
            int rollPoints = 0;

            for ( Die die : dice ) {
                rollPoints += die.roll( );
            }

            totalPoints += rollPoints;
            rollNumber++;
        }

        return rollNumber;
    }


    public String solvePart2( String filePath ) throws IOException {
        parseFile( filePath );

        int finished = 0;
        int diceSize = dice.size( );
        List<Integer> finishOrder = new ArrayList<>( );

        while ( finished < diceSize ) {
            List<Die> toRemove = new ArrayList<>( );

            for ( Die die : dice ) {
                if ( die.isFinished() ) {
                    finishOrder.add( die.id );
                    finished++;
                    toRemove.add( die );
                }
            }

            dice.removeAll( toRemove );

            for ( Die die : dice ) {
                die.roll( );
            }
        }

        StringBuilder sb = new StringBuilder( );

        for ( int i = 0; i < finishOrder.size( ); i++ ) {
            sb.append( finishOrder.get( i ) );
            if ( i < finishOrder.size( ) - 1 ) {
                sb.append( "," );
            }
        }

        return sb.toString( );
    }


    public int solvePart3( String filePath ) throws IOException {
        parseFile(filePath);

        List<List<Integer>> gridList = parseGrid( filePath );
        int rows = gridList.size( );
        int cols = gridList.get( 0 ).size( );

        int[][] grid = new int[rows][cols];
        for ( int r = 0; r < rows; r++ ) {
            for ( int c = 0; c < cols; c++ ) {
                grid[r][c] = gridList.get( r ).get( c );
            }
        }

        Set<Point> aggregate = new HashSet<>( );

        for ( Die die : dice ) {
            int value = die.roll( );

            Set<Point> possible = new HashSet<>( );
            for ( int r = 0; r < rows; r++ ) {
                for ( int c = 0; c < cols; c++ ) {
                    if ( grid[r][c] == value ) possible.add( new Point( r, c ) );
                }
            }
            aggregate.addAll( possible );

            while ( !possible.isEmpty( ) ) {
                value = die.roll( );
                Set<Point> nextSet = new HashSet<>( );
                for ( Point p : possible ) {
                    int r = p.x;
                    int c = p.y;

                    int[][] deltas = {{0,0},{1,0},{-1,0},{0,1},{0,-1}};
                    for ( int[] d : deltas ) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if ( nr < 0 || nc < 0 || nr >= rows || nc >= cols ) continue;
                        if ( grid[nr][nc] != value ) continue;
                        nextSet.add( new Point (nr, nc ) );
                    }
                }
                possible = nextSet;
                aggregate.addAll( possible );
            }
        }

        return aggregate.size( );
    }
}
