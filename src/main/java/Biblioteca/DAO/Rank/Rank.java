package Biblioteca.DAO.Rank;

public class Rank {
    private int id;
    private String rankName;

    public Rank(int index, String rankName) {
        this.id = index;
        this.rankName = rankName;
    }

    public int getId() {
        return id;
    }

    public String getRankName() {
        return rankName;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", rankName='" + rankName + '\'' +
                '}';
    }
}
