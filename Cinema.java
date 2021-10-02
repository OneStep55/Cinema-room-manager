import java.util.Scanner;

public class Cinema {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = sc.nextInt();
        char[][] arr = new char[rows][columns];
        int income = 0;
        int tickets = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                arr[i][j] = 'S';
            }
        }
        int n = 1;
        while (n != 0){
            printMenu();
            n = sc.nextInt();

            switch (n){
                case 1:
                    printCinema(arr,rows,columns);
                    break;
                case 2:
                    income += buyTicket(arr,rows,columns);
                    tickets++;
                    break;
                case 3:
                    statistics(tickets, income, rows, columns);
                    break;
                default:
                    System.out.println("Incorrect number");
                    break;
            }
        }



    }

    public static void printMenu() {
        System.out.println("1. Show the seats\n "+
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static void printCinema(char[][] arr, int row, int col) {
        System.out.println("Cinema: ");
        System.out.print("  ");
        for (int i = 1; i <= col; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < col; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static  int buyTicket(char[][] arr, int rows, int columns) {
        Scanner sc = new Scanner(System.in);
        int row, column;
        while (true) {
            System.out.println("Enter a row number:");
            row = sc.nextInt() - 1;
            System.out.println("Enter a seat number in that row:");
            column = sc.nextInt() - 1;
            if(row < 0 || column < 0  ){
                System.out.println("Wrong input!");
                continue;
            } else if (row > arr.length - 1  || column > arr[row].length - 1){
                System.out.println("Wrong input!");
                continue;
            } else if (arr[row][column] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            break;
        }
        arr[row][column] = 'B';
        int seats = rows * columns;
        int price;


        if(seats <= 60 || row + 1 <= rows / 2) {
            price = 10;
        } else {
            price = 8;
        }
        System.out.println("Ticket price: $" + price);
        return price;
    }

    public static void statistics(int tickets, int income, int rows, int columns ){
        int total = 0;
        int seats = columns * rows;
        double percentage = (double) tickets * 100 / seats;
        if(seats <= 60){
            total += 10 * seats;
        } else {
            total += 10 * columns * (rows / 2);
            total += 8 * columns * (rows - rows/2);

        }
        System.out.printf("Number of purchased tickets: %d\n", tickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.printf("Current income: $%d\n", income);
        System.out.printf("Total income: $%d\n", total);
    }
}