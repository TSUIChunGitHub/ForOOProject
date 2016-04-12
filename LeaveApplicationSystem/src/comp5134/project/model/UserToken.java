package comp5134.project.model;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.util.Date;

public class UserToken {
	private AbstractStaff mLonginStaff;
	private Date mLoginDate;
	public UserToken(AbstractStaff loginStaff, Date loginDate){
		this.mLonginStaff = loginStaff;
		this.mLoginDate = loginDate;
	}
	
	public void setLoginStaff(AbstractStaff loginStaff){
		this.mLonginStaff = loginStaff;
	}
	
	public AbstractStaff getLoginStaff(){
		return mLonginStaff;
	} 
	
	public void setDate(Date loginDate){
		this.mLoginDate = loginDate;
	}
	
	public Date getDate(){
		return mLoginDate;
	}
}
