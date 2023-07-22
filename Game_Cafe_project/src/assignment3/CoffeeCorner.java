package assignment3;

import java.util.*;

public class CoffeeCorner implements Observer
{
	private int coffeeIndex; 		// Set to 0 in constructor
	private HashMap<String, Double> coffeetypes;	// HashMap of coffee types with prices
	private ArrayList<CoffeeMachine> machines;	// Arraylist of all the available coffee machines
	private int next_machine_idx;
	
	public CoffeeCorner(int machineAmount)
	{
		this.machines = new ArrayList<CoffeeMachine>();
		this.coffeetypes = new HashMap<String, Double>();
		this.coffeeIndex = 0;
		this.next_machine_idx = 0;
		
		// 커피 데이터 입력
		this.coffeetypes.put("Capucino", 3.75);
		this.coffeetypes.put("Americano", 2.5);
		this.coffeetypes.put("Latte", 3.25);
	}
	
	private class CoffeeMachine extends Thread implements Observable
	{
		private ArrayList<Observer> observers = new ArrayList<Observer>();	// List of observers 
		private int index;			// Index of currently made coffee
		private String machineName;		// Name of the machine

		public CoffeeMachine(int index, String name)
		{
			this.index = index;
			this.machineName = name;
			
		}
		
		public void run() 
		{
	        // Thread's standard method implementation
	        // Perform coffee making operations here
			int persent = 0;
			for(int i=1; i<5; i++)
			{
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					System.out.println("sleep Error");
				}
				persent+=20;
				System.out.println("coffee with number: "+ this.index+ " is " + persent+"% done.");
			}
			
	        // Notify observers 
			for (Observer observer : observers) 
	        {
	            observer.update(index, machineName);
	        }
	    }

	    public void subscribe(Observer observer) 
	    {
	        observers.add(observer);
	    }

	    public void unsubscribe(Observer observer) 
	    {
	        observers.remove(observer);
	    }
	}
	
	public void update(int coffeeIndex, String machineName)
	{
		System.out.println("Coffee with number: "+coffeeIndex+ " is ready for " + machineName);
		this.machines.remove(0);
	}
	
	public void listCoffeeTypes() 	// Prints all types of coffee with the price
	{
		System.out.println("What kind of coffee do you want?");
		for(Map.Entry<String, Double> coffee : this.coffeetypes.entrySet())
		{
			System.out.println("Coffee: "+coffee.getKey() + ", Price: "+ coffee.getValue());
		}
	}
	
	public boolean is_machine_full()
	{
		if(this.machines.size() == CafeManager.Coffee_machine_num)
			return true;
		else {
			return false;
		}
	}
	
	public synchronized int makeCoffee(String type)	// Start preparing the coffee of the given type
	{	
		// type과 일치하는 커피 해시 찾기
		double coffee_pay=0; 
		try {
			coffee_pay = this.coffeetypes.get(type);
		} catch (Exception e) {
			System.out.println("This coffee is not in Menu");
			return 0;
		}
		
		if(CafeManager.money < coffee_pay) // 돈이 부족해서 커피를 못사는 경우
		{
			System.out.println("Not enough money, Can't buy coffee");
			return 0;
		}
		
		CafeManager.money -= coffee_pay;
		
		///// 머신 작동
		this.next_machine_idx = this.next_machine_idx % CafeManager.Coffee_machine_num;
		this.next_machine_idx++;
		
		System.out.println("The coffee is being prepared on Machine "+ this.next_machine_idx +"!");
		System.out.println("your number is: " + (this.coffeeIndex+1));
		CoffeeMachine CM = new CoffeeMachine(coffeeIndex+1, "Machine" + this.next_machine_idx);

		CM.subscribe(this);
		this.machines.add(CM);

		this.machines.get(machines.size()-1).start();
		this.coffeeIndex++;
		
		return 0;
	}
}
