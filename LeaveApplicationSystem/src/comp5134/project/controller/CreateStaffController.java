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
import comp5134.project.model.Director;
import comp5134.project.model.Supervisor;
import comp5134.project.view.CreateStaffJFrame;

public class CreateStaffController implements ActionListener{
	private CreateStaffJFrame mCreateStaffJFrame;
	private LeaveApplicationSystem mLeaveApplicationSystm;
	public CreateStaffController(CreateStaffJFrame createStaffJFrame, LeaveApplicationSystem leaveApplicationSystem){
		this.mCreateStaffJFrame = createStaffJFrame;
		this.mLeaveApplicationSystm = leaveApplicationSystem;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().equals("Create Record")){
			if(mCreateStaffJFrame.getTxtStaffId().getText().length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the StaffId!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(isStaffIdExist(mCreateStaffJFrame.getTxtStaffId().getText())){
				DialogFactory.showMessageDialog("Warning", "Staff Id already Exist!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(String.valueOf(mCreateStaffJFrame.getPasswordField().getPassword()).length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the Password!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(String.valueOf(mCreateStaffJFrame.getConfirmPasswordField().getPassword()).length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the Confirm Password!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(!String.valueOf(mCreateStaffJFrame.getPasswordField().getPassword()).equalsIgnoreCase(String.valueOf(mCreateStaffJFrame.getConfirmPasswordField().getPassword()))){
				DialogFactory.showMessageDialog("Warning", "Password not match the Confirm Password!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(mCreateStaffJFrame.getTxtFirstName().getText().length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the First Name!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(mCreateStaffJFrame.getTxtLastName().getText().length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the Last Name!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(mCreateStaffJFrame.getTxtPosition().getText().length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the Position!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			if(mCreateStaffJFrame.getTxtAge().getText().length() == 0){
				DialogFactory.showMessageDialog("Warning", "Please enter the Age!", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			int age = -1;
			/* Check the format of age and the age should not contain any letter or symbol*/
			try{
				age = Integer.parseInt(mCreateStaffJFrame.getTxtAge().getText());
			}catch(NumberFormatException e){
				DialogFactory.showMessageDialog("Error", "The age only can contain the number!", DialogFactory.ERROR_MESSAGE);
				return;
			}
			
			/* Make sure only one director can be create*/
			if(mCreateStaffJFrame.getCbType().getSelectedIndex() == StaffTypeEnum.DIRECTOR_TYPE){ 
				if(isContainDirector()){
					DialogFactory.showMessageDialog("Error", "The System only can contain one director!", DialogFactory.ERROR_MESSAGE);
					return;
				}
			}
			
			/* Create the record */
			mLeaveApplicationSystm.getStaffRecord().add(AbstractStaffFactory.createAbstractStaff(mCreateStaffJFrame.getCbType().getSelectedIndex(),
					mCreateStaffJFrame.getTxtStaffId().getText(), mCreateStaffJFrame.getTxtFirstName().getText(),
					mCreateStaffJFrame.getTxtLastName().getText(), String.valueOf(mCreateStaffJFrame.getPasswordField().getPassword()),
					mCreateStaffJFrame.getTxtPosition().getText(), age, mCreateStaffJFrame.getCbGender().getSelectedItem().toString().charAt(0),
					mCreateStaffJFrame.getTxtEmail().getText(), mCreateStaffJFrame.getCbDepartment().getSelectedItem().toString()));
			DialogFactory.showMessageDialog("Message", "Create Staff Record Success!", DialogFactory.INFORMATION_MESSAGE);
			
			/* Assign to relevant supervisor */
			if(mCreateStaffJFrame.getCbType().getSelectedIndex() == StaffTypeEnum.STAFF_TYPE){ 
				List<AbstractStaff> manageStaffList = mLeaveApplicationSystm.getAssignStaffRecordMap().get(mCreateStaffJFrame.getCbToSupervisor().getSelectedItem());
				if(manageStaffList == null){
					manageStaffList = new ArrayList<AbstractStaff>();
				} 
				manageStaffList.add(AbstractStaffFactory.createAbstractStaff(mCreateStaffJFrame.getCbType().getSelectedIndex(),
				mCreateStaffJFrame.getTxtStaffId().getText(), mCreateStaffJFrame.getTxtFirstName().getText(),
				mCreateStaffJFrame.getTxtLastName().getText(), String.valueOf(mCreateStaffJFrame.getPasswordField().getPassword()),
				mCreateStaffJFrame.getTxtPosition().getText(), age, mCreateStaffJFrame.getCbGender().getSelectedItem().toString().charAt(0),
				mCreateStaffJFrame.getTxtEmail().getText(), mCreateStaffJFrame.getCbDepartment().getSelectedItem().toString()));
				
				if(mLeaveApplicationSystm.getAssignStaffRecordMap().get(mCreateStaffJFrame.getCbToSupervisor().getSelectedItem()) == null){
					mLeaveApplicationSystm.getAssignStaffRecordMap().put(mCreateStaffJFrame.getCbToSupervisor().getSelectedItem().toString(), manageStaffList);
				}
			}
		}
	}
	
	public void initData(){
		mCreateStaffJFrame.getCbDepartment().addItem("Development");
		mCreateStaffJFrame.getCbDepartment().addItem("Admin");
		mCreateStaffJFrame.getCbDepartment().addItem("Marketing");
		mCreateStaffJFrame.getCbDepartment().addItem("Account");
		
		mCreateStaffJFrame.getCbType().addItem("Staff");
		mCreateStaffJFrame.getCbType().addItem("Supervisor");
		mCreateStaffJFrame.getCbType().addItem("Director");
		
		mCreateStaffJFrame.getCbGender().addItem("F");
		mCreateStaffJFrame.getCbGender().addItem("M");
		
		for(AbstractStaff staff : mLeaveApplicationSystm.getStaffRecord()){
			if(staff instanceof Supervisor){
				mCreateStaffJFrame.getCbToSupervisor().addItem(staff.getStaffId());
			}
		}
	}
	
	public void initListener(){
		mCreateStaffJFrame.getBtnCreateRecord().addActionListener(this);
		mCreateStaffJFrame.getCbType().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if(itemEvent.getStateChange() == ItemEvent.SELECTED){
					if (mCreateStaffJFrame.getCbType().getSelectedIndex() != StaffTypeEnum.STAFF_TYPE){
						mCreateStaffJFrame.getCbToSupervisor().removeAllItems();
					} else {
						for(AbstractStaff staff : mLeaveApplicationSystm.getStaffRecord()){
							if(staff instanceof Supervisor){
								mCreateStaffJFrame.getCbToSupervisor().addItem(staff.getStaffId());
							}
						}
					}
				}
			}
		});
	}
	
	public void showCreateStaffJFrame(boolean isShow){
		mCreateStaffJFrame.setVisible(isShow);
	}
	
	private boolean isStaffIdExist(String staffId){
		for(AbstractStaff staff : mLeaveApplicationSystm.getStaffRecord()){
			if(staff.getStaffId().equalsIgnoreCase(staffId)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isContainDirector(){
		for(AbstractStaff staff : mLeaveApplicationSystm.getStaffRecord()){
			if(staff instanceof Director){
				return true;
			}
		}
		return false;
	}
}
