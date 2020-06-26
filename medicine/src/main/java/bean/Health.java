package bean;

public class Health {
    private int heal_id;
    private String health;
    private int show;

    public Health() {
    }

    public Health(int heal_id,String health, int show) {
        this.heal_id = heal_id;
        this.health = health;
        this.show = show;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public int getHeal_id() {
        return heal_id;
    }

    public void setHeal_id(int heal_id) {
        this.heal_id = heal_id;
    }
}
