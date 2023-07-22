package assignment3;

import java.util.*;

public class CafeManager 
{
	public static double money = 10; // 0���� ū������ ����
	public static int Coffee_machine_num = 3;
	
	public static void main(String[] args)
	{
		// csv���� ��� �Է¹ޱ� -> ������ assignment2�� ���� ���� ���
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the path of the gamesfile?");
		String csv_path = scanner.next();
		
		// csv������ �о �����͸� games�� ����, coffeeCorner��ü ������ ���� ������ ����
		CSVLoader csvLoader = new CSVLoader();
		ArrayList<Game> games = csvLoader.loadGames(csv_path);
		GameCorner gameCorner = new GameCorner(games);
		
		CoffeeCorner CF = new CoffeeCorner(Coffee_machine_num); // Ŀ���ڳ� ��ü����, �ӽ� ������ static������ ����
		
		// �Է� ������ 6������ 5�� �Է����� ���´ٸ� ���� Game�� csv���Ͽ� �����ϰ� ���α׷��� �����Ѵ�.
		
		while(true)
		{
			// ���� ������ ���
			print_basic(gameCorner);
			
			// ó�� Ȱ�������� 1~6�� int ���̹Ƿ� ���ڿ��� �Է¹޾� parseint�� �����Ѵ�.
			// exceptionüũ�� inputmismatch�� ���������� ó���ߴ�.
			int num=-1;
			try 
			{
				String str = scanner.next();
				num = Integer.parseInt(str);
				if(num<1 || num >6)
					throw new Exception();
			} 
			catch (NumberFormatException e) {
				System.out.println("NumberFormat Error! Retry");
			}
			catch (Exception e) {
				System.out.println("input Error! Retry");
			}
			
			// rent�� ���� �̸��� �Է¹޾� rentoutgames()�Լ� ����
			if(num == 1)
			{
				System.out.println("Which game would you like to rent?");
				String rent_game = scanner.next();
				money+= gameCorner.rentOutGame(rent_game);
			}
			else if(num == 2) // �ݳ��� ���� �̸��� �Է¹޾� returnGame()�Լ� ����
			{
				System.out.println("Which game would you like to return?");
				String rent_game = scanner.next();
				gameCorner.returnGame(rent_game);
			}
			else if(num == 3) // ���� repair ����
			{
				System.out.println("Which game would you like to repair?");
				String game_name = scanner.next();
				money -= gameCorner.repairGame(game_name);
			}
			else if(num == 4)
			{
				// ���ο� ������ �����ϴ� ��Ȳ�̴�.
				// ������ ������ �̸�,����,Ÿ���� �Է� �޴´�. 
				System.out.println("What is the name of the game?");	
				String game_name = scanner.next();
				
				// price�Է¿��� exception ó���� ���־���(price�� �����϶�, Ÿ���� �ȸ�����)
				// �Է¹��� game type�� �־��� Card,Board,Electronic�� �ƴ϶�� Game type Error �޽��� ���
				double game_price=0.0;
				try 
				{
					System.out.println("What is the price of the game?");
					String price_str = scanner.next();
					game_price = Double.parseDouble(price_str);
					if(game_price <= 0)
						throw new Exception();
					if(money < game_price) // ���� ������ ������ �� ���ٸ�
					{
						System.out.println("Not enough funds for the game");
						continue;
					}
					
					System.out.println("What is the type of the game?");
					String game_type = scanner.next();
					
					Game new_game=null;
					if(game_type.equals("Card") || game_type.equals("Board") || game_type.equals("Electronic"))
					{
						if(game_type.equals("Card"))
						{
							new_game = new CardGame(game_name, game_price, 100);
						}
						else if(game_type.equals("Board"))
						{
							new_game = new BoardGame(game_name, game_price, 100);
						}
						else if(game_type.equals("Electronic"))
						{
							new_game = new ElectronicGame(game_name, game_price, 100);
						}
						gameCorner.buyGame(new_game);
	
					}
					else 
					{
						System.out.println("Game type Error");
					}
				} 
				catch(NumberFormatException e)
				{
					System.out.println("price NumberFormat Error.");
				}
				catch (Exception e) 
				{
					System.out.println("input price Error. price can't be Minus");
				}
			}
			else if(num == 5) // ���Ͽ� ���� cafe�� �뿩 ������ Game(rent�� ���� ����)�� �����ϰ� ���α׷� ����
			{
				System.out.println("What is the file you want to save to?");
				String path = scanner.next();
				csvLoader.saveGames(gameCorner.get_gamesinCafe(), path);
				break;
			}
			else if(num == 6) // coffee ���� ����
			{
				if(CF.is_machine_full() == true) // ���� ��� �ӽ��� ������ �̶�� �Է¹��� ����
				{
					System.out.println("All machine is running, please Order later");
					continue;
				}
				CF.listCoffeeTypes();
				String coffee_name = scanner.next();
				CF.makeCoffee(coffee_name);
			}
			
			System.out.println();
		}
	}
	
	// �⺻ ���¸� ����ϴ� �Լ�. game, rent ��ϰ� �Է� ������ 6�� ���
	public static void print_basic(GameCorner games)
	{
		games.printCafeDetails();
		
		System.out.println("What would you like to do:");
		System.out.println("1: Rent a game, 2: return a game, 3: repair a game, 4: Buy a new game, 5: save games, 6: Buy coffee");
	}

}
