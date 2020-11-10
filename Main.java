package cinema;
import java.util.*;
public class Cinema {
    private int row;
    private int col;
    private char[][] chars;
    private int totalseats;
    private int ticketsnum = 0;
    private int currentincome = 0;
    private int totalincome = 0;

    public void initCinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        col = scanner.nextInt();
        
        totalseats = row * col;
        chars = new char[row+1][col+1];

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if ( i == 0 && j == 0) {
                    chars[i][j] = ' ';
                } else if (i == 0) {
                    chars[i][j] = (char) ('0' + j);
                } else if (j == 0) {
                    chars[i][j] = (char) ('0' + i);
                } else {
                    chars[i][j] = 'S';
                }

            }
            System.out.println();
        }
        
        // calculate totalincome;
        if ( totalseats <= 60) {
            totalincome = totalseats * 10;
        } else {
            // calculate the front part
            totalincome += (row / 2 * col) * 10;
            // calculate  the left part
            totalincome += (row - (row / 2)) * col * 8;
        }
    }
    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static int getChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void showSeats() {
        System.out.println("Cinema:");
        int width = col + 1;//二维数组的宽度等于一排座位数加数字位
        int depth = row + 1;
        
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(chars[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void buyTicket() {
        Scanner scanner = new Scanner(System.in);
        int seatrow;
        int seatcol;
        while (true) {
            System.out.println("Enter a row number:");
            seatrow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatcol = scanner.nextInt();
            if (seatrow <0 || seatcol < 0 || seatrow > row || seatcol > col) {
                System.out.println("Wrong input!");
                continue;
            }
            if (chars[seatrow][seatcol] == 'S') {
                break;
            } else {
                System.out.println("That ticket has already been purchased");
                continue;
            }
            
        }
        
         
        int ticket;
        if (totalseats <= 60) {
            ticket = 10;
        } else {
            ticket = seatrow > row /2 ? 8 : 10;
        }
        currentincome += ticket;
        System.out.println("Ticket price: $" + ticket);

        // 将二维数组中相应位置设为B
        chars[seatrow][seatcol] = 'B';
        ticketsnum++;
        
    }
    
    public void statistics () {
        double percentage = ticketsnum / (double)totalseats * 100.0;
        System.out.printf("\nNumber of purchased tickets: %d\n",ticketsnum );
        System.out.printf("Percentage: %.2f%%\n",percentage);
        System.out.printf("Current income: $%d\n",currentincome);
        System.out.printf("Total income: $%d\n",totalincome);
    }


    public static void main(String[] args) {
        // Write your code here
        Cinema cinema = new Cinema();
        cinema.initCinema();
        boolean toContinue = true;
        while (true && toContinue) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    //1. Show the seats
                    cinema.showSeats();
                    break;
                case 2:
                    //2. Buy a ticket
                    cinema.buyTicket();
                    break;
                case 3:
                    cinema.statistics();
                    break;
                case 0:
                    //Exit
                    toContinue = false;
                    break;
                default:
                    //error
                    System.out.println("Error! try again");
                    break;
            }
        }

    }
}
