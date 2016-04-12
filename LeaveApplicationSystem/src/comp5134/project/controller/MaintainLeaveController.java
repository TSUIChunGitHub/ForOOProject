package comp5134.project.controller;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comp5134.project.main.DialogFactory;
import comp5134.project.main.LeaveApplicationSystem;
import comp5134.project.model.AbstractStaff;
import comp5134.project.model.Director;
import comp5134.project.model.Staff;
import comp5134.project.model.Supervisor;
import comp5134.project.view.HandleLeaveRequestJFrame;
import comp5134.project.view.MaintainLeaveJFrame;
import comp5134.project.view.MakeLeaveRequestJFrame;

public class MaintainLeaveController implements ActionListener{
	private LeaveApplicationSystem mLeaveApplicationSystm;

	private MaintainLeaveJFrame mMaintainLeaveJFrame;
	private MakeLeaveRequestJFrame mMakeLeaveRequestJFrame;
	private MakeLeaveRequestController mMakeLeaveRequestController;
	private HandleLeaveRequestJFrame mHandleLeaveReqeustJFrame;
	private HandleLeaveRequestController mHandleLeaveRequestController;
	
	public MaintainLeaveController(MaintainLeaveJFrame maintainLeaveJFrame, LeaveApplicationSystem leaveApplicationSystem){
		this.mMaintainLeaveJFrame = maintainLeaveJFrame;
		this.mLeaveApplicationSystm = leaveApplicationSystem;
	}
	
	public void initTheButton(){
		AbstractStaff staff  = mLeaveApplicationSystm.getUserToken().getLoginStaff();
		if(staff instanceof Staff){
			mMaintainLeaveJFrame.getBtnHandleLeaveRequest().setEnabled(false);
		}else if(staff instanceof Supervisor){
			// All function allow
		}else if(staff instanceof Director){
			mMaintainLeaveJFrame.getBtnReqeustToLeave().setEnabled(false);
			mMaintainLeaveJFrame.getBtnViewLeaveRequestState().setEnabled(false);
		}
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().equals("View Leave Reqeust State")){
			//Find The user record by the user token;
			AbstractStaff currentStaff = null;
			for(AbstractStaff staff : mLeaveApplicationSystm.getStaffRecord()){
				if(staff.getStaffId().equals(mLeaveApplicationSystm.getUserToken().getLoginStaff().getStaffId())){
					currentStaff = staff;
					break;
				}
			}
			if(currentStaff.getLeaveRequest() == null){
				DialogFactory.showMessageDialog("Warning", "No leave request apply!", DialogFactory.WARNING_MESSAGE);
			} else {
				DialogFactory.showMessageDialog("Warning", "You leave request status from date:"+currentStaff.getLeaveRequest().getStartDate()
						+" to date "+currentStaff.getLeaveRequest().getEndDate()+" is:"+currentStaff.getLeaveRequest().getApproveResult(), DialogFactory.WARNING_MESSAGE);
			}
		} else if(actionEvent.getActionCommand().equals("Request To Leave")){
			mMakeLeaveRequestJFrame = new MakeLeaveRequestJFrame();
			mMakeLeaveRequestController = new MakeLeaveRequestController(mMakeLeaveRequestJFrame, mLeaveApplicationSystm);
			mMakeLeaveRequestController.initListener();
			mMakeLeaveRequestController.showMakeLeaveRequestJFrame(true);
		} else if(actionEvent.getActionCommand().equals("Handle Leave Request")){
			mHandleLeaveReqeustJFrame = new HandleLeaveRequestJFrame();
			mHandleLeaveRequestController = new HandleLeaveRequestController(mHandleLeaveReqeustJFrame, mLeaveApplicationSystm);
			mHandleLeaveRequestController.initData();
			mHandleLeaveRequestController.initListener();
			mHandleLeaveRequestController.showHandleLeaveRequestJFrame(true);
		}
	}
	
	public void initListener(){
		mMaintainLeaveJFrame.getBtnViewLeaveRequestState().addActionListener(this);
		mMaintainLeaveJFrame.getBtnReqeustToLeave().addActionListener(this);
		mMaintainLeaveJFrame.getBtnHandleLeaveRequest().addActionListener(this);
	}
	
	public void showMaintainJFrame(boolean isShow){
		mMaintainLeaveJFrame.setVisible(isShow);
	}
	
}
