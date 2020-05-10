package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;
    public  Sql2oNewsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(News news) {
        String sql ="INSERT INTO news(name, content, dpt_id) VALUES (:name, :content, :dpt_id)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public News findByiId(int id) {
        String sql = "SELECT * FROM news WHERE id=:id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(News.class);
        }
    }

    @Override
    public List<News> getAllNews() {
        String sql = "SELECT * FROM news";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(News.class);
        }

    }

    @Override
    public void updateNews(int id, String name, String content, int dpt_id) {
        String sql = "UPDATE news SET (name, content, dpt_id) = (:name, :content, :dpt_id) WHERE id=:id";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("content", content)
                    .addParameter("dpt_id",dpt_id)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM news";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }


    }
}