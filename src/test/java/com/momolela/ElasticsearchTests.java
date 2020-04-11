package com.momolela;

import com.momolela.entity.Article;
//import com.momolela.repository.ArticleRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTests {

    @Autowired
    JestClient jestClient;

//    @Autowired
//    ArticleRepository articleRepository;

    /**
     * jest 测试保存数据
     */
    @Test
    public void testSave() {
        Article article = new Article();
        article.setId(1);
        article.setAuthor("sunzj");
        article.setTitle("hello world");
        article.setContent("this is a big world");
        Index index = new Index.Builder(article).index("momolela").type("news").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * jest 测试读取数据
     */
    @Test
    public void testSearch() {
        String json = "{\n\"query\":{\n\"match\":{\n\"content\":\"big\"\n}\n}\n}";
        Search search = new Search.Builder(json).addIndex("momolela").addType("news").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过spring-data的repository接口来做保存
     */
//    @Test
//    public void testSaveByRepo(){
//        Article article = new Article();
//        article.setId(2);
//        article.setAuthor("hufy");
//        article.setTitle("hello sunzj");
//        article.setContent("this is a beautiful world");
//        articleRepository.save(article);
//    }
}
