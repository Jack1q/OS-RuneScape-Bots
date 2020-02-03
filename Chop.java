import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
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
      getWalking().walk(treeArea.getRandomTile());
    }
    return 1000;
  }
}
