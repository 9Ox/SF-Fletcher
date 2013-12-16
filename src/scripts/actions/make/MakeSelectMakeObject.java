package scripts.actions.make;

import java.util.HashMap;
import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Skills;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 12/14/2013
 */
public class MakeSelectMakeObject extends Action {

    @Override
    public boolean shouldExecute() {
        return Interfaces.get(582) != null;
    }

    @Override
    public void execute() {
        if(Interfaces.get(582, 3).click("Make 10 sets")) {
            int count = 0;
            int xp = Skills.getXP(Skills.SKILLS.FLETCHING);
            for(int i = 0; i < 2500; i++) {
                if(Skills.getXP(Skills.SKILLS.FLETCHING) > xp) {
                    xp = Skills.getXP(Skills.SKILLS.FLETCHING);
                    count++;
                    i = 0;
                }
                if(count >= 10) {
                    break;
                }
                General.sleep(1);
            }
        }
    }

    @Override
    public void initOptions(HashMap<String, String> options) {
        
    }
}
