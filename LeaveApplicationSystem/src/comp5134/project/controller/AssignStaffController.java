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
import comp5134.project.main.StaffTypeEnum;
import comp5134.project.model.AbstractStaff;
import comp5134.project.model.AbstractStaffFactory;
import comp5134.project.model.Administrator;
import comp5134.project.model.Director;
import comp5134.project.model.Staff;
import comp5134.project.model.Supervisor;
import comp5134.project.view.AssignStaffJFrame;


public class AssignStaffController implements ActionListener{
	private AssignStaffJFrame mAssignStaffJFrame;
	private LeaveApplicationSystem mLeaveApplicationSystm;
	public AssignStaffController(AssignStaffJFrame assignStaffJFrame, LeaveApplicationSystem leaveApplicationSystem){
		this.mAssignStaffJFrame = assignStaffJFrame;
		this.mLeaveApplicationSystm = leaveApplicationSystem;
		mAssignStaffJFrame.initFirstStaffRecord(mLeaveApplicationSystm.getStaffRecord().get(0));
		mAssignStaffJFrame.initStaffIdComboBoxData(mLeaveApplicationSystm.getStaffRecord());
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().equals("Assign")){
			/* In order to easy to process. First, get the object  */
			AbstractStaff assignStaff = mLeaveApplicationSystm.getStaffRecord()
					.get(mAssignStaffJFrame.getCbAssignStaffId().getSelectedIndex());
			AbstractStaff toStaff = mLeaveApplicationSystm.getStaffRecord()
					.get(mAssignStaffJFrame.getCbToStaffId().getSelectedIndex());
			
			/* It is impossible to manage himself/herself */
			if(assignStaff.getStaffId().equalsIgnoreCase(toStaff.getStaffId())){
				DialogFactory.showMessageDialog("Error", "Can not manage self!", DialogFactory.ERROR_MESSAGE);
				return;
			}
			
			/* Basically, Director manger all staff. Therefore, it is impossible duplicate assign*/
			if(toStaff instanceof Director){
				DialogFactory.showMessageDialog("Error", 
						"Staff already manager By Director", DialogFactory.ERROR_MESSAGE);
				return;
			}
			
			/* administrator,supervisor and director type user not able to assign for supervise*/
			if(assignStaff instanceof Administrator){
				DialogFactory.showMessageDialog("Error", 
						"Unless the director, All staff can not manage the system adminsitrator.", DialogFactory.ERROR_MESSAGE);
				return;
			}
			
			if(assignStaff instanceof Supervisor){
				DialogFactory.showMessageDialog("Error", 
						"Unless the director, All staff can not manage the supervisor.", DialogFactory.ERROR_MESSAGE);
				return;
			}
			
			if(assignStaff instanceof Director){
				DialogFactory.showMessageDialog("Error", 
						"Are you kidding me? Are You try to assign the director for manage? No way.", DialogFactory.ERROR_MESSAGE);
				return;
			}
			/* Normally, Staff type user must assign to one supervisor when create the record ,
			 * In addition ,the system not allow each staff type user have two or more supervisor*/
			if(assignStaff instanceof Staff && toStaff instanceof Supervisor){
				DialogFactory.showMessageDialog("Error", "The system not allow staff type user manage by two supervisor or duplicate supervisor!", DialogFactory.ERROR_MESSAGE);
				return;
			}
			
			
			/* Check whether manage by relevant staff*/
			List<AbstractStaff> assignStaffList = mLeaveApplicationSystm.getAssignStaffRecordMap().get(assignStaff.getStaffId());
			if(assignStaffList != null){
				for(AbstractStaff staff : assignStaffList){
					if(staff.getStaffId().equals(toStaff.getStaffId())){
						DialogFactory.showMessageDialog("Error", "Staff is already manage by relevant staff!", DialogFactory.ERROR_MESSAGE);
						return;
					}
				}
			}
			
			List<AbstractStaff> manageStaffList = mLeaveApplicationSystm.getAssignStaffRecordMap().get(toStaff.getStaffId());
			/* Due to the list is empty, it represent not able to duplicate so add to the map directly */
			if(manageStaffList == null){
				manageStaffList = new ArrayList<AbstractStaff>();
				manageStaffList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.STAFF_TYPE,
						assignStaff.getStaffId(), assignStaff.getFirstName(),
						assignStaff.getLastName(), assignStaff.getPassword(),
						assignStaff.getPosition(), assignStaff.getAge(),assignStaff.getGender(),
						assignStaff.getEmail(), assignStaff.getDepartment()));
				mLeaveApplicationSystm.getAssignStaffRecordMap().put(toStaff.getStaffId(), manageStaffList);
				DialogFactory.showMessageDialog("Success", "Assign staff to manage complete!", DialogFactory.INFORMATION_MESSAGE);
			}else{
				 /* Check whether duplicate assign*/
				for(AbstractStaff staff : manageStaffList){
					if(staff.getStaffId().equals(assignStaff.getStaffId())){
						DialogFactory.showMessageDialog("Error", "Cannot duplicate assign!", DialogFactory.ERROR_MESSAGE);
						return;
					}
				}
				manageStaffList.add(AbstractStaffFactory.createAbstractStaff(StaffTypeEnum.STAFF_TYPE,
						assignStaff.getStaffId(), assignStaff.getFirstName(),
						assignStaff.getLastName(), assignStaff.getPassword(),
						assignStaff.getPosition(), assignStaff.getAge(),assignStaff.getGender(),
						assignStaff.getEmail(), assignStaff.getDepartment()));
				DialogFactory.showMessageDialog("Success", "Assign staff to manage complete!", DialogFactory.INFORMATION_MESSAGE);
			}
		}
	}
		
	public void initListener(){
		mAssignStaffJFrame.getCbAssignStaffId().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if(itemEvent.getStateChange() == ItemEvent.SELECTED){
					AbstractStaff staff = mLeaveApplicationSystm.getStaffRecord()
							.get(mAssignStaffJFrame.getCbAssignStaffId().getSelectedIndex());
					mAssignStaffJFrame.getTxtAssignFirstName().setText(staff.getFirstName());
					mAssignStaffJFrame.getTxtAssignLastName().setText(staff.getLastName());
					mAssignStaffJFrame.getTxtAssignPosition().setText(staff.getPosition());
					mAssignStaffJFrame.getTxtAssignDeapartment().setText(staff.getDepartment());
					if(staff instanceof Administrator){
						mAssignStaffJFrame.getTxtAssignType().setText(Administrator.class.getSimpleName());
					}else if(staff instanceof Staff){
						mAssignStaffJFrame.getTxtAssignType().setText(Staff.class.getSimpleName());
					}else if(staff instanceof Supervisor){
						mAssignStaffJFrame.getTxtAssignType().setText(Supervisor.class.getSimpleName());
					}else if(staff instanceof Director){
						mAssignStaffJFrame.getTxtAssignType().setText(Director.class.getSimpleName());
					}else{
						// Unexpect case
					}
				}
			}
		});

		mAssignStaffJFrame.getCbToStaffId().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if(itemEvent.getStateChange() == ItemEvent.SELECTED){
					AbstractStaff staff = mLeaveApplicationSystm.getStaffRecord()
							.get(mAssignStaffJFrame.getCbToStaffId().getSelectedIndex());
					mAssignStaffJFrame.getTxtToFirstName().setText(staff.getFirstName());
					mAssignStaffJFrame.getTxtToLastName().setText(staff.getLastName());
					mAssignStaffJFrame.getTxtToPosition().setText(staff.getPosition());
					mAssignStaffJFrame.getTxtToDepartment().setText(staff.getDepartment());
					if(staff instanceof Administrator){
						mAssignStaffJFrame.getTxtToType().setText(Administrator.class.getSimpleName());
					}else if(staff instanceof Staff){
						mAssignStaffJFrame.getTxtToType().setText(Staff.class.getSimpleName());
					}else if(staff instanceof Supervisor){
						mAssignStaffJFrame.getTxtToType().setText(Supervisor.class.getSimpleName());
					}else if(staff instanceof Director){
						mAssignStaffJFrame.getTxtToType().setText(Director.class.getSimpleName());
					}else{
						// Unexpect case
					}
				}
			}
		});
		mAssignStaffJFrame.getBtnAssign().addActionListener(this);
	}
	
	public void showAssignStaffJFrame(boolean isShow){
		mAssignStaffJFrame.setVisible(isShow);
	}

}
