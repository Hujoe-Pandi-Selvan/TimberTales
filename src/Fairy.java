import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Fairy extends movable  {
    public Fairy(
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
    public Point nextPosition(
            WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.x - this.position.x);
        Point newPos = new Point(this.position.x + horiz, this.position.y);

        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.y - this.position.y);
            newPos = new Point(this.position.x, this.position.y + vert);

            if (vert == 0 || world.isOccupied(newPos)) {
                newPos = this.position;
            }
        }
        return newPos;
    }

//    @Override
//    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        return false;
//    }

    public boolean moveTo(
            WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.position.adjacent(target.getPosition())) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        } else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.position.equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }


//    @Override
//    public void transformFull() {
//
//    }

//    @Override
//    public Point getPosition() {
//        return this.position;
//    }
//
//    @Override
//    public void setPosition(Point p) {
//        this.position=p;
//
//    }
//
//    @Override
//    public int getHealth() {
//        return 0;
//    }
//
//    @Override
//    public void setHealth(int n) {
//
//    }
//
//    @Override
//    public void loseHealthPoint() {
//        this.health--;
//    }


//    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
//
//    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        Optional<Entity> fairyTarget =
                world.findNearest(this.position, new ArrayList<>(Arrays.asList(Stump.class)));

        if (fairyTarget.isPresent()) {
//            System.out.println("FOUND A STUMP! " + fairyTarget.get().getPosition());
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {
                Entity sapling = tgtPos.createSapling("sapling_" + this.id,
                        imageStore.getImageList(Functions.SAPLING_KEY));

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                Functions.createActivityAction(this,world, imageStore),
                this.actionPeriod);
    }

//    @Override
//    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//return false;
//    }

//    @Override
//    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        return false;
//    }


//    @Override
//    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
//        return false;
//    }

    //    public void executeActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler) {
//        Optional<Entity> fairyTarget =
//                Functions.findNearest(world, this.position, new ArrayList<>(Arrays.asList(EntityKind.STUMP)));
//
//        if (fairyTarget.isPresent()) {
//            Point tgtPos = fairyTarget.get().position;
//
//            if (this.moveToFairy(world, fairyTarget.get(), scheduler)) {
//                Entity sapling = Functions.createSapling("sapling_" + this.id, tgtPos,
//                        Functions.getImageList(imageStore, Functions.SAPLING_KEY));
//
//                world.addEntity(sapling);
//                sapling.scheduleActions(scheduler, world, imageStore);
//            }
//        }
//
//        Functions.scheduleEvent(scheduler, this,
//                Functions.createActivityAction(this, world, imageStore),
//                this.actionPeriod);
//    }
//    public boolean moveTo(
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler) {
//        if (Functions.adjacent(this.position, target.getPosition())) {
//            world.removeEntity(target);
//            Functions.unscheduleAllEvents(scheduler, target);
//            return true;
//        } else {
//            Point nextPos = this.nextPosition(world, target.getPosition());
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = Functions.getOccupant(world, nextPos);
//                if (occupant.isPresent()) {
//                    Functions.unscheduleAllEvents(scheduler, occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//    }
//    public Point nextPosition(
//            WorldModel world, Point destPos) {
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        Point newPos = new Point(this.position.x + horiz, this.position.y);
//
//        if (horiz == 0 || world.isOccupied(newPos)) {
//            int vert = Integer.signum(destPos.y - this.position.y);
//            newPos = new Point(this.position.x, this.position.y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    }

    //    public Point nextPosition(
//            WorldModel world, Point destPos) {
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        Point newPos = new Point(this.position.x + horiz, this.position.y);
//
//        if (horiz == 0 || world.isOccupied(newPos) && Functions.getOccupancyCell(world, newPos).kind != EntityKind.STUMP) {
//            int vert = Integer.signum(destPos.y - this.position.y);
//            newPos = new Point(this.position.x, this.position.y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos) && Functions.getOccupancyCell(world, newPos).kind != EntityKind.STUMP) {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    public void scheduleActions(
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore) {
//        Functions.scheduleEvent(scheduler, this,
//                Functions.createActivityAction(this, world, imageStore),
//                this.actionPeriod);
//        Functions.scheduleEvent(scheduler, this,
//                Functions.createAnimationAction(this, 0),
//                getAnimationPeriod());
//
//    }

//    public int getAnimationPeriod() {
//        return this.animationPeriod;
//    }
//
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }
//
//    @Override
//    public List<PImage> getImages() {
//        return this.images;
//    }
//
//    @Override
//    public int getImageIndex() {
//        return this.imageIndex;
//    }
//    public PImage getCurrentImage(){return this.images.get(this.imageIndex);}


//    @Override
//    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//
//    }

}

