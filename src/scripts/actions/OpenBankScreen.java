package scripts.actions;

import java.util.HashMap;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/13/13
 */
public class OpenBankScreen extends Action {
    
    private String[] unfinishedMaterials;

    @Override
    public boolean shouldExecute() {
        if(unfinishedMaterials.length <= 1) {
            return !Banking.isBankScreenOpen() && (Inventory.find(unfinishedMaterials).length <= 0 ||
                    Inventory.find("Knife").length <= 0);
        }
        return !Banking.isBankScreenOpen() && (Inventory.find(unfinishedMaterials[0]).length <= 0 ||
                Inventory.find(unfinishedMaterials[1]).length <= 0);
    }

    @Override
    public void execute() {
        Banking.openBank();
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
}
