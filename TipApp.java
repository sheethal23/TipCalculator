import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.Dimension;
import java.awt.EventQueue;
//import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
/*import javax.swing.Box;
import javax.swing.BoxLayout;*/
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/************************************************************
 *                                                          *
 *  Class Name:  TipApp.                                    *
 *                                                          *
 *  Programmer:  Sheethal Yellisetty                        *
 *                                                          *
 *                                                          *
 *  Purpose:  To create the Graphical user Interface of     *
 *            TipCalculator                                 *
 *                                                          *
 ************************************************************/
public class TipApp extends JFrame implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	
	//Instance fields
	private JTextField num1Field = new JTextField(10);                        //To add the Textfield to the Jframe
	private JSpinner spinner = new JSpinner();                                //Jspinner to the Jframe 
	private JSlider sliderr = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);      //Jslider to the Jframe
	private JButton calcButton = new JButton("Calculate");                    //Jbuttons to the Jframe.
	private JButton clearButton = new JButton("Clear");
	private JLabel totallabel = new JLabel();                                 //Jlabel to the Jframe
	private JLabel individuallabel = new JLabel();
	
	TipCalculator obj2 = new TipCalculator();                                  //TipCalculator object


	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			TipApp obj = new TipApp("Tip Calculator");
			obj.createAndShowGUI();                                            //Calling the method createAndShowGUI()
		});
	}
	/************************************************************
	 *                                                          *
	 *  Method Name:  createAndShowGUI().                       *
	 *                                                          *
	 *  Purpose:  Method adds the action listener to the buttons.*
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	private void createAndShowGUI() {
		initComponents();

		// Add listeners for the buttons.

		calcButton.addActionListener(this);                       //adding action listener to the calculate button 
		clearButton.addActionListener(this);                      //adding action listener to the calculate button

		// Display the window.

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //default operation that closes on exit
		pack();
		setVisible(true);
	}
	/************************************************************
	 *                                                          *
	 *  Method Name:  initComponents().                         *
	 *                                                          *
	 *  Purpose:  Method adds all the JComponents to the Jpanel *
	 *            through grid layout.                          *
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	private void initComponents() {
		// TODO Auto-generated method stub

		JPanel panel = new JPanel(new GridLayout(6, 2, 5,5));                  //creating a panel of respective dimensions
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));         //setting the border property to the panel

		panel.add(new JLabel("Bill Amount"));                                  //JLabel for bill amount
		panel.add(num1Field);                                                  //Jtextfield to enter the amount
		
		panel.add(new JLabel("Tip Percentage "));                              //Jlabel for Tippercentage
		sliderr.setMajorTickSpacing(10);                                       //seeting the slider for major and minor tick spacing
		sliderr.setMinorTickSpacing(5); 
		sliderr.setPaintLabels(true);
		sliderr.setPaintTicks(true); 
		panel.add(sliderr);                                                    //adding slider to the panel
		
		panel.add(new JLabel("Party Size"));                                   //Jlabel for party size 
		panel.add(spinner);                                                    //adding spinner to the Jpanel
		
		panel.add(calcButton);                                                 //adding calculate and clear button to the Jpanel
		panel.add(clearButton);
		
		panel.add(new JLabel("Total Bill(with Tip)"));                         //Jlabel for Totalbill
		panel.add(totallabel);                                                 //adding calculated totalbill label to panel 
		panel.add(new JLabel("Individual Share"));                             //Jlabel for Individual share
		panel.add(individuallabel);                                            //adding calculated totalbill label to panel

	    add(panel, BorderLayout.CENTER);                                       //adding panel to border layout


	}
	/************************************************************
	 *                                                          *
	 *  Method Name:  actionPerformed().                        *
	 *                                                          *
	 *  Purpose:  Method performs action when buttons are clicked 
	 *            text is entered into the text field and default 
	 *            actions are performed.                        *
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		DecimalFormat dollars = new DecimalFormat("$###,##0.00");
		double amt, amount = 0, individualshare = 0;
		int value;
		if (e.getSource() == clearButton) {                //default conditions when clear button is clicked.
			num1Field.setText("");
			sliderr.setValue(20);
			spinner.setValue(1);
			obj2.setBillamt(0);
			obj2.setTippercent(20);
			obj2.setPartysize(1);
		} else {
			try {
				amt = Double.parseDouble(num1Field.getText());    //converting the text into double
				obj2.setBillamt(0);
				amount = obj2.getTotalBill(amt);                  //calling totalbillamt method of TipCalculator and calculating it
				value = (int) spinner.getValue();                 
				individualshare = obj2.getIndividualShare(amount, value); //calling IndividualShare method of TipCalculator and calculating it

			} catch (NumberFormatException nfe1) {
				// sumLabel.setText("1st number invalid.");
				JOptionPane.showMessageDialog(null, "Bill amt must be numeric", "Error", JOptionPane.ERROR_MESSAGE); //Displaying exception in dialog box
				return;
			}
			try {
				if (amt == 0) {
					throw new Exception();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Bill amt must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE); //Displaying exception in dialog box
			}

		}
		totallabel.setText(dollars.format(amount));             
		individuallabel.setText(dollars.format(individualshare));

	}
	/************************************************************
	 *                                                          *
	 *  Method Name:  stateChange().                            *
	 *                                                          *
	 *  Purpose:  Method updates for the spinner and slider if any 
	 *            of them are changed.                          *
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	@Override
	public void stateChanged(ChangeEvent e) {
		int v = (int) spinner.getValue();
		int s = sliderr.getValue();

		obj2.setTippercent(s);
		obj2.setPartysize(v);
	}

	private TipApp(String title) {
		super(title);
	}
}
