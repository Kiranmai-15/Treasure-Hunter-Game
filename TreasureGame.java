import java.util.*;

class Player {
    int x = 0;
    int y = 0;
    int health = 3;
}

class GameMap {
    int size = 5;
    int treasureX;
    int treasureY;
    Set<String> traps = new HashSet<>();
    Random random = new Random();

    GameMap() {
        treasureX = random.nextInt(size);
        treasureY = random.nextInt(size);

        for(int i=0;i<3;i++){
            traps.add(random.nextInt(size) + "," + random.nextInt(size));
        }
    }

    boolean isTrap(int x,int y){
        return traps.contains(x + "," + y);
    }

    boolean isTreasure(int x,int y){
        return x == treasureX && y == treasureY;
    }
}

public class TreasureGame {

    static void printMap(Player p){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(p.x==i && p.y==j)
                    System.out.print("P ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Player player = new Player();
        GameMap map = new GameMap();

        System.out.println("🎮 Welcome to Treasure Hunter!");
        System.out.println("Move with W A S D");

        while(player.health > 0){

            printMap(player);

            System.out.println("Health: " + player.health);
            System.out.print("Move: ");
            char move = sc.next().toUpperCase().charAt(0);

            switch(move){
                case 'W': player.x--; break;
                case 'S': player.x++; break;
                case 'A': player.y--; break;
                case 'D': player.y++; break;
            }

            if(player.x < 0) player.x = 0;
            if(player.y < 0) player.y = 0;
            if(player.x > 4) player.x = 4;
            if(player.y > 4) player.y = 4;

            if(map.isTrap(player.x, player.y)){
                player.health--;
                System.out.println("💣 You hit a trap!");
            }

            if(map.isTreasure(player.x, player.y)){
                System.out.println("💰 You found the treasure! YOU WIN!");
                return;
            }
        }

        System.out.println("💀 Game Over!");
    }
}
