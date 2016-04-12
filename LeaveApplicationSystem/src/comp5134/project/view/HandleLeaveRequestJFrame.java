package comp5134.project.view;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class HandleLeaveRequestJFrame extends JFrame {

	private JPanel contentPane;
	
	private JButton mBtnEndorse;
	private JButton mBtnDecline;
	
	private JTextField mTxtFromDate;
	private JTextField mTxtToDate;
	
	private JLabel mLblStaffId;
	private JLabel mLblFromDate;
	private JLabel mLblToDate;
	
	private JComboBox mCbStaffId;
	
	public HandleLeaveRequestJFrame() {
		setTitle("Handle Leave Request");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 233, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mBtnEndorse = new JButton("Endorse");
		mBtnEndorse.setBounds(10, 108, 87, 23);
		contentPane.add(mBtnEndorse);
		
		mBtnDecline = new JButton("Decline");
		mBtnDecline.setBounds(120, 108, 87, 23);
		contentPane.add(mBtnDecline);
		
		mTxtFromDate = new JTextField();
		mTxtFromDate.setEditable(false);
		mTxtFromDate.setBounds(97, 41, 110, 21);
		contentPane.add(mTxtFromDate);
		mTxtFromDate.setColumns(10);
		
		mTxtToDate = new JTextField();
		mTxtToDate.setEditable(false);
		mTxtToDate.setBounds(97, 72, 110, 21);
		contentPane.add(mTxtToDate);
		mTxtToDate.setColumns(10);
		
		mCbStaffId = new JComboBox();
		mCbStaffId.setBounds(97, 10, 110, 21);
		contentPane.add(mCbStaffId);
		
		mLblStaffId = new JLabel("Staff Id:");
		mLblStaffId.setBounds(10, 13, 77, 15);
		contentPane.add(mLblStaffId);
		
		mLblFromDate = new JLabel("From Date:");
		mLblFromDate.setBounds(10, 44, 77, 15);
		contentPane.add(mLblFromDate);
		
		mLblToDate = new JLabel("ToDate:");
		mLblToDate.setBounds(10, 75, 77, 15);
		contentPane.add(mLblToDate);
	}
	
	public JButton getBtnEndorse(){
		return mBtnEndorse;
	}
	
	public JButton getBtnDecline(){
		return mBtnDecline;
	}
	
	public JTextField getTxtFromDate(){
		return mTxtFromDate;
	}
	
	public JTextField getTxtToDate(){
		return mTxtToDate;
	}
	
	public JComboBox getCbStaffId(){
		return mCbStaffId;
	}
}
