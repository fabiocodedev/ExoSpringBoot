package fr.spear.revision.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.spear.revision.Bean.Article;
import fr.spear.revision.Bean.User;
import fr.spear.revision.Bean.UserLogin;
import fr.spear.revision.service.ArticleService;
import fr.spear.revision.service.UserService;

@Controller
//POUR METTRE UN DEBUT DE CHEMIN SUR TOUT LE CONTROLLER
//@RequestMapping
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	
//	****************** GET LIST *******************
	@GetMapping("/")
	public String articlePage(Article article, Model model) {
		model.addAttribute("article", articleService.getArticles());
		return "article/list";
	}
	
	
//	****************** GET ADDARTICLE *******************
	@GetMapping("/add-article")
	public String articleAdd(Article article) {
		return "article/addArticle";
	}

//	****************** POST ADDARTICLE + UPLOAD IMG *******************
	//@RequestParams = name input de limage
	@PostMapping("/add-article")
	public String articleAdd(Article article, BindingResult bindingResult, @RequestParam("imageToUpload") MultipartFile multipartFile) {
		
		//RECUPERATION DE LID POUR UPDATE
		if(article.getId() != 0) {
			System.out.println("EN MODE UPDATE !!");
			System.out.println(article.getTitre());
			System.out.println(article.getResume());
			System.out.println(article.getContenu());
			
			
			
			
			//articleService.ajoutArticle(article);
		}
		
		
		if(bindingResult.hasErrors()) {
            return "article/addArticle";
        }else {
            //On recupere le nom de l'utilisateur connecté
            String username = ((UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

            if(username != null && !multipartFile.isEmpty()) {

                String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                article.setImage(filename);

                try {

                    //DESTINATION
                    //String uploadDir = "static/images/" +article.getTitre();
                    String destination = new ClassPathResource("/src/main/resources/static/upload").getPath();

                    articleService.uploadsImage(destination, filename, multipartFile);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
                //Instnacie un nouvel objet avec le mail de user connecté
                User user = new User();
                user = userService.getUserByEmail(username);

                System.out.println(user);

                article.setUser(user);
                
                //AJOUTER UN ARTICLE
                articleService.ajoutArticle(article);


            }

        //Appel de la vue ajoutArticle
        return "article/list";
    }
	
	
	//VIEW ARTICLE
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") int articleId, Model model) {
	
		//2 METHODES AVEC OU SANS OPTIONNAL
		// AVEC OPTIONNAL
		//Optional<Article> article = articleService.getArticle(articleId);
		// APPEL DIRECTEMENT DE LA METHODE DEPUIS LE SERVICE
		if (articleService.getArticle(articleId).isPresent()) {
			model.addAttribute("article",articleService.getArticle(articleId).get());
		}
		return "article/view";
	
	}
	
	
	
	
	
	
	
}
