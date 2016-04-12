package comp5134.project.model;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
public abstract class AbstractStaff {
	protected String mStaffId;
	protected String mPassword;
	protected String mFirstName;
	protected String mLastName;
	protected String mPosition;
	protected int mAge;
	protected char mGender;
	protected String mEmail;
	protected String mDepartment;
	
	/*For Chain Of Responsibility Pattern*/
	protected AbstractStaff mNextHandleStaff;
	protected LeaveRequest mLeaveRequest;
	
	public AbstractStaff(){}
	
	public AbstractStaff(String staffId, String firstName, String lastName,String password, 
			String position, int age, char gender, String email, String department) {
		this.mStaffId = staffId;
		this.mPassword = password;
		this.mFirstName = firstName;
		this.mLastName = lastName;
		this.mPosition = position;
		this.mAge = age;
		this.mGender = gender;
		this.mEmail = email;
		this.mDepartment = department;
	}
	
	public void setStaffId(String staffId){
		this.mStaffId = staffId;
	}
	
	public String getStaffId(){
		return mStaffId;
	}
		
	public void setPassword(String password){
		this.mPassword = password;
	}
	
	public String getPassword(){
		return mPassword;
	}
	
	public void setFirstName(String firstName){
		this.mFirstName = firstName;
	}
	
	public String getFirstName(){
		return mFirstName;
	}
	
	public void setLastName(String lastName){
		this.mLastName = lastName;
	}
	
	public String getLastName(){
		return mLastName;
	}
	
	public void setPosition(String position){
		this.mPosition = position;
	}
	
	public String getPosition(){
		return mPosition;
	}
	
	public void setAge(int age){
		this.mAge = age;
	}
	
	public int getAge(){
		return mAge;
	}
	
	public void setGender(char gender){
		this.mGender = gender;
	}
	
	public char getGender(){
		return mGender;
	}
	
	public void setEmail(String email){
		this.mEmail = email;
	}
	
	public String getEmail(){
		return mEmail;
	}
	
	public void setDepartment(String department){
		this.mDepartment = department;
	}
	
	public String getDepartment(){
		return mDepartment;
	}
	
	public void setLeaveRequest(LeaveRequest leaveRequest){
		this.mLeaveRequest = leaveRequest;
	}
	
	public LeaveRequest getLeaveRequest(){
		return mLeaveRequest;
	}
	
	protected abstract String printStaffInfo();
	
	/*For Chain Of Responsibility Pattern*/
	public abstract void handleLeaveRequest(LeaveRequest leaveRequest);
	
	protected void passTheRequestToNextStaff(LeaveRequest leaveRequest){
        if(mNextHandleStaff != null) {
        	mNextHandleStaff.handleLeaveRequest(leaveRequest);
         }
	}
	
	public void setNextHandleStaff(AbstractStaff handleStaff){
		this.mNextHandleStaff = handleStaff;
	}
	
	public AbstractStaff getNextHandleStaff(){
		return mNextHandleStaff;
	}
}
