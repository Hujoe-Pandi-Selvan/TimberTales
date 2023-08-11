import processing.core.PImage;

import java.util.*;

/**
 * Represents the 2D World in which this simulation is running.
 * Keeps track of the size of the world, the background image for each
 * location in the world, and the entities that populate the world.
 */
public final class WorldModel
{
    public int numRows;
    public int numCols;
    public Background background[][];
    public Entity occupancy[][];
    public Set<Entity> entities;

    public WorldModel(int numRows, int numCols, Background defaultBackground) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.background = new Background[numRows][numCols];
        this.occupancy = new Entity[numRows][numCols];
        this.entities = new HashSet<>();

        for (int row = 0; row < numRows; row++) {
            Arrays.fill(this.background[row], defaultBackground);
        }
    }

    public boolean parseDude(
            String[] properties, ImageStore imageStore)
    {
        if (properties.length == Functions.DUDE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Functions.DUDE_COL]),
                    Integer.parseInt(properties[Functions.DUDE_ROW]));
            Entity entity = pt.createDudeNotFull(properties[Functions.DUDE_ID],
                    Integer.parseInt(properties[Functions.DUDE_ACTION_PERIOD]),
                    Integer.parseInt(properties[Functions.DUDE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[Functions.DUDE_LIMIT]),
                    imageStore.getImageList(Functions.DUDE_KEY));
            tryAddEntity(entity);
        }

        return properties.length == Functions.DUDE_NUM_PROPERTIES;
    }

    public boolean parseSapling(
            String[] properties, ImageStore imageStore)
    {
        if (properties.length == Functions.SAPLING_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Functions.SAPLING_COL]),
                    Integer.parseInt(properties[Functions.SAPLING_ROW]));
            String id = properties[Functions.SAPLING_ID];
            int health = Integer.parseInt(properties[Functions.SAPLING_HEALTH]);
            Entity entity = new Sapling( id, pt, imageStore.getImageList(Functions.SAPLING_KEY), 0, 0,
                    Functions.SAPLING_ACTION_ANIMATION_PERIOD, Functions.SAPLING_ACTION_ANIMATION_PERIOD, health, Functions.SAPLING_HEALTH_LIMIT);
            tryAddEntity(entity);
        }

        return properties.length == Functions.SAPLING_NUM_PROPERTIES;
    }

    public boolean parseBackground(
            String[] properties, ImageStore imageStore)
    {
        if (properties.length == Functions.BGND_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Functions.BGND_COL]),
                    Integer.parseInt(properties[Functions.BGND_ROW]));
            String id = properties[Functions.BGND_ID];
            setBackground(pt,
                    new Background(id, imageStore.getImageList(id)));
        }

        return properties.length == Functions.BGND_NUM_PROPERTIES;
    }

    public boolean processLine(
            String line, ImageStore imageStore)
    {
        String[] properties = line.split("\\s");
        if (properties.length > 0) {
            switch (properties[Functions.PROPERTY_KEY]) {
                case Functions.BGND_KEY:
                    return this.parseBackground(properties, imageStore);
                case Functions.DUDE_KEY:
                    return this.parseDude(properties, imageStore);
                case Functions.OBSTACLE_KEY:
                    return parseObstacle(properties, imageStore);
                case Functions.FAIRY_KEY:
                    return parseFairy(properties, imageStore);
                case Functions.HOUSE_KEY:
                    return parseHouse(properties, imageStore);
                case Functions.TREE_KEY:
                    return parseTree(properties, imageStore);
                case Functions.SAPLING_KEY:
                    return this.parseSapling(properties, imageStore);
            }
        }

        return false;
    }

    public void load(
            Scanner in, ImageStore imageStore)
    {
        int lineNumber = 0;
        while (in.hasNextLine()) {
            try {
                if (!this.processLine(in.nextLine(), imageStore)) {
                    System.err.println(String.format("invalid entry on line %d",
                            lineNumber));
                }
            }
            catch (NumberFormatException e) {
                System.err.println(
                        String.format("invalid entry on line %d", lineNumber));
            }
            catch (IllegalArgumentException e) {
                System.err.println(
                        String.format("issue on line %d: %s", lineNumber,
                                e.getMessage()));
            }
            lineNumber++;
        }
    }

    public boolean parseFairy(
            String[] properties, ImageStore imageStore)
    {
        if (properties.length == Functions.FAIRY_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Functions.FAIRY_COL]),
                    Integer.parseInt(properties[Functions.FAIRY_ROW]));
            Entity entity = pt.createFairy(properties[Functions.FAIRY_ID],
                    Integer.parseInt(properties[Functions.FAIRY_ACTION_PERIOD]),
                    Integer.parseInt(properties[Functions.FAIRY_ANIMATION_PERIOD]),
                    imageStore.getImageList(Functions.FAIRY_KEY));
            tryAddEntity(entity);
        }

        return properties.length == Functions.FAIRY_NUM_PROPERTIES;
    }

    public boolean parseObstacle(
            String[] properties, ImageStore imageStore)
    {
        if (properties.length == Functions.OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Functions.OBSTACLE_COL]),
                    Integer.parseInt(properties[Functions.OBSTACLE_ROW]));
            Entity entity = pt.createObstacle(properties[Functions.OBSTACLE_ID],
                    Integer.parseInt(properties[Functions.OBSTACLE_ANIMATION_PERIOD]),
                    imageStore.getImageList(
                            Functions.OBSTACLE_KEY));
            tryAddEntity(entity);
        }

        return properties.length == Functions.OBSTACLE_NUM_PROPERTIES;
    }

    public boolean parseTree(
            String[] properties, ImageStore imageStore)
    {
        if (properties.length == Functions.TREE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Functions.TREE_COL]),
                    Integer.parseInt(properties[Functions.TREE_ROW]));
            Entity entity = pt.createTree(properties[Functions.TREE_ID],
                    Integer.parseInt(properties[Functions.TREE_ACTION_PERIOD]),
                    Integer.parseInt(properties[Functions.TREE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[Functions.TREE_HEALTH]),
                    imageStore.getImageList(Functions.TREE_KEY));
            tryAddEntity(entity);
        }

        return properties.length == Functions.TREE_NUM_PROPERTIES;
    }

    public boolean parseHouse(
            String[] properties, ImageStore imageStore)
    {
        if (properties.length == Functions.HOUSE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Functions.HOUSE_COL]),
                    Integer.parseInt(properties[Functions.HOUSE_ROW]));
            Entity entity = pt.createHouse(properties[Functions.HOUSE_ID],
                    imageStore.getImageList(
                            Functions.HOUSE_KEY));
            tryAddEntity(entity);
        }

        return properties.length == Functions.HOUSE_NUM_PROPERTIES;
    }

    public void tryAddEntity(Entity entity) {
        if (isOccupied(entity.getPosition())) {
            // arguably the wrong type of exception, but we are not
            // defining our own exceptions yet
            throw new IllegalArgumentException("position occupied");
        }

        addEntity(entity);
    }

    public boolean withinBounds(Point pos) {
        return pos.y >= 0 && pos.y < this.numRows && pos.x >= 0
                && pos.x < this.numCols;
    }

    public boolean isOccupied(Point pos) {
        return this.withinBounds(pos) && getOccupancyCell(pos) != null;
    }

    public Optional<PImage> getBackgroundImage(
            Point pos)
    {
        if (this.withinBounds(pos)) {
            return Optional.of(ImageStore.getCurrentImage(getBackgroundCell(pos)));
        }
        else {
            return Optional.empty();
        }
    }

    public void setBackground(
            Point pos, Background background)
    {
        if (this.withinBounds(pos)) {
            setBackgroundCell(pos, background);
        }
    }

    public Entity getOccupancyCell(Point pos) {
        return this.occupancy[pos.y][pos.x];
    }

    public Background getBackgroundCell(Point pos) {
        return this.background[pos.y][pos.x];
    }

    public void setOccupancyCell(
            Point pos, Entity entity)
    {
        this.occupancy[pos.y][pos.x] = entity;
    }

    public void setBackgroundCell(
            Point pos, Background background)
    {
        this.background[pos.y][pos.x] = background;
    }

    public void removeEntityAt(Point pos) {
        if (this.withinBounds(pos) && this.getOccupancyCell(pos) != null) {
            Entity entity = this.getOccupancyCell(pos);

            /* This moves the entity just outside of the grid for
             * debugging purposes. */
            Point position = new Point(-1, -1);
            entity.setPosition(position);
            this.entities.remove(entity);
            this.setOccupancyCell(pos, null);
        }
    }

    public void moveEntity(Entity entity, Point pos) {
        Point oldPos = entity.getPosition();
        if (this.withinBounds(pos) && !pos.equals(oldPos)) {
            this.setOccupancyCell(oldPos, null);
            this.removeEntityAt(pos);
            this.setOccupancyCell(pos, entity);
            entity.setPosition(pos);
        }
    }

    public Optional<Entity> getOccupant(Point pos) {
        if (this.isOccupied(pos)) {
            return Optional.of(this.getOccupancyCell(pos));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Entity> findNearest(
            Point pos, List<Class> kinds)
    {
        List<Entity> ofType = new LinkedList<>();
        for (Class kind: kinds)
        {
            for (Entity entity : this.entities) {
                if (entity.getClass() == kind) {
                    ofType.add(entity);
                }
            }
        }

        return pos.nearestEntity(ofType);
    }

    /*
           Assumes that there is no entity currently occupying the
           intended destination cell.
        */
    public void addEntity(Entity entity) {
        if (this.withinBounds(entity.getPosition())) {
            this.setOccupancyCell(entity.getPosition(), entity);
            this.entities.add(entity);
        }
    }
    public void removeEntity(Entity entity) {
        removeEntityAt(entity.getPosition());
    }
}


