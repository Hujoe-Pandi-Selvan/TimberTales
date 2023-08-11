import processing.core.PImage;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract  class movable extends executable  {
    public movable(
            String id,
            Point position,
            List<PImage> images,
            int resourceLimit,
            int resourceCount,
            int actionPeriod,
            int animationPeriod,
            int health,
            int healthLimit) {
        super(id,position,images,resourceLimit,resourceCount,actionPeriod,animationPeriod,health,healthLimit);

    }

    public abstract boolean moveTo(
                             WorldModel world,
                             Entity target,
                             EventScheduler scheduler);
//    public Point nextPosition(WorldModel world, Point destPos){
//
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        Point newPos = new Point(this.position.x + horiz, this.position.y);
//
//        if (horiz == 0 || world.isOccupied(newPos) && !(world.getOccupancyCell(newPos) instanceof Stump)) {
//            int vert = Integer.signum(destPos.y - this.position.y);
//            newPos = new Point(this.position.x, this.position.y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos) && !(world.getOccupancyCell(newPos) instanceof Stump)) {
//                newPos = this.position;
//            }
//        }
//        return newPos;
//    }
    public Point nextPosition(WorldModel world, Point destPos) {
        Predicate<Point> canPassThrough = (point) -> {if (world.withinBounds(point)|| !world.isOccupied(point)
                || world.getOccupancyCell(point) instanceof Stump){
            return false;
        }
        return true;};
        BiPredicate<Point, Point> withinReach = Point::equals;
//        Function<Point, Stream<Point>> potentialNeighbors = point -> Stream.of(
//                new Point(point.x + 1, point.y),
//                new Point(point.x - 1, point.y),
//                new Point(point.x, point.y + 1),
//                new Point(point.x, point.y - 1)
//        );
        AStarPathingStrategy alg = new AStarPathingStrategy();
        List<Point> path = alg.computePath(this.position, destPos, canPassThrough, withinReach, PathingStrategy.CARDINAL_NEIGHBORS);

        if (path.isEmpty()) {
            // No valid path found, stay in current position
            System.out.println("no Path");
            return this.position;
        }

        // The next position is the second point in the path (the first is the current position)
//        return path.get(1);
        return path.size() > 1 ? path.get(1) : destPos;
    }

}
