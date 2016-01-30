/**
 * 
 */
package org.usfirst.frc.team4534.robot.util;

/**
 * 
 * @author Tom Sanford
 *
 */
public final class MillisecondTimer {
	
	private static long current = System.currentTimeMillis();
	private static long diff = 0;
	
	public static final long getDifference() {
		diff = System.currentTimeMillis() - current;
		current = System.currentTimeMillis();
		return diff;
	}
}
