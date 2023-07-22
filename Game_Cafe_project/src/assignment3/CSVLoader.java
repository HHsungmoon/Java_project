package assignment3;


import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class CSVLoader 
{
	public ArrayList<Game> loadGames(String fileName) 	// create games based on a CSV file
	{
		Scanner inputstream = null;
				
		// 값을 읽어오고 제대로 읽어왔다면 객체를 만들어 게임을 저장한다.
		try 
		{
			inputstream = new Scanner(new FileInputStream(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("problem opening files.");
			System.exit(0);
		}
		
		ArrayList<Game> games = new ArrayList<Game>();
		while(inputstream.hasNextLine())
		{
			String line = inputstream.nextLine();
			String[] data = line.split(",");
			Game game_type; // 파일을 읽어오면서 게임의 타입에 따라 객체 생성후 games에 추가한다.
			
			double tmp_price=0; int tmp_quality=0;
			try 
			{
				tmp_price = Double.parseDouble(data[2]);
				tmp_quality = Integer.parseInt(data[3]);
				if(data[0].equals("Card"))
				{
					game_type = new CardGame(data[1], tmp_price , tmp_quality);
					games.add(game_type);
				}
				else if(data[0].equals("Board"))
				{
					game_type = new BoardGame(data[1], tmp_price , tmp_quality);
					games.add(game_type);
				}
				else if(data[0].equals("Electronic"))
				{
					game_type = new ElectronicGame(data[1], tmp_price , tmp_quality);
					games.add(game_type);
				}
				else // 위 세가지 타입이 아닌 게임은 에러메시지만 출력한다. 다른 데이터는 정상적으로 저장
				{
					System.out.println("intput Game_type error : " + line);
				}
			} 
			catch (NumberFormatException e) 
			{
				// 데이터에 에러가 있는 파일(잘못된 파일)의 경우 메시지 출력후 프로그램 종료
				System.out.println("CSV file has Error data!");
				System.exit(0);
			}
		}
		
		return games;
	}
	public void saveGames(ArrayList<Game> games, String fileName)	// write games to a CSV file
	{
		BufferedWriter bw = null;
		PrintWriter outputstream = null;
		
		try 
		{
			bw = new BufferedWriter(new FileWriter(fileName));
			outputstream = new PrintWriter(bw);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("problem opening files. cannot find file");
			System.exit(0);
		}
		catch (IOException e) 
		{
			System.out.println("Error reading from "+fileName);
			System.exit(0);
		}
		
		for(int i=0; i< games.size(); i++)
		{
			if(games.get(i) != null)
			{
				if(games.get(i) instanceof CardGame)
				{
					String line = "Card," + games.get(i).get_name() + "," + games.get(i).get_price() + "," + games.get(i).get_quality();
					outputstream.write(line);
					outputstream.write("\n");
				}
				else if(games.get(i) instanceof BoardGame)
				{
					String line = "Board," + games.get(i).get_name() + "," + games.get(i).get_price() + "," + games.get(i).get_quality();
					outputstream.write(line);
					outputstream.write("\n");
				}
				else if(games.get(i) instanceof ElectronicGame)
				{
					String line = "Electronic," + games.get(i).get_name() + "," + games.get(i).get_price() + "," + games.get(i).get_quality();
					outputstream.write(line);
					outputstream.write("\n");
				}
				else
				{
					System.out.println("error");
					outputstream.close();
					System.exit(0);
				}
			}
		}
		outputstream.flush();
		outputstream.close();
		System.out.println("Games saved succesfully");
	}
}
