
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tetris_Game 
{
    private String Player_Name;

    Font ft = new Font("SanSerif", Font.BOLD, 25);

    public Tetris_Game(String Name)
    {
        this.Player_Name = Name;
    }

    public void Print_Name(Graphics g)
    {
        g.setFont(ft);
        g.setColor(Color.BLACK);
        int px = Tetris_Data.BOARD_WIDTH+(Tetris_Data.MAP_SIZE - Tetris_Data.BOARD_WIDTH)/3;
        g.drawString("Player1 : "+Player_Name, px, 4*Tetris_Data.BLOCK_SIZE);
    }

    public void Print_Score(Graphics g)
    {
        g.setFont(ft);
        g.setColor(Color.CYAN);
        int px = Tetris_Data.BOARD_WIDTH+(Tetris_Data.MAP_SIZE - Tetris_Data.BOARD_WIDTH)/3;
        g.drawString("Score : "+Tetris_Data.SCORE , px, (4+2)*Tetris_Data.BLOCK_SIZE);
    }

    public void Print_Key_End_Message(Graphics g)
    {
        g.setFont(ft);
        g.setColor(Color.GREEN);
        g.drawString("Stop Game", Tetris_Data.BOARD_WIDTH/2-10, Tetris_Data.BOARD_HEIGHT/2-50);
    }

    public void Print_Game_Over_Message(Graphics g)
    {
        g.setFont(ft);
        g.setColor(Color.RED);
        g.drawString("Game Over!", Tetris_Data.BOARD_WIDTH/2-10, Tetris_Data.BOARD_HEIGHT/2-50);
    }

    public static void Run_Key_Event(int keyCode, Tetris_Board Board,Tetris_Block Tblock)
    {
        if(keyCode == KeyEvent.VK_LEFT) // 왼쪽으로 한칸 이동
        {
            Tblock.Move_Left();
        } 
        else if(keyCode == KeyEvent.VK_RIGHT) // 오른쪽으로 한칸 이동
        {
            Tblock.Move_Right();
        } 
        else if(keyCode == KeyEvent.VK_DOWN) // 아래로 한칸 이동
        {
            if(Tblock.Move_Possible(0, 1)==true)
                Tblock.Move_Down();
        }
        else if(keyCode == KeyEvent.VK_A) // 왼쪽으로 회전
        {
            Tblock.Rotate_Left();
        }
        else if(keyCode == KeyEvent.VK_D) // 오른쪽으로 회전
        {
            Tblock.Rotate_Right();
        }
    }


}
