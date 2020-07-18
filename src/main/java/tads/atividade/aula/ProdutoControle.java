package tads.atividade.aula;
import lombok.var;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class ProdutoControle {
    ProdutoService produtoService;

    @Autowired
            public void setProdutoService(ProdutoService produtoService){
        this.produtoService = produtoService;
    }


    @RequestMapping("/")
    public String getHome(Model model, HttpSession session, HttpServletResponse response,HttpServletRequest request){
        List<Produto> prodlista = produtoService.findAll();
        model.addAttribute("prodlista",prodlista);
        Cookie co[] =  request.getCookies();
        String acess="nenhum acesso";

        for (var c:co
             ) {

                if(c.getName().equalsIgnoreCase("dateacess")){
                    acess=c.getValue();
                    System.out.println(acess);
                    System.out.println("entrou");
                }

                if (c.getName().equalsIgnoreCase("flag")){
                    c.setValue("desativado");
                    response.addCookie(c);
                }

        }
        model.addAttribute("ultimoCookie",acess);

        Date date = new Date();
        Date dataAcesso = new Date(session.getLastAccessedTime());
        SimpleDateFormat formatt = new SimpleDateFormat("dd/mm/yyyy-HH:mm:ss");
        String dataFormatada = formatt.format(dataAcesso);

        Cookie cookie = new Cookie("dateacess", dataFormatada);
        cookie.setMaxAge(((60*60)*24)*180);
        response.addCookie(cookie);

        model.addAttribute("acessoatul",cookie.getValue());
        return "index";
    }

    @RequestMapping("/cadastrar")
    public  String getcadastra(Model model,HttpServletRequest request,HttpSession session){
        var produto= new Produto();
        model.addAttribute("produto",produto);
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public  String adicionarProduto(@ModelAttribute @Valid Produto produto, Errors errors,HttpSession Session, HttpServletResponse response){
        boolean flag=false;

        if (errors.hasErrors()){
                return "cadastrar";
        }else {
            Cookie c = new Cookie("flag","desativado");
            c.setMaxAge(60*60);
            response.addCookie(c);
            flag=true;
            Session.setAttribute("flag",flag);
            produtoService.addProduto(produto);
            c.setValue("ativdo");
            return "redirect:/";

        }


    }
    @RequestMapping("/editar/{id}")
    public  ModelAndView editar(@PathVariable(name = "id") Long id){
        var modelAndView= new ModelAndView("editar");
        var produto = produtoService.get(id);
        modelAndView.addObject("produto",produto);
        return modelAndView;
    }
    @RequestMapping("/deletar/{id}")
    public  String deleteProduto(@PathVariable(name = "id")Long id) {
    produtoService.delete(id);
    return "redirect:/";

    }

}
