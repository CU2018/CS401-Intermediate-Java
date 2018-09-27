public class MyString
{
	private char[] letters;
	
	public MyString( String other )
	{	
		this.letters = other.toCharArray(); //将String变成char数组
		// MUST BE DEEP COPY: USE REAL STRING'S BUILT IN METHOD 
		// TO RETURN A DEEP COPY OF THE THE UNDERLYING CHAR ARRAY
	}
	public MyString( MyString other )// DONT do this ->  letters = other.letters     BECUASE THAT IS SHALLOW COpY!!!
	{	
		this.letters = new char [other.length()];	//建一个和other长度一样的letters char数组

		for (int i = 0; i < other.length(); i++) //deep copy将每个值一个个放入this.letters的数组里
		{
			this.letters[i] = other.letters[i];
		}
	}	
	public int length() //返回数组大小
	{
		int count = this.letters.length;
		return count;
	}
	public char charAt(int index) //返回放入的index对应的letters数组中的char
	{
		return this.letters[index]; // THE null CHAR
	}
	int compareTo(MyString other)
	{
		for (int i = 0; i < this.length(); i++) //通过index一个个比较char数组中对应字母是否相同，仅限this长度大于other长度的时候
		{
			if (i >= other.length()) //看i和other长度比较，大于等于的情况都说明this长度大于other，因为i<this.length
				return 1;
			if (this.letters[i] > other.letters[i]) //this和other相同index对比ascii，this大返回1，否则返回-1
				return 1;
			if (this.letters[i] < other.letters[i]) 
				return -1;
		}
		if (this.length() < other.length())  //this长度小于other长度，返回-1
			return -1;
		return 0; //经过上面的只剩下相等的情况
	}	
	public boolean equals(MyString other)
	{
		if (this.compareTo(other) == 0) //通过reuse compareTo方法，完全一样就返回true
			return true;
		else
			return false;
	}
	public int indexOf(int ch)	//int 是多字节整型，char是一字节整型，可以相互转换。char转换位int型没有任何问题，
	{                           //int转化为char型可能会造成loss of precision（只从低端截取1字节赋给char型变量）
		for (int i = 0; i < this.length(); i++) //通过index一个个看和ch相同的char，并返回index
		{
			if (this.letters[i] == ch)
				return i;
		}
		return -1;	
	}
	public int indexOf(MyString other)
	{
		int i,j;
		for (i = 0; i <= this.length() - other.length();i++) //只需要比较this和other的长度差值，再超过this的长度就没办法容下other
		{
			for(j = 0; j < other.length(); j++) 
			{
				if(this.letters[i+j] != other.letters[j]) //从this.letter[0]开始比较，一个一个比直到和other.letters[0]相同才j++看后面的字符
					break;
			}
			if (j == other.length()) //当other的最后一个字符也能匹配上时，j++，但是不会继续循环，此时j会等于other.length()，说明index正确
				return i;
		}
		return -1; 		//其他情况都是无法匹配上的情况，返回-1
	}
	public String toString() //输出普通java的String
	{
		String word = new String(this.letters); //创建一个新String，将char数组赋给它，直接转化为String
		return word;
	}
} // END MYSTRING CLASS
