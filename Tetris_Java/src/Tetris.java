import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tetris extends JFrame implements KeyListener
{    
    public Tetris_Data Tdata=new Tetris_Data();
    Tetris_Board Tboard = new Tetris_Board();
    public Tetris_Block Tblock;
    public Tetris_Game Tgame;

    private boolean Key_End = false;
    private boolean Game_End = false;

    public Tetris() 
    {
        setTitle("Tetris_Java MadeBy HSM"); // 창 제목 설정
        setSize(Tetris_Data.MAP_SIZE, Tetris_Data.MAP_SIZE); // 창 크기 설정
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 창 닫힘 동작 설정
        setLocationRelativeTo(null); // 창 위치를 화면 중앙에 배치
        setResizable(false); // 창 크기 조절 불가능하게 설정
        
        addKeyListener(this); // 키 이벤트 리스너 추가

        Tgame = new Tetris_Game("HSM");
        Tblock = new Tetris_Block();
        
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        Tboard.Print_Board(g);
        Tblock.Print_Block(g);
        Tgame.Print_Name(g);
        Tgame.Print_Score(g);
        if(Game_End == true)
            Tgame.Print_Game_Over_Message(g);
        if(Key_End == true)
            Tgame.Print_Key_End_Message(g);
    }

    // 아래 메서드는 구현되지 않았지만 KeyListener 인터페이스를 구현하기 위해 필요함
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    // 키 이벤트 처리
    public void keyPressed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE)
            Key_End = true;
        else
            Tetris_Game.Run_Key_Event(keyCode, Tboard,Tblock); // keycode에 따른 함수 수행
        repaint(); // 화면 갱신
    }
    
    public void Start_Game()
    {
        while(true) 
        {
            // 1초 대기
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 1초마다 블록 아래로 이동
            if(Tblock.Move_Possible(0, 1))
            {
                Tblock.Move_Down();
            }
            else
            {
                Tblock.Merge_Block_to_Board(Tboard);
                Tboard.Clear_Line();
                Tblock = new Tetris_Block();
            }
            
            // 게임 상태 업데이트 및 화면 갱신
            repaint();

            if(Tboard.Game_Over() == true)
            {
                Game_End = true;
                break;
            }
            if(Key_End == true)
            {
                break;
            }
        }
    }

    public static void main(String[] args) 
    {
        Tetris tetris = new Tetris();
        tetris.setVisible(true); // 게임 창을 보이게 함
        tetris.Start_Game();
    }
}