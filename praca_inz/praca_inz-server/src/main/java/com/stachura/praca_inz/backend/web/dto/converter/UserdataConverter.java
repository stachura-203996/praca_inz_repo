package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Userdata;


/**
 * Dostarcza metod do konwersji obiektów związanych z użytkownikami
 */
public class UserdataConverter {

   /* *//**
     * Tworzy obiekt encji {@link Userdata} z obiektu po edycji
     *
     * @param userEdit obiekt przesłany z widoku z informacją o zedytowanym użytkowniku
     * @param user encja użytkownika, którego pola są zmieniane
     * @return obiekt użytkownika ze zedytowanymi danymi
     *//*
    public static Userdata toUserdata(UserEditDto userEdit, User user) {
        Userdata userdata = new Userdata(userEdit.getUserdataVersion());
        userdata.setAccount(user);
        userdata.setEmail(account.getCompanies().getEmail());
        userdata.setPointsSum(account.getCompanies().getPointsSum());
        userdata.setPointsForAssociates(account.getCompanies().getPointsForAssociates());
        userdata.setPointsForSubordinates(account.getCompanies().getPointsForSubordinates());
        userdata.setSupervisor(account.getCompanies().getSupervisor());
        userdata.setUserdataCollection(account.getCompanies().getUserdataCollection());

        userdata.setName(userEdit.getName());
        userdata.setSurname(userEdit.getSurname());
        userdata.setFlatNo(userEdit.getFlatNo());
        userdata.setHouseNo(userEdit.getHouseNo());
        userdata.setStreet(userEdit.getStreet());
        userdata.setCity(userEdit.getCity());

        return userdata;
    }

    *//**
     * Tworzy obiekt encji {@link Userdata} z obiektu po edycji przez administratora
     *
     * @param userEdit obiekt przesłany z widoku z informacją o zedytowanym użytkowniku
     * @param user encja użytkownika, którego pola są zmieniane
     * @param supervisorAccount encja użytkownika, będącego nowym przełożonym edytowanego użytkownika
     * @return obiekt użytkownika ze zedytowanymi danymi
     *//*
    public static Userdata toUserdataByAdmin(UserEditByAdmin userEdit, User user, User supervisorAccount) {
        Userdata userdata = new Userdata(userEdit.getUserdataVersion());
        userdata.setAccount(user);
        userdata.setName(userEdit.getName());
        userdata.setSurname(userEdit.getSurname());
        userdata.setAddress(userEdit.getCity());
        userdata.setStreet(userEdit.getStreet());
        userdata.setHouseNo(userEdit.getHouseNo());
        userdata.setFlatNo(userEdit.getFlatNo());
        userdata.setEmail(userEdit.getEmail());

        if (supervisorAccount != null) {
            userdata.setSupervisor(supervisorAccount.getCompanies());
        }
        return userdata;
    }*/
}
