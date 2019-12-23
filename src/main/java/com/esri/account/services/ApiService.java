package com.esri.account.services;

import com.esri.account.bean.Api;
import com.esri.account.exceptions.ApiKeyException;
import com.esri.account.repository.APIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    APIRepository apiRepository;

    public ApiService(APIRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public Boolean validateApiKey(String key) {
        try {
            Api api = apiRepository.findByKey(key);
            String keyValue = api.getKey();

            if (keyValue!=null) {
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new ApiKeyException("ISSUE WITH THE API_KEY");
        }

    }
    public Api addNewApiKey(Api key) {
        return apiRepository.save(key);
    }

    public void deleteApiKey(Long Id) {
        try{
            apiRepository.deleteById(Id);
        }
        catch (Exception e){
            throw new ApiKeyException("API KEY DOES NOT EXIST");
        }
    }
}
