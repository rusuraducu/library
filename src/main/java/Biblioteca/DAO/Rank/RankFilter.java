package Biblioteca.DAO.Rank;

public class RankFilter {
    private int index;
    private String rankName;
    private int rowLimit;

    public RankFilter(int index, String rankName) {
        this.index = index;
        this.rankName = rankName;
    }

    public RankFilter(int index) {
        this.index = index;
    }

    public RankFilter(String rankName) {
        this.rankName = rankName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }
}
