package ticket;

import person.Person;

public abstract class Ticket {

    double totalPrice;
    static int ticketCounter=0;
    int ticketNumber;

    public Ticket() {
        ticketCounter +=1;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }
}
