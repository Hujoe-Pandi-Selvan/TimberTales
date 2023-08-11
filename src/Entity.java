import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public abstract class Entity // hint make entity an interface!
{
    public EntityKind kind;
    public String id;
    public Point position;
    public List<PImage> images;
    public int imageIndex;
    public int resourceLimit;
    public int resourceCount;
    public int actionPeriod;
    public int animationPeriod;
    public int health;
    public int healthLimit;
    public Entity(
            String id,
            Point position,
            List<PImage> images,
            int resourceLimit,
            int resourceCount,
            int actionPeriod,
            int animationPeriod,
            int health,
            int healthLimit) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
        this.health = health;
        this.healthLimit = healthLimit;
    }
    private static final int KEYED_RED_IDX = 2;
    private static final int KEYED_GREEN_IDX = 3;
    private static final int KEYED_BLUE_IDX = 4;

    public abstract Point getPosition();
    public abstract void setPosition(Point p);
    public abstract int getHealth();
    public abstract void setHealth(int n);
   public abstract void loseHealthPoint(); // do health-- on object
    public abstract int getAnimationPeriod();
    public abstract void nextImage();
    public abstract List<PImage> getImages();
    public abstract int getImageIndex();

    public abstract PImage getCurrentImage();

    public abstract void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore) ;//{

//    public abstract void executeActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler);

//    public abstract boolean transform(
//            WorldModel world,
//            EventScheduler scheduler,
//          ImageStore imageStore);

//    public abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);


//    public abstract Point nextPosition(WorldModel world, Point destPos);


    public abstract boolean transformPlant(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore);


}