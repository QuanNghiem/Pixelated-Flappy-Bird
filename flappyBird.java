import Corrupted_ProceduralAPI.CorruptedProceduralAPI;
import java.util.*;
public class flappyBird extends CorruptedProceduralAPI {
    ArrayList <pair> col;
    int playerFrames;
    int colFrames;
    int shiftFrames;
    int lostFrames;
    int counter;
    pair player;
    boolean lost;
    boolean again;

    public void buildGame() {
        col = new ArrayList <pair> ();
        shiftFrames = 0;
        colFrames = 90;
        playerFrames = 0;
        lostFrames = 200;
        counter = 0;
        lost = false;
        player = new pair (1, 4);
        clearGrid();
        drawTile(player.getX(), player.getY(), "yellow");
        drawCounter();
        setCounterValue(counter);
        createBorder();
    }

    public void updateGame() {
        setCounterValue(counter);

        if (!lost) {
            if (player.getY() == 0 || player.getY() == 9) {
                lost = true;
            }

            if (pressingSpace()) {
                markTileForDelete(player.getX(), player.getY());
                player.setY(player.getY() + 1);
                drawTile(player.getX(), player.getY(), "yellow");
            }

            if (shiftFrames == 12) {
                shiftLeft();
                shiftFrames = 0;
                counter++;
            }

            if (colFrames == 100) {
                createColumn();
                colFrames = 0;
            }

            if (playerFrames == 24) {
                gravity();
                playerFrames = 0;
            }

            again = false;
            shiftFrames++;
            colFrames++;
            playerFrames++;
        }

        else {

            if (pressingSpace() && lost == true) {
                again = true;
                buildGame();
            }
            else {
                clearGrid();
                createMsg();
            }
        }
    }

    public void gravity() {
        markTileForDelete(player.getX(), player.getY());
        player.setY(player.getY() - 1);
        drawTile(player.getX(), player.getY(), "yellow");
    }

    public void createColumn() {
        int colX = 25;
        int Y1 = 8;
        int Y2 = 1;
        Random rand = new Random();
        int num1 = rand.nextInt(5);
        int num2 = 5 - num1;
        for (int i = 0; i < num1; i ++) {
            drawTile(colX, Y1 - i, "green");
            col.add(new pair(colX,Y1 - i));
        }
        for (int z = 0; z <= num2; z++) {
            drawTile(colX, Y2 + z, "green");
            col.add(new pair(colX,Y2 + z));
        }

    }

    public boolean checkPlayer(int x, int y) {
        return (player.getX() == x && player.getY() == y);
    }

    public void shiftLeft() {
        for (pair i : col) {
            markTileForDelete(i.getX(), i.getY());
            if (i.getX() != 0) {
                i.setX(i.getX() - 1);
                if (i.getX() == player.getX() && i.getY() == player.getY()) {
                    lost = true;
                    again = false;
                }
                else {
                    drawTile(i.getX(), i.getY(), "green");
                }
            }
        }
    }

    public void createBorder() {
        for (int i = 0; i < 27; i ++) {
            drawTile(i, 0, "red");
            drawTile(i, 9, "red");
        }
    }

    public void playerMove() {
        markTileForDelete(player.getX(), player.getY());
        player.setX(player.getX() + 1);
        drawTile(player.getX(), player.getY(), "yellow");
    }

    public void createMsg() {
        for (int i = 2; i < 23; i++) {
            if (i != 6 && i != 11 && i != 16 && i != 19) {
                drawTile(i,6,"light blue");
            }
        }

        for (int y = 2; y < 6; y++) {
            if (y == 2) {
                drawTile(2,y,"light blue");
                drawTile(5,y,"light blue");
                drawTile(7,y,"light blue");
                drawTile(8,y,"light blue");
                drawTile(9,y,"light blue");
                drawTile(10,y,"light blue");
                drawTile(12,y,"light blue");
                drawTile(15,y,"light blue");
                drawTile(17,y,"light blue");
                drawTile(18,y,"light blue");
                drawTile(20,y,"light blue");
                drawTile(22,y,"light blue");
            }
            if (y == 3) {
                drawTile(2,y,"light blue");
                drawTile(5,y,"light blue");
                drawTile(7,y,"light blue");
                drawTile(10,y,"light blue");
                drawTile(12,y,"light blue");
                drawTile(15,y,"light blue");
                drawTile(17,y,"light blue");
                drawTile(18,y,"light blue");
                drawTile(20,y,"light blue");
                drawTile(22,y,"light blue");
            }
            if (y == 4) {
                drawTile(2,y,"light blue");
                drawTile(3,y,"light blue");
                drawTile(4,y,"light blue");
                drawTile(5,y,"light blue");
                drawTile(7,y,"light blue");
                drawTile(9,y,"light blue");
                drawTile(10,y,"light blue");
                drawTile(12,y,"light blue");
                drawTile(13,y,"light blue");
                drawTile(14,y,"light blue");
                drawTile(15,y,"light blue");
                drawTile(17,y,"light blue");
                drawTile(18,y,"light blue");
                drawTile(20,y,"light blue");
                drawTile(22,y,"light blue");
            }
            if (y == 5) {
                drawTile(2,y,"light blue");
                drawTile(5,y,"light blue");
                drawTile(7,y,"light blue");
                drawTile(12,y,"light blue");
                drawTile(15,y,"light blue");
                drawTile(17,y,"light blue");
                drawTile(18,y,"light blue");
                drawTile(20,y,"light blue");
                drawTile(21,y,"light blue");
                drawTile(22,y,"light blue");
            }
        }
    }

}

class pair {
    int x;
    int y;
    public pair (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
/* API guide
 *  
 *  Drawing Tiles:
 *      drawTile(): draw random blue tile on screen
 *      drawTile(int x, int y): draw blue tile at x and y
 *      drawTile(int x, int y, string color): draw tile of given color at x and y
 *      Colors
 *          - "green"
 *          - "red"
 *          - "blue"
 *          - "lightblue"
 *          - "purple"
 *          - "yellow"
 *  Input keys
 *      pressingLeft(): returns a boolean true if you pressed Left
 *      pressingRight():  returns a boolean true if you pressed right
 *      pressingUp(): returns a boolean true if you pressed up
 *      pressingDown(): returns a boolean true if you pressed down
 *      pressingSpace(): returns a boolean true if you pressed space
 *  
 *  Deleting Tiles
 *      markTileForDelete(int x,int y): deletes tile at given x and y
 *      clearGrid(): clears grid of all tiles
 *      
 *  Win/LoseConditions
 *      winGame(): presents win game screen
 *      loseGame(): presents lose game screen
 *  
 *  Sensing Conditions
 *      isOccupied(int x, int y): returns true if a tile is at coordinates x,y
 * 
 *  Counter
 *      drawCounter(): creates a counter in the bottom right corner
 *      setCounterValue(int value): sets counter to given value 
 *    
 *  Miscellaneous  
 *      shiftTilesLeft(): moves all tiles one square to the right
 */