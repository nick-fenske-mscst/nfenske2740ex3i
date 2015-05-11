package nfenske2740ex3i;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class ExamForm extends JFrame {

	private JPanel contentPane;
	private JList responseList;
	private JLabel resultLabel;
	private DefaultListModel responseListModel;
	private JLabel indexLabel;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnListIncorrect;
	private JButton btnIncorrect;
	private JButton buttonCorrect;
	private JButton buttonPass;
	private DriverExam exam;
	private JTextField inputTextBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExamForm() {
		setTitle("NF Ex3I");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 234, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		responseList = new JList();
		responseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responseList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responseList.setBounds(46, 33, 33, 210);
		responseList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				do_responseList_valueChanged(e);
			}
		});
		contentPane.add(responseList);
		
		JList indexList = new JList();
		indexList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		indexList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		indexList.setEnabled(false);
		indexList.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		indexList.setBounds(10, 33, 33, 210);
		contentPane.add(indexList);
		
		JLabel lblResponses = new JLabel("Responses:");
		lblResponses.setBounds(10, 11, 70, 14);
		contentPane.add(lblResponses);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(90, 11, 70, 14);
		contentPane.add(lblResult);
		
		resultLabel = new JLabel("");
		resultLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		resultLabel.setBounds(90, 34, 118, 22);
		contentPane.add(resultLabel);
		
		buttonPass = new JButton("Pass");
		buttonPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_buttonPass_actionPerformed(arg0);
			}
		});
		buttonPass.setBounds(90, 67, 118, 23);
		contentPane.add(buttonPass);
		
		buttonCorrect = new JButton("Correct");
		buttonCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_buttonCorrect_actionPerformed(e);
			}
		});
		buttonCorrect.setBounds(90, 101, 118, 23);
		contentPane.add(buttonCorrect);
		
		btnIncorrect = new JButton("Incorrect");
		btnIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnIncorrect_actionPerformed(e);
			}
		});
		btnIncorrect.setBounds(90, 135, 118, 23);
		contentPane.add(btnIncorrect);
		
		btnListIncorrect = new JButton("List Incorrect");
		btnListIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnListIncorrect_actionPerformed(e);
			}
		});
		btnListIncorrect.setBounds(90, 169, 118, 23);
		contentPane.add(btnListIncorrect);
		
		btnPrev = new JButton("Prev");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPrev_actionPerformed(e);
			}
		});
		btnPrev.setEnabled(false);
		btnPrev.setBounds(97, 228, 63, 23);
		contentPane.add(btnPrev);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNext_actionPerformed(e);
			}
		});
		btnNext.setBounds(97, 262, 63, 23);
		contentPane.add(btnNext);
		
		indexLabel = new JLabel("#0:");
		indexLabel.setBounds(10, 254, 46, 14);
		contentPane.add(indexLabel);
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		this.exam = mapper.getDriverExam();
		this.responseListModel = exam.getAnswers();
		responseList.setModel(this.responseListModel);
		
		inputTextBox = new JTextField();
		inputTextBox.setBounds(46, 251, 33, 20);
		contentPane.add(inputTextBox);
		inputTextBox.setColumns(10);
	}
	protected void do_buttonPass_actionPerformed(ActionEvent arg0) 
	{
		this.exam.setResponses((DefaultListModel)responseList.getModel());
		int invalid = this.exam.validate();
		
		if(invalid >= 0)
		{
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responseList.setSelectedIndex(invalid);
		} else {
			if(exam.passed())
			{
				resultLabel.setText("You Passed");
			} else {
				resultLabel.setText("You Failed");
			}
		}
	}
	protected void do_buttonCorrect_actionPerformed(ActionEvent e) 
	{
		this.exam.setResponses((DefaultListModel)responseList.getModel());
		int invalid = this.exam.validate();
		
		if(invalid >= 0)
		{
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responseList.setSelectedIndex(invalid);
		} else {
			resultLabel.setText("Correct: " + exam.totalCorrect());
		}
	}
	protected void do_btnIncorrect_actionPerformed(ActionEvent e) 
	{
		this.exam.setResponses((DefaultListModel)responseList.getModel());
		int invalid = this.exam.validate();
		
		if(invalid >= 0)
		{
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responseList.setSelectedIndex(invalid);
		} else {
			resultLabel.setText("Incorrect: " + exam.totalIncorrect());
		}
	}
	protected void do_btnListIncorrect_actionPerformed(ActionEvent e) 
	{
		this.exam.setResponses((DefaultListModel)responseList.getModel());
		int invalid = this.exam.validate();
		
		if(invalid >= 0)
		{
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responseList.setSelectedIndex(invalid);
		} else {
			
			int[] missed = exam.questionsMissed();
			int i = 0;
			
			String missedString = "";
			boolean missedQ = false;
			
			while(i < missed.length && missed[i] > 0)
			{
				missedQ = true;
				missedString += " ";
				missedString += missed[i];
				i++;
			}
			
			if(missedQ)
			{
				resultLabel.setText("Incorrect: " + missedString);
			} else {
				resultLabel.setText("None");
			}
		}
	}
	protected void do_btnPrev_actionPerformed(ActionEvent e) 
	{
		this.responseListModel.setElementAt(
				inputTextBox.getText().toUpperCase(), 
				responseList.getSelectedIndex());
		responseList.setSelectedIndex(responseList.getSelectedIndex() - 1);
        indexLabel.setText("#" + Integer.toString((responseList.getSelectedIndex() + 1)));
        inputTextBox.setText(responseList.getSelectedValue().toString());    

        btnNext.setEnabled(true);
        if (responseList.getSelectedIndex() == 0) 
            btnPrev.setEnabled(false);
        inputTextBox.requestFocus();
	}
	protected void do_btnNext_actionPerformed(ActionEvent e) 
	{
		this.responseListModel.setElementAt(
				inputTextBox.getText().toUpperCase(), 
				responseList.getSelectedIndex());
		responseList.setSelectedIndex(responseList.getSelectedIndex() + 1);
        indexLabel.setText("#" + Integer.toString((responseList.getSelectedIndex() + 1)));
        inputTextBox.setText(responseList.getSelectedValue().toString());
        
        btnPrev.setEnabled(true);
        if (responseList.getSelectedIndex() == responseListModel.getSize() - 1)
            btnNext.setEnabled(false);
        inputTextBox.requestFocus();
	}
	protected void do_responseList_valueChanged(ListSelectionEvent e) 
	{
		indexLabel.setText("#" + Integer.toString((responseList.getSelectedIndex() + 1)));

		inputTextBox.setText(responseList.getSelectedValue().toString());    

        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (responseList.getSelectedIndex() == responseListModel.getSize() - 1)
            btnNext.setEnabled(false);
        if (responseList.getSelectedIndex() == 0) 
            btnPrev.setEnabled(false);
        inputTextBox.requestFocus();
	}
}
