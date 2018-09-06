package Biblioteca.Actions;

import Biblioteca.DAO.Rank.Rank;
import Biblioteca.DAO.Rank.RankFilter;
import Biblioteca.Actions.Processor;
import Biblioteca.Queries.RankQueries;

public class RankProcessor extends Processor {

    public static Rank loadRankInstance(int index) {
        RankQueries rankQueries = new RankQueries();
        RankFilter rankFilter = new RankFilter(index);
        Rank rank = rankQueries.loadRank(rankFilter);
        try{
            if(!rank.getRankName().isEmpty()){
                return rank;
            }
        } catch (NullPointerException e){
            System.out.println("This rank doesn't exist.");
        }
        return null;
    }

    public static Rank loadRankInstance(String rankName) {
        RankQueries rankQueries = new RankQueries();
        RankFilter rankFilter = new RankFilter(rankName);
        Rank rank = rankQueries.loadRank(rankFilter);
        try{
            if(!rank.getRankName().isEmpty()){
                return rank;
            }
        } catch (NullPointerException e){
            System.out.println("This rank doesn't exist.");
        }
        return null;
    }

}
