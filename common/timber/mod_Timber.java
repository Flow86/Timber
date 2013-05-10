import net.minecraft.client.Minecraft;

public class mod_Timber extends BaseMod
{

  @MLProp(name="axeIds", info="List of item ids that are axes")
  public static String axes = "258, 271, 275, 279, 286";
  static apa tree;

  public String getName()
  {
    return "Timber!";
  }

  public String getVersion() {
    return "1.5.1r2";
  }

  public void load()
  {
    apa.r[17] = null;
    tree = new BlockTimberTree(17);
    wk.f[17] = null;
    wk.f[17] = new wt(-239, tree, apk.a).b("log");
    ModLoader.setInGameHook(this, true, true);
  }

  public boolean onTickInGame(float f, Minecraft minecraft) {
    if ((minecraft.g.cd() != null) && (canTimber(minecraft)))
    {
      BlockTimberTree.setAxe(true);
    }
    else BlockTimberTree.setAxe(false);

    return true;
  }

  private boolean canTimber(Minecraft minecraft) {
    return isItemAxe(minecraft.g.cd().c, axes);
  }

  private boolean isItemAxe(int itemID, String idList)
  {
    String[] ids = idList.split(",");

    for (int i = 0; i < ids.length; i++) {
      int id = Integer.parseInt(ids[i].trim());

      if (itemID == id) {
        return true;
      }
    }

    return false;
  }
}