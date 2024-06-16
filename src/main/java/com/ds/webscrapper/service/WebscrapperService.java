package com.ds.webscrapper.service;

import com.ds.webscrapper.models.Webpage;
import com.ds.webscrapper.repository.WebpageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebscrapperService {

    @Autowired
    private WebpageRepository webpageRepository;

    public Webpage scrapeAndSave(String url)  {

        try {
            Document document = Jsoup.connect(url).get();
            String dominio = getDomainFromUrl(url);
            String title = document.title();
            String description = document.select("meta[name=description]")
                    .attr("content");
            String image = document.select("meta[property=og:image]")
                    .attr("content");

            Webpage webpage = new Webpage();
            webpage.setUrl(url);
            webpage.setTitle(title);
            webpage.setDescription(description);
            webpage.setPicture(image);
            webpage.setDomain(dominio);
            webpageRepository.save(webpage);
            return webpage;

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String getDomainFromUrl(String url) {
        String domain = url.replaceFirst("^(https?://)?(www\\.)?","");
        int index = domain.indexOf('/');
        if (index != -1){
            domain = domain.substring(0,index);
        }
        return domain;
    }

    public List<String> getAllLinks(String url) {
        Webpage findWebpage = webpageRepository.findByUrl(url);
        if(findWebpage != null) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");

            links.stream().forEach(link -> {
                String linkHref = link.attr("href");
                if (linkHref.startsWith("/")) linkHref = "https://"+getDomainFromUrl(url) + linkHref;
                if (!list.contains(linkHref)) list.add(linkHref);
                });

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
}
