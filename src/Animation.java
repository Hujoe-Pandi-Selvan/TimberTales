public class Animation implements Action{

    public int repeatCount;

    public Entity entity;
    public WorldModel world;
    public ImageStore imageStore;


    public Animation(

            Entity entity,
            WorldModel world,
            ImageStore imageStore,
            int repeatCount)
    {

        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }

    public void executeAction(
            EventScheduler scheduler)
    {
        this.entity.nextImage();

        if (this.repeatCount != 1) {
            Functions.scheduleEvent(scheduler, this.entity,
                    Functions.createAnimationAction(this.entity,
                            Math.max(this.repeatCount - 1,
                                    0)),
                    this.entity.getAnimationPeriod());
        }
    }
}

