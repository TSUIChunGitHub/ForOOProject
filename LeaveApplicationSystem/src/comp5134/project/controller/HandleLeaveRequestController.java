package comp5134.project.controller;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import comp5134.project.main.DialogFactory;
import comp5134.project.main.LeaveApplicationSystem;
import comp5134.project.model.AbstractStaff;
import comp5134.project.model.Director;
import comp5134.project.model.LeaveRequest;
import comp5134.project.model.Supervisor;
import comp5134.project.view.HandleLeaveRequestJFrame;

public class HandleLeaveRequestController implements ActionListener, ItemListener{
	private LeaveApplicationSystem mLeaveApplicationSystm;
	private HandleLeaveRequestJFrame mHandleLeaveRequestJFrame;
	private List<Integer> mStaffPosition;
	
	public HandleLeaveRequestController(HandleLeaveRequestJFrame handleLeaveRequestJFrame, LeaveApplicationSystem leaveApplicationSystem){
		this.mHandleLeaveRequestJFrame = handleLeaveRequestJFrame;
		this.mLeaveApplicationSystm = leaveApplicationSystem;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		int position = findStaffPositionByStaffId(mHandleLeaveRequestJFrame.getCbStaffId().getSelectedItem().toString());
		LeaveRequest leaveRequest = mLeaveApplicationSystm.getStaffRecord().get(position).getLeaveRequest();
		
		if(actionEvent.getActionCommand().equals("Endorse")){
			/* For Supervisor approve the leave request */
			if(mLeaveApplicationSystm.getUserToken().getLoginStaff() instanceof Supervisor){
				if(leaveRequest.getIsApproveBySupervisor()){
					DialogFactory.showMessageDialog("Error", "Supervisor already proccess the request!", DialogFactory.ERROR_MESSAGE);
					return;
				}
				leaveRequest = mLeaveApplicationSystm.getStaffRecord().get(position).getLeaveRequest();
				leaveRequest.setIsApproveBySupervisor(true);
				mLeaveApplicationSystm.getStaffRecord().get(position).handleLeaveRequest(leaveRequest);
				DialogFactory.showMessageDialog("Success", "Supervisor endorse the request!", DialogFactory.INFORMATION_MESSAGE);
			/* For Director decline the leave request */
			} else if(mLeaveApplicationSystm.getUserToken().getLoginStaff() instanceof Director){
				if(leaveRequest.getIsApproveByDirect()){
					DialogFactory.showMessageDialog("Error", "Director already proccess the request!", DialogFactory.ERROR_MESSAGE);
					return;
				}
				leaveRequest = mLeaveApplicationSystm.getStaffRecord().get(position).getLeaveRequest();
				leaveRequest.setIsApproveByDirector(true);
				leaveRequest.setStatus(LeaveRequest.SUCCESS);
				mLeaveApplicationSystm.getStaffRecord().get(position).handleLeaveRequest(leaveRequest);
				DialogFactory.showMessageDialog("Success", "Director endorse the request!", DialogFactory.INFORMATION_MESSAGE);
			}
			
		} else if(actionEvent.getActionCommand().equals("Decline")){
			/* For Supervisor decline the leave request */
			if(mLeaveApplicationSystm.getUserToken().getLoginStaff() instanceof Supervisor){
				if(leaveRequest.getIsApproveBySupervisor()){
					DialogFactory.showMessageDialog("Error", "Director already proccess the request!", DialogFactory.ERROR_MESSAGE);
					return;
				}
				leaveRequest = mLeaveApplicationSystm.getStaffRecord().get(position).getLeaveRequest();
				leaveRequest.setIsApproveBySupervisor(true);
				leaveRequest.setStatus(LeaveRequest.FAIL);
				mLeaveApplicationSystm.getStaffRecord().get(position).handleLeaveRequest(leaveRequest);
				DialogFactory.showMessageDialog("Faile", "Supervisor Decline the request......", DialogFactory.INFORMATION_MESSAGE);
				/* For Director decline the leave request */
			} else if(mLeaveApplicationSystm.getUserToken().getLoginStaff() instanceof Director){
				if(leaveRequest.getIsApproveByDirect()){
					DialogFactory.showMessageDialog("Error", "Director already proccess the request!", DialogFactory.ERROR_MESSAGE);
					return;
				}
				leaveRequest = mLeaveApplicationSystm.getStaffRecord().get(position).getLeaveRequest();
				leaveRequest.setIsApproveByDirector(true);
				leaveRequest.setStatus(LeaveRequest.FAIL);
				mLeaveApplicationSystm.getStaffRecord().get(position).handleLeaveRequest(leaveRequest);
				DialogFactory.showMessageDialog("Fail", "Director Decline the request......", DialogFactory.INFORMATION_MESSAGE);
			}
		}
	}
	
	public void initListener(){
		mHandleLeaveRequestJFrame.getBtnEndorse().addActionListener(this);
		mHandleLeaveRequestJFrame.getBtnDecline().addActionListener(this);
		mHandleLeaveRequestJFrame.getCbStaffId().addItemListener(this);
	}
	
