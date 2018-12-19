package cn.ldm.thread.controller;

import cn.ldm.thread.entity.Book;
import cn.ldm.thread.service.elasticsearch.BookService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @program: thread
 * @description
 * @author: luoyongxiang
 * @create: 2018-12-15 17:17
 **/
@RestController
public class ElasticSearchController {
    @Autowired
    BookService bookService;

    @Autowired
    JestClient jestClient;

    @RequestMapping(value = "elasticsearch")
    public void elasticsearch(){
        Book book6 = new Book (611,"中国移动",new Date ());
        Book book2= new Book (211,"中的1国移动",new Date ());
        bookService.save (book2);
        bookService.save (book6);
    }


    // TODO: 2018/12/15 高亮显示无效   ElasticsearchRepository怎么高亮显示?
    @RequestMapping(value = "query")
    public Iterable<Book> query(){
        String preTag = "<font color=‘#dd4b39‘>";
        String postTag = "</font>";
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery ())
                .withPageable(PageRequest.of(0, 4))
                .withFields("bookName","date")
                .withHighlightFields(new HighlightBuilder.Field("bookName").preTags(preTag).postTags(postTag))
                .build();
        return bookService.search (searchQuery);
    }


    @RequestMapping(value = "jestQuery")
    public String jestQuery() throws IOException {
        String query= "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"bookName\": \"灰太狼\"\n" +
                "    }\n" +
                "  }, \n" +
                "  \"highlight\": {\n" +
                "    \"fields\": {\n" +
                "      \"bookName\":{}\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder (query).addIndex ("index").addType ("type").build ();
        SearchResult execute = jestClient.execute (search);
        return execute.getJsonString ();
    }







    // TODO: 2018/12/15   jestClient不行？  ok
    @RequestMapping(value = "jest")
    public void jest() throws IOException {
        Book book = new Book (1000111,"灰太狼11",new Date ());
        Index index = new Index.Builder (book).index ("book8").type ("动画8").build ();
        jestClient.execute (index);
    }
}
