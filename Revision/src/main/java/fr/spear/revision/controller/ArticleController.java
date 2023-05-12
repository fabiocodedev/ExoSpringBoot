package fr.spear.revision.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.spear.revision.Bean.Article;

@Controller
//POUR METTRE UN DEBUT DE CHEMIN SUR TOUT LE CONTROLLER
//@RequestMapping
public class ArticleController {
	
//	****************** GET LIST *******************
	@GetMapping("/")
	public String articlePage() {
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
	public String articleAdd(Article article, BindingResult bingingResult, @RequestParam("imageToUpload") MultipartFile multipartFile) {
		return "article/addArticle";
	}
}
