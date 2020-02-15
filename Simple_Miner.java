import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;

@ScriptManifest(author = "Jack Donofrio", name = "Jack's Ore Miner", version = 1.0, description = "Simple Miner and Banker", category = Category.MINING)
public class Simple_Miner extends AbstractScript
{
  Area mine = new Area(3284, 3367, 3282, 3370);
  Area varrockEastBank = new Area(3257, 3424, 3250, 3419);

  @Override
  public int onLoop()
  {
    if (mine.contains(getLocalPlayer()))
    {

      if (!getLocalPlayer().isAnimating())
      {
        GameObject rocks = getGameObjects().closest("Rocks");
        if (rocks != null)
        {
          rocks.interact();
          sleep(2500);
          if (!getLocalPlayer().isAnimating())
          {
            getWalking().walk(
              new Tile(getLocalPlayer().getX() + Calculations.random(-2, 2),
                getLocalPlayer().getY() + Calculations.random(-2, 2)));
          }
        }
      }
    }

    else if (!getInventory().isFull())
    {
      getWalking().walk(mine.getCenter());
    }

    if (getInventory().isFull())
    {
      if (varrockEastBank.contains(getLocalPlayer()))
      {
        if (getBank().isOpen())
        {
          getBank().depositAllItems();
        }
        else
        {
          getBank().openClosest();
          getBank().depositAllItems();
        }
      }
      else
      {
        getWalking().walk(varrockEastBank.getCenter());
      }
    }

    return 1000;
  }
}