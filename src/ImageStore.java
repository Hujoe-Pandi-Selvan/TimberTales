import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;

public final class ImageStore
{
    public Map<String, List<PImage>> images;
    public List<PImage> defaultImages;

    public ImageStore(PImage defaultImage) {
        this.images = new HashMap<>();
        defaultImages = new LinkedList<>();
        defaultImages.add(defaultImage);
    }
    public static void processImageLine(
            Map<String, List<PImage>> images, String line, PApplet screen) {
        String[] attrs = line.split("\\s");
        if (attrs.length >= 2) {
            String key = attrs[0];
            PImage img = screen.loadImage(attrs[1]);
            if (img != null && img.width != -1) {
                List<PImage> imgs = Functions.getImages(images, key);
                imgs.add(img);

                if (attrs.length >= Functions.KEYED_IMAGE_MIN) {
                    int r = Integer.parseInt(attrs[Functions.KEYED_RED_IDX]);
                    int g = Integer.parseInt(attrs[Functions.KEYED_GREEN_IDX]);
                    int b = Integer.parseInt(attrs[Functions.KEYED_BLUE_IDX]);
                    Functions.setAlpha(img, screen.color(r, g, b), 0);
                }
            }
        }
    }
    public static PImage getCurrentImage(Object entity) {
        if (entity instanceof Background) {
            return ((Background)entity).images.get(
                    ((Background)entity).imageIndex);
        }
        else if (entity instanceof House) {
            return ((House)entity).images.get(((House)entity).imageIndex);
        }
        else if (entity instanceof Fairy) {
            return ((Fairy)entity).images.get(((Fairy)entity).imageIndex);}
        else if (entity instanceof Dude_Full) {
            return ((Dude_Full)entity).images.get(((Dude_Full)entity).imageIndex);}
        else if (entity instanceof Dude_Not_Full) {
            return ((Dude_Not_Full)entity).images.get(((Dude_Not_Full)entity).imageIndex);}
        else if (entity instanceof Obstacle) {
            return ((Obstacle)entity).images.get(((Obstacle)entity).imageIndex);}
        else if (entity instanceof Sapling) {
            return ((Sapling)entity).images.get(((Sapling)entity).imageIndex);}
        else if (entity instanceof Stump) {
            return ((Stump)entity).images.get(((Stump)entity).imageIndex);}
        else if (entity instanceof Tree) {
            return ((Tree)entity).images.get(((Tree)entity).imageIndex);}






        else {
            throw new UnsupportedOperationException(
                    String.format("getCurrentImage not supported for %s",
                            entity));
        }
    }

    public void loadImages(
            Scanner in, PApplet screen)
    {
        int lineNumber = 0;
        while (in.hasNextLine()) {
            try {
                this.processImageLine(this.images, in.nextLine(), screen);
            }
            catch (NumberFormatException e) {
                System.out.println(
                        String.format("Image format error on line %d",
                                lineNumber));
            }
            lineNumber++;
        }
    }

    public List<PImage> getImageList(String key) {
        return this.images.getOrDefault(key, this.defaultImages);
    }
}
