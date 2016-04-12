package comp5134.project.controller;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comp5134.project.main.LeaveApplicationSystem;
import comp5134.project.view.AssignStaffJFrame;
import comp5134.project.view.CreateStaffJFrame;
import comp5134.project.view.DeleteStaffJFrame;
import comp5134.project.view.MaintainStaffJFrame;

public class MaintainStaffController implements ActionListener{
	private MaintainStaffJFrame mMaintainStaffJframe;
	private LeaveApplicationSystem mLeaveApplicationSystm;
	private CreateStaffJFrame mCreateStaffJFrame;
	private CreateStaffController mCreateStaffController;
	private DeleteStaffJFrame mDeleteStaffJFrame;
	private DeleteStaffController mDeleteStaffController;
	private AssignStaffJFrame mAssignStaffJFrame;
	private AssignStaffController mAssignStaffController;
	
	public MaintainStaffController(MaintainStaffJFrame maintainStaffJframe, LeaveApplicationSystem leaveApplicationSystem){
		this.mMaintainStaffJframe = maintainStaffJframe;
		this.mLeaveApplicationSystm = leaveApplicationSystem;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().equals("Create Staff")){
			mCreateStaffJFrame = new CreateStaffJFrame();
			mCreateStaffController = new CreateStaffController(mCreateStaffJFrame, mLeaveApplicationSystm);
			mCreateStaffController.initData();
			mCreateStaffController.initListener();
			mCreateStaffController.showCreateStaffJFrame(true);
		} else if(actionEvent.getActionCommand().equals("Delete Staff")){
			mDeleteStaffJFrame = new DeleteStaffJFrame();
			mDeleteStaffController = new DeleteStaffController(mDeleteStaffJFrame, mLeaveApplicationSystm);
			mDeleteStaffController.initListener();
			mDeleteStaffController.showCreateStaffJFrame(true);
		} else if(actionEvent.getActionCommand().equals("Assign Staff")){
			mAssignStaffJFrame = new AssignStaffJFrame();
			mAssignStaffController = new AssignStaffController(mAssignStaffJFrame, mLeaveApplicationSystm);
			mAssignStaffController.initListener();
			mAssignStaffController.showAssignStaffJFrame(true);
		}
	}
	
	public void initListener(){
		mMaintainStaffJframe.getCreateStaffJButton().addActionListener(this);
		mMaintainStaffJframe.getDeleteStaffJButton().addActionListener(this);
		mMaintainStaffJframe.getAssignStaffJButton().addActionListener(this);
	}
	
	public void showMaintainJFrame(boolean isShow){
		mMaintainStaffJframe.setVisible(isShow);
	}
	
}
