package com.stachura.praca_inz.backend.web.utils;

import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.Report;
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
            case DELIVERY_REQUEST:
                url = "/page/warehouses/delivery/request/view/" + request.getId();
                break;

            case SHIPMENT_REQUEST:
                url = "/page/warehouses/shipment/request/view/" + request.getId();
                break;
        }
        return url;
    }


    public static Notification getRequestSentNotifiaction(Request request, User reciever) {
        Notification notification = new Notification();
        notification.setUrl(getRequestUrl(request));
        notification.setUser(request.getUser());
        notification.setReaded(false);
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        if(reciever.getUserdata().getLanguage()=="ENG") {
            notification.setTitle("Request sent");
            notification.setDescription("Your request was sent to:" + reciever.getUserdata().getName() + " " + reciever.getUserdata().getSurname() + " | " + reciever.getUsername() + " Report title: " + request.getTitle()
                    + " Report description:" + request.getDescription());
        }  else{
            notification.setTitle("Zgłoszenie wysłane");
            notification.setDescription("Twoje zgłoszenie zostało wysłane do:" + reciever.getUserdata().getName() + " " + reciever.getUserdata().getSurname() + " | " + reciever.getUsername() + " Tytył raportu: " + request.getTitle()
                    + " Opis raportu:" + request.getDescription());
        }
        return notification;
    }

    public static Notification getRequestReceivedManagerNotifiaction(Request request, User user) {
        Notification notification = new Notification();
        notification.setUrl(getRequestUrl(request));
        notification.setUser(user);
        notification.setReaded(false);
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        if(user.getUserdata().getLanguage()=="ENG") {
            notification.setTitle("Request received");
            notification.setDescription("You get reguest from:" + request.getUser().getUserdata().getName() + " " + request.getUser().getUserdata().getSurname() + " | " + request.getUser().getUsername()
                    + " Report title: " + request.getTitle()
                    + " Report description:" + request.getDescription());
        } else {
            notification.setTitle("Nowe zgłoszenie");
            notification.setDescription("Dostałeś zgłoszenie od:" + request.getUser().getUserdata().getName() + " " + request.getUser().getUserdata().getSurname() + " | " + request.getUser().getUsername()
                    + " Tytuł raportu: " + request.getTitle()
                    + " Opis raportu:" + request.getDescription());
        }
        return notification;

    }


    public static Notification getReportSentNotifiaction(Report report) {
        Notification notification = new Notification();
        notification.setUser(report.getSender());
        notification.setReaded(false);
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setUrl("/ui/page/employees/reports/view/" + report.getId());
        if(report.getSender().getUserdata().getLanguage()=="ENG") {
            notification.setTitle("Report sended");
            notification.setDescription("Your report was sent to: " + report.getReciever().getUsername() + " Report title: " + report.getTitle()
                    + "Report description: " + report.getDescription());
        } else {
            notification.setTitle("Raport wysłany");
            notification.setDescription("Twój raport został wysłany do: " + report.getReciever().getUsername() + " Tytuł raportu: " + report.getTitle()
                    + " Opis raportu: " + report.getDescription());
        }
        return notification;
    }

    public static Notification getReportReceivedNotifiaction(Report report) {
        Notification notification = new Notification();
        notification.setUser(report.getReciever());
        notification.setReaded(false);
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setUrl("/ui/page/employees/reports/view/" + report.getId());
        if(report.getReciever().getUserdata().getLanguage()=="ENG") {
            notification.setTitle("Report recieved");
            notification.setDescription("You get report from: " + report.getSender().getUsername() + "Report title: " + report.getTitle()
                    + "Report description: " + report.getDescription());
        } else {
            notification.setTitle("Nowy raport");
            notification.setDescription("Otrzymałeś raport od: " + report.getSender().getUsername() + "Tytuł raportu: " + report.getTitle()
                    + " Opis raportu: " + report.getDescription());
        }
        return notification;
    }

}
