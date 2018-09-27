public class Fraction
{
	private int numer;
	private int denom;

	public int getNumer()
	{
		return numer;
	}
	public int getDenom()
	{
		return denom;
	}
	public String toString() //用于在main方法里输出正确的分数，而不是分数的memory address
	{
		return numer + "/" + denom;
	}

	public void setNumer( int n )
	{
		numer = n;
	}
	public void setDenom( int d )  //用于检测user输入的分母是否为0，若为0，提示并终止程序或抛出异常
	{
		if (d!=0)
		{
			denom=d;
		}
		else
		{
			System.out.print("STUUUUUUUUPID! The denominator should NOT be 0!!");
			System.exit(0);		
		}
	}

	public Fraction(  )
	{
		this( 0, 1 ); 
	}


	public Fraction( int n )
	{
		this( n, 1 ); 
	}

	public Fraction( int n, int d ) //只需要在full constructor中加入this.reduce()，通过this.reduce() call reduce方法
	{                               //其他的constructor就只需要通过this来call 这个full constructor 自动reduce
		setNumer(n); 
		setDenom(d); 
		this.reduce();
	}

	public Fraction( Fraction other )  //deep copy而不是shallow copy(只复制数据的内存地址)
	{
		this( other.numer, other.denom );
	}

	public Fraction reciprocal() //取倒数
	{
		return new Fraction (this.getDenom(), this.getNumer());
	}
	
	public Fraction multiply(Fraction other) //乘法：分子乘=*分子，分母*分母
	{
		return new Fraction (this.getNumer() * other.getNumer(), this.getDenom() * other.getDenom());
	}
	
	public Fraction divide(Fraction other) //除法：分子：this分子*other分母，分母：this分母*other分子
	{
		return new Fraction (this.getNumer() * other.getDenom(), this.getDenom() * other.getNumer());
	}
	
	public Fraction add(Fraction other) //加法；分子：this分子*other分母+other分子*this分母，分母：this分母*other分母
	{
		return new Fraction (this.getNumer() * other.getDenom() + other.getNumer() * this.getDenom(), this.getDenom() * other.getDenom());
	}
	
	public Fraction subtract(Fraction other) //减法：分子：this分子*other分母-other分子*this分母，分母：this分母*other分母
	{
		return new Fraction (this.getNumer() * other.getDenom() - other.getNumer() * this.getDenom(), this.getDenom() * other.getDenom());
	}
	
	private void reduce() //分数化简：分子分母同时除以他们的最大公约数gcd
	{
		int gcd = gcd(numer, denom);
		numer = numer / gcd;
		denom = denom / gcd;
	}
	
	private int gcd(int x, int y)  //辗转相除法得到最大公约数
	{                              //不需要比较x,y大小，因为比如gcd(44,14)和gcd(14,44)得到的结果是一样的，后者要多一次迭代
		int r;                     //因为14(r) = 14(x) % 44(y);while循环之后，2(r) = 44(x) % 14(y)就和gcd(44,14)第一次循环一样了
		while (y != 0)
		{
			r = x % y;
			x = y;
			y = r;
		}
		return x;
	}

}
/*  
       另一种求最大公约数的方法(复杂且时间长) 从1到gcd一个一个试
	int gcd = 1;
	int k = 2;
	
	while (k <= n1 && k <= n2)
	{
		if (n1 % k == 0 && n2 % k == 0)
			gcd = k;
		k++
	}
*/

