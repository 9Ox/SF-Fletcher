package scripts.actions;

import java.util.HashMap;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/13/2013
 */
public class WithdrawItems extends Action {

    private String[] unfinishedMaterials;
    
    @Override
    public boolean shouldExecute() {
        if(unfinishedMaterials.length <= 1) {
            return Banking.isBankScreenOpen() && (!hasMaterials() ||
                   Inventory.find("Knife").length <= 0) && Inventory.getAll().length <= 1;
        }
        return Banking.isBankScreenOpen() && !hasMaterials() && Inventory.getAll().length <= 0;
    }

    @Override
    public void execute() {
        if(unfinishedMaterials.length <= 1) {
            if(Inventory.find("Knife").length <= 0) {
                if(Banking.withdraw(1, "Knife")) {
                    Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Inventory.find("Knife").length >= 1;
                    }
                }, 2000);
                }
            }
            if(Banking.withdraw(0, unfinishedMaterials[0])) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Inventory.find(unfinishedMaterials[0]).length >= 1;
                    }
                }, 2000);
            }
        } else {
            if(Banking.withdraw(14, unfinishedMaterials[0])) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Inventory.find(unfinishedMaterials[0]).length >= 1;
                    }
                }, 2000);
            }
            if(Banking.withdraw(14, unfinishedMaterials[1])) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Inventory.find(unfinishedMaterials[1]).length >= 1;
                    }
                }, 2000);
            }
        }
    }

    @Override
    public void initOptions(HashMap<String, String> options) {
        if(options.get("Log name") != null) {
            unfinishedMaterials = new String[1];
            unfinishedMaterials[0] = options.get("Log name");
        } else {
            unfinishedMaterials = new String[2];
            unfinishedMaterials[0] = options.get("Unstrung bow name");
            unfinishedMaterials[1] = "Bow string";
        }
    }
    
    private boolean hasMaterials() {
        for(final String name : unfinishedMaterials) {
            if(Inventory.find(name).length <= 0) {
                return false;
            }
        }
        return true;
    }
}
