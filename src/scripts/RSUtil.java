package scripts;

/**
 * @author Starfox
 * @version 12/14/13
 */
public class RSUtil {
    /**
     * Gets the per hour value for the specified value.
     * @param value The value to evaluate.
     * @param startTime The time in ms when the script started.
     * @return The per hour value.
     */
    public static long getPerHour(final int value, final long startTime) {
        return (long)(value * 3600000D / (System.currentTimeMillis() - startTime));    
    }
}
