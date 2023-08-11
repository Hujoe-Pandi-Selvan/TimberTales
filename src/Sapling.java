import processing.core.PImage;

import java.util.List;

public class Sapling extends executable  {
    public Sapling(
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
    public boolean transformSapling(
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
        } else if (this.health >= this.healthLimit) {
            Tree tree = Functions.createTree("tree_" + this.id,
                    this.position,
                    Functions.getNumFromRange(Functions.TREE_ACTION_MAX, Functions.TREE_ACTION_MIN),
                    Functions.getNumFromRange(Functions.TREE_ANIMATION_MAX, Functions.TREE_ANIMATION_MIN),
                    Functions.getNumFromRange(Functions.TREE_HEALTH_MAX, Functions.TREE_HEALTH_MIN),
                    Functions.getImageList(imageStore, Functions.TREE_KEY));

            world.removeEntity(this);
            Functions.unscheduleAllEvents(scheduler, this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;

    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        this.health++;
        if (!this.transformPlant(world, scheduler, imageStore)) {
            Functions.scheduleEvent(scheduler, this,
                    Functions.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }


    public boolean transformPlant(WorldModel world,
                                  EventScheduler scheduler,
                                  ImageStore imageStore) {return this.transformSapling(world, scheduler, imageStore);}


}

