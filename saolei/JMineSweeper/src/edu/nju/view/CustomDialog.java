/*
 * Created on Dec 1, 2014
 *
 * TODO create the custom dialog
 */
package edu.nju.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomDialog {

	/**
	 *  
	 */
	public CustomDialog(JFrame parent){
		super();
		ok = false;
		
		panel = new JPanel();
		dialog = new JDialog(parent, "custom", true);
		widthField = new JTextField();
		heightField = new JTextField();
		mineNumberField = new JTextField();
		
		
		widthLabel = new JLabel("Width");
		heightLabel = new JLabel("Height");
		mineNumberLabel = new JLabel("Mine");
		okBtn = new JButton("ok");
		cancelBtn = new JButton("cancel");
		
		Font font = new Font("Monospaced", Font.PLAIN, 12);
		widthLabel.setFont(font);
		heightLabel.setFont(font);
		mineNumberLabel.setFont(font);
		okBtn.setFont(font);
		cancelBtn.setFont(font);

		widthLabel.setBounds(15, 30, 50, 20);
		heightLabel.setBounds(15, 60, 50, 20);
		mineNumberLabel.setBounds(15, 90, 50, 20);

		widthField.setBounds(60, 30, 50, 20);
		heightField.setBounds(60, 60, 50, 20);
		mineNumberField.setBounds(60, 90, 50, 20);

		okBtn.setBounds(120, 30, 70, 25);
		cancelBtn.setBounds(120, 85, 70, 25);

		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ok = true;
				dialog.setVisible(false);
			}

		});

		cancelBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				dialog.setVisible(false);
			}

		});

		panel.setLayout(null);
		panel.add(widthLabel);
		panel.add(heightLabel);
		panel.add(mineNumberLabel);
		panel.add(widthField);
		panel.add(heightField);
		panel.add(mineNumberField);
		panel.add(okBtn);
		panel.add(cancelBtn);

		dialog.setContentPane(panel);
		this.parent = parent;
	}
	
	
	public CustomDialog(JFrame parent, int width, int height, int mineNumber) {
		super();
		ok = false;
		
		panel = new JPanel();
		dialog = new JDialog(parent, "custom", true);
		widthField = new JTextField(String.valueOf(width));
		heightField = new JTextField(String.valueOf(height));
		mineNumberField = new JTextField(String.valueOf(mineNumber));
		
		
		widthLabel = new JLabel("Width");
		heightLabel = new JLabel("Height");
		mineNumberLabel = new JLabel("Mine");
		okBtn = new JButton("ok");
		cancelBtn = new JButton("cancel");
		
		Font font = new Font("Monospaced", Font.PLAIN, 12);
		widthLabel.setFont(font);
		heightLabel.setFont(font);
		mineNumberLabel.setFont(font);
		okBtn.setFont(font);
		cancelBtn.setFont(font);

		widthLabel.setBounds(15, 30, 50, 20);
		heightLabel.setBounds(15, 60, 50, 20);
		mineNumberLabel.setBounds(15, 90, 50, 20);

		widthField.setBounds(60, 30, 50, 20);
		heightField.setBounds(60, 60, 50, 20);
		mineNumberField.setBounds(60, 90, 50, 20);

		okBtn.setBounds(120, 30, 70, 25);
		cancelBtn.setBounds(120, 85, 70, 25);

		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ok = true;
				dialog.setVisible(false);
			}

		});

		cancelBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}

		});

		panel.setLayout(null);
		panel.add(widthLabel);
		panel.add(heightLabel);
		panel.add(mineNumberLabel);
		panel.add(widthField);
		panel.add(heightField);
		panel.add(mineNumberField);
		panel.add(okBtn);
		panel.add(cancelBtn);

		dialog.setContentPane(panel);
		this.parent = parent;
	}

	public boolean show() {
		ok = false;
		dialog.setBounds(parent.getX() + 50, parent.getY() + 50, 215, 175);
		dialog.setVisible(true);
		parse();
		return ok;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMineNumber() {
		return mineNumber;
	}

	private void parse() {
		try {
			width = Integer.parseInt(widthField.getText());
			height = Integer.parseInt(heightField.getText());
			mineNumber = Integer.parseInt(mineNumberField.getText());
			System.out.println("width: " + width);
			System.out.println("Height: " + height);
			System.out.println("Num: " + mineNumber);
		} catch (NumberFormatException e) {
			width = 9;
			height = 9;
			mineNumber = 10;
			return;
		}

		if (width < 9) {
			width = 9;
			widthField.setText("9");
		} else if (width > 50) {
			width = 50;
			widthField.setText("50");
		}

		if (height < 1) {
			height = 1;
			heightField.setText("1");
		} else if (height > 30) {
			height = 30;
			heightField.setText("30");
		}

		if (mineNumber > height * width || mineNumber < 1) {
			mineNumber = height * width / 7;
			mineNumberField.setText(String.valueOf(mineNumber));
		}

	}

	public boolean isOK(){
		return ok;
	}
	private JFrame parent;

	private JPanel panel;

	private JDialog dialog;

	//private JFormattedTextField widthField;
	private JTextField widthField;

	private JTextField heightField;

	private JTextField mineNumberField;

	private JLabel widthLabel;

	private JLabel heightLabel;

	private JLabel mineNumberLabel;

	private JButton okBtn;

	private JButton cancelBtn;

	private boolean ok;

	private int width;

	private int height;

	private int mineNumber;
}