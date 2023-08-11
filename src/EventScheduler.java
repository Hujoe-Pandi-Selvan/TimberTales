import java.util.*;

/**
 * Keeps track of events that have been scheduled.
 */
public final class EventScheduler
{
    public PriorityQueue<Event> eventQueue;
    public Map<Entity, List<Event>> pendingEvents;
    public double timeScale;

    public EventScheduler(double timeScale) {
        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.pendingEvents = new HashMap<>();
        this.timeScale = timeScale;
    }

    public void updateOnTime(long time) {
        while (!this.eventQueue.isEmpty()
                && this.eventQueue.peek().time < time) {
            Event next = this.eventQueue.poll();

            removePendingEvent(next);

            next.action.executeAction(this);
        }
    }

    public void removePendingEvent(
            Event event)
    {
        List<Event> pending = this.pendingEvents.get(event.entity);

        if (pending != null) {
            pending.remove(event);
        }
    }

    public void unscheduleAllEvents(
            Entity entity)
    {
        List<Event> pending = this.pendingEvents.remove(entity);

        if (pending != null) {
            for (Event event : pending) {
                this.eventQueue.remove(event);
            }
        }
    }

    public void scheduleEvent(
            Entity entity,
            Action action,
            long afterPeriod)
    {
        long time = System.currentTimeMillis() + (long)(afterPeriod
                * this.timeScale);
        Event event = new Event(action, time, entity);

        this.eventQueue.add(event);

        // update list of pending events for the given entity
        List<Event> pending = this.pendingEvents.getOrDefault(entity,
                new LinkedList<>());
        pending.add(event);
        this.pendingEvents.put(entity, pending);
    }}

//    public void executeActivityAction(Action action)
//    {
////        switch (action.entity.kind) {
////            case SAPLING:
//                if(action.entity instanceof Sapling){
//                action.entity.executeSaplingActivity(action.world,
//                        action.imageStore, this);}
////                break;
//
////            case TREE:
//                if(action.entity instanceof Tree){
//                action.entity.executeTreeActivity(action.world,
//                        action.imageStore, this);}
////                break;
//
//           // case FAIRY:
//        if(action.entity instanceof Fairy){
//                action.entity.executeFairyActivity(action.world,
//                        action.imageStore, this);}
//               // break;
//
//            //case DUDE_NOT_FULL:
//        if(action.entity instanceof Dude_Not_Full){
//                action.entity.executeDudeNotFullActivity(action.world,
//                        action.imageStore, this);}
//                //break;
//
//            //case DUDE_FULL:
//        if(action.entity instanceof Dude_Full){
//                action.entity.executeDudeFullActivity(action.world,
//                        action.imageStore, this);}
//               // break;
//
//            //default:
//        else{
//                throw new UnsupportedOperationException(String.format(
//                        "executeActivityAction not supported for %s",
//                        action.entity.kind));}
//        }
//    }
//}
