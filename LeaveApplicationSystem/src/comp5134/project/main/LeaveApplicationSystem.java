package comp5134.project.main;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp5134.project.controller.LoginController;
import comp5134.project.controller.MaintainLeaveController;
import comp5134.project.controller.MaintainStaffController;
import comp5134.project.model.AbstractStaff;
import comp5134.project.model.AbstractStaffFactory;
import comp5134.project.model.Administrator;
import comp5134.project.model.LogoutListener;
import comp5134.project.model.UserToken;
import comp5134.project.view.LoginJFrame;
import comp5134.project.view.MaintainLeaveJFrame;
import comp5134.project.view.MaintainStaffJFrame;

public class LeaveApplicationSystem implements LogoutListener{
	
	private List<AbstractStaff> mStaffRecordList;
	private Map<String, List<AbstractStaff>> mAssignStaffRecordMap;
	private UserToken mUserToken;
	
	private LoginJFrame mLoginJFrame;
	private LoginController mLoginController;
	
	private MaintainStaffJFrame mMaintanStaffJFrame;
	private MaintainStaffController mMaintainStaffController;
	
	private MaintainLeaveJFrame mMaintainLeaveJFrame;
	private MaintainLeaveController mMaintainLeaveController;
	
	public void init(){
		mStaffRecordList = new ArrayList<AbstractStaff>();
		mAssignStaffRecordMap = new HashMap<String,List<AbstractStaff>>();
		
		/* Default add the administrator account*/
		mStaffRecordList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.ADMINISTRATOR_TYPE, "chuntsui", "Chun", "TSUI", "1", "Administrator", 23, 'M', "admin@polyu.edu.hk", "Admin"));
		
		/* Also add some staff and supervisor using for demo*/
		mStaffRecordList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.STAFF_TYPE, "peterwong", "Peter", "Wong", "1", "Programmer", 19, 'M', "PeterWong@polyu.edu.hk", "Developement"));
		mStaffRecordList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.STAFF_TYPE, "marywong", "Mary", "Wong", "1", "Senior Programmer", 24, 'F', "MaryWong@polyu.edu.hk", "Developement"));
		mStaffRecordList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.STAFF_TYPE, "lalachan", "Lala", "Chan", "1", "Analysis Programmer", 18, 'F', "LalaWong@polyu.edu.hk", "Developement"));
		
		mStaffRecordList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.SUPERVISOR_TYPE, "benchan", "Ben", "Chan", "1", "Project Manager", 43, 'M', "LalaWong@polyu.edu.hk", "Developement"));
		mStaffRecordList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.SUPERVISOR_TYPE, "fishlung", "Fish", "Lung", "1", "Marketing Manager", 38, 'M', "KenCheung@polyu.edu.hk", "Marketing"));
		mStaffRecordList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.SUPERVISOR_TYPE, "helenchoi", "Helen", "Choi", "1", "Accounting Manager", 33, 'F', "HelenChoi@polyu.edu.hk", "Accounting"));
		
		mStaffRecordList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.DIRECTOR_TYPE, "bosschan", "Boss", "Chan", "1", "Boss", 33, 'F', "Boss@polyu.edu.hk", "N/A"));
		
		/* Due to each staff type user must contain one supervisor, so also assign the staff to supervisor */
		List<AbstractStaff> manageStaffList = new ArrayList<AbstractStaff>();
		manageStaffList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.STAFF_TYPE, "peterwong", "Peter", "Wong", "1", "Programmer", 19, 'M', "PeterWong@polyu.edu.hk", "Developement"));
		manageStaffList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.STAFF_TYPE, "marywong", "Mary", "Wong", "1", "Senior Programmer", 24, 'F', "MaryWong@polyu.edu.hk", "Developement"));
		manageStaffList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.STAFF_TYPE, "lalachan", "Lala", "Chan", "1", "Analysis Programmer", 18, 'F', "LalaWong@polyu.edu.hk", "Developement"));
			
		/* 'benchan' is the staffId */
		mAssignStaffRecordMap.put("benchan", manageStaffList);
	}
	
	public void run(){
		/* if not user token, it represent user did not login in the system and show login page */
		if(mUserToken == null){ 
			mLoginJFrame = new LoginJFrame();
			mLoginController = new LoginController(mLoginJFrame, this);
			mLoginController.initListener();
			mLoginController.showLoginJFrame(true);
		} else {
			/* If the administrator login, then show the maintain staff page */
			if(mUserToken.getLoginStaff() instanceof Administrator) {
				mMaintanStaffJFrame = new MaintainStaffJFrame();
				mMaintanStaffJFrame.setOnLogOutListener(this);
				mMaintainStaffController = new MaintainStaffController(mMaintanStaffJFrame, this);
				mMaintainStaffController.initListener();
				mMaintainStaffController.showMaintainJFrame(true);
			/* If the other type user login, then show the Request Leave page */
			} else {
				mMaintainLeaveJFrame = new MaintainLeaveJFrame();
				mMaintainLeaveJFrame.setOnLogOutListener(this);
				mMaintainLeaveController = new MaintainLeaveController(mMaintainLeaveJFrame, this);
				mMaintainLeaveController.initListener();
				mMaintainLeaveController.initTheButton();
				mMaintainLeaveController.showMaintainJFrame(true);
			}
		}
	}
	
	public void setStaffRecord(List<AbstractStaff> staffRecordList){
		this.mStaffRecordList = staffRecordList;
	}
	
	public List<AbstractStaff> getStaffRecord(){
		return mStaffRecordList;
	}
	
	public void setUserToken(UserToken userToken){
		mUserToken = userToken;
	}
	
	public Map<String, List<AbstractStaff>> getAssignStaffRecordMap(){
		return mAssignStaffRecordMap;
	}

	public void onLogout() {
		mLoginJFrame.setVisible(true);
	}
	
	public UserToken getUserToken(){
		return mUserToken;
	}
}
