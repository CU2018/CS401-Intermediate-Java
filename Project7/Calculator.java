import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Calculator
{
	JFrame window;
	Container content;
	JButton[] digits = new JButton[12];
	JButton[] ops = new JButton[4];
	JTextField expression;
	JButton equals;
	JTextField result;
	String [] opCodes = {"+", "-", "*", "/"};
	String express, res;
	
	public Calculator()
	{
		window = new JFrame();
		content = window.getContentPane();
		content.setLayout(new GridLayout(2,1));
		ButtonListener listener = new ButtonListener();
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,3));
		
		expression = new JTextField();
		expression.setFont(new Font("verdena", Font.BOLD, 16));
		expression.setText("");
		
		equals = new JButton("=");
		equals.setFont(new Font("verdena", Font.BOLD, 20));
		equals.addActionListener(listener);
		
		result = new JTextField();
		result.setFont(new Font("verdena", Font.BOLD, 16));
		result.setText("");
		
		topPanel.add(expression);
		topPanel.add(equals);
		topPanel.add(result);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		
		JPanel digitsPanel = new JPanel();
		digitsPanel.setLayout(new GridLayout(4,3));
		
		for (int i = 0; i < 10; i++)
		{
			digits[i] = new JButton("" + i);
			digitsPanel.add(digits[i]);
			digits[i].addActionListener(listener);
		}
		digits[10] = new JButton("C");
		digitsPanel.add(digits[10]);
		digits[10].addActionListener(listener);
		
		digits[11] = new JButton("CE");
		digitsPanel.add(digits[11]);
		digits[11].addActionListener(listener);
		
		JPanel opsPanel = new JPanel();
		opsPanel.setLayout(new GridLayout(4,1));

		for (int i = 0; i < 4; i++)
		{
			ops[i] = new JButton(opCodes[i]);
			opsPanel.add(ops[i]);
			ops[i].addActionListener(listener);
		}
		bottomPanel.add(digitsPanel);
		bottomPanel.add(opsPanel);
		
		content.add(topPanel);
		content.add(bottomPanel);
		
		window.setSize(640,480);
		window.setVisible (true);
	}
		
	class ButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			Component whichButton = (Component)e.getSource();
				
			for (int i = 0; i < 10; i++)
			{
				if (whichButton == digits[i])
					expression.setText(expression.getText() + i);
			}
				
			if (whichButton == digits[10])
				expression.setText("");
				
			if (whichButton == digits[11])
			{
				express = expression.getText();
				if (express != null && express !="" && express.length() > 0 ) 
					express = express.substring(0, express.length()-1);
				expression.setText(express);
			}
				
			for (int i = 0; i < 4; i++)
			{
				if (whichButton == ops[i])
					expression.setText(expression.getText() + opCodes[i]);
			}
				
			if (whichButton == equals)
			{
				express = expression.getText();
				if (express != null && express != "") 
				{
					ArrayList<String> operatorList = new ArrayList<String> ();
					ArrayList<String> operandList = new ArrayList<String> ();
					StringTokenizer st = new StringTokenizer(express, "+-*/", true);
					ArrayList<Double> operandDouble = new ArrayList<Double>();
					while (st.hasMoreTokens())
					{
						String token = st.nextToken();
						if ("+-*/".contains(token))
							operatorList.add(token);
						else
							operandList.add(token);
					}
					if (operatorList.size() + 1 != operandList.size())
					{
						res = "BOGUS EXPRESSION";
						result.setText(res);
					}
					else
					{
						
						for (String oper : operandList)
						{
							double operands = Double.valueOf(oper);
							operandDouble.add(operands);
						}
						if (express.contains("+") || express.contains("-") || express.contains("*") || express.contains("/"))
						{
							while (operatorList.contains("*") || operatorList.contains("/"))
							{
								int multi = operatorList.indexOf("*");
								int div = operatorList.indexOf("/");
								if ((multi != -1 && multi < div) || div == -1)
								{
									operandDouble.set(multi, operandDouble.get(multi) * operandDouble.get(multi + 1));
									operandDouble.remove(multi + 1);
									operatorList.remove(multi);
								}
								else
								{
									operandDouble.set(div, operandDouble.get(div) / operandDouble.get(div + 1));
									operandDouble.remove(div + 1);
									operatorList.remove(div);
								}
							}
							while (operatorList.contains("+") || operatorList.contains("-"))
							{
								int plus = operatorList.indexOf("+");
								int minus = operatorList.indexOf("-");
								if ((plus != -1 && plus < minus) || minus == -1)
								{
									operandDouble.set(plus, operandDouble.get(plus) + operandDouble.get(plus + 1));
									operandDouble.remove(plus + 1);
									operatorList.remove(plus);
								}
								else
								{
									operandDouble.set(minus, operandDouble.get(minus) - operandDouble.get(minus + 1));
									operandDouble.remove(minus+1);
									operatorList.remove(minus);
								}
							}
							res = operandDouble.get(0) + "";
							result.setText(res);
						}
						else
						{
							res = operandDouble.get(0) + "";
							result.setText(res);
						}
					}			
				}
			}
		}
	}

		
		public static void main (String [] args)
		{
			new Calculator();
		}
	
}