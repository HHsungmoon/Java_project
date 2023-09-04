
import java.awt.*;
/// Board와 관련된 함수
import java.util.ArrayList;

public class Tetris_Board
{
    public static final int Board_X_Size = Tetris_Data.BOARD_WIDTH / Tetris_Data.BLOCK_SIZE;
    public static final int Board_Y_Size = Tetris_Data.BOARD_HEIGHT / Tetris_Data.BLOCK_SIZE;

    public static ArrayList<int[]> TBoard;

    public Tetris_Board()
    {
        TBoard = new ArrayList<>();
        Create_Board();
    }

    private void Create_Board()
    {
        for(int y=0; y<Board_Y_Size; y++)
        {
            int[] tmp;
            tmp = new int[Board_X_Size];
            TBoard.add(y,tmp);
        }

        for(int y=0; y<Board_Y_Size; y++)
        {
            for(int x=0; x<Board_X_Size; x++)
            {
                if(x==0 || x == Board_X_Size-1)
                    TBoard.get(y)[x] = -1;
                else if(y==Board_Y_Size-1)
                    TBoard.get(y)[x] = -1;
                else
                    TBoard.get(y)[x] = 0;
            }
        }
        TBoard.get(3)[0] = -2;
        TBoard.get(3)[Board_X_Size-1]=-2;
    }

    public int Get_Board_data(int x, int y)
    {
        return TBoard.get(y)[x];
    }
    public void Set_Board_data(int x, int y, int num)
    {
        TBoard.get(y)[x] = num;
    }

    public void Print_Board(Graphics g) 
    {
        // 게임 보드 그리기

        int sx=Tetris_Data.Board_SX;
        int sy=Tetris_Data.Board_SY;
        int bs=Tetris_Data.BLOCK_SIZE;
        for (int row = 0; row < Board_Y_Size; row++) 
        {
            for (int col = 0; col < Board_X_Size; col++) 
            {
                if( TBoard.get(row)[col] == 0){}
                else if (TBoard.get(row)[col] == -1) 
                {
                    g.setColor(Tetris_Data.Color_list[8]);
                    g.fillRect((sx+col) * bs, (sy+row) * bs, bs-1, bs-1);
                }
                else if(TBoard.get(row)[col] == -2)
                {
                    g.setColor(Color.yellow);
                    g.fillRect((sx+col) * bs, (sy+row) * bs, bs-1, bs-1);
                }
                else if(TBoard.get(row)[col] >=1 && TBoard.get(row)[col] <=7)
                {
                    g.setColor(Tetris_Data.Color_list[TBoard.get(row)[col]]);
                    g.fillRect((sx+col) * bs, (sy+row) * bs, bs-1, bs-1);
                }
            }
        }
    }

    public boolean Game_Over()
    {
        for(int x=1; x<Board_X_Size-1; x++)
        {
            if(TBoard.get(3)[x] > 0)
            {
                return true;
            }
        }
        return false;
    }

    public void Clear_Line()
    {
        for(int y=Board_Y_Size-2; y>3; y--)
        {
            boolean CL = true;
            for(int x=1; x<Board_X_Size-1; x++)
            {
                if(TBoard.get(y)[x] == 0)
                {
                    CL= false;
                    break;
                }
            }
            if(CL==true)
            {
                TBoard.remove(y);
                int[] tmp = new int[Board_X_Size];
                for(int i=0; i<Board_X_Size; i++)
                    tmp[i] = 0;
                tmp[0] = -1; tmp[Board_X_Size-1]=-1;
                TBoard.add(4, tmp);
                Tetris_Data.SCORE+=1;
                y++;
            }
        }
    }
}
