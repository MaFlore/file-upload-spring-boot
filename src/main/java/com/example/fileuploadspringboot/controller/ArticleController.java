package com.example.fileuploadspringboot.controller;

import com.example.fileuploadspringboot.exception.UnsupportedFileTypeException;
import com.example.fileuploadspringboot.fileManager.FileFilter;
import com.example.fileuploadspringboot.model.Article;
import com.example.fileuploadspringboot.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FileFilter fileFilter;

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public List<Article> articles(){
        List<Article> articles = new ArrayList<>();
        try {
            articles = articleService.getAll();
        }catch(Exception e){
           System.out.println("Erreur " + e.getMessage());
       }
        return articles;
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public Article article(@PathVariable Long id){
        Article article = new Article();
        try {
            article = articleService.findById(id);
        }catch (Exception e){
            System.out.println("Erreur " + e.getMessage());
        }
        return article;
    }

    @RequestMapping(value = "/ajout/article", method = RequestMethod.POST, headers = "accept=Application/json")
    public Article ajouter(@RequestParam("image") MultipartFile file,
                           String article) throws JsonProcessingException {

        Article article1 = new ObjectMapper().readValue(article, Article.class);
        String repertoireImage = "src/main/resources/images"; // Remplacez par le chemin souhaité pour le répertoire "images"
        File repertoire = new File(repertoireImage);
        if (!repertoire.exists()) {
            boolean repertoireCree = repertoire.mkdirs();
            if (!repertoireCree) {
                // Gestion de l'erreur si le répertoire ne peut pas être créé
                throw new RuntimeException("Impossible de créer le répertoire 'images'");
            }
        }
        String nomFichier = file.getOriginalFilename();
        String nouveauNom = FilenameUtils.getBaseName(nomFichier) + "." + FilenameUtils.getExtension(nomFichier);
        File fichierDuServeur = new File(repertoire, nouveauNom);
        try {
            FileUtils.writeByteArrayToFile(fichierDuServeur, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        article1.setImage(nouveauNom);
        return articleService.add(article1);
    }

    @RequestMapping(value = "/image/article/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImages(@PathVariable Long id) {
        Article article = articleService.findById(id);
        String repertoireImage = "src/main/resources/images";
        String cheminFichierImage = repertoireImage + "/" + article.getImage();

        try {
            Path cheminVersImages = Paths.get(cheminFichierImage);
            byte[] imageBytes = Files.readAllBytes(cheminVersImages);
            String contentType = fileFilter.determineContentType(cheminFichierImage);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentLength(imageBytes.length);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Gestion des exceptions liées à la lecture du fichier
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnsupportedFileTypeException e) {
            // Gestion des exceptions lorsque le type MIME ne peut pas être résolu
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

}
