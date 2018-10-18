class Board{
    char[][] board;
    
    Board(){
        int i,j;
        board = new char[3][3];
        for(i=0;i<3;i++)
            for(j=0;j<3;j++)
                board[i][j]='-';
    }
    public int PutMark(int x, int y, char mark){
        if(board[x][y]=='-')
            {board[x][y]=mark; return 1;}
        else
            return 0;
    }
    public boolean CheckBoard(){
        int i,nr=0,j;
        boolean winner = false;
        for(i=0;i<3;i++){
            if(board[i][1]!='-' && board[i][1]==board[i][0]&& board[i][1]==board[i][2])
                winner = true;
            if(board[1][i]!='-' && board[1][i]==board[0][i]&& board[1][i]==board[2][i])
                winner = true;
        }
        if(board[1][1]!='-' && board[1][1]==board[0][0] && board[1][1]==board[2][2])
            winner = true;
        if(board[0][2]!='-' && board[0][2]==board[1][1] && board[0][2]==board[2][0])
            winner = true; 
        return winner;
    }
    public int CheckLine(int linenr){
        int i=0,sum=0;
        for(i=0;i<=2;i++)
            if(board[linenr][i] != '-')
                sum++;
        if(sum==2 && ( (board[linenr][0] == board[linenr][1]) || (board[linenr][0] == board[linenr][2]) || (board[linenr][2] == board[linenr][1]) ) )
            return 1;
        return 0;
    }
    public int CheckWinLine(int linenr, char mark){
        int i=0,sum=0;
        for(i=0;i<=2;i++)
            if(board[linenr][i] != '-')
                sum++;
        if(sum==2 && ( (board[linenr][0] == board[linenr][1] && board[linenr][1] == mark ) || (board[linenr][0] == board[linenr][2] && board[linenr][2]==mark) || (board[linenr][2] == board[linenr][1] && board[linenr][1]==mark) ) )
            return 1;
        return 0;
    }
    public int CheckCol(int colnr){
        int i=0,sum=0;
        for(i=0;i<=2;i++)
            if(board[i][colnr] != '-')
                sum++;
        if(sum==2  && ( (board[0][colnr] == board[1][colnr]) || (board[0][colnr] == board[2][colnr]) || (board[2][colnr] == board[1][colnr]) ) )
            return 1;
        return 0;
    }
    public int CheckWinCol(int colnr,char mark){
        int i=0,sum=0;
        for(i=0;i<=2;i++)
            if(board[i][colnr] != '-')
                sum++;
        if(sum==2  && ( (board[0][colnr] == board[1][colnr] && board[1][colnr]==mark) || (board[0][colnr] == board[2][colnr] && board[2][colnr]==mark) || (board[2][colnr] == board[1][colnr] && board[1][colnr]==mark) ) )
            return 1;
        return 0;
    }
    public int CheckDiag1(){
        int i,sum=0;
        for(i=0;i<3;i++)
            if(board[i][i] != '-')
                sum++;
        if(sum==2 && ( (board[0][0] == board[1][1]) || (board[0][0] == board[2][2]) || (board[2][2] == board[1][1]) ) ) 
            return 1;
        return 0;
    }
    public int CheckWinDiag1(char mark){
        int i,sum=0;
        for(i=0;i<3;i++)
            if(board[i][i] != '-')
                sum++;
        if(sum==2 && ( (board[0][0] == board[1][1] && board[1][1]==mark) || (board[0][0] == board[2][2] &&board[2][2]==mark) || (board[2][2] == board[1][1] && board[1][1]==mark) ) ) 
            return 1;
        return 0;
    }
    public int CheckDiag2(){
        int i,sum=0;
        for(i=0;i<3;i++)
            if(board[i][3-i-1] != '-')
                sum++;
        if(sum==2 && ( (board[0][2] == board[1][1]) || (board[0][2] == board[2][0]) || (board[2][0] == board[1][1]) ) ) 
            return 1;
        return 0;
    }
    public int CheckWinDiag2(char mark){
        int i,sum=0;
        for(i=0;i<3;i++)
            if(board[i][3-i-1] != '-')
                sum++;
        if(sum==2 && ( (board[0][2] == board[1][1] && board[1][1]==mark) || (board[0][2] == board[2][0] && board[0][2]==mark) || (board[2][0] == board[1][1] && board[1][1]==mark) ) ) 
            return 1;
        return 0;
    }
    public boolean FullBoard(){
        int i,j;
        int nr=0;
        boolean full = false;
        for(i=0;i<3;i++)
            for(j=0;j<3;j++)
                if(board[i][j]!='-')
                    nr++;
        if(nr==9)
            full = true;
        return full;
    }
    public void PrintBoard(){
        int i,j;
        System.out.println("The board is now:");
        for(i=0;i<3;i++){
            System.out.println();
            for(j=0;j<3;j++){
                System.out.print(" "+board[i][j]+" ");
            }
        }
        System.out.println();
    }
    
}
