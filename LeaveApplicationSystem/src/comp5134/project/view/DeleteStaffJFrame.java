package comp5134.project.view;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import comp5134.project.model.AbstractStaff;
import comp5134.project.model.Administrator;
import comp5134.project.model.Director;
import comp5134.project.model.Staff;
import comp5134.project.model.Supervisor;
import javax.swing.JTextArea;

public class DeleteStaffJFrame extends JFrame {

	private JPanel contentPane;
	
	private JTextField mTxtFirstName;
	private JTextField mTxtLastName;
	private JTextField mTxtPosition;
	private JTextField mTxtDepartment;
	private JTextField mTxtType;
	
	private JLabel mLblFirstName;
	private JLabel mLblLastName;
	private JLabel mLblStaffId;
	private JLabel mLblPosition;
	private JLabel mLblDepartment;
	private JLabel mLblType;
	private JLabel mLblMangeStaff;

	private JComboBox mCBStaffId;
	
	private JButton mBtnDeleteRecord;
	
	private JTextArea mTaManageStaff;
	/**
	 * Create the frame.
	 */
	public DeleteStaffJFrame() {
		setTitle("Delete Staff");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 196, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mLblStaffId = new JLabel("Staff Id:");
		mLblStaffId.setBounds(10, 10, 74, 15);
		contentPane.add(mLblStaffId);
		
		mCBStaffId = new JComboBox();
		mCBStaffId.setBounds(94, 7, 89, 21);
		contentPane.add(mCBStaffId);
		
		mLblFirstName = new JLabel("First Name:");
		mLblFirstName.setBounds(10, 35, 74, 15);
		contentPane.add(mLblFirstName);
		
		mTxtFirstName = new JTextField();
		mTxtFirstName.setEditable(false);
		mTxtFirstName.setBounds(94, 32, 89, 21);
		contentPane.add(mTxtFirstName);
		mTxtFirstName.setColumns(10);
		
		mLblLastName = new JLabel("Last Name:");
		mLblLastName.setBounds(10, 60, 74, 15);
		contentPane.add(mLblLastName);
		
		mTxtLastName = new JTextField();
		mTxtLastName.setEditable(false);
		mTxtLastName.setBounds(94, 57, 89, 21);
		contentPane.add(mTxtLastName);
		mTxtLastName.setColumns(10);
		
		mLblPosition = new JLabel("Position:");
		mLblPosition.setBounds(10, 85, 74, 15);
		contentPane.add(mLblPosition);
		
		mTxtPosition = new JTextField();
		mTxtPosition.setEditable(false);
		mTxtPosition.setBounds(94, 82, 89, 21);
		contentPane.add(mTxtPosition);
		mTxtPosition.setColumns(10);
		
		mLblDepartment = new JLabel("Department:");
		mLblDepartment.setBounds(10, 110, 74, 15);
		contentPane.add(mLblDepartment);
		
		mTxtDepartment = new JTextField();
		mTxtDepartment.setEditable(false);
		mTxtDepartment.setBounds(94, 107, 89, 21);
		contentPane.add(mTxtDepartment);
		mTxtDepartment.setColumns(10);
		
		mLblType = new JLabel("Type:");
		mLblType.setBounds(10, 135, 74, 15);
		contentPane.add(mLblType);
		
		mTxtType = new JTextField();
		mTxtType.setEditable(false);
		mTxtType.setBounds(94, 132, 89, 21);
		contentPane.add(mTxtType);
		mTxtType.setColumns(10);
		
		mBtnDeleteRecord = new JButton("Delete Record");
		mBtnDeleteRecord.setBounds(10, 249, 173, 23);
		contentPane.add(mBtnDeleteRecord);
		
		mLblMangeStaff = new JLabel("Manage Staff:");
		mLblMangeStaff.setBounds(10, 160, 89, 15);
		contentPane.add(mLblMangeStaff);
		
		mTaManageStaff = new JTextArea();
		mTaManageStaff.setEditable(false);
		mTaManageStaff.setBounds(10, 174, 173, 65);
		mTaManageStaff.setLineWrap(true); 
		mTaManageStaff.setWrapStyleWord(true);
		contentPane.add(mTaManageStaff);
	}
	
	public JTextField getTxtFirstName(){
		return mTxtFirstName;
	}
	
	public JTextField getTxtLastName(){
		return mTxtLastName;
	}
	
	public JTextField getTxtPosition(){
		return mTxtPosition;
	}
	
	public JTextField getTxtDepartment(){
		return mTxtDepartment;
	}
	
	public JTextField getTxtType(){
		return mTxtType;
	}
	
	public JComboBox getCbStaffId(){
		return mCBStaffId;
	}
	
	public JButton getBtnDeleteRecord(){
		return mBtnDeleteRecord;
	}
	
	public JTextArea getTaManageStaffList(){
		return mTaManageStaff;
	}
	
	public void initFirstStaffRecord(AbstractStaff staff){
		mTxtFirstName.setText(staff.getFirstName());
		mTxtLastName.setText(staff.getLastName());
		mTxtPosition.setText(staff.getPosition());
		mTxtDepartment.setText(staff.getDepartment());
		if(staff instanceof Administrator){
			mTxtType.setText(Administrator.class.getSimpleName());
		}else if(staff instanceof Staff){
			mTxtType.setText(Staff.class.getSimpleName());
		}else if(staff instanceof Supervisor){
			mTxtType.setText(Supervisor.class.getSimpleName());
		}else if(staff instanceof Director){
			mTxtType.setText(Director.class.getSimpleName());
		}else{
			// Unexpect case
		}
	}
	
	public void initStaffIdComboBoxData(List<AbstractStaff> staffRecord){
		for(AbstractStaff staff : staffRecord){
			mCBStaffId.addItem(staff.getStaffId());
		}
	}
	
	public void initManagerStaffRecordData(List<AbstractStaff> managerStaffRecord){
		if(managerStaffRecord == null){
			mTaManageStaff.setText("No staff to manage.");
		}else{
			String listText = "";
			for(AbstractStaff staff : managerStaffRecord){
				listText += staff.getStaffId()+",";
			}
			mTaManageStaff.setText(listText);
		}
	}
}
