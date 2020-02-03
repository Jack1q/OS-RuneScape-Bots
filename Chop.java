import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;

@ScriptManifest(author = "Jack Donofrio", name = "Jack's Tree Cutter", version = 1.0, description = "Simple Tree Cutter and Log Burner", category = Category.WOODCUTTING)
public class Chop extends AbstractScript
{
  Area treeArea = new Area(3071, 3314, 3122, 3299);

  @Override
  public int onLoop()
  {
    if (treeArea.contains(getLocalPlayer()))
    {
      if (getInventory().isFull())
      {

        int playerX = getLocalPlayer().getX();
        int playerY = getLocalPlayer().getY();
        Tile next = new Tile(playerX + 1, playerY + 1);

        GameObject fire = getGameObjects().closest("Fire");
        if (getLocalPlayer().getTile().equals(fire.getTile()))
          getWalking().walk(next);
        getInventory().get("Tinderbox").useOn("Logs");
        sleep(3000);

      }
      if (!getLocalPlayer().isAnimating())
      {
        GameObject tree = getGameObjects().closest("Tree");
        if (tree != null)
        {
          tree.interact("Chop down");
        }
      }
    }
    else
    {
      getWalking().walk(treeArea.getCenter());
    }
    return 1000;
  }
}
