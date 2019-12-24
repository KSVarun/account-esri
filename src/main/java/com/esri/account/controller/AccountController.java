package com.esri.account.controller;

import com.esri.account.bean.Account;
import com.esri.account.exceptions.ApiKeyException;
import com.esri.account.services.AccountService;
import com.esri.account.services.ApiService;
import com.esri.account.services.ValidationErrorService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountController {
    private AccountService accountService;
    private ValidationErrorService validationErrorService;
    private ApiService apiService;

    public AccountController(AccountService accountService, ValidationErrorService validationErrorService, ApiService apiService) {
        this.accountService=accountService;
        this.validationErrorService=validationErrorService;
        this.apiService=apiService;
    }


    @GetMapping("/")
    public ResponseEntity<?> findAllGame(@PageableDefault Pageable pageable,
                                      //   @RequestHeader Map<String, String> header,
                                         HttpServletRequest request){
        String key=request.getHeader("X-API-Key");
//        List<String> keys = new ArrayList<>();
//        header.forEach((k,v)->{keys.add(k);keys.add(v);});
//        System.out.println("value------>"+keys.stream().collect(Collectors.toList()));
        if (key==null){
            throw new ApiKeyException("Missing request header X-API-Key");
        }
        if(apiService.validateApiKey(key)) {
            return new ResponseEntity<Iterable<Account>>(accountService.findAllAccountData(pageable), HttpStatus.OK);
        }
        return new ResponseEntity<String>("BAD_REQUEST",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{accountNo}")
    public ResponseEntity<?> findAllGamesByName(@PageableDefault Pageable pageable, @PathVariable Long accountNo, @RequestHeader(value="X-API-Key") String key){
        if(apiService.validateApiKey(key)) {
            return new ResponseEntity<Iterable<Account>>(accountService.findAccountDataByAccountNo(pageable,accountNo), HttpStatus.OK);
        }
        return new ResponseEntity<String>("BAD_REQUEST",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/")
    public ResponseEntity<?> addNewGame(@Valid @RequestBody Account account, BindingResult result){
        ResponseEntity<?> errorMap = validationErrorService.ValidationService(result);
        if(errorMap!=null) return errorMap;
        Account newGame=accountService.addNewAccountData(account);
        return new ResponseEntity<Account>(newGame,HttpStatus.CREATED);}

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        accountService.deleteAccountData(id);
        return new ResponseEntity<String>("Account Data Deleted", HttpStatus.OK);
    }
}
