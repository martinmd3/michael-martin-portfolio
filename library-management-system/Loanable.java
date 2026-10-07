


public interface Loanable {

    // example method. this one is going to be blank but it's here to show it is usable.
    double calculateLateFee(int daysLate);

    // mark as loaned and returned will call to the abstract class from the subclasses to switch availability boolean
    boolean markAsLoaned();
    
    boolean returnLoaned();
}
