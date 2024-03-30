package fr.epita.assistants.notifyme.user;

import fr.epita.assistants.notifyme.notify.INotificationSender;

import java.util.ArrayList;
import java.util.List;

public class User implements IMultiNotificationSender {

    public String username;

    public List<INotificationSender> list;

    public User(final String username, final List<INotificationSender> parNotificationList) {
        this.username = username;
        this.list = parNotificationList;
    }
    public User(final String username) {
        this.username = username;
        this.list = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }


    @Override
    public void sendNotifications(String parRecipient, String parMessage) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).notify(username, parRecipient, parMessage);
        }
    }

    @Override
    public void addNotifier(INotificationSender parNotifier) {
        list.add(parNotifier);
    }

    @Override
    public List<INotificationSender> getNotifiers() {
        return list;
    }
}
