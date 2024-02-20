/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Activity class.
 * @author Kentaro Ogura
 */
class ActivityTest {

	/**
	 * Tests the checkConflict method for when there are no conflicts present.
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Tests the checkConflict method for when start and end times are the same. 
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Tests the checkConflict method for when end time conflicts with start time.
	 */
	@Test
	public void testCheckConflictWithConflictEndTimeStartTime() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1500);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1500, 1530);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Tests the checkConflict method when a conflict is caused due to a conflicting start time. 
	 */
	@Test
	public void testCheckConflictWithSameDayConflictingStartTime() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1345, 1500);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Tests the checkConflict method when a conflict is caused due to a conflicting end time. 
	 */
	@Test
	public void testCheckConflictWithSameDayConflictingEndTime() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1200, 1345);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	

}
