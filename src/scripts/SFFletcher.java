package scripts;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.tribot.api.Timing;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;
import scripts.framework.Initializer;

/**
 * @author Starfox
 * @version 12/11/13
 */
@ScriptManifest(name = "SF AIO Fletcher", authors = "Starfox", category = "Fletching", description = 
        "Does everything related to the fletching skill.")
public class SFFletcher extends Script implements MessageListening07, Painting {
    
    //private boolean showDebug = false;
    
    @Override
    public void run() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SFFletcherGUI("Thank you for choosing SF AIO Flecther." + 
                        " This script is still in beta; please post any bugs you find on the script thread.").setVisible(true);
            }
        });
        Initializer.initialize(25);
    }

    @Override
    public void serverMessageReceived(String string) {
        if(string.contains("You carefully cut")) {
            DynamicVariables.scriptCount += string.contains("shaft") ? 
                    Integer.parseInt(string.substring(string.indexOf("into ") + 5, string.indexOf(" arrow"))) : 1;
        }
        if(string.contains("You add a string")) {
            DynamicVariables.scriptCount++;
        }
        if(string.contains("You attach")) {
            DynamicVariables.scriptCount += Integer.parseInt(string.substring(string.indexOf("to ") + 3, string.indexOf(" arrow")));
        }
        if(string.contains("You fletch")) {
            DynamicVariables.scriptCount += Integer.parseInt(string.substring(string.indexOf("fletch ") + 7, 
                    string.contains("bolts") ? string.indexOf(" bolts") : string.indexOf(" darts")));
        }
    }

    @Override
    public void playerMessageReceived(String string, String string1) {
    }

    @Override
    public void personalMessageReceived(String string, String string1) {
    }

    @Override
    public void tradeRequestReceived(String string) {
    }

    @Override
    public void clanMessageReceived(String string, String string1) {
    }

    @Override
    public void onPaint(Graphics gr) {
        Graphics2D g = (Graphics2D)gr;
        g.setColor(Color.BLACK);
        g.drawRect(4, 4, 145, 110);
        g.setColor(new Color(0, 0, 0, 190));
        g.fillRect(4, 4, 145, 110);
        g.setColor(new Color(247, 247, 247, 190));
        g.drawString("SF AIO Fletcher", 35, 20);
        g.drawString("_______________", 24, 23);
        g.drawString("Time ran: " + Timing.msToString(System.currentTimeMillis() - DynamicVariables.startTime), 8, 40);
        g.drawString("Made (/h): " + DynamicVariables.scriptCount + " (" 
                + RSUtil.getPerHour(DynamicVariables.scriptCount, DynamicVariables.startTime) + ")", 8, 55);
        /*g.setColor(Color.WHITE);
        g.drawRect(24, 70, 103, 30);
        g.setColor(new Color(247, 247, 247, 190));
        g.drawString("Debug", 58, 89);
        if(showDebug) {
            g.setColor(Color.WHITE);
            g.drawString(DynamicVariables.action, 4, 125);
        }*/
    }

    /*@Override
    public void mouseClicked(MouseEvent e) {
        Point p = Mouse.getPos();
        if(p.x >= 24 && p.x <= 103-24 && p.y >= 70 && p.y <= 100) {
            showDebug = !showDebug;
        }
    }*/
}
