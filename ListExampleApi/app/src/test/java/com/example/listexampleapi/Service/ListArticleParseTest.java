package com.example.listexampleapi.Service;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Service.Parse.ListArticleParse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListArticleParseTest {
    private ListArticleParse listArticleParse;
    private JSONObject jsonObjectWithTwoArticles;
    private JSONObject jsonObjectWithArtilceA;
    private JSONObject jsonObjectWithArtilceB;
    private String idKey;
    private String titleKey;
    private String priceKey;
    private String thumbnailKey;
    private String resultsKey;

    @Before
    public void initObjects() throws JSONException {
        this.listArticleParse = new ListArticleParse();
        this.jsonObjectWithTwoArticles = new JSONObject();
        this.jsonObjectWithArtilceA = new JSONObject();
        this.jsonObjectWithArtilceB = new JSONObject();

        this.idKey = "id";
        this.titleKey = "title";
        this.priceKey = "price";
        this.thumbnailKey = "thumbnail";
        this.resultsKey = "results";

        this.jsonObjectWithArtilceA.put(idKey,"MLA00000001");
        this.jsonObjectWithArtilceA.put(titleKey,"Article A");
        this.jsonObjectWithArtilceA.put(priceKey,"100");
        this.jsonObjectWithArtilceA.put(thumbnailKey,"http://imageA.com");

        this.jsonObjectWithArtilceB.put(idKey,"MLA00000002");
        this.jsonObjectWithArtilceB.put(titleKey,"Article B");
        this.jsonObjectWithArtilceB.put(priceKey,"100");
        this.jsonObjectWithArtilceB.put(thumbnailKey,"https://imageB.com");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(0,jsonObjectWithArtilceA);
        jsonArray.put(1,jsonObjectWithArtilceB);

        this.jsonObjectWithTwoArticles.put(resultsKey, jsonArray);
    }


    @Test
    public void givenAnImageUrlWithHtppWhenParseArticleLisThenImageUrlIsWithHttps() {
        String thumbnailAExpected = "https://imageA.com";

        ArrayList<Article> actualArticleList = (ArrayList<Article>) this.listArticleParse.parseArticleList(this.jsonObjectWithTwoArticles);
        Article actualArticleA = actualArticleList.get(0);

        Assert.assertEquals(thumbnailAExpected, actualArticleA.getUrlItemImage());
    }

    @Test
    public void givenAnImageUrlWithHtppsWhenParseArticleLisThenImageUrlIsWithHttps() {
        String thumbnailBExpected = "https://imageB.com";

        ArrayList<Article> actualArticleList = (ArrayList<Article>) this.listArticleParse.parseArticleList(this.jsonObjectWithTwoArticles);
        Article actualArticleB = actualArticleList.get(1);

        Assert.assertEquals(thumbnailBExpected, actualArticleB.getUrlItemImage());
    }

    @Test
    public void givenAJsonObjectWhenParseArticleListThenShouldReturnAListWithTwoArticles() {
        String idAExpected = "MLA00000001";
        String titleAExpected = "Article A";
        String priceAExpected = "100";
        String thumbnailAExpected = "https://imageA.com";
        String idBExpected = "MLA00000002";
        String titleBExpected = "Article B";
        String priceBExpected = "100";
        String thumbnailBExpected = "https://imageB.com";

        ArrayList<Article> actualArticleList = (ArrayList<Article>) this.listArticleParse.parseArticleList(this.jsonObjectWithTwoArticles);
        Article actualArticleA = actualArticleList.get(0);
        Article actualArticleB = actualArticleList.get(1);

        Assert.assertEquals(idAExpected, actualArticleA.getId());
        Assert.assertEquals(titleAExpected, actualArticleA.getTitle());
        Assert.assertEquals(priceAExpected, actualArticleA.getPrice());
        Assert.assertEquals(thumbnailAExpected, actualArticleA.getUrlItemImage());
        Assert.assertEquals(idBExpected, actualArticleB.getId());
        Assert.assertEquals(titleBExpected, actualArticleB.getTitle());
        Assert.assertEquals(priceBExpected, actualArticleB.getPrice());
        Assert.assertEquals(thumbnailBExpected, actualArticleB.getUrlItemImage());
    }

    public void xxx() throws JSONException {
        String idAExpected = "MLA00000001";
        String titleAExpected = "Article A";
        String priceAExpected = "100";
        String thumbnailAExpected = "https://imageA.com";
        String conditionAExpected = "nuevo";
        boolean acceptsMercadoPagoBooleanAExpected = true;
        String acceptsMercadoPagoAExpected = "true";
        Article articleA = new Article(idAExpected, titleAExpected, priceAExpected, thumbnailAExpected);
        articleA.setCondition(conditionAExpected);
        articleA.setMercadoPagoAccept(acceptsMercadoPagoAExpected);
        this.jsonObjectWithArtilceA.put("condition", conditionAExpected);
        this.jsonObjectWithArtilceA.put("accepts_mercadopago", acceptsMercadoPagoAExpected);


        Article actualArticleA= this.listArticleParse.parseArticle(this.jsonObjectWithArtilceA, articleA);

        Assert.assertEquals(idAExpected, actualArticleA.getId());
        Assert.assertEquals(titleAExpected, actualArticleA.getTitle());
        Assert.assertEquals(priceAExpected, actualArticleA.getPrice());
        Assert.assertEquals(thumbnailAExpected, actualArticleA.getUrlItemImage());
        Assert.assertEquals(conditionAExpected, actualArticleA.getCondition());
        Assert.assertEquals(acceptsMercadoPagoBooleanAExpected, actualArticleA.getUrlItemImage());
    }
}