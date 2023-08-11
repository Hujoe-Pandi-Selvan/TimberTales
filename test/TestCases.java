//import java.util.*;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestCases
//{
//
////   public VirtualWorld vWorld = new VirtualWorld();
////
////
//   @BeforeAll @BeforeEach
//   public void setup()
//   {
//      VirtualWorld vWorld = new VirtualWorld();
//      // -------------- load world but do not schedule entities --------------------------------
//      vWorld.imageStore = new ImageStore(
//              vWorld.createImageColored(vWorld.TILE_WIDTH, vWorld.TILE_HEIGHT,
//                      vWorld.DEFAULT_IMAGE_COLOR));
//      vWorld.world = new WorldModel(vWorld.WORLD_ROWS, vWorld.WORLD_COLS,
//              vWorld.createDefaultBackground(vWorld.imageStore));
//      vWorld.view = new WorldView(vWorld.VIEW_ROWS, vWorld.VIEW_COLS, vWorld, vWorld.world, vWorld.TILE_WIDTH,
//              vWorld.TILE_HEIGHT);
//      vWorld.scheduler = new EventScheduler(vWorld.timeScale);
//
//      vWorld.loadImages(vWorld.IMAGE_LIST_FILE_NAME, vWorld.imageStore, vWorld);
//      vWorld.loadWorld(vWorld.world, "test1.sav", vWorld.imageStore);
//
//      vWorld.nextTime = System.currentTimeMillis() + vWorld.TIMER_ACTION_PERIOD;
//   }
//
//   ////////////////////////////////////////////////////////////
//   //        After First iteration:  Dude not Full           //
//   ////////////////////////////////////////////////////////////
//   // the following tests split apart the executeDudeNotFull //
//   // method and tests each part individually                //
//   //--------------------------------------------------------//
//   @Test
//   public void testFindNearest()
//   {
//      // setup steps
//      VirtualWorld vWorld = new VirtualWorld();
//      vWorld.imageStore = new ImageStore(
//              vWorld.createImageColored(vWorld.TILE_WIDTH, vWorld.TILE_HEIGHT,
//                      vWorld.DEFAULT_IMAGE_COLOR));
//      vWorld.world = new WorldModel(vWorld.WORLD_ROWS, vWorld.WORLD_COLS,
//              null);
//      //vWorld.view = null;
//      vWorld.scheduler = new EventScheduler(vWorld.timeScale);
//      vWorld.loadWorld(vWorld.world, "test1.sav", vWorld.imageStore);
//
//      //vWorld.nextTime = System.currentTimeMillis() + vWorld.TIMER_ACTION_PERIOD;
//
//      // get expected and target from world model - only should be one of each
//      Entity actual = null;
//
//      for (Entity e: vWorld.world.entities)
//      {
//         if (e.kind == EntityKind.DUDE_NOT_FULL)
//            actual = e;
//      }
//      Optional<Entity> target = Optional.empty();
//      if (actual != null)
//      {
//         target = vWorld.world.findNearest(actual.position, new ArrayList<>(Arrays.asList(EntityKind.TREE, EntityKind.SAPLING)));
//      }
//
//      Entity expected = new Point(1, 1).createTree("tree", 1050, 61, 1, vWorld.imageStore.getImageList(Functions.TREE_KEY));
//      assertTrue(expected.equals(target.get()));
//
//   }
//   @Test
//   // took step to tree, but did not pick up yet
//   public void testMoveToNotFull()
//   {
//      // get expected and target from world model - only should be one of each
//      Entity eActual = null;
//      Entity target = null;
//      for (Entity e: vWorld.world.entities)
//      {
//         if (e.kind == EntityKind.DUDE_NOT_FULL)
//            eActual = e;
//         if (e.kind == EntityKind.TREE)
//            target = e;
//      }
//
//      // call the method to test
//      if (eActual != null)
//         Functions.moveToNotFull(eActual, vWorld.world, target, vWorld.scheduler);
//
//      // check data points we care about are correct
//      Entity expected = Functions.createDudeNotFull("dude", new Point(1, 3), 720, 100, 1, Functions.getImageList(vWorld.imageStore, Functions.DUDE_KEY));
//      assertTrue(eActual != null && eActual.getPosition().equals(expected.getPosition()));
//      assertTrue(eActual != null && eActual.resourceCount == expected.resourceCount);
//   }
//
//   @Test
//   // take another step so you are now adjacent to the tree
//   public void testExecuteDudeNotFull()
//   {
//      // get expected and target from world model - only should be one of each
//      Entity eActual = null;
//      for (Entity e: vWorld.world.entities)
//      {
//         if (e.kind == EntityKind.DUDE_NOT_FULL)
//            eActual = e;
//      }
//
//      // call the method to test
//      if (eActual != null)
//         Functions.executeDudeNotFullActivity(eActual, vWorld.world, vWorld.imageStore, vWorld.scheduler);
//
//      // check data points we care about are correct
//      Entity expected = Functions.createDudeNotFull("dude", new Point(1, 2), 720, 100, 1, Functions.getImageList(vWorld.imageStore, Functions.DUDE_KEY));
//      assertTrue(expected.equals(eActual));
//   }
//
//}
