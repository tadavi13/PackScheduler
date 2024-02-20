/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * An interface used to check for schedule conflicts.
 * @author Kentaro Ogura
 */
public interface Conflict {
	/**
	 * Checks conflicts between activities.
	 * @param possibleConflictingActivity an activity with a potential conflict
	 * @throws ConflictException if an Activity has a conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

	
}
