public class Author {
    public String name;
    public String id;
    public String org;


    @Override
    public String toString() {
        return '{'+
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", org='" + org + '\'' +
                '}';
    }
}

