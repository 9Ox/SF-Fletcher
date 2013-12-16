package scripts.actions.make;

import java.util.HashMap;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/14/13
 */
public class MakeUseItemWithItem extends Action {
    
    private String[] items;

    @Override
    public boolean shouldExecute() {
        return Inventory.find(items).length >= 1;
    }

    @Override
    public void execute() {
        if(Banking.isBankScreenOpen()) {
            Banking.close();
        }
        if(Inventory.find(items)[0].click("Use")) {
            Inventory.find(items)[1].click("Use");
        }
    }

    @Override
    public void initOptions(HashMap<String, String> options) {
        items = new String[2];
        items[0] = options.get("First item");
        items[1] = options.get("Second item");
    }
}
