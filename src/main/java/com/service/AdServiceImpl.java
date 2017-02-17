package com.service;

import com.dao.AdDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Ad;
import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdDAO adDAO;

    @Override
    public List<Ad> getAllByUserId(Users users) {
        if (users.equals(null))
            return null;
        return adDAO.getAllByUserId(users);
    }

    @Override
    public List<Ad> getAllAdsByOwnerEmail(String email) {
        if (email == null)
            return null;
        List<Ad> ads = adDAO.getAllAdsByOwnerEmail(email);
        return ads;
    }

    @Override
    public Ad getExpensiveAd(List<Ad> ads) {
        long max = Integer.MIN_VALUE;
        Ad mostExpensive = null;
        for (Ad ad : ads) {
            if (max < ad.getTotalPrice()) {
                mostExpensive = ad;
                max = ad.getTotalPrice();
            }
        }
        return mostExpensive;
    }

    @Override
    public Ad getCheapestAd(List<Ad> ads) {
            long min = Integer.MAX_VALUE;
            Ad mostCheapest = null;
            for (Ad ad : ads) {
                if (min > ad.getTotalPrice()) {
                    mostCheapest = ad;
                    min = ad.getTotalPrice();
                }
            }
            return mostCheapest;
    }

    @Override
    public Ad register(Ad ad) {
       return adDAO.save(ad);
    }

    @Override
    public String AdToJson(Map<String, Object> ads) throws Exception {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(stringWriter, ads);
        String result = stringWriter.toString();
        return result;
    }
}
