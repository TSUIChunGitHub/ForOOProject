package comp5134.project.view;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import comp5134.project.model.AbstractStaff;
import comp5134.project.model.Administrator;
import comp5134.project.model.Director;
import comp5134.project.model.Staff;
import comp5134.project.model.Supervisor;

import java.awt.Font;
import java.util.List;

public class AssignStaffJFrame extends JFrame {

	private JPanel mAssignStaffContentPane;
	private JPanel mAssignPanel;
	private JPanel mToPanel;
	
	private JLabel mLblAssignStaffId;
	private JLabel mLblAssignFirstName;
	private JLabel mLblAssignLastName;
	private JLabel mLblAssignPosition;
	private JLabel mLblAssignDepartment;
	private JLabel mLblAssignType;
	private JLabel mLblAssign;
	private JLabel mLblToStaffId;
	private JLabel mLblToFirstName;
	private JLabel mLblToLastName;
	private JLabel mLblToPosition;
	private JLabel mLblToDepartment;
	private JLabel mLblToType;
	private JLabel mLblTo;
	
	private JTextField mTxtAssignFirstName;
	private JTextField mTxtAssignLastName;
	private JTextField mTxtAssignPosition;
	private JTextField mTxtAssignDeapartment;
	private JTextField mTxtAssignType;
	private JTextField mTxtToFirstName;
	private JTextField mTxtToLastName;
	private JTextField mTxtToPosition;
	private JTextField mTxtToDepartment;
	private JTextField mTxtToType;

	private JComboBox mCbAssignStaffId;
	private JComboBox mCbToStaffId;
	
