//import processing.core.PImage;
//
//import java.awt.desktop.SystemEventListener;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class Dude_Full extends executable implements Entity {
//    public Dude_Full(
//            String id,
//            Point position,
//            List<PImage> images,
//            int resourceLimit,
//            int resourceCount,
//            int actionPeriod,
//            int animationPeriod,
//            int health,
//            int healthLimit) {
//        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod, health, healthLimit);
//
//    }
//
//    public void transformFull( // DudeFull
//                               WorldModel world,
//                               EventScheduler scheduler,
//                               ImageStore imageStore) {
//        Entity miner = Functions.createDudeNotFull(this.id,
//                this.position, this.actionPeriod,
//                this.animationPeriod,
//                this.resourceLimit,
//                this.images);
//
//        world.removeEntity(this);
//        Functions.unscheduleAllEvents(scheduler, this);
//
//        world.addEntity(miner);
//        miner.scheduleActions(scheduler, world, imageStore);
//    }
//
//    public void executeActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler) {
//        Optional<Entity> fullTarget =
//                Functions.findNearest(world, this.position, new ArrayList<>(Arrays.asList(House.class)));
//
//        if (fullTarget.isPresent()) {
////            System.out.println("HOUSE FOUND!");
//        }
//        if (fullTarget.isPresent() && moveTo(world,
//                fullTarget.get(), scheduler)) {
//            transformFull(world, scheduler, imageStore);
//        } else {
//            Functions.scheduleEvent(scheduler, this,
//                    Functions.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }
//
//
//    public boolean moveTo(   // Dude
//                             WorldModel world,
//                             Entity target,
//                             EventScheduler scheduler) {
//        if (Functions.adjacent(this.position, target.getPosition())) {
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
//
//}
import processing.core.PImage;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Dude_Full extends movable {
    public Dude_Full(
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

    public void transformFull( // DudeFull
                               WorldModel world,
                               EventScheduler scheduler,
                               ImageStore imageStore) {
        Entity miner = Functions.createDudeNotFull(this.id,
                this.position, this.actionPeriod,
                this.animationPeriod,
                this.resourceLimit,
                this.images);

        world.removeEntity(this);
        Functions.unscheduleAllEvents(scheduler, this);

        world.addEntity(miner);
        miner.scheduleActions(scheduler, world, imageStore);
    }
    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> fullTarget =
                Functions.findNearest(world, this.position, new ArrayList<>(Arrays.asList(House.class)));

        if(fullTarget.isPresent()) {
//            System.out.println("HOUSE FOUND!");
        }
        if (fullTarget.isPresent() && moveTo(world,
                fullTarget.get(), scheduler))
        {
            transformFull(world, scheduler, imageStore);
        }
        else {
            Functions.scheduleEvent(scheduler, this,
                    Functions.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }

//    @Override
//    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        return false;
//    }

//    @Override
//    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        return false;
//    }

    public boolean moveTo(   // Dude
                             WorldModel world,
                             Entity target,
                             EventScheduler scheduler) {
        if (Functions.adjacent(this.position, target.getPosition())) {
            return true;
        } else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.position.equals(nextPos)) {
                Optional<Entity> occupant = Functions.getOccupant(world, nextPos);
                if (occupant.isPresent()) {
                    Functions.unscheduleAllEvents(scheduler, occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

//    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.x -this.position.x);
        Point newPos = new Point(this.position.x+horiz,this.position.y);

        if (horiz==0||world.isOccupied(newPos)&& !(world.getOccupancyCell(newPos) instanceof Stump)){
            int vert = Integer.signum(destPos.y-this.position.y);
            newPos=new Point(this.position.x,this.position.y+vert);

            if(vert==0||world.isOccupied(newPos)&& ! (world.getOccupancyCell(newPos) instanceof Stump)){
                newPos=this.position;
            }
        }
        return newPos;
    }

//    @Override
//    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        return false;
//    }

//    @Override
//    public Point getPosition() {
//        return this.position;
//    }
//
//    @Override
//    public void setPosition(Point p) {
//        this.position= p;
//
//    }


//    public void loseHealthPoint() {
//        this.health--;
//
//    }



//    public void transformFull() {
//
//    }

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
//    }
//    public int getAnimationPeriod(){
//        return this.animationPeriod;
//    }
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }

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
//
//    public void setHealth(int n){
//        this.health=n;
//    }
//
//    @Override
//    public void loseHealthPoint() {
//        this.health--;
//    }
//
//    public int getHealth(){
//        return this.health;
//    }

//    public void transformFull() {
//
//    }
}
