package comp5134.project.view;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import comp5134.project.model.DateLabelFormatter;
import javax.swing.JButton;

public class MakeLeaveRequestJFrame extends JFrame {

	private JPanel contentPane;

	private UtilDateModel mFromDateUtilModel;
	private Properties mFromDateProperties;
	private UtilDateModel mToDateUtilModel;
	private Properties mToDateProperties;

	private JDatePanelImpl mFromDatePanel;
	private JDatePickerImpl mFromDatePicker;
	private JDatePanelImpl mToDatePanel;
	private JDatePickerImpl mToDatePicker;
	
	private JLabel mLblFromDate;
	private JLabel mLblToDate;
	
	private JButton mBtnAddRequest;
	
	public MakeLeaveRequestJFrame() {
		setTitle("Make Leave Request");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 211, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mFromDateUtilModel = new UtilDateModel();
		mFromDateProperties = new Properties();
		mFromDateProperties.put("text.today", "Today");
		mFromDateProperties.put("text.month", "Month");
		mFromDateProperties.put("text.year", "Year");
		
		mFromDatePanel = new JDatePanelImpl(mFromDateUtilModel, mFromDateProperties);
		mFromDatePicker = new JDatePickerImpl(mFromDatePanel, new DateLabelFormatter());
		mFromDatePicker.setBounds(58, 9, 126, 21);		
		contentPane.add(mFromDatePicker);
		
		mLblFromDate = new JLabel("From:");
		mLblFromDate.setBounds(6, 9, 69, 15);
		contentPane.add(mLblFromDate);
		
		mLblToDate = new JLabel("To:");
		mLblToDate.setBounds(6, 42, 69, 15);
		contentPane.add(mLblToDate);
		
		mBtnAddRequest = new JButton("Add Request");
		mBtnAddRequest.setBounds(29, 77, 155, 23);
		contentPane.add(mBtnAddRequest);
		
		mToDateUtilModel = new UtilDateModel();
		mToDateProperties = new Properties();
		mToDateProperties.put("text.today", "Today");
		mToDateProperties.put("text.month", "Month");
		mToDateProperties.put("text.year", "Year");
			
		mToDatePanel = new JDatePanelImpl(mToDateUtilModel, mToDateProperties);
		mToDatePicker = new JDatePickerImpl(mToDatePanel, new DateLabelFormatter());
		mToDatePicker.setBounds(58, 40, 126, 21);		
		contentPane.add(mToDatePicker);
	}

	public JDatePickerImpl getToDatePicker(){
		return mToDatePicker;
	}
	
	public JDatePickerImpl getFromDatePicker(){
		return mFromDatePicker;
	}
	
	public JButton getBtnAddRequest(){
		return mBtnAddRequest;
	}
}