	private JButton mBtnAssign;
	/**
	 * Create the frame.
	 */
	public AssignStaffJFrame() {
		setResizable(false);
		setTitle("Assign Staff");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 431, 258);
		mAssignStaffContentPane = new JPanel();
		mAssignStaffContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mAssignStaffContentPane);
		mAssignStaffContentPane.setLayout(null);
		
		mAssignPanel = new JPanel();
		mAssignPanel.setLayout(null);
		mAssignPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mAssignPanel.setBounds(10, 31, 190, 159);
		mAssignStaffContentPane.add(mAssignPanel);
		
		mLblAssignStaffId = new JLabel("Staff Id:");
		mLblAssignStaffId.setBounds(10, 10, 74, 15);
		mAssignPanel.add(mLblAssignStaffId);
		
		mCbAssignStaffId = new JComboBox();
		mCbAssignStaffId.setBounds(94, 7, 89, 21);
		mAssignPanel.add(mCbAssignStaffId);
		
		mLblAssignFirstName = new JLabel("First Name:");
		mLblAssignFirstName.setBounds(10, 35, 74, 15);
		mAssignPanel.add(mLblAssignFirstName);
		
		mTxtAssignFirstName = new JTextField();
		mTxtAssignFirstName.setEditable(false);
		mTxtAssignFirstName.setColumns(10);
		mTxtAssignFirstName.setBounds(94, 32, 89, 21);
		mAssignPanel.add(mTxtAssignFirstName);
		
		mLblAssignLastName = new JLabel("Last Name:");
		mLblAssignLastName.setBounds(10, 60, 74, 15);
		mAssignPanel.add(mLblAssignLastName);
		
		mTxtAssignLastName = new JTextField();
		mTxtAssignLastName.setEditable(false);
		mTxtAssignLastName.setColumns(10);
		mTxtAssignLastName.setBounds(94, 57, 89, 21);
		mAssignPanel.add(mTxtAssignLastName);
		
		mLblAssignPosition = new JLabel("Position:");
		mLblAssignPosition.setBounds(10, 85, 74, 15);
		mAssignPanel.add(mLblAssignPosition);
		
		mTxtAssignPosition = new JTextField();
		mTxtAssignPosition.setEditable(false);
		mTxtAssignPosition.setColumns(10);
		mTxtAssignPosition.setBounds(94, 82, 89, 21);
		mAssignPanel.add(mTxtAssignPosition);
		
		mLblAssignDepartment = new JLabel("Department:");
		mLblAssignDepartment.setBounds(10, 110, 74, 15);
		mAssignPanel.add(mLblAssignDepartment);
		
		mTxtAssignDeapartment = new JTextField();
		mTxtAssignDeapartment.setEditable(false);
		mTxtAssignDeapartment.setColumns(10);
		mTxtAssignDeapartment.setBounds(94, 107, 89, 21);
		mAssignPanel.add(mTxtAssignDeapartment);
		
		mLblAssignType = new JLabel("Type:");
		mLblAssignType.setBounds(10, 135, 74, 15);
		mAssignPanel.add(mLblAssignType);
		
		mTxtAssignType = new JTextField();
		mTxtAssignType.setEditable(false);
		mTxtAssignType.setColumns(10);
		mTxtAssignType.setBounds(94, 132, 89, 21);
		mAssignPanel.add(mTxtAssignType);
		
		mLblAssign = new JLabel("Assign:");
		mLblAssign.setFont(new Font("新細明體", Font.BOLD, 12));
		mLblAssign.setBounds(20, 10, 180, 15);
		mAssignStaffContentPane.add(mLblAssign);
		
		mToPanel = new JPanel();
		mToPanel.setLayout(null);
		mToPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mToPanel.setBounds(231, 31, 190, 159);
		mAssignStaffContentPane.add(mToPanel);
		
		mLblToStaffId = new JLabel("Staff Id:");
		mLblToStaffId.setBounds(10, 10, 74, 15);
		mToPanel.add(mLblToStaffId);
		
		mCbToStaffId = new JComboBox();
		mCbToStaffId.setBounds(94, 7, 89, 21);
		mToPanel.add(mCbToStaffId);
		
		mLblToFirstName = new JLabel("First Name:");
		mLblToFirstName.setBounds(10, 35, 74, 15);
		mToPanel.add(mLblToFirstName);
		
		mTxtToFirstName = new JTextField();
		mTxtToFirstName.setEditable(false);
		mTxtToFirstName.setColumns(10);
		mTxtToFirstName.setBounds(94, 32, 89, 21);
		mToPanel.add(mTxtToFirstName);
		
		mLblToLastName = new JLabel("Last Name:");
		mLblToLastName.setBounds(10, 60, 74, 15);
		mToPanel.add(mLblToLastName);
		
		mTxtToLastName = new JTextField();
		mTxtToLastName.setEditable(false);
		mTxtToLastName.setColumns(10);
		mTxtToLastName.setBounds(94, 57, 89, 21);
		mToPanel.add(mTxtToLastName);
		
		mLblToPosition = new JLabel("Position:");
		mLblToPosition.setBounds(10, 85, 74, 15);
		mToPanel.add(mLblToPosition);
		
		mTxtToPosition = new JTextField();
		mTxtToPosition.setEditable(false);
		mTxtToPosition.setColumns(10);
		mTxtToPosition.setBounds(94, 82, 89, 21);
		mToPanel.add(mTxtToPosition);
		
		mLblToDepartment = new JLabel("Department:");
		mLblToDepartment.setBounds(10, 110, 74, 15);
		mToPanel.add(mLblToDepartment);
		
		mTxtToDepartment = new JTextField();
		mTxtToDepartment.setEditable(false);
		mTxtToDepartment.setColumns(10);
		mTxtToDepartment.setBounds(94, 107, 89, 21);
		mToPanel.add(mTxtToDepartment);
		
		mLblToType = new JLabel("Type:");
		mLblToType.setBounds(10, 135, 74, 15);
		mToPanel.add(mLblToType);
		
		mTxtToType = new JTextField();
		mTxtToType.setEditable(false);
		mTxtToType.setColumns(10);
		mTxtToType.setBounds(94, 132, 89, 21);
		mToPanel.add(mTxtToType);
		
		mBtnAssign = new JButton("Assign");
		mBtnAssign.setBounds(148, 196, 139, 23);
		mAssignStaffContentPane.add(mBtnAssign);
		
		mLblTo = new JLabel("To:");
		mLblTo.setFont(new Font("新細明體", Font.BOLD, 12));
		mLblTo.setBounds(241, 10, 174, 15);
		mAssignStaffContentPane.add(mLblTo);
	}
	
	public JTextField getTxtAssignFirstName(){
		return mTxtAssignFirstName;
	}
	
	public JTextField getTxtAssignLastName(){
		return mTxtAssignLastName;
	}
	
	public JTextField getTxtAssignPosition(){
		return mTxtAssignPosition;
	}

	public JTextField getTxtAssignDeapartment(){
		return mTxtAssignDeapartment;
	}

	public JTextField getTxtAssignType(){
		return mTxtAssignType;
	}

	public JTextField getTxtToFirstName(){
		return mTxtToFirstName;
	}
	
	public JTextField getTxtToLastName(){
		return mTxtToLastName;
	}

	public JTextField getTxtToPosition(){
		return mTxtToPosition;
	}

	public JTextField getTxtToDepartment(){
		return mTxtToDepartment;
	}
	
	public JTextField getTxtToType(){
		return mTxtToType;
	}
	
	public JComboBox getCbAssignStaffId(){
		return mCbAssignStaffId;
	}
	
	public JComboBox getCbToStaffId(){
		return mCbToStaffId;
	}

	public JButton getBtnAssign(){
		return mBtnAssign;
	}
	
	public void initFirstStaffRecord(AbstractStaff staff){
		mTxtAssignFirstName.setText(staff.getFirstName());
		mTxtAssignLastName.setText(staff.getLastName());
		mTxtAssignPosition.setText(staff.getPosition());
		mTxtAssignDeapartment.setText(staff.getDepartment());
		mTxtToFirstName.setText(staff.getFirstName());
		mTxtToLastName.setText(staff.getLastName());
		mTxtToPosition.setText(staff.getPosition());
		mTxtToDepartment.setText(staff.getDepartment());
		if(staff instanceof Administrator){
			mTxtAssignType.setText(Administrator.class.getSimpleName());
			mTxtToType.setText(Administrator.class.getSimpleName());
		}else if(staff instanceof Staff){
			mTxtAssignType.setText(Staff.class.getSimpleName());
			mTxtToType.setText(Staff.class.getSimpleName());
		}else if(staff instanceof Supervisor){
			mTxtAssignType.setText(Supervisor.class.getSimpleName());
			mTxtToType.setText(Supervisor.class.getSimpleName());
		}else if(staff instanceof Director){
			mTxtAssignType.setText(Director.class.getSimpleName());
			mTxtToType.setText(Director.class.getSimpleName());
		}else{
			// Unexpect case
		}
	}
	
	public void initStaffIdComboBoxData(List<AbstractStaff> staffRecord){
		for(AbstractStaff staff : staffRecord){
			mCbAssignStaffId.addItem(staff.getStaffId());
			mCbToStaffId.addItem(staff.getStaffId());
		}
	}
}
