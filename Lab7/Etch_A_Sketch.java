import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Etch_A_Sketch implements MouseListener, MouseMotionListener, ActionListener  // NOTE multiple interfaces
{
	JFrame window;
	Container content;
	JButton colorButton;
	Color currentColor;
	Color [] colors;
	int mouseX,mouseY,oldX,oldY, colorIndex;

	public Etch_A_Sketch()
	{
		JFrame window = new JFrame("Classic Etch a Sketch");
		colorButton = new JButton("Change Color");
		content = window.getContentPane();
		content.add(colorButton);
		content.setLayout( new FlowLayout() );
		content.addMouseListener(this);        // "this" is the class that implements that listener
		content.addMouseMotionListener(this);  // "this" is the class that implements that listener
		colorButton.addActionListener(this); //call下面actionPerformed方法，this代指用接口ActionListener的类
		window.setSize(640,480);
		window.setVisible(true);
		
		colors = new Color [] {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
		currentColor = Color.BLACK;
	}
	// ..............................................................
	// IMPLEMENTING MOUSELISTENER REQUIRES YOU TO WRITE (OVER-RIDE) THESE METHODS 

	//when you press & release with NO MOVEMENT while pressed
	public void mouseClicked( MouseEvent me) 
	{
		mouseX = me.getX();
		mouseY = me.getY();
		
	}
	
	// when you press 
	public void mousePressed( MouseEvent me)
	{
		mouseX = me.getX();
		mouseY = me.getY();
		//repaint();
	}

	//when you let release after dragging
	public void mouseReleased( MouseEvent me)
	{
		mouseX = me.getX();
		mouseY = me.getY();
		//repaint();
	}

	// the mouse just moved off of the JFrame
	public void mouseExited( MouseEvent me)
	{
		mouseX = me.getX();
		mouseY = me.getY();
		//repaint();
	}
	
	// the mouse just moved onto the JFrame
	public void mouseEntered( MouseEvent me)
	{
		mouseX = me.getX();
		mouseY = me.getY();
		//repaint();
	}
	// ...............................................................
	// IMPLEMENTING MOUSEMOTIONLISTENER REQUIRES YOU WRITE (OVER-RIDE) THESE METHODS 

	// mouse is moving while pressed
	public void mouseDragged( MouseEvent me) //按住鼠标拖动
	{
		mouseX = me.getX();
		mouseY = me.getY();

		if (oldX ==0 )
		{
			oldX=mouseX;
			oldY=mouseY;
			return;
		}
		
		// draw  dot (actually small line segment) between old (x,y) and current (x,y)
		
		Graphics g = content.getGraphics(); // use g to draw onto the pane
		g.setColor(currentColor); //通过改变过的currentColor改变graphics颜色
		g.drawLine( oldX,oldY, mouseX, mouseY );
		oldX = mouseX;
		oldY = mouseY;
		//repaint();
	}
	
	// moved mouse but not pressed
	public void mouseMoved( MouseEvent me)
	{
		mouseX = me.getX();
		mouseY = me.getY();
		//repaint();
	}
	
	public void actionPerformed (ActionEvent e) //因为implements了接口ActionListener，类中必须要出现actionPerformed方法
	{
		Component pressed = (Component) e.getSource(); //e.getSource得到的是在constructor中call colorButton.addActionListener(this); 的colorButton
		                                               //， 返回当前动作所指向的对象，将变量pressed赋值为 鼠标事件的发生源
		if (pressed == colorButton) //pressed已经指向colorButton, 当colorButton被点击时，两个对象相等
		{
			colorIndex = (colorIndex + 1) % colors.length; //通过取余来从index[1]开始切换颜色
			currentColor = colors[colorIndex];
		}
	}
	/*
	public void actionPerformed(ActionEvent e)
	{
		Component theEventer = (Component) e.getSource();
		theEventer.setForeground(theColors[index]);
		index = (index + 1) % theColors.length;
		theEventer.setBackground(theColors[index]);
	}
	*/
	// ..............................................................

	public static void main( String[] args)
	{
		new Etch_A_Sketch();
	}
	// a helper utility
}//EOF