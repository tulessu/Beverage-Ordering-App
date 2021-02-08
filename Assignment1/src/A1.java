
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class A1 extends JFrame implements ActionListener, MouseListener, KeyListener {

	// define global variables
	private static final long serialVersionUID = 1L;
	private JLabel lblSize, lblType, lblNum, lblInfo;
	private JTextField txtNum;
	private JButton btnAdd, btnOrder;
	private JRadioButton rdJuice, rdWater, rdTea, rdCoffee;
	private JComboBox cbSize, cbType;
	private ButtonGroup btnGroups;

	// default orderedItems
	ArrayList<String> orderedItems = new ArrayList<String>();

	// two dimensional string array to hold ordered beverages
	String orderedBev[][] = new String[9][2];
	{
		orderedBev[0][0] = "Apple Juice";
		orderedBev[0][1] = "0"; // initialized [i][1]'s 0 to know which ones are not ordered
		orderedBev[1][0] = "Orange Juice";
		orderedBev[1][1] = "0";
		orderedBev[2][0] = "Pineapple Juice";
		orderedBev[2][1] = "0";
		orderedBev[3][0] = "Water with ice";
		orderedBev[3][1] = "0";
		orderedBev[4][0] = "Water";
		orderedBev[4][1] = "0";
		orderedBev[5][0] = "Tea with sugar";
		orderedBev[5][1] = "0";
		orderedBev[6][0] = "Tea";
		orderedBev[6][1] = "0";
		orderedBev[7][0] = "Coffee with milk";
		orderedBev[7][1] = "0";
		orderedBev[8][0] = "Coffee";
		orderedBev[8][1] = "0";

	}
	// default prices
	double total = 0.0;
	double price = 0.0;

	public A1() {

		// set layout
		setLayout(null);
		setSize(700, 700);
		setLocation(600, 200);

		// initialize variables here
		lblSize = new JLabel("Select Size:");
		lblSize.setLocation(150, 65);
		lblSize.setSize(100, 50);
		add(lblSize);

		// string array for combo box options
		cbSize = new JComboBox();
		String[] selectSize = new String[3];
		selectSize[0] = "Small";
		selectSize[1] = "Medium";
		selectSize[2] = "Large";
		ComboBoxModel selectS = new DefaultComboBoxModel(selectSize);
		cbSize.setModel(selectS);
		cbSize.setLocation(150, 110);
		cbSize.setSize(100, 30);
		cbSize.setBackground(Color.WHITE);
		add(cbSize);

		lblType = new JLabel("Select which type of beverage you want to order:");
		lblType.setLocation(150, 150);
		lblType.setSize(350, 50);
		add(lblType);

		lblInfo = new JLabel("");
		lblInfo.setLocation(150, 450);
		lblInfo.setSize(300, 50);
		add(lblInfo);

		// set juice radio button
		btnGroups = new ButtonGroup();
		rdJuice = new JRadioButton("Juice");
		rdJuice.setLocation(150, 200);
		rdJuice.setSize(80, 50);
		add(rdJuice);

		// set water radio button
		rdWater = new JRadioButton("Water");
		rdWater.setLocation(230, 200);
		rdWater.setSize(70, 50);
		add(rdWater);

		rdTea = new JRadioButton("Tea");
		rdTea.setLocation(310, 200);
		rdTea.setSize(70, 50);
		add(rdTea);

		rdCoffee = new JRadioButton("Coffee");
		rdCoffee.setLocation(380, 200);
		rdCoffee.setSize(100, 50);
		add(rdCoffee);

		// makes radio buttons to choose just one at once
		btnGroups.add(rdJuice);
		btnGroups.add(rdWater);
		btnGroups.add(rdTea);
		btnGroups.add(rdCoffee);

		lblNum = new JLabel("Select how many glasses you want to order:");
		lblNum.setLocation(150, 250);
		lblNum.setSize(300, 50);
		add(lblNum);

		// set text field for number of glasses ordered
		txtNum = new JTextField();
		txtNum.setLocation(150, 300);
		txtNum.setSize(280, 30);
		add(txtNum);

		// set buttons
		btnAdd = new JButton("Add");
		btnAdd.setLocation(150, 350);
		btnAdd.setSize(100, 50);
		add(btnAdd);

		btnOrder = new JButton("Order");
		btnOrder.setLocation(350, 350);
		btnOrder.setSize(100, 50);
		add(btnOrder);

		// register buttons to respond actions
		btnAdd.addActionListener(this);
		btnOrder.addActionListener(this);
		cbSize.addActionListener(this);
		rdJuice.setSelected(true);

		setVisible(true);
	}

	public static void main(String[] args) {
		new A1();
	}

	// reset
	private void reset() {
		txtNum.setText("");
		btnAdd.setSelected(true);
		// default options
		rdJuice.setSelected(true);
		cbSize.setSelectedItem("Small");
	}

	// executes when order button is clicked
	private void orderList() {
		String priceList = "";// initialized

		for (int i = 0; i < orderedBev.length; i++) {// this for loop looks for the beverages that ordered
			if (orderedBev[i][1] != "0") { // 0 means the user didn't ordered that i'th beverage
				priceList += orderedBev[i][0] + "-" + orderedBev[i][1] + System.getProperty("line.separator");
				// adds every beverage's price and type every time user orders one
				// with line.seperator we can print the price list line by line
			}
		}
		JOptionPane.showMessageDialog(null, priceList + ""); // when user clicks order button this shows the price list
																// that user ordered

		JOptionPane.showMessageDialog(null, "You should pay " + total + " TL");// when user presses OK after pricelist
																				// message this shows the total price
																				// that user must pay

	}

	// executes when add button is clicked
	private void orderType() {

		String selectedSize = cbSize.getSelectedItem().toString(); // converts the selected combo box option to string

		// default no-beverage-selected
		boolean beverageSelected = false;
		String selectedBeverage = "";

		// default orderedItemsGlass
		int orderedItemsGlass = 0;
		double price = 0;

		// if fails catches NumberFormatException error
		orderedItemsGlass = Integer.parseInt(txtNum.getText());
		System.out.println(txtNum.getText());

		try {
			if (rdJuice.isSelected()) {
				beverageSelected = true;
				Object[] fruits = { "Apple", "Orange", "Pineapple" }; // options for combo box when juice is selected
				Object fruit = JOptionPane.showInputDialog(null, "Select a fruit.", "Select a fruit",
						JOptionPane.QUESTION_MESSAGE, null, fruits, fruits[0]);
				selectedBeverage = fruit.toString() + " Juice"; // converts the selected combo box option to string

				if (selectedSize.equals("Small")) {
					if (fruit.equals("Apple")) { // if small and apple is selected price will be...
						price = 10.0;
					}
					if (fruit.equals("Orange")) {
						price = 12.0;
					}
					if (fruit.equals("Pineapple")) {
						price = 15.0;
					}
				}

				if (selectedSize.equals("Medium")) {
					if (fruit.equals("Apple")) {
						price = 11.0;
					}
					if (fruit.equals("Orange")) {// if medium and orange is selected price will be...
						price = 13.0;
					}
					if (fruit.equals("Pineapple")) {
						price = 16.0;
					}
				}

				if (selectedSize.equals("Large")) {
					if (fruit.equals("Apple")) {
						price = 13.0;
					}
					if (fruit.equals("Orange")) {
						price = 15.0;
					}
					if (fruit.equals("Pineapple")) {
						price = 17.0;
					}
				}

			}
			if (rdWater.isSelected()) {
				beverageSelected = true;
				Object[] options = { "Yes", "No" };
				int option = JOptionPane.showOptionDialog(null, "Would you like ice?", "Ice", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]); // if water is selected user chooses
																					// ice or no ice

				if (selectedSize.equals("Small")) {
					if (option == JOptionPane.YES_OPTION) {

						selectedBeverage = "Water with ice";
						price = 1.0;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Water without ice";
						price = 0.75;

					}

				}

				if (selectedSize.equals("Medium")) {
					if (option == JOptionPane.YES_OPTION) {

						selectedBeverage = "Water with ice";

						price = 1.5;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Water without ice";

						price = 1.0;

					}

				}

				if (selectedSize.equals("Large")) {
					if (option == JOptionPane.YES_OPTION) {

						selectedBeverage = "Water with ice";

						price = 1.75;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Water without ice";

						price = 1.25;

					}

				}
			}
			if (rdTea.isSelected()) {
				beverageSelected = true;
				Object[] options = { "Yes", "No" };
				int option = JOptionPane.showOptionDialog(null, "Would you like sugar?", "Sugar",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if (selectedSize.equals("Small")) {
					if (option == JOptionPane.YES_OPTION) { // if user selects yes then assume selectedBeverage as Tea
															// with sugar

						selectedBeverage = "Tea with sugar";

						price = 1.20;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Tea";

						price = 1.10;

					}
				}

				if (selectedSize.equals("Medium")) {
					if (option == JOptionPane.YES_OPTION) {

						selectedBeverage = "Tea with sugar";

						price = 1.5;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Tea";

						price = 1.4;

					}
				}

				if (selectedSize.equals("Large")) {
					if (option == JOptionPane.YES_OPTION) {

						selectedBeverage = "Tea with sugar";

						price = 2.0;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Tea";

						price = 1.9;

					}
				}
			}
			if (rdCoffee.isSelected()) {
				beverageSelected = true;
				Object[] options = { "Yes", "No" };
				int option = JOptionPane.showOptionDialog(null, "Would you like milk?", "Milk",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if (selectedSize.equals("Small")) {
					if (option == JOptionPane.YES_OPTION) {

						selectedBeverage = "Coffee with milk";

						price = 2.50;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Coffee";

						price = 2.25;

					}
				}

				if (selectedSize.equals("Medium")) {
					if (option == JOptionPane.YES_OPTION) {

						selectedBeverage = "Coffee with milk";

						price = 3.0;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Coffee";

						price = 2.75;

					}
				}

				if (selectedSize.equals("Large")) {
					if (option == JOptionPane.YES_OPTION) {

						selectedBeverage = "Coffee with milk";

						price = 4.0;

					} else if (option == JOptionPane.NO_OPTION) {

						selectedBeverage = "Coffee";

						price = 3.75;

					}
				}

			}

			if (beverageSelected && orderedItemsGlass > 0) {

				total += orderedItemsGlass * price; // total price will be number of glass times the beverage's price
													// added to recent total

				lblInfo.setText(String.valueOf(orderedItemsGlass) + " glass(es) of " + selectedSize + " "
						+ selectedBeverage + " added."); //this shows the current selected beverage and num of glass

			    //for final price list
				if (selectedBeverage.equals("Apple Juice")) { 
                    //adds selected beverages and holds everytime 
					double x = Double.valueOf(orderedBev[0][1]) + (orderedItemsGlass * price);
					orderedBev[0][1] = String.valueOf(x); //the last value of orderedBev's price
					System.out.println("Apple juice " + orderedBev[0][1] + " TL"); 
				}
				if (selectedBeverage.equals("Orange Juice")) {

					double x = Double.valueOf(orderedBev[1][1]) + (orderedItemsGlass * price);
					orderedBev[1][1] = String.valueOf(x);
					System.out.println("Orange juice " + orderedBev[1][1] + " TL");
				}
				if (selectedBeverage.equals("Pineapple Juice")) {

					double x = Double.valueOf(orderedBev[2][1]) + (orderedItemsGlass * price);
					orderedBev[2][1] = String.valueOf(x);
					System.out.println("Pineapple juice " + orderedBev[2][1] + " TL");
				}
				if (selectedBeverage.equals("Water with ice")) {

					double x = Double.valueOf(orderedBev[3][1]) + (orderedItemsGlass * price);
					orderedBev[3][1] = String.valueOf(x);
					System.out.println("Water with ice " + orderedBev[3][1] + " TL");
				}
				if (selectedBeverage.equals("Water")) {

					double x = Double.valueOf(orderedBev[4][1]) + (orderedItemsGlass * price);
					orderedBev[4][1] = String.valueOf(x);
					System.out.println("Water " + orderedBev[4][1] + " TL");
				}
				if (selectedBeverage.equals("Tea with sugar")) {

					double x = Double.valueOf(orderedBev[5][1]) + (orderedItemsGlass * price);
					orderedBev[5][1] = String.valueOf(x);
					System.out.println("Tea with sugar " + orderedBev[5][1] + " TL");
				}
				if (selectedBeverage.equals("Tea")) {

					double x = Double.valueOf(orderedBev[6][1]) + (orderedItemsGlass * price);
					orderedBev[6][1] = String.valueOf(x);
					System.out.println("Tea " + orderedBev[6][1] + " TL");
				}
				if (selectedBeverage.equals("Coffee with milk")) {

					double x = Double.valueOf(orderedBev[7][1]) + (orderedItemsGlass * price);
					orderedBev[7][1] = String.valueOf(x);
					System.out.println("Pineapple juice " + orderedBev[7][1] + " TL");
				}
				if (selectedBeverage.equals("Coffee")) {

					double x = Double.valueOf(orderedBev[8][1]) + (orderedItemsGlass * price);
					orderedBev[8][1] = String.valueOf(x);
					System.out.println("Coffee " + orderedBev[8][1] + " TL");
				}

			}

		} catch (NumberFormatException e1) {//if user enters non-integer number or else
			JOptionPane.showMessageDialog(this, "Enter an integer number", "Invalid data", JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnAdd)) {

			// boolean beverageSelected;
			if (txtNum.getText().isEmpty()) {
				txtNum.setText("0");
			}

			int orderedItemsGlass = Integer.parseInt(txtNum.getText());
			// if none of the radio buttons clicked
			if (orderedItemsGlass == 0) { //default orderedItemGlass was 0
				                         // if user didn't enter a beverage in text field

				JOptionPane.showMessageDialog(this, "Choose a beverage type and enter an amount.",
						"No option selected ", JOptionPane.ERROR_MESSAGE);

				reset(); 

			}

			orderType(); //if add button is selected executes orderType() and reset() methods
			reset();
		}

		if (e.getSource().equals(btnOrder)) { //if order button is selected executes orderList() method
			orderList();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
