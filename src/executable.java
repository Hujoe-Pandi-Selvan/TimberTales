import processing.core.PImage;

import java.util.List;

public abstract class executable extends scheduler {
    public executable(
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
    public  void scheduleActions(EventScheduler scheduler,
                                WorldModel world,
                                ImageStore imageStore){
        Functions.scheduleEvent(scheduler, this,
                Functions.createActivityAction(this, world, imageStore),
                this.actionPeriod);
        Functions.scheduleEvent(scheduler, this,
                Functions.createAnimationAction(this, 0),
                getAnimationPeriod());
    }
    public abstract void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler);
}
