package com.qf.j1808.controller;

import com.qf.j1808.service.SolrItemService;
import com.qf.j1808.util.SolrResult;
import com.qf.j1808.vo.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Controller
public class SolrItemController {
    @Autowired
    private SolrItemService itemService;

    @RequestMapping("/input")
    @ResponseBody
    public MsgResult dataInput(){
        MsgResult msgResult = itemService.dataInputSolr();
        return msgResult;
    }

    @RequestMapping("/search")
    public String search(String keyword,
                         @RequestParam (name="page",defaultValue = "1") Integer page,
                         @RequestParam (name="pageSize",defaultValue = "30") Integer pageSize,
                         Model model){

//        对查询条件做编码处理
       /* try {
            keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        System.out.println("query conditions:"+keyword);

     SolrResult result = itemService.queryItemsByPageAndKeyword(keyword,page,pageSize);

        long recourdCount = result.getRecourdCount();
        long totalPages=0;
        if(recourdCount%pageSize==0){
            totalPages=recourdCount/pageSize;
        }else{
            totalPages=recourdCount/pageSize+1;

        }

        model.addAttribute("keyword",keyword);//查询条件
        model.addAttribute("totalPages",totalPages);//总页数
        model.addAttribute("page",page);//当前页码

        model.addAttribute("recourdCount",recourdCount);//查询总记录数
        model.addAttribute("itemList",result.getItemList());//查询出的当前页的结果集

        return "search";
    }

    /*@RequestMapping(value="/search")
    public String search(String keyword,
                         @RequestParam(name="page",defaultValue = "1") Integer page,
                         @RequestParam(name="pageSize",defaultValue = "30") Integer pageSize,
                         Model model){
        try {
            keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("query conditions:"+keyword);
       SolrResult result = itemService.queryItemsByPageAndKeyword(keyword,page,pageSize);
       long recourdCount = result.getRecourdCount();
       long totalPages =0;
        if(recourdCount%pageSize==0){
            totalPages = recourdCount/pageSize;
        }else{
            totalPages = recourdCount/pageSize +1;
        }
        model.addAttribute("keyword",keyword);
        model.addAttribute("totalPages",null);
        model.addAttribute("page",page);
        model.addAttribute("recourdCount",null);
        model.addAttribute("itemList",null);

        return "search";
    }*/
}
