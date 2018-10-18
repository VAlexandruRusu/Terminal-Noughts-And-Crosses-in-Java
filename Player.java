class Player{
    public String name;
    public char mark;
    
    Player(String name,char mark){
       this.name = name;
       this.mark = mark;
    }
    public void info(){
        System.out.println("Name: "+name);
        System.out.println("Mark: "+mark);
        System.out.println();
    }
}
