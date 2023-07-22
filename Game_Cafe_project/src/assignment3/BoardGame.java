package assignment3;

public class BoardGame extends Game
{
	public BoardGame(String name, double price, int quality)
	{
		super(name,price, quality);
	}
	public double getRepairCost() 		// $0.04 per quality point
	{
		double cost = (100-this.quality) * 0.04;
		return cost;
	}
	public void lowerQuality()		// Lowers the quality of the game by 25 points
	{
		this.quality -= 25;
	}
	public String getQuality()		// Quality > 70 == Good, 70-50 == Okay, < 50 == Bad
	{
		if(this.quality > 70)
			return "Good";
		else if(this.quality < 50)
			return "Bad";
		else 
			return "Okay";
	}
	public String toString()
	{
		return "Name: "+ this.get_name() + ", Quality: " + this.getQuality() 
		+ ", Price: " + this.get_price() + " , type: Board";
	}
}
