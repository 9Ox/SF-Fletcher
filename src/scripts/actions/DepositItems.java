package scripts.actions;

import java.util.HashMap;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/13/13
 */
public class DepositItems extends Action {
    
    private String[] unfinishedMaterials;

    @Override
    public boolean shouldExecute() {
        if(unfinishedMaterials.length <= 1) {
            return Banking.isBankScreenOpen() && Inventory.getAll().length > (Inventory.find("Knife").length >= 1 ? 1 : 0);
        }
        return Banking.isBankScreenOpen() && Inventory.getAll().length > 0;
    }

    @Override
    public void execute() {
        if(Banking.depositAllExcept("Knife") >= 1) {
            final int start = Inventory.getAll().length;
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Inventory.getAll().length < start;
                }
            }, 2000);
        }
    }

    @Override
    public void initOptions(HashMap<String, String> options) {
        if(options.get("Log name") != null) {
        unfinishedMaterials = new String[1];
            unfinishedMaterials[0] = options.get("Log name");
        } else {
        unfinishedMaterials = new String[2];
            unfinishedMaterials[0] = "Bow string";
            unfinishedMaterials[1] = options.get("Unstrung bow name");
        }
    }
}
