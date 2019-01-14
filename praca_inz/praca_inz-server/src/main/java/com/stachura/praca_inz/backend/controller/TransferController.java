package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.service.TransferService;
import com.stachura.praca_inz.backend.web.dto.TransferAddDto;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/transfer")
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = AppBaseException.class)
public class TransferController {

    @Autowired
    private TransferService transferService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<TransferListElementDto> getAll() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return transferService.getAllTransfers(auth.getName());
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<TransferListElementDto> getAllTransfersForLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return transferService.getAllTransfersForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<TransferListElementDto> getAllTransfersForLoggedUser(@PathVariable String username) {
        return transferService.getAllTransfersForLoggedUser(username);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Transfer get(@PathVariable Long id) throws AppBaseException {
        return transferService.getTransferById(id);
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody TransferAddDto transferAddDto) throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        transferService.createNewTransfer(transferAddDto,auth.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Transfer transfer) {
        try {
            transferService.updateTransfer(transfer);
        } catch (AppBaseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws AppBaseException {
        transferService.deleteTransferById(id);
    }
}
