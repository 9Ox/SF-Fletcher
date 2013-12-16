package scripts.actions;

import java.util.HashMap;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/14/13
 */
public class CloseBankScreen extends Action {
    
    private String[] itemsNeeded;

    @Override
    public boolean shouldExecute() {
        return Banking.isBankScreenOpen() && Inventory.find(itemsNeeded[0]).length >= 1 &&
               Inventory.find(itemsNeeded[1]).length >= 1;
    }

    @Override
    public void execute() {
        if(Banking.close()) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return !Banking.isBankScreenOpen();
                }
            }, 2000);
        }
    }

    @Override
    public void initOptions(HashMap<String, String> options) {
        itemsNeeded = new String[2];
        if(options.get("Log name") != null) {
            itemsNeeded[0] = "Knife";
            itemsNeeded[1] = options.get("Log name");
        } else {
            itemsNeeded = new String[2];
            itemsNeeded[0] = options.get("Unstrung bow name");
            itemsNeeded[1] = "Bow string";
        }
    }
}
