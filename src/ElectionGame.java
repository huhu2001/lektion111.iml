import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.*;

/**
 * This is Main Class
 * @author Huanghui
 * @since 2020-11-10
 */
public class ElectionGame {
    /**
     * This is main method
     * @param args notUsed
     */
    public static void main(String[] args) {
        USAState[] State = new USAState[2];
        USACandidate C1 = new USACandidate();
        USACandidate C2 = new USACandidate();
        Scanner input = new Scanner(System.in);
        int winner;

        System.out.println("Please input the name of Candidate1: ");
        C1.name = input.next();
        System.out.println("Please input the party of Candidate1(0: Republic, 1: Democratic): ");
        C1.party = input.nextInt();
        if(C1.party != 0 && C1.party != 1) {
            System.out.println("Wrong Input!");
            return;
        }

        System.out.println("Please input the name of Candidate2: ");
        C2.name = input.next();
        System.out.println("Please input the party of Candidate2(0: Republic, 1: Democratic): ");
        C2.party = input.nextInt();

        if((C2.party != 0 && C2.party != 1) || (C1.party == C2.party)) {
            System.out.println("Wrong Input!");
            return;
        }

        for(int i = 0; i < 2; i++) {
            State[i] = new USAState();
        }

        State[0].name = "NY";
        State[0].population = 2.0f;

        State[1].name = "CA";
        State[1].population = 10.2f;

        for(int i = 0; i < 2; i++) {
            winner = State[i].whoWin();
            if(winner == C1.party) {
                C1.ticketNumber += State[i].generateTicket();
            } else if(winner == C2.party) {
                C2.ticketNumber += State[i].generateTicket();
            }
        }

        System.out.println("The total tickets of " + C1.name + "(Candidate1) is: " + C1.ticketNumber);
        System.out.println("The total tickets of " + C2.name + "(Candidate2) is: " + C2.ticketNumber);

        if (C1.ticketNumber > C2.ticketNumber) {
            System.out.println("The winner is " + C1.name + "!!!" );
        } else if(C1.ticketNumber < C2.ticketNumber) {
            System.out.println("The winner is " + C2.name + "!!!" );
        } else {
            System.out.println("Draw!!!" );
        }

        System.out.println();
        for(int i = 0; i < 2; i++) {
            State[i].printResult();
        }
    }
}


/**
 * This is USA State class
 */
class USAState {
    String name;
    float population; /* the unit is million */
    private float rep, dem;
    private int ticket;

    /**
     * This method is used to generate ticket number of this State
     * @return ticket number of this State
     */
    public int generateTicket() {
        /* one ticket for each 250000 population */
        ticket = Math.round(population*1000000/250000);
        return ticket;
    }

    /**
     * This method is used to decide who wins this State
     * @return 0, Republic wins; 1, Democratic wins
     */
    public int whoWin() {
        rep = 0.0f;
        dem = 0.0f;
        Random Ran = new Random();

        while (rep == dem) {
            rep = (float)(Math.round(Ran.nextFloat() * 100)) / 100;
            dem = 1 - rep;
        }

        if (rep > dem)
            return 0;
        else
            return 1;
    }

    /**
     * This method is used to print the state result
     */
    public void printResult() {
        System.out.println("The State " + name + ", the result is: " );
        System.out.println("Republic gets " + (rep * 100) + "%; Democratic gets " + (dem * 100) + "%");

        if (rep > dem)
            System.out.println("The winner in State " + name +
                    " is Republic! They get " +  ticket + " tickets!");
        else
            System.out.println("The winner in State " + name +
                    " is Democratic! They get " +  ticket + " tickets!");

        System.out.println();
    }
}

/**
 * This is USA Election Candidate class
 */
class USACandidate {
    String name;
    int party;
    int ticketNumber;
}