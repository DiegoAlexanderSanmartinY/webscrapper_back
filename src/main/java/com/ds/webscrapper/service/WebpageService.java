package com.ds.webscrapper.service;

import com.ds.webscrapper.models.Webpage;
import com.ds.webscrapper.repository.WebpageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebpageService {

    @Autowired
    private WebpageRepository webpageRepository;

    public List<Webpage> getAllWebpages(String query) {
        List<Webpage> list = new ArrayList<>();
        Iterable<Webpage> webpagesList = webpageRepository.findByText(query);
        webpagesList.forEach(webpage -> list.add(webpage));
        return list;
    }
    public Webpage getWebpageById(Integer id){
        return webpageRepository.findById(id).orElse(null);
    }
    public Webpage saveWebpage(Webpage webpage) {
        return webpageRepository.save(webpage);
    }

    public void deleteWebpage(Integer id) {
        webpageRepository.deleteById(id);
    }

}
