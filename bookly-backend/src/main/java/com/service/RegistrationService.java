package com.service;

import com.entities.Author;
import com.entities.Category;
import com.entities.Language;
import com.entities.Publisher;
import com.repository.AuthorRepository;
import com.repository.CategoryRepository;
import com.repository.LangaugeRepository;
import com.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private LangaugeRepository langaugeRepository;

    //register
    public Author registerAuthor(Author author)
    {
        return authorRepository.save(author);
    }

    public Author getAuthor(Integer id)
    {
        return authorRepository.getById(id);
    }

    public List<Author> getAllAuthor()
    {
        return authorRepository.findAll();
    }

    public Category getCategory(Integer id)
    {
        return categoryRepository.getById(id);
    }

    public Publisher getPublisher(Integer id)
    {
        return publisherRepository.getById(id);
    }

    public Language getLangauage(Integer id)
    {
        return langaugeRepository.getById(id);
    }

    public List<Publisher> getAllPublisher()
    {
        return publisherRepository.findAll();
    }

    public Publisher registerPublisher(Publisher publisher){
        return publisherRepository.save(publisher);
    }


    public List<Language> getAllLang()
    {
        return langaugeRepository.findAll();
    }

    public Language registerLang(Language language){
        return langaugeRepository.save(language);
    }
}
