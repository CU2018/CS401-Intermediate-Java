public class MyStringP
{
	private char [] letters;
	
	public MyStringP (String other)
	{
		this.letters = other.toCharArray();
	}
	public MyString (MyString other)
	{
		this.letters = new char [other.length()];
		for (int i = 0; i < other.length(); i++)
		{
			this.letters[i] = other.letters[i];
		}
	}
	public int length()
	{
		int count = this.letters.length;
		return count;
	}
	public char charAt (index)
	{
		return this.letters[index];
	}
	int compareTo (MyString other)
	{
		for (int i = 0; i < this.length(); i++)
		{
			if (i >= other.length())
				return 1;
			if (this.letters[i] > other.letters[i])
				return 1;
			if (this.letters[i] < other.letters[i])
				return -1;
		}
		if (this.length() < other.length())
			return -1;
		return 0;
	}
	public boolean equals (MyString other)
	{
		if (this.compareTo(other) == 0)
			return true;
		else
			return false;
	}
	public indexOf (int ch)
	{
		for (int i = 0; i <this.length(); i++)
		{
			if (this.letters[i] == ch)
				return i;
		}
		return -1;
	}
	public indexOf (MyString other)
	{
		int i,j;
		for (int i = 0; i <= this.length() - other.length(); i++)
		{
			for (int j = 0; j < other.length(); j++)
			{
				if (this.letters[i+j] != other.letters[j])
					break;
			}
			if (j >= other.length())
				return i;
		}
		return -1;
	}
	public String toString()
	{
		String word = new String(this.letters);
		return word;
	}
}