//import processing.core.PImage;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class Dude_Not_Full extends movable implements Entity {
//    public Dude_Not_Full(
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
//    public void executeActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler) {
//        Optional<Entity> target =
//                Functions.findNearest(world, this.position, new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));
//
//        if (!target.isPresent() || !this.moveTo(world,
//                target.get(),
//                scheduler)
//                || !this.transformNotFull(world, scheduler, imageStore)) {
//            Functions.scheduleEvent(scheduler, this,
//                    Functions.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }
//
//    public boolean moveTo( // DudeNotFull
//                           WorldModel world,
//                           Entity target,
//                           EventScheduler scheduler) {
//
//
//        if (Functions.adjacent(this.position, target.getPosition())) {
//            this.resourceCount += 1;
//            //System.out.println("BEFORE: "+target.getHealth());
//            target.loseHealthPoint();
//            //System.out.println("AFTER: "+target.getHealth());
//            //target.setHealth(target.getHealth()-1);
////            target.health--;
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
//    public boolean transformNotFull( // DudeNotFull
//                                     WorldModel world,
//                                     EventScheduler scheduler,
//                                     ImageStore imageStore) {
//        if (this.resourceCount >= this.resourceLimit) {
//            Entity miner = Functions.createDudeFull(this.id,
//                    this.position, this.actionPeriod,
//                    this.animationPeriod,
//                    this.resourceLimit,
//                    this.images);
//
//            world.removeEntity(this);
//            Functions.unscheduleAllEvents(scheduler, this);
//
//            world.addEntity(miner);
//            miner.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }
//}
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Dude_Not_Full extends movable  {
    public Dude_Not_Full(
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
    //    public void executeActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> fullTarget =
//                Functions.findNearest(world, this.position, new ArrayList<>(Arrays.asList(EntityKind.HOUSE)));
//
//        if (fullTarget.isPresent() && moveToFull(world,
//                fullTarget.get(), scheduler))
//        {
//            transformFull(world, scheduler, imageStore);
//        }
//        else {
//            Functions.scheduleEvent(scheduler, this,
//                    Functions.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }
    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        Optional<Entity> target =
                Functions.findNearest(world, this.position, new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (!target.isPresent() || !this.moveTo(world,
                target.get(),
                scheduler)
                || !this.transformNotFull(world, scheduler, imageStore)) {
            Functions.scheduleEvent(scheduler, this,
                    Functions.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }

//    @Override
//    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        return false;
//    }

//    public boolean transform( // DudeNotFull
//                                     WorldModel world,
//                                     EventScheduler scheduler,
//                                     ImageStore imageStore) {
//        if (this.resourceCount >= this.resourceLimit) {
//            Entity miner = Functions.createDudeFull(this.id,
//                    this.position, this.actionPeriod,
//                    this.animationPeriod,
//                    this.resourceLimit,
//                    this.images);
//
//            world.removeEntity(this);
//            Functions.unscheduleAllEvents(scheduler, this);
//
//            world.addEntity(miner);
//            miner.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }

    @Override
//    public boolean moveTo(WorldModel world, EventScheduler scheduler) {
//        return false;
//    }

    public boolean moveTo( // DudeNotFull
                           WorldModel world,
                           Entity target,
                           EventScheduler scheduler) {


        if (Functions.adjacent(this.position, target.getPosition())) {
            this.resourceCount += 1;
            //System.out.println("BEFORE: "+target.getHealth());
            target.loseHealthPoint();
            //System.out.println("AFTER: "+target.getHealth());
            //target.setHealth(target.getHealth()-1);
//            target.health--;
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
//
//    @Override
//    public Point getPosition() {
//        return this.position;
//    }
//
//    @Override
//    public void setPosition(Point p) {
//        this.position = p;
//    }
//
//    @Override
//    public void loseHealthPoint() {
//        this.health--;
//
//    }
//    public void setHealth(int n){
//        this.health=n;
//    }
//
//
//    public int getHealth(){
//        return this.health;
//    }
//
//

    public boolean transformNotFull( // DudeNotFull
                                     WorldModel world,
                                     EventScheduler scheduler,
                                     ImageStore imageStore) {
        if (this.resourceCount >= this.resourceLimit) {
            Entity miner = Functions.createDudeFull(this.id,
                    this.position, this.actionPeriod,
                    this.animationPeriod,
                    this.resourceLimit,
                    this.images);

            world.removeEntity(this);
            Functions.unscheduleAllEvents(scheduler, this);

            world.addEntity(miner);
            miner.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

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
//
//    @Override
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//
//    }
//
//    @Override
//    public List<PImage> getImages() {
//        return getImages();
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
