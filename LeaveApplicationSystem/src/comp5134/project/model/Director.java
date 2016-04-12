package comp5134.project.model;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
public class Director extends AbstractStaff{
	
	public Director(){}
	
	public Director(String staffId, String firstName, String lastName,
			String password, String position, int age, char gender,
			String email, String department) {
		super(staffId, firstName, lastName, password, position, age, gender, email,
				department);
	}

	@Override
	protected String printStaffInfo() {
		return this.getClass().getName()+":"+mStaffId+","+mFirstName+","+mLastName+","+mPosition+","+mDepartment+"\n";
	}

	@Override
	public void handleLeaveRequest(LeaveRequest leaveRequest) {
		this.mLeaveRequest = leaveRequest;
		if(leaveRequest.getIsApproveByDirect() && leaveRequest.getIsApproveBySupervisor() && leaveRequest.getStatus() != leaveRequest.PENDING) {
			if(leaveRequest.getStatus() == leaveRequest.SUCCESS){
				leaveRequest.setApproveResult("Success");
			} else {
				leaveRequest.setApproveResult("Fail");
			}
		}  else {
			super.passTheRequestToNextStaff(leaveRequest);
		}
	}
}
