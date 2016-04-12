package comp5134.project.controller;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import comp5134.project.main.DialogFactory;
import comp5134.project.main.LeaveApplicationSystem;
import comp5134.project.model.AbstractStaff;
import comp5134.project.model.AbstractStaffFactory;
import comp5134.project.model.Administrator;
import comp5134.project.model.Director;
import comp5134.project.model.Staff;
import comp5134.project.model.Supervisor;
import comp5134.project.model.UserToken;
import comp5134.project.view.LoginJFrame;

public class LoginController implements ActionListener{

	private LoginJFrame mLoginJFrame;
	private LeaveApplicationSystem mLeaveApplicationSystm;
	public LoginController(LoginJFrame loginJFrame, LeaveApplicationSystem leaveApplicationSystem){
		this.mLoginJFrame = loginJFrame;
		this.mLeaveApplicationSystm = leaveApplicationSystem;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		/* Login case */
		if(actionEvent.getActionCommand().equals("Login")) {
			if(mLoginJFrame.getJTextField().getText().length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the userId!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			if(String.valueOf(mLoginJFrame.getJPasswordFiled().getPassword()).length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the password!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			AbstractStaff staff = loginProcess(mLeaveApplicationSystm.getStaffRecord());
			if(staff == null){
				DialogFactory.showMessageDialog("Login Fail", "Please make sure userId or Password is correct!", DialogFactory.INFORMATION_MESSAGE);
				return;
			} else {
				mLeaveApplicationSystm.setUserToken(new UserToken(staff, new Date()));
				mLoginJFrame.dispose();
				mLeaveApplicationSystm.run();
			}
		}
	}
	
	public void initListener(){
		mLoginJFrame.getLoginJButton().addActionListener(this);
	}
	
	public void showLoginJFrame(boolean isShow){
		mLoginJFrame.setVisible(isShow);
	}
	
	public AbstractStaff loginProcess(List<AbstractStaff> staffRecord){
		for(AbstractStaff staff : staffRecord ){
			if(staff.getStaffId().equalsIgnoreCase(mLoginJFrame.getJTextField().getText())
					&& staff.getPassword().equalsIgnoreCase(String.valueOf(mLoginJFrame.getJPasswordFiled().getPassword()))){
				if(staff instanceof Staff){
					return AbstractStaffFactory.createAbstractStaff(0, staff.getStaffId(), staff.getFirstName(), staff.getLastName(), staff.getPassword(), staff.getPosition(), staff.getAge(), 
							staff.getGender(), staff.getEmail(), staff.getDepartment());
				} else if(staff instanceof Supervisor){
					return AbstractStaffFactory.createAbstractStaff(1, staff.getStaffId(), staff.getFirstName(), staff.getLastName(), staff.getPassword(), staff.getPosition(), staff.getAge(), 
							staff.getGender(), staff.getEmail(), staff.getDepartment());
				} else if(staff instanceof Director){
					return AbstractStaffFactory.createAbstractStaff(2, staff.getStaffId(), staff.getFirstName(), staff.getLastName(), staff.getPassword(), staff.getPosition(), staff.getAge(), 
							staff.getGender(), staff.getEmail(), staff.getDepartment());
				} else if(staff instanceof Administrator){
					return AbstractStaffFactory.createAbstractStaff(3, staff.getStaffId(), staff.getFirstName(), staff.getLastName(), staff.getPassword(), staff.getPosition(), staff.getAge(), 
							staff.getGender(), staff.getEmail(), staff.getDepartment());
				}
				return staff;
			}
		}
		
		return null;
	}
}