	public void showHandleLeaveRequestJFrame(boolean isShow){
		mHandleLeaveRequestJFrame.setVisible(isShow);
	}

	public void initData(){
		/* For Supervisor handle leave request */
		if(mLeaveApplicationSystm.getUserToken().getLoginStaff() instanceof Supervisor){
			List<AbstractStaff> staffList = mLeaveApplicationSystm.getAssignStaffRecordMap()
					.get(mLeaveApplicationSystm.getUserToken().getLoginStaff().getStaffId());
			if(staffList == null){
				return;
			}
			mStaffPosition = new ArrayList<Integer>();
			for(AbstractStaff staff : staffList){
				for(int index = 0;index < mLeaveApplicationSystm.getStaffRecord().size();index++){
					if(staff.getStaffId().equalsIgnoreCase(mLeaveApplicationSystm.getStaffRecord().get(index).getStaffId())){
						if(mLeaveApplicationSystm.getStaffRecord().get(index).getLeaveRequest() != null){
							if(!mLeaveApplicationSystm.getStaffRecord().get(index).getLeaveRequest().getIsApproveBySupervisor()
									&& mLeaveApplicationSystm.getStaffRecord().get(index).getLeaveRequest().getStatus() == LeaveRequest.PENDING){
								mStaffPosition.add(index);
								continue;
							}
						}
					}
				}
			}
			
			/* Init comboBox item */
			for(int index = 0; index < mStaffPosition.size();index++){
				if(mLeaveApplicationSystm.getStaffRecord().get(mStaffPosition.get(index)).getLeaveRequest() != null){
					mHandleLeaveRequestJFrame.getCbStaffId().addItem(mLeaveApplicationSystm.getStaffRecord().get(mStaffPosition.get(index)).getStaffId());
				}
			}
			
			if(mStaffPosition.size() > 0){
				mHandleLeaveRequestJFrame.getTxtFromDate().setText(mLeaveApplicationSystm.getStaffRecord()
						.get(mStaffPosition.get(0)).getLeaveRequest().getStartDate());
				mHandleLeaveRequestJFrame.getTxtToDate().setText(mLeaveApplicationSystm.getStaffRecord()
						.get(mStaffPosition.get(0)).getLeaveRequest().getEndDate());
			}
		/* For Director handle request */
		} else if(mLeaveApplicationSystm.getUserToken().getLoginStaff() instanceof Director){
			mStaffPosition = new ArrayList<Integer>();
			for(int index = 0;index < mLeaveApplicationSystm.getStaffRecord().size();index++){
				if(mLeaveApplicationSystm.getStaffRecord().get(index).getLeaveRequest() != null){
					if(mLeaveApplicationSystm.getStaffRecord().get(index).getLeaveRequest().getIsApproveBySupervisor()
							&& mLeaveApplicationSystm.getStaffRecord().get(index).getLeaveRequest().getStatus() == LeaveRequest.PENDING){
						mStaffPosition.add(index);
					}
				}
			}
			/* Init comboBox item */
			for(int index = 0; index < mStaffPosition.size();index++){
				if(mLeaveApplicationSystm.getStaffRecord().get(mStaffPosition.get(index)).getLeaveRequest() != null){
					mHandleLeaveRequestJFrame.getCbStaffId().addItem(mLeaveApplicationSystm.getStaffRecord().get(mStaffPosition.get(index)).getStaffId());
				}
			}
			
			if(mStaffPosition.size() > 0){
				mHandleLeaveRequestJFrame.getTxtFromDate().setText(mLeaveApplicationSystm.getStaffRecord()
						.get(mStaffPosition.get(0)).getLeaveRequest().getStartDate());
				mHandleLeaveRequestJFrame.getTxtToDate().setText(mLeaveApplicationSystm.getStaffRecord()
						.get(mStaffPosition.get(0)).getLeaveRequest().getEndDate());
			}
		
		}
	}
	
	private int findStaffPositionByStaffId(String staffId){
	    for(int index = 0;index < mLeaveApplicationSystm.getStaffRecord().size();index++){
	    	if(mLeaveApplicationSystm.getStaffRecord().get(index).getStaffId().equalsIgnoreCase(staffId)){
	    		return index;
	    	}
	    }
	    return -1;
	}

	public void itemStateChanged(ItemEvent itemEvent) {
		if(itemEvent.getStateChange() == ItemEvent.SELECTED){
			int staffPosition = findStaffPositionByStaffId(mHandleLeaveRequestJFrame.getCbStaffId().getSelectedItem().toString());
			AbstractStaff staff = mLeaveApplicationSystm.getStaffRecord().get(staffPosition);
			mHandleLeaveRequestJFrame.getTxtFromDate().setText(staff.getLeaveRequest().getStartDate());
			mHandleLeaveRequestJFrame.getTxtToDate().setText(staff.getLeaveRequest().getEndDate());
		}
	}
}
