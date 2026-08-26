package org.example.OOPSLabSession.MileStone_B;

public interface Lendable {
    boolean lend(User user);
    void returnBook(User user);
    boolean isAvailable();

}
