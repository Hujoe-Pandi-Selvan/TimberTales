public class Activity implements Action {


    public Entity entity;
    public WorldModel world;
    public ImageStore imageStore;
    public int repeatCount;
    public Activity(

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
//        switch (this.entity.Kind) {
//            case SAPLING:
        if(this.entity instanceof Sapling ) {
            ((Sapling)this.entity).executeActivity(this.world,
                    this.imageStore, scheduler);}
//                break;

//            case TREE:
        else if(this.entity instanceof Tree ){
            ((Tree)this.entity).executeActivity(this.world,
                    this.imageStore, scheduler);}
//                break;

//            case FAIRY:
        else if (this.entity instanceof Fairy ){
            ((Fairy)this.entity).executeActivity(this.world,
                    this.imageStore, scheduler);}
//                break;

//            case DUDE_NOT_FULL:
        else if (this.entity instanceof Dude_Not_Full ){
            ((Dude_Not_Full)this.entity).executeActivity(this.world,
                    this.imageStore, scheduler);
        }
//                break;

//            case DUDE_FULL:
        else if (this.entity instanceof Dude_Full ){
            ((Dude_Full)this.entity).executeActivity(this.world,
                    this.imageStore, scheduler);}
//                break;

        else{
            throw new UnsupportedOperationException(String.format(
                    "executeActivityAction not supported for %s",
                    this.entity.getClass()));}
    }


}
