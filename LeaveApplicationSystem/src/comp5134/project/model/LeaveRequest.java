package comp5134.project.model;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
public class LeaveRequest {
	public static final int PENDING = 0;
	public static final int FAIL = 1;
	public static final int SUCCESS = 2;
	
	private String mStartDate;
	private String mEndDate;
	private int mStatus;
	private boolean mIsApproveBySupervisor;
	private boolean mIsApproveByDirector;
	private String mApproveResult;

	public LeaveRequest(String startDate,String endDate){
		this.mStartDate = startDate;
		this.mEndDate = endDate;
		this.mStatus = 0;
		this.mIsApproveBySupervisor = false;
		this.mIsApproveByDirector = false;
		this.mApproveResult = "Pending";
	}
	
	public void setStartDate(String startDate){
		this.mStartDate = startDate;
	}
	
	public String getStartDate(){
		return mStartDate;
	}
	
	public void setEndDate(String endDate){
		this.mEndDate = endDate;
	}
	
	public String getEndDate(){
		return mEndDate;
	}
	
	
	public void setStatus(int status){
		this.mStatus = status;
	}
	
	public int getStatus(){
		return mStatus;
	}
	
	public void setIsApproveBySupervisor(boolean isApproveBySupervisor){
		this.mIsApproveBySupervisor = isApproveBySupervisor;
	}
	
	public boolean getIsApproveBySupervisor(){
		return mIsApproveBySupervisor;
	}
	
	public void setIsApproveByDirector(boolean isApproveByDirector){
		this.mIsApproveByDirector = isApproveByDirector;
	}
	
	public boolean getIsApproveByDirect(){
		return mIsApproveByDirector;
	}
	
	public void setApproveResult(String approveResult){
		this.mApproveResult = approveResult;
	}
	
	public String getApproveResult(){
		return mApproveResult;
	}
}
