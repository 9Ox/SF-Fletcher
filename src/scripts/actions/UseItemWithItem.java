package scripts.actions;

import java.util.HashMap;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/13/13
 */
public class UseItemWithItem extends Action {
    
    private int masterIndex;
    private String firstItem;
    private String secondItem;

    @Override
    public boolean shouldExecute() {
        return !Banking.isBankScreenOpen() && Interfaces.get(masterIndex) == null &&
               Inventory.find(firstItem).length >= 1 && Inventory.find(secondItem).length >= 1;
    }

    @Override
    public void execute() {
        if(Inventory.find(firstItem)[0].click("Use")) {
            if(Inventory.find(secondItem)[0].click("Use")) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Interfaces.get(masterIndex) != null;
                    }
                }, 2500);
            }
        }
    }

    @Override
    public void initOptions(HashMap<String, String> options) {
        if(options.get("Log name") != null) {
            firstItem = "Knife";
            secondItem = options.get("Log name");
            if(options.get("Log name").equalsIgnoreCase("logs")) {
                masterIndex = 305;
            } else {
                masterIndex = 304;
            }
        } else if(options.get("Strung bow name") != null) {
            firstItem = "Bow string";
            secondItem = options.get("Unstrung bow name");
            masterIndex = 309;
        } else {
            firstItem = options.get("First item");
            secondItem = options.get("Second item");
            masterIndex = 582;
        }
    }
}
