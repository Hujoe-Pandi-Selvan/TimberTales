/**
 * An action that can be taken by an entity
 */
public interface Action // hint make an interface!
{
//    public ActionKind kind;
//    public Entity entity;
//    public WorldModel world;
//    public ImageStore imageStore;
//    public int repeatCount;
//
//    public Action(
//            ActionKind kind,
//            Entity entity,
//            WorldModel world,
//            ImageStore imageStore,
//            int repeatCount)
//    {
//        this.kind = kind;
//        this.entity = entity;
//        this.world = world;
//        this.imageStore = imageStore;
//        this.repeatCount = repeatCount;
//    }

//    public void executeAction(EventScheduler scheduler) {
//        switch (this.kind) {
//            case ACTIVITY:
//                this.executeActivityAction(scheduler);
//                break;
//
//            case ANIMATION:
//                this.executeAnimationAction(scheduler);
//                break;
//        }
//    }

    /// ACTIVITY - hint make a class!
    public void executeAction(
            EventScheduler scheduler);
//    {
////        switch (this.entity.Kind) {
////            case SAPLING:
//                if(this.entity instanceof Sapling ) {
//                            ((Sapling)this.entity).executeSaplingActivity(this.world,
//                        this.imageStore, scheduler);}
////                break;
//
////            case TREE:
//                if(this.entity instanceof Tree ){
//                    ((Tree)this.entity).executeTreeActivity(this.world,
//                        this.imageStore, scheduler);}
////                break;
//
////            case FAIRY:
//                if (this.entity instanceof Fairy ){
//                    ((Fairy)this.entity).executeFairyActivity(this.world,
//                        this.imageStore, scheduler);}
////                break;
//
////            case DUDE_NOT_FULL:
//        if (this.entity instanceof Dude_Not_Full ){
//            ((Dude_Not_Full)this.entity).executeFairyActivity(this.world,
//                    this.imageStore, scheduler);}
////                break;
//
////            case DUDE_FULL:
//        if (this.entity instanceof Dude_Full ){
//            ((Dude_Full)this.entity).executeFairyActivity(this.world,
//                    this.imageStore, scheduler);}
////                break;
//
//            else{
//                throw new UnsupportedOperationException(String.format(
//                        "executeActivityAction not supported for %s",
//                        this.entity.getClass()));}
//        }
//

    /// ACTIVITY - hint make a class!
//    public void executeAnimationAction(
//            EventScheduler scheduler);
//    {
//        Functions.nextImage(this.entity);
//
//        if (this.repeatCount != 1) {
//            Functions.scheduleEvent(scheduler, this.entity,
//                    Functions.createAnimationAction(this.entity,
//                            Math.max(this.repeatCount - 1,
//                                    0)),
//                    Functions.getAnimationPeriod(this.entity));
//        }
//    }
}
