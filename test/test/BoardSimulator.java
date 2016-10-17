package test;

import static java.util.Objects.requireNonNull;
import static java.util.Spliterators.spliteratorUnknownSize;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import task.CellLocation;
import task.CellState;
import task.GameBoard;

public class BoardSimulator {

    public static GameBoard board(String file) {
        List<String> lines = linesOf(file);
        return field -> {
            Coordinate coord = FIELD_COORDINATE.get(requireNonNull(field));
            switch (lines.get(coord.y).charAt(coord.x)) {
            case 'x':
                return CellState.OCCUPIED_BY_X;
            case 'o':
                return CellState.OCCUPIED_BY_O;
            default:
                return CellState.EMPTY;
            }
        };
    }

    private static List<String> linesOf(String file) {
        Scanner boardScanner = new Scanner(ConsultantTest.class.getResourceAsStream(file + ".txt"));
        return StreamSupport.stream(spliteratorUnknownSize(boardScanner, 0), false)
                .collect(Collectors.toList());
    }

    private static final Map<CellLocation, Coordinate> FIELD_COORDINATE = new EnumMap<>(CellLocation.class);
    static {
        FIELD_COORDINATE.put(CellLocation.TOP_LEFT, Coordinate.of(0, 0));
        FIELD_COORDINATE.put(CellLocation.TOP_CENTRE, Coordinate.of(1, 0));
        FIELD_COORDINATE.put(CellLocation.TOP_RIGHT, Coordinate.of(2, 0));

        FIELD_COORDINATE.put(CellLocation.CENTRE_LEFT, Coordinate.of(0, 1));
        FIELD_COORDINATE.put(CellLocation.CENTRE_CENTRE, Coordinate.of(1, 1));
        FIELD_COORDINATE.put(CellLocation.CENTRE_RIGHT, Coordinate.of(2, 1));

        FIELD_COORDINATE.put(CellLocation.BOTTOM_LEFT, Coordinate.of(0, 2));
        FIELD_COORDINATE.put(CellLocation.BOTTOM_CENTRE, Coordinate.of(1, 2));
        FIELD_COORDINATE.put(CellLocation.BOTTOM_RIGHT, Coordinate.of(2, 2));
    }

    private static final class Coordinate {
        private static final Coordinate[] VALUES = {
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(0, 2),
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(2, 0),
                new Coordinate(2, 1),
                new Coordinate(2, 2) };

        final int x;
        final int y;

        private Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Coordinate of(int x, int y) {
            return VALUES[x * 3 + y];
        }
    }

}
