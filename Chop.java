import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.script.Category;

@ScriptManifest(author = "Jack Donofrio", name = "Jack's Tree Cutter", version = 1.0, description = "Simple Tree Cutter and Log Burner", category = Category.WOODCUTTING)
public class Chop extends AbstractScript
{

  @Override
  public int onLoop()
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
    return 1000;

  }

}
