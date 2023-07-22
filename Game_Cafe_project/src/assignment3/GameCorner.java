package assignment3;

import java.util.ArrayList;

public class GameCorner 
{
	private ArrayList<Game> gamesinCafe;
	private ArrayList<Game> rentedOutGames;

	public GameCorner(ArrayList<Game> games)
	{
		this.gamesinCafe = new ArrayList<Game>();
		for(int i=0; i<games.size(); i++)
		{
			this.gamesinCafe.add(games.get(i));
		}
		this.rentedOutGames = new ArrayList<Game>();
	}
	
	public ArrayList<Game> get_gamesinCafe()
	{
		return this.gamesinCafe;
	}
	
	public double rentOutGame(String name)                   // Cost of renting is the game��s price * 0.5
	{
		int idx = getIndexGamesInCafe(name);
		// out_index -1 �̶�� �ǹ̴� �迭���� ������ ã�� ���� ���̱� ������ �Ʒ� �ڵ� ����
		if(idx == -1) 
		{
			System.out.println("This is not one of our games that can be rented!");
			return 0;
		}
		if(this.gamesinCafe.get(idx).getQuality().equals("Bad") ) 
		{
			System.out.println("Game needs to repair. can not rent game!");
			return 0;
		}
		// ���� Rent ����
		// ������ ���Ӱ��� ����x0.5 ��ŭ money ����
		double money = this.gamesinCafe.get(idx).get_price()*0.5;
		
		// gamesinCafe, rentedOutGames ������Ʈ
		this.rentedOutGames.add(this.gamesinCafe.get(idx));
		this.gamesinCafe.remove(idx);
		
		System.out.println("Game rented succefully");
		return money;
	}
	public void returnGame(String name)
	{
		int idx = getIndexRentedOutGames(name);
		// -1 �̶�� �ǹ̴� �迭���� ������ ã�� ���� ���̱� ������ �Ʒ� �ڵ� ����
		if(idx == -1)
		{
			System.out.println("This is not one of our games that is rented out!");
			return;
		}
		
		// �ݳ��ϸ� quality�� ���ߴ� �Լ� ����
		this.rentedOutGames.get(idx).lowerQuality();
		
		// gamesinCafe, rentedOutGames ������Ʈ
		this.gamesinCafe.add(this.rentedOutGames.get(idx));
		this.rentedOutGames.remove(idx);
		System.out.println("Game returned succefully");
	}
	public void buyGame(Game game)
	{	
		CafeManager.money -= game.get_price(); // �� ����
		
		this.gamesinCafe.add(game);
		
		System.out.println("Game bought succesfully");
	}
	public void printCafeDetails()                   // Prints all games in the cafe and rented out, and the money in the register
	{
		// ���� ������ ����ϴ� �ڵ�
		System.out.printf("Money: %.2f\n", CafeManager.money);
		System.out.println("Games in cafe:");
		for(int i=0; i<this.gamesinCafe.size(); i++)
		{
			if(gamesinCafe.get(i) != null)
			{
				System.out.println(gamesinCafe.get(i).toString());
			}
		}
		System.out.println("Games rented out:");
		for(int i=0; i< this.rentedOutGames.size(); i++)
		{
			if(rentedOutGames.get(i) != null)
			{
				System.out.println(this.rentedOutGames.get(i).toString());
			}
				
		}
	}
	public double repairGame(String name)
	{
		int idx = getIndexGamesInCafe(name);
		if(idx == -1) // ������ ã�� ������ ��
		{
			System.out.println("Can't repair. this game is not in cafe");
			return 0;
		}
		// ����Ƽ�� Good�̾ ������ �ʿ� ������
		if(gamesinCafe.get(idx).getQuality().equals("Good")) 
		{
			System.out.println("This game quality is good. need not repair");
			return 0;
		}
		
		double cost = this.gamesinCafe.get(idx).getRepairCost(); // ��������� ����
		this.gamesinCafe.get(idx).repair();
		return cost;
	}
	private int getIndexGamesInCafe(String name)         // Helper function to get the index of the given game in the array, -1 if game doesn��t exist.
	{
		for(int i=0; i<this.gamesinCafe.size(); i++)
		{
			if(gamesinCafe.get(i) != null)
			{
				if(this.gamesinCafe.get(i).get_name().equals(name))
					return i;
			}	
		}
		return -1;
	}
	private int getIndexRentedOutGames(String name)   // Helper function to get the index of the given game in the array, -1 if game doesn��t exist.
	{
		for(int i=0; i<this.rentedOutGames.size(); i++)
		{
			if(rentedOutGames.get(i) != null)
			{
				if(rentedOutGames.get(i).get_name().equals(name))
					return i;
			}
		}
		return -1;
	}
}
