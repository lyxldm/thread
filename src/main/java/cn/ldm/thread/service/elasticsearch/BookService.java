package cn.ldm.thread.service.elasticsearch;

import cn.ldm.thread.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program: thread
 * @description
 * @author: luoyongxiang
 * @create: 2018-12-15 17:19
 **/
public interface BookService extends ElasticsearchRepository<Book,Integer> {

}
