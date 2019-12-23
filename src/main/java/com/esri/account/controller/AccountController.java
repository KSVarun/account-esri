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
    public ResponseEntity<?> findAllGame(@PageableDefault Pageable pageable, HttpServletRequest request){
        String key=request.getHeader("X-API-Key");
        System.out.println("key--------"+key);
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

    @PostMapping("/newAccountData")
    public ResponseEntity<?> addNewGame(@Valid @RequestBody Account account, BindingResult result){
        ResponseEntity<?> errorMap = validationErrorService.ValidationService(result);
        if(errorMap!=null) return errorMap;
        Account newGame=accountService.addNewAccountData(account);
        return new ResponseEntity<Account>(newGame,HttpStatus.CREATED);}

    @DeleteMapping("{accountNo}")
    public ResponseEntity<?> deleteGame(@PathVariable Long accountNo) {
        accountService.deleteAccountData(accountNo);
        return new ResponseEntity<String>("Account Data Deleted", HttpStatus.OK);
    }
}
