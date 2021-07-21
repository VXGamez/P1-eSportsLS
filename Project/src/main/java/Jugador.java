import java.util.List;

public class Jugador{

    private String name;
    private int total_kills;
    private int total_deaths;
    private int total_assistances;
    private String nationality;
    private String main_position;
    private List<Leyenda> main_legends;
    private float winrate;
    private String equip_pertany;
    private float kda;

    public String getEquip_pertany() {
        return equip_pertany;
    }

    public void setEquip_pertany(String equip_pertany) {
        this.equip_pertany = equip_pertany;
    }

    public String getName() {
        return name;
    }

    public void setWinrate(float winrate) {
        this.winrate = winrate;
    }

    public float getWinrate() {
        return winrate;
    }

    public void setKda(float kda) {
        this.kda = kda;
    }

    public float getKda(){
        return (float) ((total_kills + (total_assistances*0.5))/(total_deaths));
    }

    public String getNationality() {
        return nationality;
    }


    @Override
    public String toString() {
        return "Jugador{" +
                "name='" + name + '\'' +
                ", winrate=" + winrate +
                ", equip_pertany='" + equip_pertany + '\'' +
                ", kda=" + kda +
                '}';
    }
}
