package scripts.actions.string;

import java.util.HashMap;
import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/14/2013
 */
public class StringSelectMakeObject extends Action {
    
    private final String[] MATERIALS = new String[2];

    @Override
    public boolean shouldExecute() {
        return !Banking.isBankScreenOpen() && Interfaces.get(309) != null && 
               Inventory.find(MATERIALS[0]).length >= 1 && Inventory.find(MATERIALS[1]).length >= 1;
    }

    @Override
    public void execute() {
        if(Interfaces.get(309, 2).click("Make All")) {
            for(int i = 0; i < 3500; i++) {
                if(Player.getAnimation() != 1) {
                    i = 0;
                }
                if(Inventory.getCount(new String[]{MATERIALS[0]}) <= 0 || Inventory.getCount(new String[]{MATERIALS[1]}) <= 0) {
                    break;
                }
                General.sleep(1);
            }
        }
    }

    @Override
    public void initOptions(HashMap<String, String> options) {
        MATERIALS[0] = "Bow string";
        MATERIALS[1] = options.get("Unstrung bow name");
    }
}
