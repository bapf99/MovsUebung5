package de.thbingen.Uebung5.ports.out;

import de.thbingen.Uebung5.ports.in.User;

public interface UpdateNotificationPort {

    public void notify(User user, Operation operation);

}
