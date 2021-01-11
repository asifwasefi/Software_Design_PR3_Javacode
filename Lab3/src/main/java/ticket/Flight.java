package ticket;



public class Flight extends Ticket {



    public Flight(double totalPrice) {
        super.totalPrice = totalPrice;
        super.ticketNumber = super.ticketCounter;
    }
}
