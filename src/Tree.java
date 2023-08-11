import processing.core.PImage;

import java.util.List;

public  class Tree extends executable {
    public Tree(
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
    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {

        if (!this.transform(world, scheduler, imageStore)) {

            Functions.scheduleEvent(scheduler, this,
                    Functions.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }
    public boolean transformPlant(WorldModel world,
                                  EventScheduler scheduler,
                                  ImageStore imageStore) {

        return !this.transform(world, scheduler, imageStore);
    }
//            return this.transformSapling(world, scheduler, imageStore);
//        } else {
//            throw new UnsupportedOperationException(
//                    String.format("transformPlant not supported for %s", this));
//        }
//    }
    public boolean transform(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore) {

        if (this.health <= 0) {

            Entity stump = Functions.createStump(this.id,
                    this.position,
                    Functions.getImageList(imageStore, Functions.STUMP_KEY));

            world.removeEntity(this);
            Functions.unscheduleAllEvents(scheduler, this);

            world.addEntity(stump);

            return true;
        }

        return false;
    }


    public void scheduleActions(EventScheduler scheduler,
                               WorldModel world,
                               ImageStore imageStore){
        Functions.scheduleEvent(scheduler, this,
                Functions.createActivityAction(this, world, imageStore),
                this.actionPeriod);
        Functions.scheduleEvent(scheduler, this,
                Functions.createAnimationAction(this, 0),
                getAnimationPeriod());
    }


//    public int getAnimationPeriod(){
//        return this.animationPeriod;
//}
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }
//
//    @Override
//    public List<PImage> getImages() {
//        return this.images;
//    }
//    public PImage getCurrentImage(){return this.images.get(this.imageIndex);}
//
//    @Override
//    public int getImageIndex() {
//        return this.imageIndex;
//    }

//    @Override
//    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//
//    }
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

//    @Override
//    public void transformFull() {
//
//    }



}
