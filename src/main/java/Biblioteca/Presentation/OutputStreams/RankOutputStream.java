package Biblioteca.Presentation.OutputStreams;

import Biblioteca.DAO.Rank.Rank;
import Biblioteca.Actions.RankProcessor;

import java.util.Scanner;

public class RankOutputStream {

    public Rank getRankFromDatabaseUsingScanner() {

        Scanner scanner = new Scanner(System.in);
        boolean readyToReturn = false;

        Rank rank = null;

        while (!readyToReturn) {

            System.out.println("Enter the rank's name: ");
            String rankName = scanner.nextLine();
            rank = RankProcessor.loadRankInstance(rankName);

            if (rank == null) { continue; }

            readyToReturn = true;
        }
        return rank;
    }
}
