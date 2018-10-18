import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class xsio{//static as they will be accesed and modified by a seperate method
        static String[] names = new String[2]; //Create global names and mark strings, will be changed depending on the mode of the game
        static String[] mark =  new String[2];  
        static int robot = 0; //Variable which controls the mode that will be chosen
        static Scanner input = new Scanner(System.in);//Add a scanner
        static String yesorno = new String();//A simple y/n string to check for whether the user wants to keep playing and stuff
        static String rules = new String();//A string which will output the content of rules.txt if the user decides to read the rules
    public static void main(String[] args){        
        CreatePlayers();//Create the players first (it will just change the names,mark strings with the appropriate data depending on the mode chosen
        char mark1 = mark[0].charAt(0);
        char mark2 = mark[1].charAt(0);//Transform the marks into chars (to print more easily the board)
        Player p1 = new Player(names[0],mark1);
        Player p2 = new Player(names[1],mark2);
        Computer p3 = new Computer(names[1],mark2);
        Computer p4 = new Computer(names[0],mark1);//Create four players, 2 of them are computers 
        switch(robot){//Depending on robot (modified in CreatePlayers) play a different type of game with different combinations of AI and Players
            case 0:
                PlayGamePvP(p1,p2);
                break;
            case 1:
                PlayGamePvRandoAI(p1,p3);
                break;
            case 3:
                PlayGameRandoAIvRandoAI(p3,p4);
                break;
            case 4:
                PlayGamePvMediumAI(p1,p3);
                break;
            case 5:
                PlayGameMediumAIvsMediumAI(p3,p4);
                break;
        }
    }
    public static void PrintData(Player p1, Player p2){
        //Prints player info, asks if ready, asks if the user knows the rules of the game(will be done in the beginning of every method)
        System.out.println();
        System.out.println("Player information:");
        p1.info();//Information is stored in the player object and will be retrieved from there
        p2.info();
        System.out.println();
        System.out.println("Ready to play? y/n");
        yesorno = input.nextLine();
        if(yesorno.equals("n")){
            System.out.println(":(");
            input.close();//close the scanner
            System.exit(0);//Force - exit the program
        }
        //Print the rules of the game from a file in case the player doesn't know them, again use the yesorno string
        System.out.println("Let's start. Do you know the rules of the game? y/n");
        yesorno = input.nextLine();
        if(yesorno.equals("n")){
            System.out.println();System.out.println("The rules of Noughts and Crosses:");System.out.println();
            rules = ReadTheRulesOfTheGame();//call the method that wil read the text, put it in the string
            System.out.println(rules);//and print the string
        }
    }
    public static void PlayGamePvP(Player p1, Player p2){//Play a simple PvP game
        PrintData(p1,p2);
        int nr = 0,x,y,score1=0,score2=0;
        boolean Replay = true;
        while(Replay == true){//As long as the users want to replay 
        Board board1 = new Board();//Create a new board
        board1.PrintBoard();//Print it for reference
        while(board1.CheckBoard() == false && board1.FullBoard()==false){//As long as there is no winner and the board isnt full
            if(nr==0)
                {AskMove(board1,p1);nr=1;}//ask a move from the player, switch the player 
            else
                {AskMove(board1,p2);nr=0;}
            board1.PrintBoard();//Print the board after the move
        }
        if(board1.CheckBoard() == true){//if we have a winner print the winner, increase the score. if not, draw.
            if(nr == 0)
                {System.out.println(p2.name+" is the winner!");score1++;}
            else 
                {System.out.println(p1.name+" is the winner!");score2++;}
        }
        else
            System.out.println("The board is full, it's a draw!");
        Replay = Replay(p1,score2,p2,score1);//Method prints the score, asks if it wants to replay, returns a boolean and stops execution of the method if false
        }
    }
    public static void PlayGamePvRandoAI(Player p1, Player p2){//Takes a player and a Computer object, the only difference is that p2 will make a random move and not a input
        PrintData(p1,p2);
        int nr = 0,x,y,score1=0,score2=0;
        boolean Replay = true;
        while(Replay == true){
        Board board1 = new Board();
        board1.PrintBoard();
        while(board1.CheckBoard() == false && board1.FullBoard()==false){
            if(nr==0)
                {AskMove(board1,p1);nr=1;}
            else
                {MakeAIRandoMove(board1,p2);nr=0;}
            board1.PrintBoard();
        }
        if(board1.CheckBoard() == true){
            if(nr == 0)
                {System.out.println(p2.name+" is the winner!");score1++;}
            else 
                {System.out.println(p1.name+" is the winner!");score2++;}
        }
        else
            System.out.println("The board is full, it's a draw!");
        Replay = Replay(p1,score2,p2,score1);
        }
    }
    public static void PlayGamePvMediumAI(Player p1, Player p2){//Player vs mediumAI, same thing as above but with a different method to control the AI move
        PrintData(p1,p2);
        int nr = 0,x,y,score1=0,score2=0;
        boolean Replay = true;
        while(Replay == true){
        Board board1 = new Board();
        board1.PrintBoard();
        while(board1.CheckBoard() == false && board1.FullBoard()==false){
            if(nr==0)
                {AskMove(board1,p1);nr=1;}
            else
                {MakeAIMediumMove(board1,p2);nr=0;}
            board1.PrintBoard();
        }
        if(board1.CheckBoard() == true){
            if(nr == 0)
                {System.out.println(p2.name+" is the winner!");score1++;}
            else 
                {System.out.println(p1.name+" is the winner!");score2++;}
        }
        else
            System.out.println("The board is full, it's a draw!");
        Replay = Replay(p1,score2,p2,score1);
        }
    }
    public static void PlayGameRandoAIvRandoAI(Player p1, Player p2){//Play a AI vs AI game (random AI s)
        PrintData(p1,p2);
        int nr = 0,x,y,score1=0,score2=0;
        boolean Replay = true;
        while(Replay == true){
        Board board1 = new Board();
        board1.PrintBoard();
        while(board1.CheckBoard() == false && board1.FullBoard()==false){
            if(nr==0)
                {MakeAIRandoMove(board1,p1);nr=1;}
            else
                {MakeAIRandoMove(board1,p2);nr=0;}
            board1.PrintBoard();
        }
        if(board1.CheckBoard() == true){
            if(nr == 0)
                {System.out.println(p2.name+" is the winner!");score1++;}
            else 
                {System.out.println(p1.name+" is the winner!");score2++;}
        }
        else
            System.out.println("The board is full, it's a draw!");
        Replay = Replay(p1,score2,p2,score1);
        }
    }
    public static void PlayGameMediumAIvsMediumAI(Player p1, Player p2){//MediumAI vs Medium AI
        PrintData(p1,p2);
        int nr = 0,x,y,score1=0,score2=0;
        boolean Replay = true;
        while(Replay == true){
        Board board1 = new Board();
        board1.PrintBoard();
        while(board1.CheckBoard() == false && board1.FullBoard()==false){
            if(nr==0)
                {MakeAIMediumMove(board1,p1);nr=1;}
            else
                {MakeAIMediumMove(board1,p2);nr=0;}
            board1.PrintBoard();
        }
        if(board1.CheckBoard() == true){
            if(nr == 0)
                {System.out.println(p2.name+" is the winner!");score1++;}
            else 
                {System.out.println(p1.name+" is the winner!");score2++;}
        }
        else
            System.out.println("The board is full, it's a draw!");
        Replay = Replay(p1,score2,p2,score1);
        }
    }
    public static boolean Replay(Player p1, int score2, Player p2, int score1 ){//Prints the score, asks for user input regarding playing another game
        boolean Replay = true;
        System.out.println("Scoreboard:");//print the scoreboard
        System.out.println(p1.name+":"+score2+" | "+p2.name+":"+score1);
        input.nextLine();//yesorno had issues here with reading bcs of the input.nextInt, first give it a new line to read
        System.out.println("Do you want to play again? y/n");
        yesorno = input.nextLine();//get the input of that line, if n end the game (replay = false), if not,the loop will keep on
        if(yesorno.equals("n")){
                System.out.print("Thank you for playing! The winner of the matchup is: ");
                if(score1>score2)System.out.print(p2.name);if(score1<score2) System.out.print(p1.name);if(score1==score2)System.out.print(" it's a draw!");
                System.out.println();
                Replay=false;
            }
        return Replay;
    }
    public static void AskMove(Board board1,Player p1){//Ask for a player move
        int done = 0,x,y;
        while(done == 0){
            System.out.println(p1.name+", please make your move:");
            System.out.println("Input x:");
            x = input.nextInt();
            System.out.println("Input y:");
            y = input.nextInt();
            if(x > 2 || y>2 || x<0 || y<0)
                System.out.println("x,y can only be in 0-2 range");//correcting will not be done using exceptions
            else{//correcting is done using the while loop (done)
                if(board1.PutMark(x,y,p1.mark)==0)//If placing a mark where the user wanted is not viable (there is already another mark there) do the thing again
                    System.out.println("Invalid position, input again");
                else
                done = 1;//If everything is ok go to the next player (PutMark instantly puts the mark there if it is viable (!=0)
            }
        }
    }
    public static void MakeAIRandoMove(Board board1,Player p1){//Generate two random coordonates for the AI to do, keep generating until it finds a viable one
        int x,y;
        System.out.println("RandoAI just placed a mark");
        do{
            x =(int) ( Math.random()*3);
            y =(int) ( Math.random()*3);
        }while(board1.PutMark(x,y,p1.mark)==0);
    }
    
    public static void MakeAIMediumMove(Board board1,Player p1){
        int x,y,block=0,checkline=0,i=0,checkcol=0,mademove=0,j,winningmove=0,savedx=9,savedy=9;
        System.out.println("MediumAI just placed a mark");
//HOW IT WORKS: mademove = 0, this variable will keep staying 0 until I find a move that is either: 1.A middle move 2.A line/col move 3.Diagonal move 4.Random move
//These will be taken IN THE ORDER GIVEN ABOVE
//Priorities: 1.Middle
//2.For every other one first: Check if it needs to put a mark (either a winning move or a move that prevents loss)
//If it is a winning move make it INSTANTLY, mark that it shouldn't check anything else (done = 1)
//If there isn't a winning move BUT it MIGHT lose if it doesn't put a mark there SAVE the X,Y,because it might try to block a line instead of winning a diagonal for example
//If it checks everything and savedx,savedy hav a viable value (not 9) it means that there is NO WINNING MOVE, but there is a MOVE TO PREVENT LOSS, so do that.
//If everything remains default (mademove, savedx,savedy) it means that there is nothing to be done so it will just do a random move
        if(mademove == 0 && board1.board[1][1]=='-')
            {board1.PutMark(1,1,p1.mark);mademove=1;System.out.println("I've done a middle move");} 
        if(mademove == 0){
            i=0;
            while(i<=2){  
                if(board1.CheckLine(i)!=0){
                    if(board1.CheckWinLine(i,p1.mark)==1){
                        j=0;
                        while(board1.board[i][j]!='-')
                            j++;
                        board1.board[i][j]=p1.mark;mademove=1;i=3;System.out.println("I've done a move that wins the line");
                    }
                    else{
                        j=0;
                        while(board1.board[i][j]!='-')
                        j++;
                        savedx=i;
                        savedy=j;
                    }
                }
                    i++;
            }
        }
        if(mademove == 0){
            i=0;
            while(i<=2){
                if(board1.CheckCol(i)!=0){
                    if(board1.CheckWinCol(i,p1.mark)==1){
                        j=0;
                        while(board1.board[j][i]!='-')
                            j++;
                        board1.board[j][i]=p1.mark;mademove=1;i=3;System.out.println("I've done a move that wins the col");
                    }
                    else{
                        j=0;
                        while(board1.board[j][i]!='-')
                            j++;
                        savedx=j;
                        savedy=i;
                    }
                }
                i++;
            }
        }
        if(mademove == 0){
            i=0;
            while(i<=2 && board1.CheckDiag1()==0)
                i++;
            if(i==3)
                i=2;
            if(board1.CheckDiag1()!=0){
                if(board1.CheckWinDiag1(p1.mark)==1){
                    j=0;
                    while(board1.board[j][i]!='-')
                        j++;
                    board1.board[j][j]=p1.mark;mademove=1;System.out.println("I've done a move that wins the diag1");}
                else{
                    j=0;
                    while(board1.board[j][j]!='-')
                        j++;
                    savedx=j;savedy=j;
                }
            }

        }
        if(mademove == 0) {   
            i=0;
            while(i<=2 && board1.CheckDiag2()==0)
                i++;
            if(i==3)
                i=2;
            if(board1.CheckWinDiag2(p1.mark)==1){
                j=0;
                while(board1.board[j][3-j-1]!='-')
                    j++;
                board1.board[j][3-j-1]=p1.mark;mademove=1;System.out.println("I've done a move that wins the diag2");}
            else{
                if(board1.CheckDiag2()!=0){
                    j=0;
                    while(board1.board[j][3-j-1]!='-')
                        j++;
                    savedx=j;
                    savedy=3-j-1;
                }
            }
        }
        if(savedx != 9 && savedy != 9 && mademove == 0)
            board1.PutMark(savedx,savedy,p1.mark);
        if(mademove == 0 && savedx==9 && savedy==9){
            do{
                x =(int) ( Math.random()*3);
                y =(int) ( Math.random()*3);
            }while(board1.PutMark(x,y,p1.mark)==0);
            System.out.println("I've done a random move");
        }
    }
    public static String ReadTheRulesOfTheGame(){//Method that reads the rules of the game from a file
        String everything = null;
        BufferedReader br = null;
        //Declare a string (which will be returned) and a buffered reader 
        try{//Try to read from the file and build a string builder, which reads line by line until null
        br = new BufferedReader(new FileReader("rules.txt"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {//as long as there is text append the line and use the separator. Read the next line afterwards
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        //String everything in the end, catch exceptions
        everything = sb.toString();
        }catch(FileNotFoundException | IOException e){System.err.println("Something went wrong with the rules file "+e.getMessage());}
        finally {//If there is input (everything is not null) close the BufferedReader in the end
            if (everything != null) {
                try {
                    br.close();
                } catch (IOException e) {e.printStackTrace();}// This is unrecoverable. Just report it and move on
            }
        }
        return everything;
    }
    //Method which reads the player input and corrects any errors
    public static String ReadType(String previous, String type){//import the scanner, the previous player input (name/mark, if it exists) and the current
            String thistype;
            int count = 0;
            int maxTries = 3;//3 mistakes makes, more than that ends the program
            System.out.print("Type a "+type+":");//type = name or mark
                while(true){//while there are not more bad inputs than necessary 
                    try{
                        thistype = input.nextLine();//read the input
                        if(thistype.equals("") || thistype.equals(" "))//If there is an empty input throw error message 
                            if(maxTries-count-1 != 0)//If the user hasn't gone past the allowed tries let him try again
                                throw new NameEmptyException("Can't input an empty "+type+", you have "+(maxTries-count-1)+" tries left, please input again:");
                            else//If he did go past the nr of tries give a final eror message
                                throw new NameEmptyException("Sorry, but you have failed to input a correct "+type+".");
                        if(thistype.equals(previous))//If player 2 gives the same input as player 1 give the same error message depending on the nr of tries left
                            if(maxTries-count-1 != 0)
                                throw new NameEmptyException("Can't have two players with the same "+type+" , input another "+type+", you have "+(maxTries-count-1)+ " tries left");
                            else
                                throw new NameEmptyException("Sorry, but you have failed to input a correct "+type+".");
                        if(type.equals("mark") && thistype.length()>1)//Also give a message if a wild mark appears
                            throw new NameEmptyException("You can't input a mark longer than 1 character,you have "+(maxTries-count-1)+ " tries left");
                        return thistype; // If it went past the error messages return the type to use it in the main program
                    }catch(NameEmptyException e){ // A simple name empty exception for the first if 
                        System.err.println("NameEmptyException: " + e.getMessage());
                        if (++count == maxTries)//While true ask for input, check for errors. If the nr of tries is too high throw the error and stop the program
                            throw e;
                     }
                }
    }
    public static void CreatePlayers(){//Creates players and modifies the names, mark (global) strings depending on the input given
        System.out.println("Choose mode: 1.Player vs AI | 2. Player vs Player | 3.AI vs AI");
        int mode = input.nextInt();
        input.nextLine();
        switch(mode){
            case 2://PvP
                for (int i = 0; i < 2; i++){
                    System.out.println("Player "+(i+1)+" data");
                    if(i==0){
                        names[i] = ReadType("","name");
                        mark[i] = ReadType("","mark");
                    }
                    else{names[i] = ReadType(names[0],"name");
                        mark[i] = ReadType(mark[0],"mark");
                    }
                }
                break;
            case 1://PvAI
                System.out.println("Choose AI difficulty: 1.Random AI 2.Medium AI");//Choose the AI type(calls a method with the name and points to main the game type)
                int AImode = input.nextInt();
                input.nextLine();
                switch(AImode){
                case 1:
                    CreatePvAI("RandoAI");
                    robot = 1;
                    break;
                case 2:
                    CreatePvAI("MediumAI");
                    robot = 4;
                    break;
                }
                break;
            case 3://AIvAI (just use a generic name, not bothering with this one)
                names[0] = "AINumbroUno";
                mark[0] = "X";
                names[1] = "AINumbroDuo";
                mark[1] = "O";
                System.out.println("Will this be a battle of: 1.RandoAI's 2.MediumAI's?");
                int AIvsAIMode = input.nextInt();
                input.nextLine();
                if(AIvsAIMode == 1)
                    robot = 3;
                else
                    robot = 5;
                break;
            default:
                input.nextLine();
                CreatePlayers();
                break;
            }
    }
    public static void CreatePvAI(String AIName){//Modifies the variable strings with the appropriate names and marks (only for Player vs AI)
                    names[0] = ReadType("","name");//get a user input for name and mark (previous is empty as there is no previous)
                    mark[0] = ReadType("","mark");
                    names[1] = AIName;//NAme of the AI will be the one given in the above method
                    mark[1] = "X";//And by default it will have a cross mark
                    if(mark[0].equals("X") || mark[0].equals("x"))//if cross is taken take Noughts
                        mark[1] = "O";
    }

}//End of class
