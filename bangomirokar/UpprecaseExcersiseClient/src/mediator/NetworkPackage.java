package mediator;

public class NetworkPackage {
    private String  type;
    private String content;

    public NetworkPackage(String type, String content) {
        this.type = type;
        this.content = content;
    }
    @Override
    public String toString() {
        return "NetworkPackage{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
