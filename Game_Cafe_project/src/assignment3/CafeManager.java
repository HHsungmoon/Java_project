package assignment3;

import java.util.*;

public class CafeManager 
{
	public static double money = 10; // 0보다 큰값으로 설정
	public static int Coffee_machine_num = 3;
	
	public static void main(String[] args)
	{
		// csv파일 경로 입력받기 -> 파일은 assignment2때 받은 파일 사용
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the path of the gamesfile?");
		String csv_path = scanner.next();
		
		// csv파일을 읽어서 데이터를 games에 저장, coffeeCorner객체 생성후 게임 데이터 저장
		CSVLoader csvLoader = new CSVLoader();
		ArrayList<Game> games = csvLoader.loadGames(csv_path);
		GameCorner gameCorner = new GameCorner(games);
		
		CoffeeCorner CF = new CoffeeCorner(Coffee_machine_num); // 커피코너 객체생성, 머신 갯수는 static변수로 설정
		
		// 입력 선택지 6개에서 5가 입력으로 들어온다면 현재 Game을 csv파일에 저장하고 프로그램을 종료한다.
		
		while(true)
		{
			// 현재 정보를 출력
			print_basic(gameCorner);
			
			// 처음 활동선택은 1~6의 int 값이므로 문자열로 입력받아 parseint를 수행한다.
			// exception체크는 inputmismatch와 범위오류로 처리했다.
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
			
			// rent할 게임 이름을 입력받아 rentoutgames()함수 수행
			if(num == 1)
			{
				System.out.println("Which game would you like to rent?");
				String rent_game = scanner.next();
				money+= gameCorner.rentOutGame(rent_game);
			}
			else if(num == 2) // 반납할 게임 이름을 입력받아 returnGame()함수 수행
			{
				System.out.println("Which game would you like to return?");
				String rent_game = scanner.next();
				gameCorner.returnGame(rent_game);
			}
			else if(num == 3) // 게임 repair 수행
			{
				System.out.println("Which game would you like to repair?");
				String game_name = scanner.next();
				money -= gameCorner.repairGame(game_name);
			}
			else if(num == 4)
			{
				// 새로운 게임을 구매하는 상황이다.
				// 구매할 게임의 이름,가격,타입을 입력 받는다. 
				System.out.println("What is the name of the game?");	
				String game_name = scanner.next();
				
				// price입력에서 exception 처리를 해주었고(price가 음수일때, 타입이 안맞을때)
				// 입력받은 game type이 주어진 Card,Board,Electronic이 아니라면 Game type Error 메시지 출력
				double game_price=0.0;
				try 
				{
					System.out.println("What is the price of the game?");
					String price_str = scanner.next();
					game_price = Double.parseDouble(price_str);
					if(game_price <= 0)
						throw new Exception();
					if(money < game_price) // 돈이 부족해 구매할 수 없다면
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
			else if(num == 5) // 파일에 현재 cafe에 대여 가능한 Game(rent한 게임 제외)을 저장하고 프로그램 종료
			{
				System.out.println("What is the file you want to save to?");
				String path = scanner.next();
				csvLoader.saveGames(gameCorner.get_gamesinCafe(), path);
				break;
			}
			else if(num == 6) // coffee 구매 실행
			{
				if(CF.is_machine_full() == true) // 현재 모든 머신이 가동중 이라면 입력받지 않음
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
	
	// 기본 상태를 출력하는 함수. game, rent 목록과 입력 선택지 6개 출력
	public static void print_basic(GameCorner games)
	{
		games.printCafeDetails();
		
		System.out.println("What would you like to do:");
		System.out.println("1: Rent a game, 2: return a game, 3: repair a game, 4: Buy a new game, 5: save games, 6: Buy coffee");
	}

}
