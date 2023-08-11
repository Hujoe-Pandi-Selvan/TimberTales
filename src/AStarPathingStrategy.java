import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AStarPathingStrategy implements PathingStrategy{

    public  List<Point> computePath(Point start, Point end, Predicate<Point> canPassThrough, BiPredicate<Point, Point> withinReach, Function<Point, Stream<Point>> potentialNeighbors) {


            // Initialize the open and closed sets
            Set<Point> openSet = new HashSet<>();
            Set<Point> closedSet = new HashSet<>();
            Map<Point, Point> cameFrom = new HashMap<>();

            // Initialize the start node and add it to the open set
            Map<Point, Double> gScore = new HashMap<>();
            gScore.put(start, 0.0);
            Map<Point, Double> fScore = new HashMap<>();
            fScore.put(start, heuristicCostEstimate(start, end));
            List<Point> expectedPath = Arrays.asList();
            openSet.add(start);
            if ( start.equals(end)){
                return expectedPath;
            }
            while (!openSet.isEmpty()) {
                // Get the node with the lowest fScore value
                Point current = openSet.stream()
                        .min(Comparator.comparingDouble(fScore::get))
                        .orElseThrow();
                System.out.println(current.toString());
                // If we have reached the end node, return the path
                if (withinReach.test(current, end)) {
                    return reconstructPath(cameFrom, current);
                }

                // Move the current node from the open set to the closed set
                openSet.remove(current);
                closedSet.add(current);

                // Check the current node's neighbors
                potentialNeighbors.apply(current)
                        .filter(canPassThrough)
                        .filter(neighbor -> !closedSet.contains(neighbor))
                        .forEach(neighbor -> {
                            double tentativeGScore = gScore.getOrDefault(current, Double.MAX_VALUE) + distanceBetween(current, neighbor);
                            if (!openSet.contains(neighbor) || tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                                cameFrom.put(neighbor, current);
                                gScore.put(neighbor, tentativeGScore);
                                fScore.put(neighbor, tentativeGScore + heuristicCostEstimate(neighbor, end));
                                if (!openSet.contains(neighbor)) {
                                    openSet.add(neighbor);
                                }
                            }
                        });
            }

            // If there is no path, return an empty list
            return Collections.emptyList();
        }

        public static List<Point> reconstructPath(Map<Point, Point> cameFrom, Point current) {
            List<Point> path = new ArrayList<>();
            path.add(current);
            while (cameFrom.containsKey(current)) {
                current = cameFrom.get(current);
                path.add(0, current);
            }
            return path;
        }

        public static double distanceBetween(Point a, Point b) {
            // Calculate the Euclidean distance between two points
            return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        }

        public  static double heuristicCostEstimate(Point start, Point end) {
            // Use the Euclidean distance as the heuristic cost estimate
            return distanceBetween(start, end);
        }




    // TODO implement your heuristic
    public static double getHeuristic(Point p1, Point p2){
        double distance = 0.0;
        return distance;
    }

    public static double getManhattanDistance(Point p1, Point p2) {
        final int dx = p1.x - p2.x;
        final int dy = p1.y - p2.y;
        return Math.abs(dx) + Math.abs(dy);
    }

}
