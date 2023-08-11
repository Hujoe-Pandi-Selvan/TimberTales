import processing.core.PImage;

import java.util.List;

public class Stump extends scheduler{
    public Stump(
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

//    @Override
//    public void transformFull() {
//
//    }



//    @Override
//    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
//        return false;
//    }


//    public boolean moveTo(WorldModel world,  EventScheduler scheduler) {
//        return false;
//    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
//        int horiz = Integer.signum(destPos.x -this.position.x);
//        Point newPos = new Point(this.position.x+horiz,this.position.y);
//
//        if (horiz==0||world.isOccupied(newPos)){
//            int vert = Integer.signum(destPos.y-this.position.y);
//            newPos=new Point(this.position.x,this.position.y+vert);
//
//            if(vert==0||world.isOccupied(newPos)){
//                newPos=this.position;
//            }
//        }
//        return newPos;
        return null;
    }


    @Override
    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        return false;
    }

//    @Override
//    public Point getPosition() {
//        return this.position;
//    }
//
//    @Override
//    public void setPosition(Point p) {
//        this.position=p;
//
//    }
//
//    @Override
//    public int getHealth() {
//        return 0;
//    }
//
//    @Override
//    public void setHealth(int n) {
//
//    }
//
//    @Override
//    public void loseHealthPoint() {
//        this.health--;
//
//    }
//
//    public int getAnimationPeriod(){
//        return this.animationPeriod;
//    }
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }
//
//    @Override
//    public List<PImage> getImages() {
//        return this.images;
//    }
//
//    @Override
//    public int getImageIndex() {
//        return this.imageIndex;
//    }
//    public PImage getCurrentImage(){return this.images.get(this.imageIndex);}

    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {

    }

//    @Override
//    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//
//    }
}
