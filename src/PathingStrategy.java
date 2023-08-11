import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface PathingStrategy {
    List<Point> computePath(Point start, Point end,
                            Predicate<Point> canPassThrough,
                            BiPredicate<Point, Point> withinReach,
                            Function<Point, Stream<Point>> potentialNeighbors);

    // lambda function that returns the North, East, South, West neighbors
    Function<Point, Stream<Point>> CARDINAL_NEIGHBORS =
            point ->
                    Stream.<Point>builder()
                            .add(new Point(point.x - 1, point.y)) // up
                            .add(new Point(point.x, point.y + 1)) // right
                            .add(new Point(point.x + 1, point.y)) // down
                            .add(new Point(point.x, point.y - 1)) // left
                            .build();
}
