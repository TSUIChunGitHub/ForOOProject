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
import java.util.List;

import comp5134.project.main.DialogFactory;
import comp5134.project.main.LeaveApplicationSystem;
import comp5134.project.model.AbstractStaff;
import comp5134.project.model.Administrator;
import comp5134.project.model.Director;
import comp5134.project.model.Staff;
import comp5134.project.model.Supervisor;
import comp5134.project.view.DeleteStaffJFrame;

public class DeleteStaffController implements ActionListener, ItemListener{
	private DeleteStaffJFrame mDeleteStaffJFrame;
	private LeaveApplicationSystem mLeaveApplicationSystm;
	public DeleteStaffController(DeleteStaffJFrame deleteStaffJFrame, LeaveApplicationSystem leaveApplicationSystem){
		this.mDeleteStaffJFrame = deleteStaffJFrame;
		this.mLeaveApplicationSystm = leaveApplicationSystem;
		mDeleteStaffJFrame.initFirstStaffRecord(mLeaveApplicationSystm.getStaffRecord().get(0));
		mDeleteStaffJFrame.initStaffIdComboBoxData(mLeaveApplicationSystm.getStaffRecord());
		mDeleteStaffJFrame.initManagerStaffRecordData(mLeaveApplicationSystm.getAssignStaffRecordMap()
				.get(mLeaveApplicationSystm.getStaffRecord().get(0)));

	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().equals("Delete Record")){
			AbstractStaff staff = mLeaveApplicationSystm.getStaffRecord()
					.get(mDeleteStaffJFrame.getCbStaffId().getSelectedIndex());
			
			/* Administrator should not able to delete */
			if(staff instanceof Administrator){
				DialogFactory.showMessageDialog("Warning", "Administraor account should not able to delete.", DialogFactory.WARNING_MESSAGE);
				return;
			}
			/* Director also not able to delete directly*/
			if(staff instanceof Director){
				DialogFactory.showMessageDialog("Warning", "Director account should not able to delete directly.", DialogFactory.WARNING_MESSAGE);
				return;
			}
			
			/* Also If the supervisor already manage the staff, It also cannot remove directly */
			if(staff instanceof Supervisor){
				if(mLeaveApplicationSystm.getAssignStaffRecordMap().get(staff.getStaffId()) != null){
					DialogFactory.showMessageDialog("Warning", "Supervisor now manage some staff, You can not delete the record directly!", DialogFactory.WARNING_MESSAGE);
					return;
				}
			}
			
			deleteStaffById(staff.getStaffId());
			DialogFactory.showMessageDialog("Information", "Delete Record Success!", DialogFactory.INFORMATION_MESSAGE);
			
			/* Also delete the relevant record, Such as manager record*/
			mLeaveApplicationSystm.getAssignStaffRecordMap().remove(staff.getStaffId());
			
			for (Object key : mLeaveApplicationSystm.getAssignStaffRecordMap().keySet()) {
				List<AbstractStaff> manageStaffRecord = mLeaveApplicationSystm.getAssignStaffRecordMap().get(key);
				for(int index = 0;index < manageStaffRecord.size();index++){
					if(manageStaffRecord.get(index).getStaffId().equalsIgnoreCase(staff.getStaffId())){
						manageStaffRecord.remove(index);
						continue;
					}
				}
			}
			
			/*Reset the ComboBox Data */
			mDeleteStaffJFrame.getCbStaffId().removeAllItems();
			mDeleteStaffJFrame.initFirstStaffRecord(mLeaveApplicationSystm.getStaffRecord().get(0));
			mDeleteStaffJFrame.initStaffIdComboBoxData(mLeaveApplicationSystm.getStaffRecord());
			mDeleteStaffJFrame.initManagerStaffRecordData(mLeaveApplicationSystm.getAssignStaffRecordMap()
					.get(mLeaveApplicationSystm.getStaffRecord().get(0).getStaffId()));
		}
	}
		
	public void initListener(){
		mDeleteStaffJFrame.getBtnDeleteRecord().addActionListener(this);
		mDeleteStaffJFrame.getCbStaffId().addItemListener(this);
	}
	
	public void showCreateStaffJFrame(boolean isShow){
		mDeleteStaffJFrame.setVisible(isShow);
	}

	public void itemStateChanged(ItemEvent itemEvent) {
		if(itemEvent.getStateChange() == ItemEvent.SELECTED){
			AbstractStaff staff = mLeaveApplicationSystm.getStaffRecord()
					.get(mDeleteStaffJFrame.getCbStaffId().getSelectedIndex());
			mDeleteStaffJFrame.getTxtFirstName().setText(staff.getFirstName());
			mDeleteStaffJFrame.getTxtLastName().setText(staff.getLastName());
			mDeleteStaffJFrame.getTxtPosition().setText(staff.getPosition());
			mDeleteStaffJFrame.getTxtDepartment().setText(staff.getDepartment());
			if(staff instanceof Administrator){
				mDeleteStaffJFrame.getTxtType().setText(Administrator.class.getSimpleName());
			}else if(staff instanceof Staff){
				mDeleteStaffJFrame.getTxtType().setText(Staff.class.getSimpleName());
			}else if(staff instanceof Supervisor){
				mDeleteStaffJFrame.getTxtType().setText(Supervisor.class.getSimpleName());
			}else if(staff instanceof Director){
				mDeleteStaffJFrame.getTxtType().setText(Director.class.getSimpleName());
			}else{
				// Unexpect case
			}
			if(!(staff instanceof Director)){
				List<AbstractStaff> managerStaffRecord = mLeaveApplicationSystm.getAssignStaffRecordMap()
						.get(staff.getStaffId()); 
				if(managerStaffRecord == null){
					mDeleteStaffJFrame.getTaManageStaffList().setText("No staff to manage.");
				}else{
					String listText = "";
					for(AbstractStaff managerStaff : managerStaffRecord){
						listText += managerStaff.getStaffId()+",";
					}
					mDeleteStaffJFrame.getTaManageStaffList().setText(listText.substring(0, listText.length() - 1));
				}
			}else{
				mDeleteStaffJFrame.getTaManageStaffList().setText("Manage All Saff");
			}
		}
	}
	
	private void deleteStaffById(String staffId){
		for(int index = 0;index < mLeaveApplicationSystm.getStaffRecord().size();index++){
			if(mLeaveApplicationSystm.getStaffRecord().get(index).getStaffId().equalsIgnoreCase(staffId)){
				mLeaveApplicationSystm.getStaffRecord().remove(index);
				return;
			}
		}
	}
}