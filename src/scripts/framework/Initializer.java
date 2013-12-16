package scripts.framework;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import scripts.DynamicVariables;


/**
 * @author Starfox
 */
public class Initializer {
    public static void initialize(final int loopSpeed) {
        Initializer i = new Initializer();
        i.waitForGUI();
        Mouse.setSpeed(DynamicVariables.mouseSpeed);
        General.println("Mouse speed: " + DynamicVariables.mouseSpeed);
        Loop loop = new Loop(loopSpeed);
        loop.loop();
    }

    private void waitForGUI() {
        Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                return Manager.isHasClosed();
            }
        }, 600000);
    }
}
