import processing.core.PImage;

import java.util.List;

public abstract class scheduler extends Entity {
//    public EntityKind kind;
//    public String id;
//    public Point position;
//    public List<PImage> images;
//    public int imageIndex;
//    public int resourceLimit;
//    public int resourceCount;
//    public int actionPeriod;
//    public int animationPeriod;
//    public int health;
//    public int healthLimit;
//    private static final int KEYED_RED_IDX = 2;
//    private static final int KEYED_GREEN_IDX = 3;
//    private static final int KEYED_BLUE_IDX = 4;

    public scheduler(
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
    public Point getPosition() {
        return this.position;
    }
    public void setPosition(Point p) {
        this.position=p;

    }

    public int getHealth() {
        return 0;
    }


    public void setHealth(int n) {

    }


    public void loseHealthPoint() {
        this.health--;

    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }


    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        return false;
    }




    public boolean moveTo(WorldModel world, Entity entity, EventScheduler scheduler) {
        return false;
    }


    public Point nextPosition(WorldModel world, Point destPos) {
        return null;
    }


    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        return false;
    }



    public int getAnimationPeriod(){
        return this.animationPeriod;
    }
    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }


    public List<PImage> getImages() {
        return this.images;
    }


    public int getImageIndex() {
        return this.imageIndex;
    }
    public PImage getCurrentImage(){return this.images.get(this.imageIndex);}
    public abstract void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore) ;

}

