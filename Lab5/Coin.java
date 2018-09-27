/*
	Coin.java THIS IS THE ONLY FILE YOU HAND IN
	THERE IS NO MAIN METHOD IN THIS CLASS!
*/
import java.util.Random;
public class Coin
{
	private final int HEADS = 1; //声明常量HEADS和TAILS
	private final int TAILS = 0;
	private int numHeads, numTails; 
	private Random r;
	public int getNumHeads()
	{
		return numHeads;
	}
	public int getNumTails()
	{
		return numTails;
	}
	private void setNumHeads(int h)
	{
		numHeads = h;
	}
	private void setNumTails(int t)
	{
		numTails = t;
	}
	public void reset() //重置游戏的方法
	{
		setNumHeads(0);
		setNumTails(0);
	}
	
	public Coin(int n) //full constructor 将Coin(seed)放入Random中，让产生的随机数相同
	{
		r = new Random(n);
		setNumHeads(0);
		setNumTails(0);
	}
	public String flip()
	{
		int test = r.nextInt(2); //结果是1或者2,50%的几率
		if (test == TAILS)
		{
			setNumTails(getNumTails() + 1); //加一是因为原本是0
			return "T";
		}
		else
		{
			setNumHeads(getNumHeads() + 1);
			return "H";
		}
	}
	
	
	
	
	
	
	
	
	
	
	

} // END COIN CLASS