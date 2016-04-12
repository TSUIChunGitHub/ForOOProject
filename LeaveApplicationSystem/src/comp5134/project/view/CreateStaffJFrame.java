package comp5134.project.view;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CreateStaffJFrame extends JFrame {

	private JPanel contentPane;
	
	private JPasswordField mTxtPassword;
	private JPasswordField mTxtConfirmPassword;
	
	private JTextField mTxtFirstName;
	private JTextField mTxtLastName;
	private JTextField mTxtPosition;
	private JTextField mTxtAge;
	private JTextField mTxtStaffId;
	private JTextField mTxtEmail;
	
	private JLabel mLblStaffId;
	private JLabel mLblPassword;
	private JLabel mLblFirstName;
	private JLabel mLblLastName;
	private JLabel mLblAge;
	private JLabel mLblPosition;
	private JLabel mLblGender;
	private JLabel mLblDepartment;
	private JLabel mLblType;
	private JLabel mLblConfirmPassword;
	private JLabel mLblEmail;
	private JLabel mLblToSupervisor;
	
	private JComboBox mCbDepartment;
	private JComboBox mCbGender;
	private JComboBox mCbType;
	private JComboBox mCbToSupervisor;

	private JButton mBtnCreateRecord;
	/**
	 * Create the frame.
	 */
	public CreateStaffJFrame() {
		setResizable(false);
		setTitle("Create Staff");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 226, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mTxtStaffId = new JTextField();
		mTxtStaffId.setBounds(107, 10, 96, 21);
		contentPane.add(mTxtStaffId);
		mTxtStaffId.setColumns(10);
		
		mLblStaffId = new JLabel("Staff Id:");
		mLblStaffId.setBounds(10, 13, 73, 15);
		contentPane.add(mLblStaffId);
		
		mLblPassword = new JLabel("Password:");
		mLblPassword.setBounds(10, 38, 73, 15);
		contentPane.add(mLblPassword);
		
		mTxtPassword = new JPasswordField();
		mTxtPassword.setBounds(107, 35, 96, 21);
		contentPane.add(mTxtPassword);
		
		mLblFirstName = new JLabel("First Name:");
		mLblFirstName.setBounds(10, 89, 73, 15);
		contentPane.add(mLblFirstName);
		
		mTxtFirstName = new JTextField();
		mTxtFirstName.setBounds(107, 86, 96, 21);
		contentPane.add(mTxtFirstName);
		mTxtFirstName.setColumns(10);
		
	    mLblLastName = new JLabel("Last Name:");
		mLblLastName.setBounds(9, 114, 74, 15);
		contentPane.add(mLblLastName);
		
		mTxtLastName = new JTextField();
		mTxtLastName.setBounds(107, 111, 96, 21);
		contentPane.add(mTxtLastName);
		mTxtLastName.setColumns(10);
		
		mLblPosition = new JLabel("Position:");
		mLblPosition.setBounds(10, 139, 73, 15);
		contentPane.add(mLblPosition);
		
		mTxtPosition = new JTextField();
		mTxtPosition.setBounds(107, 136, 96, 21);
		contentPane.add(mTxtPosition);
		mTxtPosition.setColumns(10);
		
		mLblAge = new JLabel("Age:");
		mLblAge.setBounds(10, 164, 73, 15);
		contentPane.add(mLblAge);
		
		mTxtAge = new JTextField();
		mTxtAge.setBounds(107, 161, 40, 21);
		contentPane.add(mTxtAge);
		mTxtAge.setColumns(10);
		
		mCbGender = new JComboBox();
		mCbGender.setBounds(107, 186, 40, 21);
		contentPane.add(mCbGender);
		
		mLblGender = new JLabel("Gender:");
		mLblGender.setBounds(10, 189, 73, 15);
		contentPane.add(mLblGender);
		
		mLblDepartment = new JLabel("Department:");
		mLblDepartment.setBounds(10, 241, 73, 15);
		contentPane.add(mLblDepartment);
		
		mCbDepartment = new JComboBox();
		mCbDepartment.setBounds(107, 238, 96, 21);
		contentPane.add(mCbDepartment);
		
		mLblType = new JLabel("Type:");
		mLblType.setBounds(10, 265, 73, 15);
		contentPane.add(mLblType);
		
		mCbType = new JComboBox();
		mCbType.setBounds(107, 262, 96, 21);
		contentPane.add(mCbType);
		
		mBtnCreateRecord = new JButton("Create Record");
		mBtnCreateRecord.setBounds(24, 315, 179, 23);
		contentPane.add(mBtnCreateRecord);
		
		mTxtConfirmPassword = new JPasswordField();
		mTxtConfirmPassword.setBounds(107, 61, 96, 21);
		contentPane.add(mTxtConfirmPassword);
		
		mLblConfirmPassword = new JLabel("Confirm Password:");
		mLblConfirmPassword.setBounds(10, 64, 89, 15);
		contentPane.add(mLblConfirmPassword);
		
		mLblEmail = new JLabel("Email:");
		mLblEmail.setBounds(10, 214, 46, 15);
		contentPane.add(mLblEmail);
		
		mTxtEmail = new JTextField();
		mTxtEmail.setBounds(107, 211, 96, 21);
		contentPane.add(mTxtEmail);
		mTxtEmail.setColumns(10);
		
		mLblToSupervisor = new JLabel("To Supervisor:");
		mLblToSupervisor.setBounds(10, 290, 89, 15);
		contentPane.add(mLblToSupervisor);
		
		mCbToSupervisor = new JComboBox();
		mCbToSupervisor.setBounds(107, 287, 96, 21);
		contentPane.add(mCbToSupervisor);
	}
		
	public JPasswordField getPasswordField(){
		return mTxtPassword;
	}
	
	public JPasswordField getConfirmPasswordField(){
		return mTxtConfirmPassword;
	}
	
	public JTextField getTxtEmail(){
		return mTxtEmail;
	}
	
	public JTextField getTxtStaffId(){
		return mTxtStaffId;
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
	
	public JTextField getTxtAge(){
		return mTxtAge;
	}
	
	public JComboBox getCbDepartment(){
		return mCbDepartment;
	}
	
	public JComboBox getCbType(){
		return mCbType;
	}
	
	public JComboBox getCbGender(){
		return mCbGender;
	}
	
	public JComboBox getCbToSupervisor(){
		return mCbToSupervisor;
	}
	
	public JButton getBtnCreateRecord(){
		return mBtnCreateRecord;
	}
}
