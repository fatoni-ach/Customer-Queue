package untag_informatika.c1461600015.customerqueue;

public class Number {
    private Integer number_next;
    private Number(){

    }

    public Number(int number){
        this.number_next = number;
    }

    public Integer getNumber_next() {
        return number_next;
    }

    public void setNumber_next(Integer number_next) {
        this.number_next = number_next;
    }
}
