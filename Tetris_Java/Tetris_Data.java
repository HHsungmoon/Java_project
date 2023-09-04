
import java.awt.*;
import java.util.ArrayList;

/// block 데이터, board데이터, 전역변수값, Color값
public class Tetris_Data 
{
    public static final int BLOCK_SIZE = 20;
    public static final int BOARD_WIDTH = 20 * BLOCK_SIZE;  // 보드의 가로 길이
    public static final int BOARD_HEIGHT = (25 + 4) * BLOCK_SIZE; // 보드의 세로 길이
    public static final int MAP_XY_Size = 38;
    public static final int MAP_SIZE = MAP_XY_Size * BLOCK_SIZE;    // 전체 게임 진행 맵의 크기
    
    public static final int Board_SX = 3;
    public static final int Board_SY = (MAP_SIZE - BOARD_HEIGHT)/BLOCK_SIZE/2;
    public static int SCORE=0;

    public static ArrayList<int[][][]> Block_type = new ArrayList<int[][][]>();

    public Tetris_Data()
    {
        Make_block_type();
    }

    public static void Make_block_type()
    {
        Block_type.add(null);
        Block_type.add(Tetris_Data.Block_T);
        Block_type.add(Tetris_Data.Block_J);
        Block_type.add(Tetris_Data.Block_L);
        Block_type.add(Tetris_Data.Block_I);
        Block_type.add(Tetris_Data.Block_Z);
        Block_type.add(Tetris_Data.Block_S);
        Block_type.add(Tetris_Data.Block_O);
    }

    // 배경색 T J L I Z S O 보드블록 
    public static final Color Color_list[] = { 
        Color.black, 
        Color.MAGENTA,
        Color.blue,
        Color.orange,
        Color.cyan,
        Color.red,
        Color.green,
        Color.yellow,
        Color.gray,
    };
    
    public static final int[][][] Block_T = {
        {
            {0,1,0,0},
            {1,1,1,0},
            {0,0,0,0},
            {0,0,0,0}
        },
        {
            {0,1,0,0},
            {0,1,1,0},
            {0,1,0,0},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {1,1,1,0},
            {0,1,0,0},
            {0,0,0,0}
        },
        {
            {0,1,0,0},
            {1,1,0,0},
            {0,1,0,0},
            {0,0,0,0}
        }
    };
    public static final int[][][] Block_J = {
        {
            {0,0,1,0},
            {0,0,1,0},
            {0,1,1,0},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {0,1,0,0},
            {0,1,1,1},
            {0,0,0,0}
        },
            {
            {0,0,0,0},
            {0,1,1,0},
            {0,1,0,0},
            {0,1,0,0}
        },
        {
            {0,0,0,0},
            {1,1,1,0},
            {0,0,1,0},
            {0,0,0,0}
        }
    };
    public static final int[][][] Block_L = {
        {
            {0,1,0,0},
            {0,1,0,0},
            {0,1,1,0},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {0,1,1,1},
            {0,1,0,0},
            {0,0,0,0}
        },
            {
            {0,0,0,0},
            {0,1,1,0},
            {0,0,1,0},
            {0,0,1,0}
        },
            {
            {0,0,0,0},
            {0,0,1,0},
            {1,1,1,0},
            {0,0,0,0}
        }
    };
    
    // 로테이트 2가지 I,Z,S
    public static final int[][][] Block_I = {
        {
            {0,0,0,0},
            {0,0,0,0},
            {1,1,1,1},
            {0,0,0,0}
        },
        {
            {0,0,1,0},
            {0,0,1,0},
            {0,0,1,0},
            {0,0,1,0}
        },
        {
            {0,0,0,0},
            {1,1,1,1},
            {0,0,0,0},
            {0,0,0,0}
        },
        {
            {0,1,0,0},
            {0,1,0,0},
            {0,1,0,0},
            {0,1,0,0}
        }
    };
    public static final int[][][] Block_Z = {
        {
            {0,0,0,0},
            {1,1,0,0},
            {0,1,1,0},
            {0,0,0,0}
        },
        {
            {0,0,1,0},
            {0,1,1,0},
            {0,1,0,0},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {0,1,1,0},
            {0,0,1,1},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {0,0,1,0},
            {0,1,1,0},
            {0,1,0,0}
        }
    };
    public static final int[][][] Block_S = {
        {
            {0,0,0,0},
            {0,0,1,1},
            {0,1,1,0},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {0,1,0,0},
            {0,1,1,0},
            {0,0,1,0}
        },
        {
            {0,0,0,0},
            {0,1,1,0},
            {1,1,0,0},
            {0,0,0,0}
        },
        {
            {0,1,0,0},
            {0,1,1,0},
            {0,0,1,0},
            {0,0,0,0}
        }
    };
    
    // 로테이트 1가지 O
    public static final int[][][] Block_O = {
        {
            {0,0,0,0},
            {0,1,1,0},
            {0,1,1,0},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {0,1,1,0},
            {0,1,1,0},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {0,1,1,0},
            {0,1,1,0},
            {0,0,0,0}
        },
        {
            {0,0,0,0},
            {0,1,1,0},
            {0,1,1,0},
            {0,0,0,0}
        }
    };
}
