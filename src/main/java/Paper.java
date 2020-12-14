import java.util.List;
import java.util.Map;

public class Paper {
    int year, n_citation;
    String title, page_start, page_end, doc_type, publisher, volume, issue, doi, id;
    List<String> references;
    List<Object> fos;
    Object indexed_abstract;
    List<Author> authors;
    Map<String, String> venue;

    public String getId() {
        return id;
    }


    public List<String> getReferences() {
        return references;
    }


    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Paper#" +
                "Id=" + id + '\n' + "Title: " + title + '\n'
                + "ref=" + references + "}" + "\nAuthor" + authors + "\n-----------------\n";


    }

}