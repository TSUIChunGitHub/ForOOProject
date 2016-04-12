package comp5134.project.controller;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import comp5134.project.main.DialogFactory;
import comp5134.project.main.LeaveApplicationSystem;
import comp5134.project.main.StaffTypeEnum;
import comp5134.project.model.AbstractStaff;
import comp5134.project.model.AbstractStaffFactory;
import comp5134.project.model.Director;
import comp5134.project.model.LeaveRequest;
import comp5134.project.model.Staff;
import comp5134.project.model.Supervisor;
import comp5134.project.view.MakeLeaveRequestJFrame;

public class MakeLeaveRequestController implements ActionListener {
	private LeaveApplicationSystem mLeaveApplicationSystm;
	private MakeLeaveRequestJFrame mMakeLeaveRequestJFrame;
	
	public MakeLeaveRequestController(MakeLeaveRequestJFrame makeLeaveRequestJFrame, LeaveApplicationSystem leaveApplicationSystem){
		this.mMakeLeaveRequestJFrame = makeLeaveRequestJFrame;
		this.mLeaveApplicationSystm = leaveApplicationSystem;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().equals("Add Request")){
			/* Ensure the date Picker have choose the date */
			if(mMakeLeaveRequestJFrame.getFromDatePicker().getModel().getValue() == null){
				DialogFactory.showMessageDialog("Warning", "Please choose the start of leave date!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(mMakeLeaveRequestJFrame.getToDatePicker().getModel().getValue() == null){
				DialogFactory.showMessageDialog("Warning", "Please choose the end of leave date!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
		    Date fromDate = (Date) mMakeLeaveRequestJFrame.getFromDatePicker().getModel().getValue();
		    DateFormat fromDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			
		    Date toDate = (Date) mMakeLeaveRequestJFrame.getToDatePicker().getModel().getValue();
		    DateFormat toDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		    
		    if(toDate.before(fromDate)){
				DialogFactory.showMessageDialog("Error", "Please choose the end date befor the start date!", DialogFactory.ERROR_MESSAGE);
				return;
		    }
		    
		    LeaveRequest leaveRequest = new LeaveRequest(fromDateFormat.format(fromDate) , toDateFormat.format(toDate));
		    
		    /* If the type of user is staff, the request will pass to supervisor, then director, So the chain will be staff-> supervisor -> director */
		    if(mLeaveApplicationSystm.getUserToken().getLoginStaff() instanceof Staff){
		    
			    /* Find the staff record by the position */
			    int staffPosition = findStaffPositionByStaffId(mLeaveApplicationSystm.getUserToken().getLoginStaff().getStaffId());
			    
			    /* Check whether have request */
			    if(mLeaveApplicationSystm.getStaffRecord().get(staffPosition).getLeaveRequest() != null 
			    		&& mLeaveApplicationSystm.getStaffRecord().get(staffPosition).getLeaveRequest().getStatus() == LeaveRequest.PENDING){
			    	DialogFactory.showMessageDialog("Error", "You already make a leave request", DialogFactory.ERROR_MESSAGE);
			    	return;
			    }
			    
			    
			    mLeaveApplicationSystm.getStaffRecord().get(staffPosition).setLeaveRequest(leaveRequest);
			    /* Find the name the manage supervisor */
			    String key = findSupervisorStaffId(mLeaveApplicationSystm.getUserToken().getLoginStaff().getStaffId());
			    int supervisorPosition = findStaffPositionByStaffId(key);
	
			    AbstractStaff manageSupervisor = mLeaveApplicationSystm.getStaffRecord().get(supervisorPosition);
			    AbstractStaff newManageSupervisor = AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.SUPERVISOR_TYPE,
			    		manageSupervisor.getStaffId(), manageSupervisor.getFirstName(), manageSupervisor.getLastName(), manageSupervisor.getPassword(),
			    		manageSupervisor.getPosition(), manageSupervisor.getAge(), manageSupervisor.getGender(), manageSupervisor.getEmail(), manageSupervisor.getDepartment());
			    mLeaveApplicationSystm.getStaffRecord().get(staffPosition).setNextHandleStaff(newManageSupervisor);
			    
			    AbstractStaff managerDirector = mLeaveApplicationSystm.getStaffRecord().get(findDirectorPositon());
			    AbstractStaff newManageDirector = AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.DIRECTOR_TYPE,
			    		managerDirector.getStaffId(), managerDirector.getFirstName(), managerDirector.getLastName(), managerDirector.getPassword(),
			    		managerDirector.getPosition(), managerDirector.getAge(), managerDirector.getGender(), managerDirector.getEmail(), managerDirector.getDepartment());
			    
			    mLeaveApplicationSystm.getStaffRecord().get(staffPosition).getNextHandleStaff().setNextHandleStaff(newManageDirector);
			    mLeaveApplicationSystm.getStaffRecord().get(staffPosition).getNextHandleStaff().getNextHandleStaff().setNextHandleStaff(null);
			    /* Start the chain */
			    mLeaveApplicationSystm.getStaffRecord().get(staffPosition).handleLeaveRequest(leaveRequest);
			    
			    DialogFactory.showMessageDialog("Success", "The request has make wait for reply!", DialogFactory.WARNING_MESSAGE);
			    /* If the type of user is supervisor,the chain will be more simple,  the request will pass to director only So the chain will be supervisor -> director */
		    } else if(mLeaveApplicationSystm.getUserToken().getLoginStaff() instanceof Supervisor){
		    	int supervisorPosition = findStaffPositionByStaffId(mLeaveApplicationSystm.getUserToken().getLoginStaff().getStaffId());
		    	
			    /* Check whether have request */
			    if(mLeaveApplicationSystm.getStaffRecord().get(supervisorPosition).getLeaveRequest() != null 
			    		&& mLeaveApplicationSystm.getStaffRecord().get(supervisorPosition).getLeaveRequest().getStatus() == LeaveRequest.PENDING){
			    	DialogFactory.showMessageDialog("Error", "You already make a leave request", DialogFactory.ERROR_MESSAGE);
			    	return;
			    }
		    	
			    AbstractStaff managerDirector = mLeaveApplicationSystm.getStaffRecord().get(findDirectorPositon());
			    AbstractStaff newManageDirector = AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.DIRECTOR_TYPE,
			    		managerDirector.getStaffId(), managerDirector.getFirstName(), managerDirector.getLastName(), managerDirector.getPassword(),
			    		managerDirector.getPosition(), managerDirector.getAge(), managerDirector.getGender(), managerDirector.getEmail(), managerDirector.getDepartment());
			    
			    mLeaveApplicationSystm.getStaffRecord().get(supervisorPosition).setNextHandleStaff(newManageDirector);
			    
			    mLeaveApplicationSystm.getStaffRecord().get(supervisorPosition).getNextHandleStaff().setNextHandleStaff(null);
			    /* Due to supervisor only need to approve by director, so assume the request is approve by supervisor */
			    leaveRequest.setIsApproveBySupervisor(true);
			    /* Start the chain */
			    mLeaveApplicationSystm.getStaffRecord().get(supervisorPosition).handleLeaveRequest(leaveRequest);
			    
			    DialogFactory.showMessageDialog("Success", "The request has make wait for reply!", DialogFactory.WARNING_MESSAGE);
		    }
		}
	}
	
	public void initListener(){
		mMakeLeaveRequestJFrame.getBtnAddRequest().addActionListener(this);

	}
	
	public void showMakeLeaveRequestJFrame(boolean isShow){
		mMakeLeaveRequestJFrame.setVisible(isShow);
	}
	
	private int findStaffPositionByStaffId(String staffId){
	    for(int index = 0;index < mLeaveApplicationSystm.getStaffRecord().size();index++){
	    	if(mLeaveApplicationSystm.getStaffRecord().get(index).getStaffId().equalsIgnoreCase(staffId)){
	    		return index;
	    	}
	    }
	    return -1;
	}
	
	private String findSupervisorStaffId(String staffId){
	    /* Find the position of the staff 's supervisor */
		for (Object key : mLeaveApplicationSystm.getAssignStaffRecordMap().keySet()) {
			List<AbstractStaff> manageStaffRecord = mLeaveApplicationSystm.getAssignStaffRecordMap().get(key);
			for(int index = 0;index < manageStaffRecord.size();index++){
				if(manageStaffRecord.get(index).getStaffId().equalsIgnoreCase(staffId)){
					return (String)key;
				}
			}
		}
		return null;
	}
	
	private int findDirectorPositon(){
	    for(int index = 0;index < mLeaveApplicationSystm.getStaffRecord().size();index++){
	    	if(mLeaveApplicationSystm.getStaffRecord().get(index) instanceof Director){
	    		return index;
	    	}
	    }
	    return -1;
	}
}
