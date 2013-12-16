package scripts.actions.cut;

import java.util.HashMap;
import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/13/13
 */
public class CutSelectMakeObject extends Action {
    
    private int masterIndex;
    private int childIndex;
    private String logName;

    @Override
    public boolean shouldExecute() {
        return !Banking.isBankScreenOpen() && Interfaces.get(masterIndex, childIndex) != null;
    }

    @Override
    public void execute() {
        if(Interfaces.get(masterIndex, childIndex).click("Make X")) {
            General.sleep(600);
            //TODO: Find a way around the always visible enter interface
            Keyboard.typeSend("99");
            for(int i = 0; i < 3500; i++) {
                if(Player.getAnimation() != -1) {
                    i = 0;
                }
                if(Inventory.getCount(new String[]{logName}) <= 0) {
                    break;
                }
                General.sleep(1);
            }
        }
    }

    @Override
    public void initOptions(HashMap<String, String> options) {
        if(options.get("Log name") != null) {
            logName = options.get("Log name");
            if(options.get("Log name").equalsIgnoreCase("logs")) {
                masterIndex = 305;
                if(options.get("Unstrung bow name").contains("shaft")) {
                    childIndex = 9;
                } else if(options.get("Unstrung bow name").contains("short")) {
                    childIndex = 13;
                } else {
                    childIndex = 17;
                }
            } else {
                masterIndex = 304;
                if(options.get("Unstrung bow name").contains("short")) {
                    childIndex = 8;
                } else {
                    childIndex = 12;
                }
            }
        }
    }
}
