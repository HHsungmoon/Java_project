
/// 블록과 관련된 함수들

import java.awt.*;
import java.util.Random;


public class Tetris_Block
{    
    private int bx;
    private int by;
    private int type; // null T J L I Z S O
    private int rotaion; 
    private int[][][] block_shape;

    public Tetris_Block()
    {
        this.bx = (Tetris_Data.BOARD_WIDTH/Tetris_Data.BLOCK_SIZE/2)-2;
        this.by = 0;

        Random rand = new Random();
        this.type = rand.nextInt(7) +1; // 1~7]
        this.rotaion = (int)Math.random()*4; //0~3의 값이 나옴

        this.block_shape = Tetris_Data.Block_type.get(type);
    }

    public void Print_Block(Graphics g) 
    {
        g.setColor(Tetris_Data.Color_list[type]);
        // 블록 그리기
        int now_x = Tetris_Data.Board_SX+bx;
        int now_y = Tetris_Data.Board_SY+by;
        for (int row = 0; row < 4; row++) 
        {
            for (int col = 0; col < 4; col++) 
            {
                if (this.block_shape[rotaion][row][col] == 1) 
                {
                    g.fillRect((now_x+col) * Tetris_Data.BLOCK_SIZE, (now_y+row) * Tetris_Data.BLOCK_SIZE, Tetris_Data.BLOCK_SIZE-1, Tetris_Data.BLOCK_SIZE-1);
                }
            }
        }
    }

    public int Get_bx()
    {
        return bx;
    }
    public int Get_by()
    {
        return by;
    }
    public int[][] Get_shape()
    {
        return this.block_shape[this.rotaion];
    }
    public int Get_type()
    {
        return this.type;
    }

    public boolean Move_Possible(int x, int y)
    {
        for(int tx=0; tx<4; tx++)
        {
            for(int ty=0; ty<4; ty++)
            {
                if(this.block_shape[this.rotaion][ty][tx]==1)
                {
                    if(by+ty+y < Tetris_Board.Board_Y_Size && bx+tx+x >= 0 && bx+tx+x < Tetris_Board.Board_X_Size)
                    {
                        if(Tetris_Board.TBoard.get(by+ty+y)[bx+tx+x] !=0)
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public void Merge_Block_to_Board(Tetris_Board Board)
    {
        for(int ty=0; ty<4; ty++)
        {
            for(int tx=0; tx<4; tx++)
            {
                if(this.block_shape[this.rotaion][ty][tx] == 1)
                {
                    Board.Set_Board_data(bx+tx, by+ty, type);
                }
            }
        }
    }

    public void Move_Down()
    {
        this.by += 1;
    }
    public void Move_Right()
    {
        if(Move_Possible(1, 0)==true)
            this.bx += 1;
    }
    public void Move_Left()
    {
        if(Move_Possible(-1, 0)==true)
            this.bx -= 1;
    }

    private boolean Rotate_Possible(int rt_num)
    {
        for(int tx=0; tx<4; tx++)
        {
            for(int ty=0; ty<4; ty++)
            {
                if(this.block_shape[rt_num][ty][tx]==1)
                {
                    if(by+ty < Tetris_Board.Board_Y_Size && bx+tx >= 0 && bx+tx < Tetris_Board.Board_X_Size)
                    {
                        if(Tetris_Board.TBoard.get(by+ty)[bx+tx] !=0)
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public void Rotate_Right()
    {
        int tmp = (this.rotaion+1)%4 ;
        if(Rotate_Possible(tmp) == true)
            this.rotaion = tmp;
    }
    public void Rotate_Left()
    {
        int tmp = (this.rotaion+4 -1)%4;
        if(Rotate_Possible(tmp) == true)
            this.rotaion = tmp;
    }
}
