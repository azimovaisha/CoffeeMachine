package machine;

import java.util.Scanner;

enum Status {
    buying, fillingWater, fillingMilk, fillingCoffee, fillingCups, standby
}

public class CoffeeMachine {

    public static Status currStatus;

    int water;
    int milk;
    int coffee;
    int cups;
    int munnies;

    CoffeeMachine() {
        currStatus = Status.standby;
        water = 400;
        milk = 540;
        coffee = 120;
        cups = 9;
        munnies = 550;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    public void onInputReceived(String input) {
        //Available actions: buy, fill, take, remaining, exit

        switch (currStatus) {
            case standby:
                switch (input){
                    case "exit":
                        System.exit(0);
                    case "buy":
                        currStatus = Status.buying;
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                        break;
                    case "remaining":
                        displayRemaining();
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        break;
                    case "take":
                        dispenseMoney();
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        break;
                    case "fill" :
                        currStatus = Status.fillingWater;
                        System.out.println("Write how many ml of water you want to add: ");
                        break;
                }
                break;
            case buying:
                switch (input) {
                    case "1":
                        makeEspresso();
                        break;
                    case "2":
                        makeLatte();
                        break;
                    case "3":
                        makeCappuccino();
                        break;
                    case "back":
                        currStatus = Status.standby;
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        break;
                }
                break;

            //Filling goes in order: water>milk>coffee>cups
            case fillingWater:
                int mlW = Integer.parseInt(input);
                water += mlW;
                System.out.println("Write how many ml of milk you want to add: ");
                currStatus = Status.fillingMilk;
                break;
            case fillingMilk:
                int mlM = Integer.parseInt(input);
                milk += mlM;
                System.out.println("Write how many grams of coffee beans you want to add: ");
                currStatus = Status.fillingCoffee;
                break;
            case fillingCoffee:
                int grams = Integer.parseInt(input);
                coffee += grams;
                System.out.println("Write how many disposable cups of coffee you want to add: ");
                currStatus = Status.fillingCups;
                break;
            case fillingCups:
                int numCups = Integer.parseInt(input);
                cups += numCups;
                currStatus = Status.standby;
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
        }
    }

    public void dispenseMoney(){
        System.out.println("I gave you $" + munnies);
        this.munnies = 0;
    }

    public void displayRemaining(){
        System.out.println("The coffee machine has:\n" + water + " ml of water\n" + milk + " ml of milk");
        System.out.println(coffee + " g of coffee beans\n" + cups + " disposable cups\n$" + munnies + " of money");
    }

    public void makeEspresso() {
        if (water < 250) {
            System.out.println("Sorry, not enough water!");
        } else if (coffee < 16) {
            System.out.println("Sorry, not enough coffee!");
        } else if (cups == 0) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= 250;
            coffee -= 16;
            munnies += 4;
            cups--;
        }

        currStatus = Status.standby;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    public void makeLatte() {
        if (water < 350) {
            System.out.println("Sorry, not enough water!");
        } else if (coffee < 20) {
            System.out.println("Sorry, not enough coffee!");
        } else if (cups == 0) {
            System.out.println("Sorry, not enough cups!");
        } else if(milk < 75) {
            System.out.println("Sorry, not enough milk!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= 350;
            coffee -= 20;
            milk -= 75;
            munnies += 7;
            cups--;
        }
        currStatus = Status.standby;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    public void makeCappuccino() {
        if (water < 200) {
            System.out.println("Sorry, not enough water!");
        } else if (coffee < 12) {
            System.out.println("Sorry, not enough coffee!");
        } else if (cups == 0) {
            System.out.println("Sorry, not enough cups!");
        } else if(milk < 100) {
            System.out.println("Sorry, not enough milk!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= 200;
            milk -= 100;
            coffee -= 12;
            munnies += 6;
            cups--;
        }
        currStatus = Status.standby;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    public static void main(String[] args) {
        CoffeeMachine meh = new CoffeeMachine();
        Scanner s = new Scanner(System.in);
        while(s.hasNext()){
            String input = s.next();
            meh.onInputReceived(input);
        }
    }


}
