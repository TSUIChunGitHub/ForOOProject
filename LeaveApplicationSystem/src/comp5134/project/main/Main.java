package comp5134.project.main;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
public class Main {
	// Singleton pattern
	private static LeaveApplicationSystem mLeaveApplicationSystem; 
	public static void main(String args[]){
		mLeaveApplicationSystem = new LeaveApplicationSystem();
		mLeaveApplicationSystem.init();
		mLeaveApplicationSystem.run();
	}
}
