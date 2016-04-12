package comp5134.project.model;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
public class AbstractStaffFactory {
	private static final int ESTAFF = 0;
	private static final int ESUPERVISOR = 1;
	private static final int EDIRECTOR = 2;
	private static final int EADMINISTRATOR = 3;

	public static AbstractStaff createAbstractStaff(int type, String staffId, String firstName, String lastName,String password, 
			String position, int age, char gender, String email, String department) {
		switch (type){
			case ESTAFF:
				return new Staff(staffId, firstName, lastName, password, position, age, gender, email, department);
			case ESUPERVISOR:
				return new Supervisor(staffId, firstName, lastName, password, position, age, gender, email, department);
			case EDIRECTOR:
				return new Director(staffId, firstName, lastName, password, position, age, gender, email, department);
			case EADMINISTRATOR:
				return new Administrator(staffId, firstName, lastName, password, position, age, gender, email, department);
			default:
				System.err.println("Error - Input the invalid type");
				break;
		}
		return null;
	}
}
