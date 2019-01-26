package com.stachura.praca_inz.backend.web.utils;

import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.Confirmation;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.model.security.User;

import java.util.Calendar;

public class NotificationMessages {


    public static String getRequestUrl(Request request) {
        String url = null;
        switch (request.getRequestType()) {
            case DEVICE_REQUEST:
                url = "/page/devices/request/view/" + request.getId();
                break;
            case TRANSFER_REQUEST:
                url = "/page/devices/transfer/request/view/" + request.getId();
                break;
        }
        return url;
    }


    public static Notification getRequestSentNotifiaction(Request request, User reciever) {
        Notification notification = new Notification();
        notification.setUrl(getRequestUrl(request));
        notification.setUser(request.getUser());
        notification.setReaded(false);
        notification.setCreateDate(Calendar.getInstance());
        notification.setDeleted(false);
        if(reciever.getUserdata().getLanguage()=="ENG") {
            notification.setTitle("Request sent");
            notification.setDescription("Your request was sent to:" + reciever.getUserdata().getName() + " " + reciever.getUserdata().getSurname() + " | " + reciever.getUsername() + " Request title: " + request.getTitle()
                    + " Request description:" + request.getDescription());
        }  else{
            notification.setTitle("Zgłoszenie wysłane");
            notification.setDescription("Twoje zgłoszenie zostało wysłane do:" + reciever.getUserdata().getName() + " " + reciever.getUserdata().getSurname() + " | " + reciever.getUsername() + " Tytył zgłoszenia: " + request.getTitle()
                    + " Opis zgłoszenia:" + request.getDescription());
        }
        return notification;
    }

    public static Notification getRequestReceivedManagerNotifiaction(Request request, User user) {
        Notification notification = new Notification();
        notification.setUrl(getRequestUrl(request));
        notification.setUser(user);
        notification.setReaded(false);
        notification.setCreateDate(Calendar.getInstance());
        notification.setDeleted(false);
        if(user.getUserdata().getLanguage()=="ENG") {
            notification.setTitle("Request received");
            notification.setDescription("You get reguest from:" + request.getUser().getUserdata().getName() + " " + request.getUser().getUserdata().getSurname() + " | " + request.getUser().getUsername()
                    + " Request title: " + request.getTitle()
                    + " Request description:" + request.getDescription());
        } else {
            notification.setTitle("Nowe zgłoszenie");
            notification.setDescription("Dostałeś zgłoszenie od:" + request.getUser().getUserdata().getName() + " " + request.getUser().getUserdata().getSurname() + " | " + request.getUser().getUsername()
                    + " Tytuł zgłoszenia: " + request.getTitle()
                    + " Opis zgłoszenia:" + request.getDescription());
        }
        return notification;

    }


    public static Notification getReportSentNotifiaction(Confirmation confirmation) {
        Notification notification = new Notification();
        notification.setUser(confirmation.getSender());
        notification.setReaded(false);
        notification.setCreateDate(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setUrl("/employees/confirmations/view/" + confirmation.getId());
        if(confirmation.getSender().getUserdata().getLanguage()=="ENG") {
            notification.setTitle("Confirmation sended");
            notification.setDescription("Your confirmation was sent to: " + confirmation.getReciever().getUserdata().getName() + " " + confirmation.getReciever().getUserdata().getSurname() + " | " + confirmation.getReciever().getUsername() + " Confirmation title: " + confirmation.getTitle()
                    + "Confirmation description: " + confirmation.getDescription());
        } else {
            notification.setTitle("Raport wysłany");
            notification.setDescription("Twóje potwierdzenie zostało wysłane do: " + confirmation.getReciever().getUserdata().getName() + " " + confirmation.getReciever().getUserdata().getSurname() + " | " + confirmation.getReciever().getUsername() + " Tytuł potwierdzenia: " + confirmation.getTitle()
                    + " Opis potwierdzenia: " + confirmation.getDescription());
        }
        return notification;
    }

    public static Notification getReportReceivedNotifiaction(Confirmation confirmation) {
        Notification notification = new Notification();
        notification.setUser(confirmation.getReciever());
        notification.setReaded(false);
        notification.setCreateDate(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setUrl("/employees/confirmations/view/" + confirmation.getId());
        if(confirmation.getReciever().getUserdata().getLanguage()=="ENG") {
            notification.setTitle("Confirmation recieved");
            notification.setDescription("You get confirmation from: " + confirmation.getSender().getUserdata().getName() + " " + confirmation.getSender().getUserdata().getSurname() + " | " + confirmation.getSender().getUsername()  + " Confirmation title: " + confirmation.getTitle()
                    + "Confirmation description: " + confirmation.getDescription());
        } else {
            notification.setTitle("Nowy raport");
            notification.setDescription("Otrzymałeś potwierdzenie od: " + confirmation.getSender().getUserdata().getName() + " " + confirmation.getSender().getUserdata().getSurname() + " | " + confirmation.getSender().getUsername()  + " Tytuł potwierdzenia: " + confirmation.getTitle()
                    + " Opis potwierdzenia: " + confirmation.getDescription());
        }
        return notification;
    }

}
