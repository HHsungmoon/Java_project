package assignment3;

public abstract class Game 
{
	private String name;
	private double price;
	protected int quality; // 0~100
	
	public Game(String name, double price, int quality) 
	{
		this.name = name;
		this.quality = quality;
		this.price = price;
	}
	public String toString()
	{
		return "Name: "+ this.name + ", Quality: " + this.quality + ", Price: " + this.price +".";
	}
	public void repair()
	{
		this.quality = 100;
	}
	public abstract double getRepairCost(); 
	public abstract void lowerQuality();			
	public abstract String getQuality();

	public String get_name()
	{
		return this.name;
	}
	public double get_price()
	{
		return this.price;
	}
	public int get_quality()
	{
		return this.quality;
	}
}
